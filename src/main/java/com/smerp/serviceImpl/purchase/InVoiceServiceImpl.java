package com.smerp.serviceImpl.purchase;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.GoodsReceiptLineItems;
import com.smerp.model.inventory.GoodsReturn;
import com.smerp.model.inventory.InVoice;
import com.smerp.model.inventory.InVoiceLineItems;
import com.smerp.model.inventory.LineItemsBean;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.PurchaseOrderLineItems;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.model.search.SearchFilter;
import com.smerp.repository.purchase.GoodsReceiptRepository;
import com.smerp.repository.purchase.GoodsReturnRepository;
import com.smerp.repository.purchase.InVoiceLineItemsRepository;
import com.smerp.repository.purchase.InVoiceRepository;
import com.smerp.repository.purchase.PurchaseOrderRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.inventory.VendorAddressService;
import com.smerp.service.inventory.VendorsContactDetailsService;
import com.smerp.service.master.PlantService;
import com.smerp.service.purchase.GoodsReceiptService;
import com.smerp.service.purchase.GoodsReturnService;
import com.smerp.service.purchase.InVoiceService;
import com.smerp.service.purchase.PurchaseOrderService;
import com.smerp.util.DocNumberGenerator;
import com.smerp.util.EmailGenerator;
import com.smerp.util.EnumSearchFilter;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.GetConvertedDocStatusList;
import com.smerp.util.GetSearchFilterResult;
import com.smerp.util.RequestContext;
import com.smerp.util.UnitPriceListItems;

@Service
public class InVoiceServiceImpl  implements InVoiceService {

	private static final Logger logger = LogManager.getLogger(InVoiceServiceImpl.class);

	@Autowired
	private InVoiceRepository inVoiceRepository;

	@Autowired
	private InVoiceLineItemsRepository inVoiceLineItemsRepository;
	
	@Autowired
	private GoodsReturnRepository goodsReturnRepository;
	
	@Autowired
	private GoodsReceiptService goodsReceiptService;
	
	@Autowired
	private GoodsReceiptRepository goodsReceiptRepository;
	
	@Autowired
	GoodsReturnService goodsReturnService;
	
	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private VendorAddressService vendorAddressService;
	
	@Autowired
	private VendorsContactDetailsService vendorsContactDetailsService;

	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	private EmailGenerator emailGenerator;
	
	@PersistenceContext    
	private EntityManager entityManager;
	
	@Autowired
	private ProductService  productService;
	
	@Autowired
	private DocNumberGenerator docNumberGenerator;
	
	@Autowired
	private PlantService plantService;
	
	@Autowired
	private GetSearchFilterResult getSearchFilterResult;
	
	@Autowired
	private GetConvertedDocStatusList getConvertedDocStatusList;

