package com.smerp.serviceImpl.purchase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.Vendor;
import com.smerp.model.admin.VendorAddress;
import com.smerp.model.admin.VendorsContactDetails;
import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.GoodsReceiptLineItems;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.PurchaseOrderLineItems;
import com.smerp.repository.purchase.GoodsReceiptLineItemsRepository;
import com.smerp.repository.purchase.GoodsReceiptRepository;
import com.smerp.repository.purchase.PurchaseOrderRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.VendorAddressService;
import com.smerp.service.inventory.VendorsContactDetailsService;
import com.smerp.service.purchase.GoodsReceiptService;
import com.smerp.service.purchase.PurchaseOrderService;
import com.smerp.util.EmailGenerator;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.RequestContext;
import com.smerp.util.UnitPriceListItems;

@Service
@Transactional
public class GoodsReceiptServiceImpl  implements GoodsReceiptService {

	private static final Logger logger = LogManager.getLogger(RequestForQuotationServiceImpl.class);

	@Autowired
	GoodsReceiptRepository goodsReceiptRepository;

	@Autowired
	GoodsReceiptLineItemsRepository goodsReceiptLineItemsRepository;

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
	EmailGenerator emailGenerator;

	@Override
	public GoodsReceipt save(GoodsReceipt goodsReceipt) {

		switch (goodsReceipt.getStatusType()) {
		case "DR":
			goodsReceipt.setStatus(EnumStatusUpdate.DRAFT.getStatus());
			break;
		case "SA":
			goodsReceipt.setStatus(EnumStatusUpdate.OPEN.getStatus());
			break;

		case "RE":
			goodsReceipt.setStatus(EnumStatusUpdate.REJECTED.getStatus());
			break;
		case "APP":
			goodsReceipt.setStatus(EnumStatusUpdate.APPROVEED.getStatus());
			break;
		case "CA":
			goodsReceipt.setStatus(EnumStatusUpdate.CANCELED.getStatus());
			break;
		default:
			logger.info("Type Not Matched:" + goodsReceipt.getStatusType());
			break;
		}

		if (goodsReceipt.getId() != null) { // delete List Of Items.
			GoodsReceipt goodsReceiptObj = goodsReceiptRepository
					.findById(goodsReceipt.getId()).get();
			List<GoodsReceiptLineItems> requestLists = goodsReceiptObj.getGoodsReceiptLineItems();
			
			if(requestLists.size()>0 && requestLists!=null) {
				goodsReceiptLineItemsRepository.deleteAll(requestLists);  // Delete All list items 
				}
			
			
			if(goodsReceipt.getPoId()==null) {  // if RfqId null remove list items 
			List<GoodsReceiptLineItems> listItems = goodsReceipt.getGoodsReceiptLineItems();
			if (listItems != null) {
				for (int i = 0; i < listItems.size(); i++) {
					if (listItems.get(i).getProdouctNumber() == null && listItems.get(i).getSacCode() == null) {
						listItems.remove(i);
					}
				}
				goodsReceipt.setGoodsReceiptLineItems(listItems);
			}
			
			}else {
				 logger.info("convertd Po to GR Data -->" +goodsReceipt);
			}
		}
         logger.info("goodsReceipt -->" +goodsReceipt);
		Vendor vendor = vendorService.findById(goodsReceipt.getVendor().getId());
		VendorAddress vendorShippingAddress = vendorAddressService.findById(goodsReceipt.getVendorShippingAddress().getId());
		VendorAddress vendorPayAddress = vendorAddressService.findById(goodsReceipt.getVendorPayTypeAddress().getId());
		
		VendorsContactDetails vendorsContactDetails =vendorsContactDetailsService.findById(goodsReceipt.getVendorContactDetails().getId());

		goodsReceipt.setVendor(vendor);
		goodsReceipt.setVendorContactDetails(vendorsContactDetails);
		goodsReceipt.setVendorShippingAddress(vendorShippingAddress);
		goodsReceipt.setVendorPayTypeAddress(vendorPayAddress);
		
		if(goodsReceipt.getStatusType()!=null &&  goodsReceipt.getStatusType().equals("APP")) {
			try {
			   	goodsReceipt =getListAmount(goodsReceipt);
    			 RequestContext.initialize();
    		     RequestContext.get().getConfigMap().put("mail.template", "goodsReceiptEmail.ftl");  //Sending Email
    		     emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendGoodsReceiptEmail(goodsReceipt);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		}
		
		/*if(goodsReceipt.getPoId()!=null) {
		PurchaseOrder po = purchaseOrderService.findById(goodsReceipt.getPoId());
		if(!checkQuantityPoGr(po)) {
			
			 logger.info("purchaseOrder    COMPLETED -->");
		} }*/
		
		
		goodsReceipt= goodsReceiptRepository.save(goodsReceipt);
		
		if(goodsReceipt.getPoId()!=null) {
		PurchaseOrder po = purchaseOrderService.findById(goodsReceipt.getPoId());
		
		String status = setStatusOfPurchaseOrder(goodsReceipt);
		po.setStatus(status);
		purchaseOrderRepository.save(po);
		}
		
		return goodsReceipt; 
		 
	}

	@Override
	public GoodsReceipt saveGR(String poId) {

		GoodsReceipt gr = new GoodsReceipt();
		PurchaseOrder po = purchaseOrderService.findById((Integer.parseInt(poId)));
		logger.info("po" + po);
		/*GoodsReceipt dup_gr =goodsReceiptRepository.findByPoId(po.getId());  // check PO exist in  GR
        if(dup_gr==null) { */
		GoodsReceipt grDetails = findLastDocumentNumber();
		if (grDetails != null && grDetails.getDocNumber() != null) {
			gr.setDocNumber(GenerateDocNumber.documentNumberGeneration(grDetails.getDocNumber()));
		} else {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDateTime now = LocalDateTime.now();
		gr.setDocNumber(GenerateDocNumber.documentNumberGeneration("GR"+(String)dtf.format(now) +"0"));
		}

		if (po != null) {
			gr.setDocumentDate(po.getDocumentDate());
			gr.setStatus(EnumStatusUpdate.OPEN.getStatus());
			gr.setPostingDate(po.getPostingDate());
			gr.setCategory(po.getCategory());
			gr.setRemark(po.getRemark());
			gr.setReferenceDocNumber(po.getDocNumber());
			gr.setRequiredDate(po.getRequiredDate());
			gr.setPoId(po.getId());
			gr.setIsActive(true);
			gr.setVendor(po.getVendor());
			gr.setVendorContactDetails(po.getVendorContactDetails());
			gr.setVendorPayTypeAddress(po.getVendorPayTypeAddress());
			gr.setVendorShippingAddress(po.getVendorShippingAddress());
			
		
			gr.setFreight(po.getFreight());
			gr.setTotalDiscount(po.getTotalDiscount());
		
			/*List<GoodsReceipt> listGoodsReceipt = goodsReceiptRepository
					.findByListPoId(po.getId());  // check Multiple  Quantity
			Integer grQunatity =0;*/
			
			List<PurchaseOrderLineItems> poItms = po.getPurchaseOrderlineItems();
			List<GoodsReceiptLineItems> lineItems =new ArrayList<GoodsReceiptLineItems>();
			if (poItms != null) {
				for (int i = 0; i < poItms.size(); i++) {
					GoodsReceiptLineItems line = new GoodsReceiptLineItems();
					line.setProdouctNumber(poItms.get(i).getProdouctNumber());
					line.setProductGroup(poItms.get(i).getProductGroup());
					line.setDescription(poItms.get(i).getDescription());
					line.setHsn(poItms.get(i).getHsn());
					
				/*	if(poItms.get(i).getProdouctNumber()!=null) {
						 grQunatity = getListGoodsProductCount(listGoodsReceipt,  poItms.get(i).getProdouctNumber());
					}else if(poItms.get(i).getSacCode()!=null) {
						 grQunatity = getListGoodsProductCount(listGoodsReceipt,  poItms.get(i).getSacCode());
					}*/
					
				
					//line.setRequiredQuantity(poItms.get(i).getRequiredQuantity() - grQunatity);
					line.setRequiredQuantity(0);
					
					line.setSacCode(poItms.get(i).getSacCode());
					line.setUom(poItms.get(i).getUom());
					line.setWarehouse(poItms.get(i).getWarehouse());
					line.setProductId(poItms.get(i).getProductId());
					line.setTaxCode(poItms.get(i).getTaxCode());
					line.setUnitPrice(poItms.get(i).getUnitPrice());
					
					line.setTaxTotal(poItms.get(i).getTaxTotal());
					
					line.setTotal(poItms.get(i).getTotal());
					
					lineItems.add(line);
				}
			}
			
			gr.setGoodsReceiptLineItems(lineItems);
			
			gr= getListAmount(gr); // Set Amount....Like Tax Total Amt
			
			
		}
		logger.info("gr" + gr);
		gr = goodsReceiptRepository.save(gr);
		
		po.setStatus(EnumStatusUpdate.PARTIALLY_RECEIVED.getStatus());  // Set Partial Complted
		purchaseOrderRepository.save(po);
		
		return gr;
     
	}
	
	
	
	
	public String  setStatusOfPurchaseOrder(GoodsReceipt goodsReceipt) {
		logger.info("set Status-->");
		String status="";
		PurchaseOrder purchaseOrder = purchaseOrderService.findById(goodsReceipt.getPoId());
		
		List<GoodsReceiptLineItems> grListItems = goodsReceipt.getGoodsReceiptLineItems();
		List<PurchaseOrderLineItems> poListItems = purchaseOrder.getPurchaseOrderlineItems();
		
		Map<String, Integer> poListData = prepareMapForProductQunatityPR(purchaseOrder, poListItems); // get po list
		
		Map<String, Integer> grListData = prepareMapForProductQunatityGR(goodsReceipt);   // get !Rejected  list
		
		Map<String, Integer> grApproveListData = prepareMapForApprovedProductQunatityGR(goodsReceipt);  // get Approved list
	  
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
	}

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
	
	private Map<String, Integer> prepareMapForProductQunatityGR(GoodsReceipt goodReceipt) {
		Map<String, Integer> grMapListData = new LinkedHashMap<>();

		List<GoodsReceipt> listGoodsReceipt = goodsReceiptRepository.findByListPoId(goodReceipt.getPoId(),EnumStatusUpdate.REJECTED.getStatus()); // check
																											// Multiple
		grMapListData = getGoodsReceiptRealQunatityList(goodReceipt, grMapListData, listGoodsReceipt);
		
		logger.info("grMapListData-->" + grMapListData);

		return grMapListData;
	}
	
	private Map<String, Integer> prepareMapForApprovedProductQunatityGR(GoodsReceipt goodReceipt) {
		Map<String, Integer> grMapListData = new LinkedHashMap<>();

		List<GoodsReceipt> listGoodsReceipt = goodsReceiptRepository.findByListPoId(goodReceipt.getPoId(),EnumStatusUpdate.APPROVEED.getStatus()); // check
																											// Multiple
																											// Quantity
		grMapListData = getGoodsReceiptRealQunatityList(goodReceipt, grMapListData, listGoodsReceipt);
		
		logger.info("grMapListData-->" + grMapListData);

		return grMapListData;
	}

	private Map<String, Integer> getGoodsReceiptRealQunatityList(GoodsReceipt goodReceipt, Map<String, Integer> grMapListData,
			List<GoodsReceipt> listGoodsReceipt) {
		for (int i = 0; i < listGoodsReceipt.size(); i++) {
			GoodsReceipt goodsReceiptObj = listGoodsReceipt.get(i);
			List<GoodsReceiptLineItems> goodsReceiptLineItems = goodsReceiptObj.getGoodsReceiptLineItems();
			for (int j = 0; j < goodsReceiptLineItems.size(); j++) {
				GoodsReceiptLineItems grlist = goodsReceiptLineItems.get(j);
				logger.info("grlist===>" + grlist);
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
	public GoodsReceipt saveCancelStage(String rfqId) {
		GoodsReceipt gr = goodsReceiptRepository.findById(Integer.parseInt(rfqId)).get();
		gr.setStatus(EnumStatusUpdate.CANCELED.getStatus());
		goodsReceiptRepository.save(gr);
		return gr;
		
	}

	@Override
	public GoodsReceipt findLastDocumentNumber() {
		return goodsReceiptRepository.findTopByOrderByIdDesc();
	}

	@Override
	public List<GoodsReceipt> findAll() {
		return goodsReceiptRepository.findAll();
	}

	@Override
	public List<GoodsReceipt> findByIsActive() {
		return goodsReceiptRepository.findByIsActive(true);
	}

	@Override
	public GoodsReceipt findById(int id) {
		return goodsReceiptRepository.findById(id).get();
	}
	
	
	
	
	@Override
	public GoodsReceipt getListAmount(GoodsReceipt goodsReceipt) {
		logger.info("getListAmount-->");
		List<GoodsReceiptLineItems> listItems = goodsReceipt.getGoodsReceiptLineItems();
		
		PurchaseOrder po = null;
		List<PurchaseOrderLineItems> poItms =null;
		 List<GoodsReceipt> listGoodsReceipt =null;
		if(goodsReceipt.getPoId()!=null) {
		 po = purchaseOrderService.findById(goodsReceipt.getPoId());
		 poItms = po.getPurchaseOrderlineItems();
		listGoodsReceipt = goodsReceiptRepository
					.findByListPoId(po.getId(),EnumStatusUpdate.REJECTED.getStatus());  // check Multiple  Quantity
		}
		
		
		Double addAmt=0.0;
		Double addTaxAmt=0.0;
		Integer grQunatity=0;
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				GoodsReceiptLineItems grlist = listItems.get(i);
				if(grlist.getUnitPrice()!=null) {
				addTaxAmt += UnitPriceListItems.getTaxAmt(grlist.getRequiredQuantity(),grlist.getUnitPrice(),grlist.getTaxCode());
				addAmt +=UnitPriceListItems.getTotalAmt(grlist.getRequiredQuantity(),grlist.getUnitPrice(), grlist.getTaxCode());
				grlist.setTaxTotal(""+UnitPriceListItems.getTaxAmt(grlist.getRequiredQuantity(),grlist.getUnitPrice(),grlist.getTaxCode()));
				grlist.setTotal(""+UnitPriceListItems.getTotalAmt(grlist.getRequiredQuantity(),grlist.getUnitPrice(), grlist.getTaxCode()));
				
				if(goodsReceipt.getPoId()!=null) {
				if(poItms.get(i).getProdouctNumber()!=null ) {
					 grQunatity = getListGoodsProductCount(listGoodsReceipt,  poItms.get(i).getProdouctNumber());
				}else if(poItms.get(i).getSacCode()!=null ) {
					 grQunatity = getListGoodsProductCount(listGoodsReceipt,  poItms.get(i).getSacCode());
				} 
				
				logger.info("poItms.get(i).getRequiredQuantity()-->" + poItms.get(i).getRequiredQuantity());
				logger.info("grQunatity-->" + grQunatity);
				grlist.setTempRequiredQuantity(poItms.get(i).getRequiredQuantity() - grQunatity);
				
				
				}
				
			
				
				}else {
				grlist.setTaxTotal("");
				grlist.setTotal("");	
				}
			}
			goodsReceipt.setGoodsReceiptLineItems(listItems);
		}
		goodsReceipt.setTotalBeforeDisAmt(addAmt);
		goodsReceipt.setTaxAmt(""+addTaxAmt);
		logger.info("addAmt-->" + addAmt); 
		logger.info("goodsReceipt.getTotalDiscount()-->" + goodsReceipt.getTotalDiscount());
		logger.info("goodsReceipt.getFreight()-->" + goodsReceipt.getFreight());
		Double total_amt=0.0;
		if(goodsReceipt.getTotalDiscount()==null) goodsReceipt.setTotalDiscount(0.0);
		if(goodsReceipt.getFreight()==null) goodsReceipt.setFreight(0);
			
			
		 total_amt= UnitPriceListItems.getTotalPaymentAmt(addAmt, goodsReceipt.getTotalDiscount(), goodsReceipt.getFreight());
		goodsReceipt.setAmtRounding(UnitPriceListItems.getRoundingValue(total_amt));
		goodsReceipt.setTotalPayment(total_amt);
		
		
	
	return goodsReceipt;
	}
	
	
	
	
	
	
	
	
	
	/*@Override
	public String checkStatusPoGr(PurchaseOrder purchaseOrder) {
	    String status ="";
		GoodsReceipt  goodsReceipt = goodsReceiptRepository.findByPoId(purchaseOrder.getId());
		if(goodsReceipt!=null) {
		List<PurchaseOrderLineItems> poListItems = purchaseOrder.getPurchaseOrderlineItems();
		
		Map<String, Integer> poListData = prepareMapForProductQunatityPR(purchaseOrder, poListItems);
		
		Map<String, Integer> grListData = prepareMapForProductQunatityGR(goodsReceipt);
		
      
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
	
	
	@Override
	public Boolean checkQuantityPoGr(PurchaseOrder purchaseOrder) {
		List<GoodsReceipt> listGoodsReceipt = goodsReceiptRepository
				.findByListPoId(purchaseOrder.getId(),EnumStatusUpdate.REJECTED.getStatus());
		logger.info("listGoodsReceipt-->" +listGoodsReceipt);
		
	/*	String status = setStatusOfPurchaseOrder(listGoodsReceipt.get(0));
		logger.info("status-->" +status); //Test the Status if you want  */
		
		Integer prQunatity = getListPoQuantityCount(purchaseOrder);
		Integer grQunatity = getListGRQunatityCount(listGoodsReceipt);
		
		if(prQunatity > grQunatity)
			return true;
		else
			return false;
	}

	private Integer getListGRQunatityCount(List<GoodsReceipt> listGoodsReceipt) {
	
		Integer grQunatity=0;
		if (listGoodsReceipt != null) {
			for (int i = 0; i < listGoodsReceipt.size(); i++) {
				GoodsReceipt  goodsReceipt = listGoodsReceipt.get(i);
				List<GoodsReceiptLineItems> listItems = goodsReceipt.getGoodsReceiptLineItems();
			for (int j = 0; j < listItems.size(); j++) {
				GoodsReceiptLineItems grlist = listItems.get(j);
				if(grlist.getRequiredQuantity()!=null) {
					grQunatity +=  grlist.getRequiredQuantity();
				}
			}
		  }
		}
		logger.info("grQunatity===>" +grQunatity);
		return grQunatity;
	}

	

	
	private Integer getListGoodsProductCount(List<GoodsReceipt> listGoodsReceipt, String category) {
		Integer qunatity = 0;
		logger.info("category===>" +category);
	
		for (int i = 0; i < listGoodsReceipt.size(); i++) {
			GoodsReceipt goodsReceiptObj = listGoodsReceipt.get(i);
			List<GoodsReceiptLineItems> goodsReceiptLineItems = goodsReceiptObj.getGoodsReceiptLineItems();
			for (int j = 0; j < goodsReceiptLineItems.size(); j++) {
				GoodsReceiptLineItems grlist = goodsReceiptLineItems.get(j);
				logger.info("grlist===>" +grlist);
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
		
		logger.info("prQunatity===>" +prQunatity);
		return prQunatity;
	}
	
	
	
	
	
	
	
	
	
	@Override
	public GoodsReceipt delete(int id) {
		GoodsReceipt goodsReceipt = goodsReceiptRepository.findById(id).get();
		goodsReceipt.setIsActive(false);
		goodsReceiptRepository.save(goodsReceipt);
		return goodsReceipt;
	}

			public static void main(String[] args) {
				
				Map<String, Integer> poListData = new LinkedHashMap<>();
				poListData.put("PO2019010747$p103", 10);
				poListData.put("PO2019010747$p123", 20);
				
				Map<String, Integer> grListData = new LinkedHashMap<>();
				grListData.put("PO2019010747$p103", 5);
				grListData.put("PO2019010747$p123", 10);
				
			  
		        System.out.println("is Vaild-->" +poListData.keySet().equals( grListData.keySet()));
		        
		        System.out.println("is Vaild-->" +poListData.keySet().equals( grListData.keySet()));
		        
		       /* if(key1.containsAll(key2)) {
		        	    List<Integer> values1 = new ArrayList<Integer>(poListData.values());
		        	    List<Integer> values2 = new ArrayList<Integer>(grListData.values());
		        	    Collections.sort(values1);
		        	    Collections.sort(values2);
				
			   }*/
	
			}

}
