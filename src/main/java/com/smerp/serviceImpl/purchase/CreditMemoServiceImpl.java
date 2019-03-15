package com.smerp.serviceImpl.purchase;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.Vendor;
import com.smerp.model.admin.VendorAddress;
import com.smerp.model.admin.VendorsContactDetails;
import com.smerp.model.inventory.CreditMemo;
import com.smerp.model.inventory.CreditMemoLineItems;
import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.InVoice;
import com.smerp.model.inventory.InVoiceLineItems;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.PurchaseOrderLineItems;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.repository.purchase.CreditMemoLineItemsRepository;
import com.smerp.repository.purchase.CreditMemoRepository;
import com.smerp.repository.purchase.GoodsReceiptRepository;
import com.smerp.repository.purchase.InVoiceRepository;
import com.smerp.repository.purchase.PurchaseOrderRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.VendorAddressService;
import com.smerp.service.inventory.VendorsContactDetailsService;
import com.smerp.service.master.PlantService;
import com.smerp.service.purchase.CreditMemoService;
import com.smerp.service.purchase.GoodsReceiptService;
import com.smerp.service.purchase.InVoiceService;
import com.smerp.service.purchase.PurchaseOrderService;
import com.smerp.util.DocNumberGenerator;
import com.smerp.util.EmailGenerator;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.RequestContext;
import com.smerp.util.UnitPriceListItems;
@Service
public class CreditMemoServiceImpl implements CreditMemoService{

	private static final Logger logger = LogManager.getLogger(RequestForQuotationServiceImpl.class);

	@Autowired
	CreditMemoRepository creditMemoRepository;

	@Autowired
	CreditMemoLineItemsRepository creditMemoLineItemsRepository;

	@Autowired
	VendorService vendorService;
	
	@Autowired
	VendorAddressService vendorAddressService;
	
	@Autowired
	VendorsContactDetailsService vendorsContactDetailsService;

	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	InVoiceService inVoiceService;
	
	
	@Autowired
	InVoiceRepository inVoiceRepository;
	
	@Autowired
	GoodsReceiptRepository goodsReceiptRepository;
	
	@Autowired
	GoodsReceiptService goodsReceiptService;
	
	@PersistenceContext    
	private EntityManager entityManager;

	@Autowired
	EmailGenerator emailGenerator;
	
	@Autowired
	private DocNumberGenerator docNumberGenerator;
	
	@Autowired
	PlantService plantService;
	