	@Override
	public InVoice save(InVoice inVoice) {
		inVoice.setCategory("Item");
		switch (inVoice.getStatusType()) {
		case "DR":
			inVoice.setStatus(EnumStatusUpdate.DRAFT.getStatus());
			break;
		case "SA":
			inVoice.setStatus(EnumStatusUpdate.OPEN.getStatus());
			break;

		case "RE":
			inVoice.setStatus(EnumStatusUpdate.REJECTED.getStatus());
			break;
		case "APP":
			inVoice.setStatus(EnumStatusUpdate.APPROVEED.getStatus());
			break;
		case "CA":
			inVoice.setStatus(EnumStatusUpdate.CANCELED.getStatus());
			break;
		default:
			logger.info("Type Not Matched:" + inVoice.getStatusType());
			break;
		}

		if (inVoice.getId() != null) { // delete List Of Items.
			
			InVoice inVoiceObj = inVoiceRepository
					.findById(inVoice.getId()).get();
			List<InVoiceLineItems> requestLists = inVoiceObj.getInVoiceLineItems();
			
			if(requestLists.size()>0 && requestLists!=null  && inVoice.getGrId()==null) {
				inVoiceLineItemsRepository.deleteAll(requestLists);  // Delete All list items 
				}else {
					inVoice.setInVoiceLineItems(requestLists);
				}
			
			
			if(inVoice.getGrId()==null) {  // if RfqId null remove list items 
			List<InVoiceLineItems> listItems = inVoice.getInVoiceLineItems();
			if (listItems != null) {
				for (int i = 0; i < listItems.size(); i++) {
					if (listItems.get(i).getProdouctNumber() == null && listItems.get(i).getSacCode() == null) {
						listItems.remove(i);
						i--;
					}
				}
				inVoice.setInVoiceLineItems(listItems);
			}
			
			}else {
			}
			 if(inVoice.getGrId() != null) { 
				 GoodsReceipt gr = inVoice.getGrId();
	        	
				Vendor vendor = vendorService.findById(gr.getVendor().getId());
	     		VendorAddress vendorShippingAddress = vendorAddressService.findById(gr.getVendorShippingAddress().getId());
	     		VendorAddress vendorPayAddress = vendorAddressService.findById(gr.getVendorPayTypeAddress().getId());
	     		VendorsContactDetails vendorsContactDetails =vendorsContactDetailsService.findById(gr.getVendorContactDetails().getId());

	     		inVoice.setVendor(vendor);
	     		inVoice.setVendorContactDetails(vendorsContactDetails);
	     		inVoice.setVendorShippingAddress(vendorShippingAddress);
	     		inVoice.setVendorPayTypeAddress(vendorPayAddress);
	     		
	     		inVoice.setPlant(inVoice.getGrId().getPlant());
	         }
		}
		
         if(inVoice.getGrId()==null) { 
        	Vendor vendor = vendorService.findById(inVoice.getVendor().getId());
     		VendorAddress vendorShippingAddress = vendorAddressService.findById(inVoice.getVendorShippingAddress().getId());
     		VendorAddress vendorPayAddress = vendorAddressService.findById(inVoice.getVendorPayTypeAddress().getId());
     		
     		VendorsContactDetails vendorsContactDetails =vendorsContactDetailsService.findById(inVoice.getVendorContactDetails().getId());

     		inVoice.setVendor(vendor);
     		inVoice.setVendorContactDetails(vendorsContactDetails);
     		inVoice.setVendorShippingAddress(vendorShippingAddress);
     		inVoice.setVendorPayTypeAddress(vendorPayAddress);
         }
        Vendor vendor = vendorService.findById(inVoice.getVendor().getId());
		VendorAddress vendorShippingAddress = vendorAddressService.findById(inVoice.getVendorShippingAddress().getId());
		VendorAddress vendorPayAddress = vendorAddressService.findById(inVoice.getVendorPayTypeAddress().getId());
		
		VendorsContactDetails vendorsContactDetails =vendorsContactDetailsService.findById(inVoice.getVendorContactDetails().getId());

		inVoice.setVendor(vendor);
		inVoice.setVendorContactDetails(vendorsContactDetails);
		inVoice.setVendorShippingAddress(vendorShippingAddress);
		inVoice.setVendorPayTypeAddress(vendorPayAddress);
		
		 if(inVoice.getStatus()!=null &&  !inVoice.getStatus().equals(EnumStatusUpdate.DRAFT.getStatus())) {
			try {
			   	inVoice =getListAmount(inVoice);
			   	if(inVoice.getId()!=null) {
			   		InVoice inVoiceObj = inVoiceRepository.findById(inVoice.getId()).get();
					logger.info(inVoiceObj.getCreatedBy().getUserEmail());
					inVoice.setCreatedBy(inVoiceObj.getCreatedBy());
				 }
    			 RequestContext.initialize();
    		     RequestContext.get().getConfigMap().put("mail.template", "invoiceEmail.ftl");  //Sending Email
    		     emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendInvoiceEmail(inVoice);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		}
		 
		 if(inVoice.getStatus()!=null &&  inVoice.getStatus().equals(EnumStatusUpdate.REJECTED.getStatus())) {
			 GoodsReceipt goodsReceipt =  inVoice.getGrId();
			  if(goodsReceipt!=null) {
				goodsReceipt.setStatus(EnumStatusUpdate.APPROVEED.getStatus());  // Set GOODS_RETURN
				goodsReceiptRepository.save(goodsReceipt);
			  }
		 }
		 
		
		inVoice= inVoiceRepository.save(inVoice);
		
		return inVoice; 
		 
	}

	@Override
	public InVoice saveInv(String grId) {

		InVoice inv = new InVoice();
		GoodsReceipt gr = goodsReceiptService.findById((Integer.parseInt(grId)));
		/*check any goods returns for this goods receipt and set status to cancelled*/
		changeGoodsReturnStatus(gr);
		 
        	Integer count = docNumberGenerator.getDocCountByDocType(EnumStatusUpdate.INV.getStatus());
        	InVoice greDetails = findLastDocumentNumber();
		if (greDetails != null && greDetails.getDocNumber() != null) {
			inv.setDocNumber(GenerateDocNumber.documentNumberGeneration(greDetails.getDocNumber(),count));
		} else {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDateTime now = LocalDateTime.now();
		inv.setDocNumber(GenerateDocNumber.documentNumberGeneration("INV"+(String)dtf.format(now) +"0",count));
		}

		if (gr != null) {
			inv.setDocumentDate(gr.getDocumentDate());
			inv.setStatus(EnumStatusUpdate.OPEN.getStatus());
			inv.setPostingDate(gr.getPostingDate());
			inv.setCategory(gr.getCategory());
			inv.setRemark(gr.getRemark());
			inv.setPlant(gr.getPlant());
			inv.setDeliverTo(gr.getDeliverTo());
			inv.setReferenceDocNumber(gr.getDocNumber());
			inv.setRequiredDate(gr.getRequiredDate());
			inv.setGrId(gr);
			inv.setIsActive(true);
			inv.setVendor(gr.getVendor());
			inv.setVendorContactDetails(gr.getVendorContactDetails());
			inv.setVendorPayTypeAddress(gr.getVendorPayTypeAddress());
			inv.setVendorShippingAddress(gr.getVendorShippingAddress());
		
			inv.setFreight(gr.getFreight());
			inv.setTotalDiscount(gr.getTotalDiscount());

			List<GoodsReceiptLineItems> grItms = gr.getGoodsReceiptLineItems();
			
			List<InVoiceLineItems> lineItems =new ArrayList<InVoiceLineItems>();
			if (grItms != null) {
				for (int i = 0; i < grItms.size(); i++) {
					InVoiceLineItems line = new InVoiceLineItems();
					line.setProdouctNumber(grItms.get(i).getProdouctNumber());
					line.setProductGroup(grItms.get(i).getProductGroup());
					line.setDescription(grItms.get(i).getDescription());
					line.setHsn(grItms.get(i).getHsn());
					
					/*line.setRequiredQuantity(grItms.get(i).getRequiredQuantity());*/
					
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
			
			inv.setInVoiceLineItems(lineItems);
			
			/*inv= getListAmount(inv);*/ // Set Amount....Like Tax Total Amt
			
		}
		inv.setCategory("Item");
		
		
		/*SetRequiredQuantity*/
		
		/*Set Headers*/
		
		String sql= " select total_gr_amount_product_tax,total_gr_amount_product_cost,total_discount,freight,total_gr_amount_after_discount"
				+ " ,total_gr_amount_after_discount_rounding from vw_goods_received_amount where id= " +inv.getGrId().getId();
		
		logger.info("sql ----> " + sql);
		Query query = entityManager.createNativeQuery(sql);
		  List<Object[]>	list = query.getResultList();
		
	     for(Object[] tuple : list) {
	    	 inv.setTaxAmt(tuple[0] == null ? "0" : ( tuple[0]).toString());
	    	 inv.setTotalBeforeDisAmt(tuple[1] == null ? 0: (Double.parseDouble(tuple[1].toString())));
	    	// goodsReceipt.setTotalDiscount(tuple[2] == null ?  0: (Double.parseDouble(tuple[2].toString())));
	    	 //goodsReceipt.setFreight(tuple[3] == null  ? 0 : (Integer.parseInt(tuple[3].toString())));
	    	 inv.setAmtRounding(tuple[4] == null ? "0" : ( tuple[4]).toString());
	    	 inv.setTotalPayment(tuple[5] == null ? 0: (Double.parseDouble(tuple[5].toString())));
	    	
	     }
	     /*--Set Headers--*/
	     
	     
	     /*--Set Lists--*/
	     
	 	String sqlList= " select product_number,current_quantity,creditmemo_quantity,product_tax,product_cost_tax from vw_goods_received_lineitems_amount where id= " +inv.getGrId().getId();
	 	String productNumber ="";
	    logger.info("sqlList ----> " + sqlList);
	    Query queryList = entityManager.createNativeQuery(sqlList);
	      List<Object[]>    invoiceList = queryList.getResultList();
	        
	    
	     Map<String, Integer> grListData = new LinkedHashMap<>();
	     for(Object[] tuple : invoiceList) {
	         productNumber = tuple[0] == null ? "0" : ( tuple[0]).toString();
	         grListData.put(productNumber, Integer.parseInt(tuple[1].toString()));
	     }
	    
	     List<InVoiceLineItems> listItems = inv.getInVoiceLineItems();
	     for (int i = 0; i < listItems.size(); i++) {
	    	 InVoiceLineItems invlist = listItems.get(i);
	        
	        for(Map.Entry m:grListData.entrySet()){
	               if(invlist.getProdouctNumber().equals(m.getKey())) {
	                   invlist.setRequiredQuantity((Integer)m.getValue());    
	                 }
	        }
	    }
	     inv.setInVoiceLineItems(listItems);
	   
	    
		
		/*SetRequiredQuantity*/
		
		inv = inVoiceRepository.save(inv);
		
		gr.setStatus(EnumStatusUpdate.INVOICE.getStatus());  // Set Partial Complted
		goodsReceiptRepository.save(gr);
		
		return inv;
       /* }else {
        	return dup_inv;
        }
       */
	}
	
	private void changeGoodsReturnStatus(GoodsReceipt gr) {
		List<GoodsReturn> greList = goodsReturnService.findByGoodsReceiptId(gr, EnumStatusUpdate.OPEN.getStatus());
		for (GoodsReturn gre:greList)  {
			gre.setStatus(EnumStatusUpdate.CANCELED.getStatus());
		}
	}
	
	/*@Override
	public String  setStatusOfPurchaseOrder(InVoice inVoice) {
		logger.info("set Status-->");
		String status="";
		PurchaseOrder purchaseOrder = purchaseOrderService.findById(inVoice.getGrId());
		
		List<InVoiceLineItems> grListItems = inVoice.getInVoiceLineItems();
		List<PurchaseOrderLineItems> poListItems = purchaseOrder.getPurchaseOrderlineItems();
		
		Map<String, Integer> poListData = prepareMapForProductQunatityPR(purchaseOrder, poListItems); // get po list
		
		Map<String, Integer> grListData = prepareMapForProductQunatityGR(inVoice);   // get !Rejected  list
		
		Map<String, Integer> grApproveListData = prepareMapForApprovedProductQunatityGR(inVoice);  // get Approved list
	  
		if(grListData.size()>0) {   // check gr  when !Rejected list
        if(poListData.keySet().equals( grListData.keySet())) {  // check keys pr and gr  when !Rejected list
        	    List<Integer> values1 = new ArrayList<Integer>(poListData.values());
        	    List<Integer> values2 = new ArrayList<Integer>(grListData.values());
        	    Collections.sort(values1);
        	    Collections.sort(values2);
        	  if(values1.equals(values2)) {  // check values  pr and gr
        		  if(poListData.keySet().equals( grApproveListData.keySet())) {  // check keys pr and gr  when Approved list
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
		}else {  // No gr list
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
		
		return poListData;
	}
	
	public Map<String, Integer> prepareMapForProductQunatityGR(InVoice goodReceipt) {
		Map<String, Integer> grMapListData = new LinkedHashMap<>();

		List<InVoice> listinVoice = inVoiceRepository.findByListGrId(goodReceipt.getGrId(),EnumStatusUpdate.REJECTED.getStatus()); // check
																											// Multiple
		grMapListData = getInVoiceRealQunatityList(goodReceipt, grMapListData, listinVoice);
		

		return grMapListData;
	}
	
	private Map<String, Integer> prepareMapForApprovedProductQunatityGR(InVoice goodReceipt) {
		Map<String, Integer> grMapListData = new LinkedHashMap<>();

		List<InVoice> listinVoice = inVoiceRepository.findByApproveListGrId(goodReceipt.getGrId(),EnumStatusUpdate.APPROVEED.getStatus()); // check
																											// Multiple
																											// Quantity
		grMapListData = getInVoiceRealQunatityList(goodReceipt, grMapListData, listinVoice);
		

		return grMapListData;
	}

	private Map<String, Integer> getInVoiceRealQunatityList(InVoice goodReceipt, Map<String, Integer> grMapListData,
			List<InVoice> listinVoice) {
		for (int i = 0; i < listinVoice.size(); i++) {
			InVoice inVoiceObj = listinVoice.get(i);
			List<InVoiceLineItems> inVoiceLineItems = inVoiceObj.getInVoiceLineItems();
			for (int j = 0; j < inVoiceLineItems.size(); j++) {
				InVoiceLineItems grlist = inVoiceLineItems.get(j);
				String key = "";
				
				if( grlist.getProdouctNumber()!=null) {
					key = goodReceipt.getReferenceDocNumber() + "$" + grlist.getProdouctNumber();
					}else {
					key = goodReceipt.getReferenceDocNumber() + "$" + grlist.getSacCode();
					}
				
				if (!grMapListData.containsKey(key)) {

					if (grlist.getProdouctNumber() != null && grlist.getRequiredQuantity() != 0) {
						grMapListData.put(key,
								grlist.getRequiredQuantity());
					} else if (grlist.getSacCode() != null && grlist.getRequiredQuantity() != 0) {
						grMapListData.put(key,
								grlist.getRequiredQuantity());
					}
				} else {
					Integer tempQunatity = grMapListData.get(key);

					if (grlist.getProdouctNumber() != null && grlist.getRequiredQuantity() != 0) {
						grMapListData.put(key,
								tempQunatity + grlist.getRequiredQuantity());
					} else if (grlist.getSacCode() != null && grlist.getRequiredQuantity() != 0) {
						grMapListData.put(key,
								tempQunatity + grlist.getRequiredQuantity());
					}
				}

			}
		}
		
		return grMapListData;
	}
	
	
	
	
	
	@Override
	public InVoice saveCancelStage(String rfqId) {
		InVoice gr = inVoiceRepository.findById(Integer.parseInt(rfqId)).get();
		gr.setStatus(EnumStatusUpdate.CANCELED.getStatus());
		gr.setCategory("Item");
		inVoiceRepository.save(gr);
		return gr;
		
	}

	@Override
	public InVoice findLastDocumentNumber() {
		return inVoiceRepository.findTopByOrderByIdDesc();
	}

	@Override
	public List<InVoice> findAll() {
		return inVoiceRepository.findAll();
	}

	@Override
	public List<InVoice> findByIsActive() {
		return inVoiceRepository.findByIsActive(true,plantService.findPlantIds());
	}
	
	@Override
	public List<InVoice> invApprovedList() {
		return inVoiceRepository.invApprovedList(EnumStatusUpdate.APPROVEED.getStatus(),plantService.findPlantIds());
	}
	

	@Override
	public InVoice findById(int id) {
		return inVoiceRepository.findById(id).get();
	}
	
	
	@Override
	public InVoice getInVoiceById(int id) {
		InVoice invoice = inVoiceRepository.findById(id).get();
		/*Set Headers*/
		
		String sql= " select total_inv_amount_product_tax,total_inv_amount_product,total_discount,freight,total_inv_amount_after_discount"
				+ " ,total_inv_amount_after_discount_rounding from vw_invoice_amount where id= " +id;
		
		logger.info("sql ----> " + sql);
		Query query = entityManager.createNativeQuery(sql);
		  List<Object[]>	list = query.getResultList();
		
	     for(Object[] tuple : list) {
	    	 invoice.setTaxAmt(tuple[0] == null ? "0" : ( tuple[0]).toString());
	    	 invoice.setTotalBeforeDisAmt(tuple[1] == null ? 0: (Double.parseDouble(tuple[1].toString())));
	    	// invoice.setTotalDiscount(tuple[2] == null ?  0: (Double.parseDouble(tuple[2].toString())));
	    	 //invoice.setFreight(tuple[3] == null  ? 0 : (Integer.parseInt(tuple[3].toString())));
	    	 invoice.setTotalPayment(tuple[5] == null ? 0: (Double.parseDouble(tuple[5].toString())));
	    	 invoice.setAmtRounding(tuple[4] == null ? "0" : ( tuple[4]).toString());
	     }
	     
	     /*--Set Headers--*/
	     
	     
	     return invoice;
	}
	
	@Override
	public List<LineItemsBean> getLineItemsBean(int id) {
		
	     /*--Set Lists--*/
		List<LineItemsBean> addListItems = new ArrayList<LineItemsBean>();
	 	String sqlList= " select product_number,description,uom,sku_quantity,unit_price,\r\n" + 
	 			"tax_code,inv_product_tax,inv_product_amount,product_group,hsn,warehouse,inv_final_quantity,tax_description \r\n" + 
	 			"from vw_invoice_lineitems_amount where id=" +id;
	    logger.info("sqlList ----> " + sqlList);
	    Query queryList = entityManager.createNativeQuery(sqlList);
	      List<Object[]>    invoiceList = queryList.getResultList();
	        
	    
	     for(Object[] tuple : invoiceList) {
	    	 LineItemsBean ineItemsObj = new LineItemsBean();
	    	 ineItemsObj.setProdouctNumber(tuple[0] == null ? "0" : ( tuple[0]).toString());
	    	 ineItemsObj.setDescription(tuple[1] == null ? "0" : ( tuple[1]).toString());
	    	 ineItemsObj.setUom(tuple[2] == null ? "0" : ( tuple[2]).toString());
	    	 ineItemsObj.setSku(tuple[3] == null ? "0" : ( tuple[3]).toString());
	    	 ineItemsObj.setUnitPrice(tuple[4] == null ? 0: (Double.parseDouble(tuple[4].toString())));
	    	 ineItemsObj.setTaxCode(tuple[5] == null ? 0: (Double.parseDouble(tuple[5].toString())));
	    	 ineItemsObj.setTaxTotal(tuple[6] == null ? "0" : ( tuple[6]).toString());
	    	 ineItemsObj.setTotal(tuple[7] == null ? "0" : ( tuple[7]).toString());
	    	 ineItemsObj.setProductGroup(tuple[8] == null ? "0" : ( tuple[8]).toString());
	    	 ineItemsObj.setHsn(tuple[9] == null ? "0" : ( tuple[9]).toString());
	    	 ineItemsObj.setWarehouse(tuple[10] == null ? 0 :Integer.parseInt(tuple[10].toString()));
	    	 ineItemsObj.setRequiredQuantity(tuple[11] == null ? 0 :Integer.parseInt(tuple[11].toString()));
	    	 ineItemsObj.setTempRequiredQuantity(tuple[11] == null ? 0 :Integer.parseInt(tuple[11].toString()));
	    	 ineItemsObj.setTaxDescription(tuple[12] == null ? "0" : ( tuple[12]).toString());
	    	 addListItems.add(ineItemsObj);
	         
	     }
	     
	     return addListItems;
	}
	
	
	
	
	/*@Override
	public InVoice getInVoiceRequireQuantityById(int id) {
		InVoice invoice = inVoiceRepository.findById(id).get();
		
		String sqlList= " select product_number,creditmemo_quantity,inv_final_quantity,inv_product_tax,inv_amount_tax from vw_invoice_lineitems_amount where id= " +id;
		String productNumber =""; 
		Integer creditmemoQuantity=0;
		logger.info("sqlList ----> " + sqlList);
		Query queryList = entityManager.createNativeQuery(sqlList);
		  List<Object[]>	invoiceList = queryList.getResultList();
		  
		  List<InVoiceLineItems> listItems = invoice.getInVoiceLineItems();
			
			List<InVoiceLineItems> addListItems = new ArrayList<InVoiceLineItems>();
		logger.info("invoiceList Size -----> " + invoiceList.size());
		int j=0;
	     for(Object[] tuple : invoiceList) {
	    	 InVoiceLineItems invList = listItems.get(j);
	    	 productNumber = tuple[0] == null ? "0" : ( tuple[0]).toString();
	    	 creditmemoQuantity = tuple[1] == null  ? 0 : (Integer.parseInt(tuple[1].toString()));
	    	 
	    		for (int i = 0; i < listItems.size(); i++) {
	    			InVoiceLineItems invlist = listItems.get(i);
	    			
	    			if(productNumber.equals(invlist.getProdouctNumber())) {
	    			invList.setRequiredQuantity(tuple[2] == null  ? 0 : (Integer.parseInt(tuple[2].toString())));
	    			invList.setTaxTotal(tuple[3] == null ? "0" : ( tuple[3]).toString());
					invList.setTotal(tuple[4] == null ? "0" : ( tuple[4]).toString());
					break;
					}
	    		}
	    		addListItems.add(invList);
	    		j++;
	     }
	     
	 	invoice.setInVoiceLineItems(addListItems);
	 	
	 	return invoice;
	}*/
	
	
	
	@Override
	public InVoice getListAmount(InVoice inVoice) {
		logger.info("getListAmount-->");
		List<InVoiceLineItems> listItems = inVoice.getInVoiceLineItems();
		
		List<InVoiceLineItems> addListItems = new ArrayList<InVoiceLineItems>();
		Double addAmt=0.0;
		Double addTaxAmt=0.0;
		Integer greQunatity=0;
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				InVoiceLineItems invList = listItems.get(i);
				if(invList.getUnitPrice()!=null && invList.getRequiredQuantity()!=null) {
				addTaxAmt += UnitPriceListItems.getTaxAmt(invList.getRequiredQuantity(),invList.getUnitPrice(),invList.getTaxCode());
				addAmt +=UnitPriceListItems.getTotalINVAmt(invList.getRequiredQuantity(),invList.getUnitPrice());
				invList.setTaxTotal(""+UnitPriceListItems.getTaxAmt(invList.getRequiredQuantity(),invList.getUnitPrice(),invList.getTaxCode()));
				invList.setTotal(""+UnitPriceListItems.getTotalINVAmt(invList.getRequiredQuantity(),invList.getUnitPrice()));
				} 
				addListItems.add(invList);
			  }
			inVoice.setInVoiceLineItems(addListItems);
			}
			
		
		inVoice.setTotalBeforeDisAmt(addAmt);
		inVoice.setTaxAmt(""+addTaxAmt);
		 
		Double total_amt=0.0;
		Double total_payment = 0.0;
		if(inVoice.getTotalDiscount()==null) inVoice.setTotalDiscount(0.0);
		if(inVoice.getFreight()==null) inVoice.setFreight(0.0);
			
			
		 total_amt= UnitPriceListItems.getTotalAmtPayment(addAmt, inVoice.getTotalDiscount(), inVoice.getFreight(),addTaxAmt);
		 
		 if(inVoice.getGrId() != null) {
				total_payment =(double) Math.round(total_amt);
			}else {
				total_payment = inVoice.getTotalPayment();
			}
		inVoice.setAmtRounding(""+df2.format(total_amt));
		//inVoice.setTotalPayment(total_amt);
		inVoice.setRoundedOff("" + df2.format(total_payment - total_amt));
	
	return inVoice;
	}
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	
	
	
	
	
	
	/*@Override
	public String checkStatusPoGr(PurchaseOrder purchaseOrder) {
	    String status ="";
		inVoice  inVoice = inVoiceRepository.findByGrId(purchaseOrder.getId());
		if(inVoice!=null) {
		List<PurchaseOrderLineItems> poListItems = purchaseOrder.getPurchaseOrderlineItems();
		
		Map<String, Integer> poListData = prepareMapForProductQunatityPR(purchaseOrder, poListItems);
		
		Map<String, Integer> grListData = prepareMapForProductQunatityGR(inVoice);
		
      
        if(poListData.keySet().equals( grListData.keySet())) {
        	    List<Integer> values1 = new ArrayList<Integer>(poListData.values());
        	    List<Integer> values2 = new ArrayList<Integer>(grListData.values());
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
	public Boolean checkQuantityPoGr(PurchaseOrder purchaseOrder) {
		List<InVoice> listinVoice = inVoiceRepository
				.findByListGrId(purchaseOrder.getId(),EnumStatusUpdate.REJECTED.getStatus());
		logger.info("listinVoice-->" +listinVoice);
		
		Integer prQunatity = getListPoQuantityCount(purchaseOrder);
		Integer grQunatity = getListGRQunatityCount(listinVoice);
		
		if(prQunatity > grQunatity)
			return true;
		else
			return false;
	}*/