	@Override
	public CreditMemo save(CreditMemo creditMemo) {
		creditMemo.setCategory("Item");
		switch (creditMemo.getStatusType()) {
		case "DR":
			creditMemo.setStatus(EnumStatusUpdate.DRAFT.getStatus());
			break;
		case "SA":
			creditMemo.setStatus(EnumStatusUpdate.OPEN.getStatus());
			break;

		case "RE":
			creditMemo.setStatus(EnumStatusUpdate.REJECTED.getStatus());
			break;
		case "APP":
			creditMemo.setStatus(EnumStatusUpdate.APPROVEED.getStatus());
			break;
		case "CA":
			creditMemo.setStatus(EnumStatusUpdate.CANCELED.getStatus());
			break;
		default:
			logger.info("Type Not Matched:" + creditMemo.getStatusType());
			break;
		}
		List<CreditMemoLineItems> listItems = creditMemo.getCreditMemoLineItems();
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				if (listItems.get(i).getProdouctNumber() == null && listItems.get(i).getSacCode() == null) {
					listItems.remove(i);
					i--;
				}
			}
			creditMemo.setCreditMemoLineItems(listItems);
		}
		if (creditMemo.getId() != null) { // delete List Of Items.
			CreditMemo creditMemoObj = creditMemoRepository.findById(creditMemo.getId()).get();
			List<CreditMemoLineItems> requestLists = creditMemoObj.getCreditMemoLineItems();
			
			if(requestLists.size()>0 && requestLists!=null) {
				creditMemoLineItemsRepository.deleteAll(requestLists);  // Delete All list items 
				}
			
			
			if(creditMemo.getInvId()==null) {  // if RfqId null remove list items 
			
			
			}else {
				 logger.info("CreditMemo Data -->" +creditMemo);
			}
		
         logger.info("creditMemo -->" +creditMemo);
         if(creditMemo.getInvId()!=null) {
 			InVoice inv = creditMemo.getInvId();
		Vendor vendor = vendorService.findById(inv.getVendor().getId());
		VendorAddress vendorShippingAddress = vendorAddressService.findById(inv.getVendorShippingAddress().getId());
		VendorAddress vendorPayAddress = vendorAddressService.findById(inv.getVendorPayTypeAddress().getId());
		VendorsContactDetails vendorsContactDetails =vendorsContactDetailsService.findById(inv.getVendorContactDetails().getId());

		creditMemo.setVendor(vendor);
		creditMemo.setVendorContactDetails(vendorsContactDetails);
		creditMemo.setVendorShippingAddress(vendorShippingAddress);
		creditMemo.setVendorPayTypeAddress(vendorPayAddress);
         } 
         
		}
		
		
		 if(creditMemo.getStatus()!=null &&  !creditMemo.getStatus().equals(EnumStatusUpdate.DRAFT.getStatus())) {
		try {
		   	creditMemo =getListAmount(creditMemo);
		   	if(creditMemo.getId()!=null) {
		   		CreditMemo creditMemoObj = creditMemoRepository.findById(creditMemo.getId()).get();
				logger.info(creditMemoObj.getCreatedBy().getUserEmail());
				creditMemo.setCreatedBy(creditMemoObj.getCreatedBy());
			 }
			 RequestContext.initialize();
		     RequestContext.get().getConfigMap().put("mail.template", "creditMemoEmail.ftl");  //Sending Email
		     emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendCreditMemoEmail(creditMemo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
		
		creditMemo= creditMemoRepository.save(creditMemo);
		
		 if(creditMemo.getStatus()!=null &&  !creditMemo.getStatus().equals(EnumStatusUpdate.DRAFT.getStatus())) {
		
			if(creditMemo.getInvId()!=null) {
				InVoice invoice  = creditMemo.getInvId();
				
				if(creditMemo.getStatus().equals(EnumStatusUpdate.APPROVEED.getStatus())) {
					
					if(invoice.getGrId()!=null) {
					GoodsReceipt goodsReceipt =  goodsReceiptRepository.findById(invoice.getGrId().getId()).get();
					PurchaseOrder purchaseOrder = goodsReceiptService.setStatusOfPurchaseOrder(goodsReceipt);  // change status PO
					logger.info("purchaseOrder -->" +purchaseOrder);
					}
					invoice = inVoiceService.setStatusOfInVoice(invoice);
				}
			}
			
		}
			
		return creditMemo; 
	}

	@Override
	public CreditMemo saveCM(String inId) {

		CreditMemo inv = new CreditMemo();
		InVoice in = inVoiceService.findById((Integer.parseInt(inId)));
		logger.info("inId" + inId);
		/*CreditMemo dup_gre =creditMemoRepository.findByPoId(po.getId());  // check PO exist in  GR
        if(dup_gre==null) { */
		Integer count = docNumberGenerator.getDocCountByDocType(EnumStatusUpdate.CM.getStatus());
		CreditMemo greDetails = findLastDocumentNumber();
		if (greDetails != null && greDetails.getDocNumber() != null) {
			inv.setDocNumber(GenerateDocNumber.documentNumberGeneration(greDetails.getDocNumber(),count));
		} else {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDateTime now = LocalDateTime.now();
		inv.setDocNumber(GenerateDocNumber.documentNumberGeneration("CM"+(String)dtf.format(now) +"0",count));
		}

		if (in != null) {
			inv.setDocumentDate(in.getDocumentDate());
			inv.setStatus(EnumStatusUpdate.OPEN.getStatus());
			inv.setPostingDate(in.getPostingDate());
			inv.setCategory(in.getCategory());
			inv.setRemark(in.getRemark());
			inv.setDeliverTo(in.getDeliverTo());
			inv.setReferenceDocNumber(in.getDocNumber());
			inv.setRequiredDate(in.getRequiredDate());
			inv.setInvId(in);
			inv.setIsActive(true);
			inv.setVendor(in.getVendor());
			inv.setVendorContactDetails(in.getVendorContactDetails());
			inv.setVendorPayTypeAddress(in.getVendorPayTypeAddress());
			inv.setVendorShippingAddress(in.getVendorShippingAddress());
			
			inv.setFreight(in.getFreight());
			inv.setTotalDiscount(in.getTotalDiscount());
		
			/*List<CreditMemo> listCreditMemo = creditMemoRepository
					.findByListPoId(po.getId());  // check Multiple  Quantity
			Integer creQunatity =0;*/
			
			List<InVoiceLineItems> grItms = in.getInVoiceLineItems();
			
			List<CreditMemoLineItems> lineItems =new ArrayList<CreditMemoLineItems>();
			if (grItms != null) {
				for (int i = 0; i < grItms.size(); i++) {
					CreditMemoLineItems line = new CreditMemoLineItems();
					line.setProdouctNumber(grItms.get(i).getProdouctNumber());
					line.setProductGroup(grItms.get(i).getProductGroup());
					line.setDescription(grItms.get(i).getDescription());
					line.setHsn(grItms.get(i).getHsn());
					line.setSku(grItms.get(i).getSku());
					
				/*	if(poItms.get(i).getProdouctNumber()!=null) {
						 creQunatity = getListGoodsProductCount(listCreditMemo,  poItms.get(i).getProdouctNumber());
					}else if(poItms.get(i).getSacCode()!=null) {
						 creQunatity = getListGoodsProductCount(listCreditMemo,  poItms.get(i).getSacCode());
					}*/
					
					//line.setRequiredQuantity(poItms.get(i).getRequiredQuantity() - creQunatity);
					//line.setRequiredQuantity(0);
					
					line.setSacCode(grItms.get(i).getSacCode());
					line.setUom(grItms.get(i).getUom());
					line.setSku(grItms.get(i).getSku());
					line.setWarehouse(grItms.get(i).getWarehouse());
					line.setProductId(grItms.get(i).getProductId());
					line.setTaxCode(grItms.get(i).getTaxCode());
					line.setTaxDescription(grItms.get(i).getTaxDescription());
					line.setUnitPrice(grItms.get(i).getUnitPrice());
					
					line.setTaxTotal(grItms.get(i).getTaxTotal());
					
					line.setTotal(grItms.get(i).getTotal());
					
					lineItems.add(line);
				}
			}
			
			inv.setCreditMemoLineItems(lineItems);
			
			inv= getListAmount(inv); // Set Amount....Like Tax Total Amt
			
			
		}
		logger.info("inv" + inv);
		inv.setCategory("Item");
		inv = creditMemoRepository.save(inv);
		
	/*	in.setStatus(EnumStatusUpdate.PARTIALLY_RECEIVED.getStatus());  // Set Partial Complted
		inVoiceRepository.save(in);*/
		
		return inv;
     
	}
	
	
	
	
	/*public String  setStatusOfPurchaseOrder(CreditMemo creditMemo) {
		logger.info("set Status-->");
		String status="";
		//PurchaseOrder purchaseOrder = purchaseOrderService.findById(creditMemo.getPoId());
		PurchaseOrder purchaseOrder =null;
		List<CreditMemoLineItems> greListItems = creditMemo.getCreditMemoLineItems();
		List<PurchaseOrderLineItems> poListItems = purchaseOrder.getPurchaseOrderlineItems();
		
		Map<String, Integer> poListData = prepareMapForProductQunatityPR(purchaseOrder, poListItems); // get po list
		
		Map<String, Integer> greListData = prepareMapForProductQunatityGR(creditMemo);   // get !Rejected  list
		
		Map<String, Integer> greApproveListData = prepareMapForApprovedProductQunatityGR(creditMemo);  // get Approved list
	  
		if(greListData.size()>0) {   // check inv  when !Rejected list
        if(poListData.keySet().equals( greListData.keySet())) {  // check keys pr and inv  when !Rejected list
        	    List<Integer> values1 = new ArrayList<Integer>(poListData.values());
        	    List<Integer> values2 = new ArrayList<Integer>(greListData.values());
        	    Collections.sort(values1);
        	    Collections.sort(values2);
        	  if(values1.equals(values2)) {  // check values  pr and inv
        		  if(poListData.keySet().equals( greApproveListData.keySet())) {  // check keys pr and inv  when Approved list
            		  status = EnumStatusUpdate.CLOSED.getStatus(); 
            	  }else {
            		  status = EnumStatusUpdate.COMPLETED.getStatus();
            	  }
        	  }else {
        		  status = EnumStatusUpdate.PARTIALLY_RECEIVED.getStatus();
        	  }
        	 
        	  
        	  
        }else { // all keys are not matched  
        	status = EnumStatusUpdate.PARTIALLY_RECEIVED.getStatus();
        }
		}else {  // No inv list
        	status = EnumStatusUpdate.APPROVEED.getStatus();
        }
    	logger.info("status-->" + status);
		return status;
	}*/

	private Map<String, Integer> prepareMapForProductQunatityPR(PurchaseOrder purchaseOrder,List<PurchaseOrderLineItems> poListItems) {
		 Map<String, Integer> poListData =new LinkedHashMap<>();
		if (poListItems != null) {
			for (int i = 0; i < poListItems.size(); i++) {
				PurchaseOrderLineItems polist = poListItems.get(i);
				String key ="";
				if(polist.getProdouctNumber()!=null) {
				 key = purchaseOrder.getDocNumber() + "$" + polist.getProdouctNumber();
				}else {
				 key = purchaseOrder.getDocNumber() + "$" + polist.getSacCode();
				}
				if (polist.getProdouctNumber() != null && polist.getRequiredQuantity() != 0) {
					poListData.put(key, polist.getRequiredQuantity());
				} else if (polist.getSacCode() != null && polist.getRequiredQuantity() != 0) {
					poListData.put(key, polist.getRequiredQuantity());
				}
			}
		}
		
		logger.info("poListData-->" + poListData);
		return poListData;
	}
	
	private Map<String, Integer> prepareMapForProductQunatityGR(CreditMemo goodReceipt) {
		Map<String, Integer> greMapListData = new LinkedHashMap<>();

		List<CreditMemo> listCreditMemo = creditMemoRepository.findByListInvId(goodReceipt.getInvId(),EnumStatusUpdate.REJECTED.getStatus()); // check
																											// Multiple
		greMapListData = getGoodsReturnRealQunatityList(goodReceipt, greMapListData, listCreditMemo);
		
		logger.info("greMapListData-->" + greMapListData);

		return greMapListData;
	}
	
	private Map<String, Integer> prepareMapForApprovedProductQunatityGR(CreditMemo goodReceipt) {
		Map<String, Integer> greMapListData = new LinkedHashMap<>();

		List<CreditMemo> listCreditMemo = creditMemoRepository.findByListInvId(goodReceipt.getInvId(),EnumStatusUpdate.APPROVEED.getStatus()); // check
																											// Multiple
																											// Quantity
		greMapListData = getGoodsReturnRealQunatityList(goodReceipt, greMapListData, listCreditMemo);
		
		logger.info("greMapListData-->" + greMapListData);

		return greMapListData;
	}

	private Map<String, Integer> getGoodsReturnRealQunatityList(CreditMemo goodReceipt, Map<String, Integer> greMapListData,
			List<CreditMemo> listCreditMemo) {
		for (int i = 0; i < listCreditMemo.size(); i++) {
			CreditMemo creditMemoObj = listCreditMemo.get(i);
			List<CreditMemoLineItems> goodsReturnLineItems = creditMemoObj.getCreditMemoLineItems();
			for (int j = 0; j < goodsReturnLineItems.size(); j++) {
				CreditMemoLineItems crelist = goodsReturnLineItems.get(j);
				logger.info("crelist===>" + crelist);
				String key = "";
				
				if( crelist.getProdouctNumber()!=null) {
					key = goodReceipt.getReferenceDocNumber() + "$" + crelist.getProdouctNumber();
					}else {
					key = goodReceipt.getReferenceDocNumber() + "$" + crelist.getSacCode();
					}
				
				if (!greMapListData.containsKey(key)) {

					if (crelist.getProdouctNumber() != null && crelist.getRequiredQuantity() != 0) {
						greMapListData.put(key,
								crelist.getRequiredQuantity());
					} else if (crelist.getSacCode() != null && crelist.getRequiredQuantity() != 0) {
						greMapListData.put(key,
								crelist.getRequiredQuantity());
					}
				} else {
					Integer tempQunatity = greMapListData.get(key);

					if (crelist.getProdouctNumber() != null && crelist.getRequiredQuantity() != 0) {
						greMapListData.put(key,
								tempQunatity + crelist.getRequiredQuantity());
					} else if (crelist.getSacCode() != null && crelist.getRequiredQuantity() != 0) {
						greMapListData.put(key,
								tempQunatity + crelist.getRequiredQuantity());
					}
				}

			}
		}
		
		return greMapListData;
	}
	
	
	
	
	
	@Override
	public CreditMemo saveCancelStage(String rfqId) {
		CreditMemo inv = creditMemoRepository.findById(Integer.parseInt(rfqId)).get();
		inv.setStatus(EnumStatusUpdate.CANCELED.getStatus());
		creditMemoRepository.save(inv);
		return inv;
		
	}

	@Override
	public CreditMemo findLastDocumentNumber() {
		return creditMemoRepository.findTopByOrderByIdDesc();
	}

	@Override
	public List<CreditMemo> findAll() {
		return creditMemoRepository.findAll();
	}

	@Override
	public List<CreditMemo> findByIsActive() {
		return creditMemoRepository.findByIsActive(true,plantService.findPlantIds());
	}

	@Override
	public CreditMemo findById(int id) {
		return creditMemoRepository.findById(id).get();
	}
	
	
	
	
	@Override
	public CreditMemo getListAmount(CreditMemo creditMemo) {
		logger.info("getListAmount-->");
		List<CreditMemoLineItems> listItems = creditMemo.getCreditMemoLineItems();
		
		List<CreditMemoLineItems> addListItems = new ArrayList<CreditMemoLineItems>();
		
		InVoice in = null;
		List<InVoiceLineItems> grItms =null;
		 List<CreditMemo> listCreditMemo =null;
		if(creditMemo.getInvId()!=null) {
			in = creditMemo.getInvId();
			grItms = in.getInVoiceLineItems();
		listCreditMemo = creditMemoRepository
					.findByListInvId(in,EnumStatusUpdate.REJECTED.getStatus());  // check Multiple  Quantity
		}
		
		
		Double addAmt=0.0;
		Double addTaxAmt=0.0;
		Integer creQunatity=0;
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				CreditMemoLineItems crelist = listItems.get(i);
				if(crelist.getUnitPrice()!=null && crelist.getRequiredQuantity()!=null) {
				addTaxAmt += UnitPriceListItems.getTaxAmt(crelist.getRequiredQuantity(),crelist.getUnitPrice(),crelist.getTaxCode());
				addAmt +=UnitPriceListItems.getTotalINVAmt(crelist.getRequiredQuantity(),crelist.getUnitPrice());
				crelist.setTaxTotal(""+UnitPriceListItems.getTaxAmt(crelist.getRequiredQuantity(),crelist.getUnitPrice(),crelist.getTaxCode()));
				crelist.setTotal(""+UnitPriceListItems.getTotalINVAmt(crelist.getRequiredQuantity(),crelist.getUnitPrice()));
				
				if(creditMemo.getInvId()!=null) {
				if(grItms.get(i).getProdouctNumber()!=null ) {
					 creQunatity = getListGoodsProductCount(listCreditMemo,  grItms.get(i).getProdouctNumber());
				}else if(grItms.get(i).getSacCode()!=null ) {
					 creQunatity = getListGoodsProductCount(listCreditMemo,  grItms.get(i).getSacCode());
				} }
				
				logger.info("GRItms.get(i).getRequiredQuantity()-->" + grItms.get(i).getRequiredQuantity());
				logger.info("creQunatity-->" + creQunatity);
				/*if(creditMemo.getStatus().equals(EnumStatusUpdate.APPROVEED.getStatus())) {
					crelist.setTempRequiredQuantity(grItms.get(i).getRequiredQuantity() - creQunatity);
					}else 
					{
						crelist.setTempRequiredQuantity(grItms.get(i).getRequiredQuantity());
					}*/
				
				}else {
				crelist.setTaxTotal("");
				crelist.setTotal("");	
				}
				
				addListItems.add(crelist);
				
			}
			creditMemo.setCreditMemoLineItems(addListItems);
		}
		creditMemo.setTotalBeforeDisAmt(addAmt);
		creditMemo.setTaxAmt(""+addTaxAmt);
		logger.info("addAmt-->" + addAmt); 
		logger.info("creditMemo.getTotalDiscount()-->" + creditMemo.getTotalDiscount());
		logger.info("creditMemo.getFreight()-->" + creditMemo.getFreight());
		Double total_amt=0.0;
		Double total_payment = 0.0;
		if(creditMemo.getTotalDiscount()==null) creditMemo.setTotalDiscount(0.0);
		if(creditMemo.getFreight()==null) creditMemo.setFreight(0.0);
		
		
		 total_amt= UnitPriceListItems.getTotalAmtPayment(addAmt, creditMemo.getTotalDiscount(), creditMemo.getFreight(),addTaxAmt);
		 if(creditMemo.getInvId() != null) {
				total_payment =(double) Math.round(total_amt);
			}else {
				total_payment = creditMemo.getTotalPayment();
			}
		 logger.info("creditMemo.total_payment()-->" + total_payment);
			logger.info("creditMemo.total_amt()-->" + total_amt);
		creditMemo.setAmtRounding(""+df2.format(total_amt));
		creditMemo.setTotalPayment(total_amt);
		creditMemo.setRoundedOff("" + df2.format(total_payment - total_amt));
	
	return creditMemo;
	}
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	
	@Override
	public CreditMemo getCreditMemoById(int id) {
		CreditMemo creditMemo = creditMemoRepository.findById(id).get();
		
	     
	 	String sqlList= " select product_number,inv_final_quantity,creditmemo_quantity,inv_product_tax,inv_amount_tax from vw_invoice_lineitems_amount where id= " +creditMemo.getInvId().getId();
	 	String productNumber ="";
	    logger.info("sqlList ----> " + sqlList);
	    Query queryList = entityManager.createNativeQuery(sqlList);
	      List<Object[]>    invoiceList = queryList.getResultList();
	        
	    logger.info("invoiceList Size -----> " + invoiceList.size());
	    
	     Map<String, Integer> grListData = new LinkedHashMap<>();
	     for(Object[] tuple : invoiceList) {
	         productNumber = tuple[0] == null ? "0" : ( tuple[0]).toString();
	         grListData.put(productNumber, Integer.parseInt(tuple[1].toString()));
	     }
	    
	     List<CreditMemoLineItems> listItems = creditMemo.getCreditMemoLineItems();
	     for (int i = 0; i < listItems.size(); i++) {
	    	 CreditMemoLineItems invlist = listItems.get(i);
	        
	        for(Map.Entry m:grListData.entrySet()){
	               logger.info("Keys & Values" +m.getKey()+" "+m.getValue());
	               if(invlist.getProdouctNumber().equals(m.getKey())) {
	                   invlist.setTempRequiredQuantity((Integer)m.getValue());    
	                 }
	        }
	    }
	     creditMemo.setCreditMemoLineItems(listItems);
	     
	     return creditMemo;
	}
	
	
	
	
	
	
	
	
	
	/*@Override
	public String checkStatusPoGr(PurchaseOrder purchaseOrder) {
	    String status ="";
		CreditMemo  creditMemo = creditMemoRepository.findByPoId(purchaseOrder.getId());
		if(creditMemo!=null) {
		List<PurchaseOrderLineItems> poListItems = purchaseOrder.getPurchaseOrderlineItems();
		
		Map<String, Integer> poListData = prepareMapForProductQunatityPR(purchaseOrder, poListItems);
		
		Map<String, Integer> greListData = prepareMapForProductQunatityGR(creditMemo);
		
      
        if(poListData.keySet().equals( greListData.keySet())) {
        	    List<Integer> values1 = new ArrayList<Integer>(poListData.values());
        	    List<Integer> values2 = new ArrayList<Integer>(greListData.values());
        	    Collections.sort(values1);
        	    Collections.sort(values2);
        	  if(values1.containsAll(values2)) {
        		  status = EnumStatusUpdate.COMPLETED.getStatus();
        	  }else {
        		  status = EnumStatusUpdate.PARTIALLY_RECEIVED.getStatus();
        	  }
        }else {
        	status = EnumStatusUpdate.APPROVEED.getStatus();
        }
		}else {
			status = EnumStatusUpdate.APPROVEED.getStatus();
		}
		return status;
	}*/
	
	
	/*@Override
	public Boolean checkQuantityInv(InVoice creditMemo) {
		List<CreditMemo> listCreditMemo = creditMemoRepository
				.findByListInvId(creditMemo.getId(),EnumStatusUpdate.REJECTED.getStatus());
		logger.info("listCreditMemo-->" +listCreditMemo);
		
		String status = setStatusOfPurchaseOrder(listCreditMemo.get(0));
		logger.info("status-->" +status); //Test the Status if you want  
		
		Integer invQunatity = getListInvQuantityCount(creditMemo);
		Integer creQunatity = getListCMQunatityCount(listCreditMemo);
		
		if(invQunatity > 0)
			return true;
		else
			return false;
	}*/

	private Integer getListCMQunatityCount(List<CreditMemo> listCreditMemo) {
	
		Integer creQunatity=0;
		if (listCreditMemo != null) {
			for (int i = 0; i < listCreditMemo.size(); i++) {
				CreditMemo  creditMemo = listCreditMemo.get(i);
				logger.info("creditMemo-->" +creditMemo);
				List<CreditMemoLineItems> listItems = creditMemo.getCreditMemoLineItems();
			for (int j = 0; j < listItems.size(); j++) {
				CreditMemoLineItems crelist = listItems.get(j);
				if(crelist.getRequiredQuantity()!=null) {
					creQunatity +=  crelist.getRequiredQuantity();
				}
			}
		  }
		}
		logger.info("creQunatity===>" +creQunatity);
		return creQunatity;
	}

	

	
	private Integer getListGoodsProductCount(List<CreditMemo> listCreditMemo, String category) {
		Integer qunatity = 0;
		logger.info("category===>" +category);
	
		for (int i = 0; i < listCreditMemo.size(); i++) {
			CreditMemo creditMemoObj = listCreditMemo.get(i);
			List<CreditMemoLineItems> goodsReturnLineItems = creditMemoObj.getCreditMemoLineItems();
			for (int j = 0; j < goodsReturnLineItems.size(); j++) {
				CreditMemoLineItems crelist = goodsReturnLineItems.get(j);
				logger.info("crelist===>" +crelist);
				if (crelist.getRequiredQuantity() != null) {
					if (category.equals(crelist.getProdouctNumber()) || category.equals(crelist.getSacCode()))
						qunatity += crelist.getRequiredQuantity();
					    logger.info("creQunatity===>" +qunatity);
				}
			}
		}
		
		return qunatity;
	}
	
	
	
	
	
	
	
	private Integer getListInvQuantityCount(InVoice creditMemo) {
		List<InVoiceLineItems> listItems = creditMemo.getInVoiceLineItems();
		Integer invQunatity=0;
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				InVoiceLineItems grlist = listItems.get(i);
				if(grlist.getRequiredQuantity()!=null) {
					invQunatity += grlist.getRequiredQuantity();
				}
			}
		}
		
		logger.info("invQunatity===>" +invQunatity);
		return invQunatity;
	}
	
	
	
	
	
	@Override
	public CreditMemo delete(int id) {
		CreditMemo creditMemo = creditMemoRepository.findById(id).get();
		creditMemo.setIsActive(false);
		creditMemoRepository.save(creditMemo);
		return creditMemo;
	}

			public static void main(String[] args) {
				
				Map<String, Integer> poListData = new LinkedHashMap<>();
				poListData.put("PO2019010747$p103", 10);
				poListData.put("PO2019010747$p123", 20);
				
				Map<String, Integer> greListData = new LinkedHashMap<>();
				greListData.put("PO2019010747$p103", 5);
				greListData.put("PO2019010747$p123", 10);
				
			  
		        System.out.println("is Vaild-->" +poListData.keySet().equals( greListData.keySet()));
		        
		        System.out.println("is Vaild-->" +poListData.keySet().equals( greListData.keySet()));
		        
		       /* if(key1.containsAll(key2)) {
		        	    List<Integer> values1 = new ArrayList<Integer>(poListData.values());
		        	    List<Integer> values2 = new ArrayList<Integer>(greListData.values());
		        	    Collections.sort(values1);
		        	    Collections.sort(values2);
				
			   }*/
	
			}
			
			
    /*  public InVoice updateInVoiceQunatity(Integer inId,CreditMemo creditMemoObj) {
				
				InVoice invoiceObj = inVoiceRepository.findById(inId).get();
				int grId = 0;
				GoodsReceipt goodsReceiptObj =null;
				List<GoodsReceiptLineItems> goodsReceipLists  =null;
				if(invoiceObj.getGrId()!=null) {
					 goodsReceiptObj = goodsReceiptRepository.findById(invoiceObj.getGrId()).get();
				}
				
				List<InVoiceLineItems> inVoiceLists = invoiceObj.getInVoiceLineItems();  // Total Qunatity List.
				
				if(invoiceObj.getGrId()!=null) {
				goodsReceipLists = goodsReceiptObj.getGoodsReceiptLineItems();  // Total Qunatity List.
				}
				
				List<CreditMemoLineItems> creditMemoLists = creditMemoObj.getCreditMemoLineItems();    // Return Qunatity List.
				
				List<InVoiceLineItems> inVoiceRemLists  = new ArrayList<InVoiceLineItems>();
				
				List<GoodsReceiptLineItems> goodsReceiptRemLists  = new ArrayList<GoodsReceiptLineItems>();
				
				if (inVoiceLists != null) {
					for (int i = 0; i < inVoiceLists.size(); i++) {
						CreditMemoLineItems crelist = creditMemoLists.get(i);
						InVoiceLineItems invlist = new InVoiceLineItems();
						
							 invlist = inVoiceLists.get(i);
							if (crelist.getProdouctNumber() != null  && invlist.getProdouctNumber()!=null && ( invlist.getProdouctNumber().equals(crelist.getProdouctNumber()) ||  invlist.getSacCode().equals(crelist.getSacCode()) ) ) {
								
								invlist.setRequiredQuantity(invlist.getRequiredQuantity()-crelist.getRequiredQuantity());
							
						    }
						inVoiceRemLists.add(invlist);
					}
				}
				
				if (goodsReceipLists != null) {
					for (int i = 0; i < goodsReceipLists.size(); i++) {
						CreditMemoLineItems crelist = creditMemoLists.get(i);
						GoodsReceiptLineItems grlist = new GoodsReceiptLineItems();
						
							 grlist = goodsReceipLists.get(i);
							if (crelist.getProdouctNumber() != null  && grlist.getProdouctNumber()!=null && ( grlist.getProdouctNumber().equals(crelist.getProdouctNumber()) ||  grlist.getSacCode().equals(crelist.getSacCode()) ) ) {
								
								grlist.setRequiredQuantity(grlist.getRequiredQuantity()-crelist.getRequiredQuantity());
							
						}
						goodsReceiptRemLists.add(grlist);
					}
				}
				
				
				invoiceObj.setInVoiceLineItems(inVoiceRemLists);
				invoiceObj.setStatus(EnumStatusUpdate.CREDITMEMO.getStatus());
				invoiceObj= inVoiceRepository.save(invoiceObj);
				
				if(invoiceObj.getGrId()!=null) {
				goodsReceiptObj.setGoodsReceiptLineItems(goodsReceiptRemLists);
				goodsReceiptObj.setStatus(EnumStatusUpdate.GOODS_RETURN.getStatus());
				goodsReceiptObj= goodsReceiptRepository.save(goodsReceiptObj);
				}
				return invoiceObj;
			}*/
      
     
      
      

}