	private Integer getListGRQunatityCount(List<InVoice> listinVoice) {
	
		Integer grQunatity=0;
		if (listinVoice != null) {
			for (int i = 0; i < listinVoice.size(); i++) {
				InVoice  inVoice = listinVoice.get(i);
				List<InVoiceLineItems> listItems = inVoice.getInVoiceLineItems();
			for (int j = 0; j < listItems.size(); j++) {
				InVoiceLineItems grlist = listItems.get(j);
				if(grlist.getRequiredQuantity()!=null) {
					grQunatity +=  grlist.getRequiredQuantity();
				}
			}
		  }
		}
		return grQunatity;
	}

	

	
	private Integer getListGoodsProductCount(List<InVoice> listinVoice, String category) {
		Integer qunatity = 0;
	
		for (int i = 0; i < listinVoice.size(); i++) {
			InVoice inVoiceObj = listinVoice.get(i);
			List<InVoiceLineItems> inVoiceLineItems = inVoiceObj.getInVoiceLineItems();
			for (int j = 0; j < inVoiceLineItems.size(); j++) {
				InVoiceLineItems grlist = inVoiceLineItems.get(j);
				if (grlist.getRequiredQuantity() != null) {
					if (category.equals(grlist.getProdouctNumber()) || category.equals(grlist.getSacCode()))
						qunatity += grlist.getRequiredQuantity();
				}
			}
		}
		
		return qunatity;
	}
	
	
	
	
	
	
	
	private Integer getListPoQuantityCount(PurchaseOrder purchaseOrder) {
		List<PurchaseOrderLineItems> listItems = purchaseOrder.getPurchaseOrderlineItems();
		Integer prQunatity=0;
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				PurchaseOrderLineItems polist = listItems.get(i);
				if(polist.getRequiredQuantity()!=null) {
					prQunatity += polist.getRequiredQuantity();
				}
			}
		}
		
		return prQunatity;
	}
	
	
	
	
	
	
	
	
	
	@Override
	public InVoice delete(int id) {
		InVoice inVoice = inVoiceRepository.findById(id).get();
		inVoice.setIsActive(false);
		inVoiceRepository.save(inVoice);
		return inVoice;
	}

			public static void main(String[] args) {
				
				Map<String, Integer> poListData = new LinkedHashMap<>();
				poListData.put("PO2019010747$p103", 10);
				poListData.put("PO2019010747$p123", 20);
				
				Map<String, Integer> grListData = new LinkedHashMap<>();
				grListData.put("PO2019010747$p103", 5);
				grListData.put("PO2019010747$p123", 10);
				
			  
				logger.info("is Vaild-->" +poListData.keySet().equals( grListData.keySet()));
		        
				logger.info("is Vaild-->" +poListData.keySet().equals( grListData.keySet()));
		        
		       /* if(key1.containsAll(key2)) {
		        	    List<Integer> values1 = new ArrayList<Integer>(poListData.values());
		        	    List<Integer> values2 = new ArrayList<Integer>(grListData.values());
		        	    Collections.sort(values1);
		        	    Collections.sort(values2);
				
			   }*/
	
			}
			
			@Override
			public InVoice  setStatusOfInVoice(InVoice invoice) {
				logger.info("set Status-->");
				String status="";
				
				if(invoice!=null) {
				String sqlList= "select\r\n" + 
						"inv.id as invId\r\n" + 
						",inl.product_id\r\n" + 
						",inl.required_quantity inv_quantity\r\n" + 
						",sum(cml.required_quantity) AS cmQuantity\r\n" + 
						",inl.required_quantity-sum(cml.required_quantity) balance_qty\r\n" + 
						"from tbl_invoice inv\r\n" + 
						"join tbl_invoice_lineitems inl on inl.inv_id = inv.id\r\n" + 
						"left join tbl_credit_memo cmh on inv.id = cmh.inv_id and cmh.status not in( 'Rejected' ,'Open') \r\n" + 
						"left join tbl_credit_memo_lineitems cml ON cmh.id = cml.cre_id and cml.product_id=inl.product_id\r\n" + 
						"group by inv.id,inl.product_id,inl.required_quantity having inv.id="+invoice.getId();
				
				Integer balenceQuantity=0;
				
			 	logger.info("sqlList ----> " + sqlList);
				Query queryList = entityManager.createNativeQuery(sqlList);
				 List<Object[]>	list = queryList.getResultList();
					
				     for(Object[] tuple : list) {
				    	 balenceQuantity =(Integer)(tuple[4] == null  ? 0 : (Integer.parseInt((tuple[4].toString()))));
				     }
			     
				 
				    if(balenceQuantity==0) {
				    	 status = EnumStatusUpdate.CREDITMEMO.getStatus();
				    }else {
				    	 status = EnumStatusUpdate.PARTIALLY_CREDITED.getStatus();
				    }
				
			}
				invoice.setStatus(status);
				inVoiceRepository.save(invoice);
			return invoice;	
	  }
			
			

	@Override
	public boolean findByDocNumber(String invDocNum) {
		List<InVoice> invList = inVoiceRepository.findByDocNumber(invDocNum);
		if(invList.size()>0) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public List<InVoice> searchFilterBySelection(SearchFilter searchFilter){
		if(searchFilter.getToDate()==null) {
			searchFilter.setToDate(new Date());
		}
		
		searchFilter.setTypeOf(EnumSearchFilter.INVTABLE.getStatus());
		if(searchFilter.getIsConvertedDoc()!= null && searchFilter.getIsConvertedDoc().equals("true")) {
			List<String> statusList = getConvertedDocStatusList.getINVStatusList();
			searchFilter.setStatusList(statusList);
			if(searchFilter.getSortBy()!=null) {
				if((!searchFilter.getSearchBy().equals("select") && !searchFilter.getFieldName().isEmpty()) || (searchFilter.getFromDate()!=null && searchFilter.getToDate()!=null )) {
					
					String resultQuery = getSearchFilterResult.getQueryBysearchFilterSelection(searchFilter);
					
					Query query = entityManager.createQuery(resultQuery);
					List<InVoice> list = query.getResultList();
					return list;
				}else {
				List<InVoice> list = invApprovedList();
				return list;
			}
			}else {
				List<InVoice> list = invApprovedList();
				return list;
			}
		} else if(searchFilter.getSortBy()!=null) {
			if((!searchFilter.getSearchBy().equals("select") && !searchFilter.getFieldName().isEmpty()) || (searchFilter.getFromDate()!=null && searchFilter.getToDate()!=null )) {
				
				String resultQuery = getSearchFilterResult.getQueryBysearchFilterSelection(searchFilter);
				
				Query query = entityManager.createQuery(resultQuery);
				List<InVoice> list = query.getResultList();
				return list;
			}else {
			List<InVoice> list = findByIsActive();
			return list;
		}
		}else {
			List<InVoice> list = findByIsActive();
			return list;
		}
	}
}
