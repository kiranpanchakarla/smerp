package com.smerp.serviceImpl.purchase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.Vendor;
import com.smerp.model.admin.VendorAddress;
import com.smerp.model.admin.VendorsContactDetails;
import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.GoodsReceiptLineItems;
import com.smerp.model.inventory.InVoice;
import com.smerp.model.inventory.InVoiceLineItems;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.PurchaseOrderLineItems;
import com.smerp.repository.purchase.GoodsReceiptRepository;
import com.smerp.repository.purchase.GoodsReturnRepository;
import com.smerp.repository.purchase.InVoiceLineItemsRepository;
import com.smerp.repository.purchase.InVoiceRepository;
import com.smerp.repository.purchase.PurchaseOrderRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.VendorAddressService;
import com.smerp.service.inventory.VendorsContactDetailsService;
import com.smerp.service.purchase.GoodsReceiptService;
import com.smerp.service.purchase.InVoiceService;
import com.smerp.service.purchase.PurchaseOrderService;
import com.smerp.util.EmailGenerator;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.RequestContext;
import com.smerp.util.UnitPriceListItems;

@Service
public class InVoiceServiceImpl  implements InVoiceService {

	private static final Logger logger = LogManager.getLogger(RequestForQuotationServiceImpl.class);

	@Autowired
	InVoiceRepository inVoiceRepository;

	@Autowired
	InVoiceLineItemsRepository inVoiceLineItemsRepository;
	
	@Autowired
	GoodsReturnRepository goodsReturnRepository;
	
	@Autowired
	GoodsReceiptService goodsReceiptService;
	
	@Autowired
	GoodsReceiptRepository goodsReceiptRepository;
	
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
					}
				}
				inVoice.setInVoiceLineItems(listItems);
			}
			
			}else {
				 logger.info("convertd Po to GR Data -->" +inVoice);
			}
		}
         logger.info("inVoice -->" +inVoice);
		Vendor vendor = vendorService.findById(inVoice.getVendor().getId());
		VendorAddress vendorShippingAddress = vendorAddressService.findById(inVoice.getVendorShippingAddress().getId());
		VendorAddress vendorPayAddress = vendorAddressService.findById(inVoice.getVendorPayTypeAddress().getId());
		
		VendorsContactDetails vendorsContactDetails =vendorsContactDetailsService.findById(inVoice.getVendorContactDetails().getId());

		inVoice.setVendor(vendor);
		inVoice.setVendorContactDetails(vendorsContactDetails);
		inVoice.setVendorShippingAddress(vendorShippingAddress);
		inVoice.setVendorPayTypeAddress(vendorPayAddress);
		
		if(inVoice.getStatusType()!=null &&  inVoice.getStatusType().equals("APP")) {
			try {
			   	inVoice =getListAmount(inVoice);
    			 RequestContext.initialize();
    		     RequestContext.get().getConfigMap().put("mail.template", "inVoiceEmail.ftl");  //Sending Email
    		     emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendInvoiceEmail(inVoice);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		}
		
		
		
		
		inVoice= inVoiceRepository.save(inVoice);
		
		
		
		return inVoice; 
		 
	}

	@Override
	public InVoice saveInv(String grId) {

		InVoice inv = new InVoice();
		GoodsReceipt gr = goodsReceiptService.findById((Integer.parseInt(grId)));
		logger.info("grId" + grId);
		InVoice dup_inv =inVoiceRepository.findByGrId(gr.getId());  // check PO exist in  GR
        if(dup_inv==null) {
		InVoice greDetails = findLastDocumentNumber();
		if (greDetails != null && greDetails.getDocNumber() != null) {
			inv.setDocNumber(GenerateDocNumber.documentNumberGeneration(greDetails.getDocNumber()));
		} else {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDateTime now = LocalDateTime.now();
		inv.setDocNumber(GenerateDocNumber.documentNumberGeneration("INV"+(String)dtf.format(now) +"0"));
		}

		if (gr != null) {
			inv.setDocumentDate(gr.getDocumentDate());
			inv.setStatus(EnumStatusUpdate.OPEN.getStatus());
			inv.setPostingDate(gr.getPostingDate());
			inv.setCategory(gr.getCategory());
			inv.setRemark(gr.getRemark());
			inv.setReferenceDocNumber(gr.getDocNumber());
			inv.setRequiredDate(gr.getRequiredDate());
			inv.setGrId(gr.getId());
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
					line.setRequiredQuantity(grItms.get(i).getRequiredQuantity());
					line.setSacCode(grItms.get(i).getSacCode());
					line.setUom(grItms.get(i).getUom());
					line.setSku(grItms.get(i).getSku());
					line.setWarehouse(grItms.get(i).getWarehouse());
					line.setProductId(grItms.get(i).getProductId());
					line.setTaxCode(grItms.get(i).getTaxCode());
					line.setUnitPrice(grItms.get(i).getUnitPrice());
					line.setTaxTotal(grItms.get(i).getTaxTotal());
					line.setTotal(grItms.get(i).getTotal());
					lineItems.add(line);
				}
			}
			
			inv.setInVoiceLineItems(lineItems);
			
			inv= getListAmount(inv); // Set Amount....Like Tax Total Amt
			
			
		}
		logger.info("inv" + inv);
		inv.setCategory("Item");
		inv = inVoiceRepository.save(inv);
		
		gr.setStatus(EnumStatusUpdate.INVOICE.getStatus());  // Set Partial Complted
		goodsReceiptRepository.save(gr);
		
		return inv;
        }else {
        	return dup_inv;
        }
     
	}
	
	
	
	@Override
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
	
	public Map<String, Integer> prepareMapForProductQunatityGR(InVoice goodReceipt) {
		Map<String, Integer> grMapListData = new LinkedHashMap<>();

		List<InVoice> listinVoice = inVoiceRepository.findByListGrId(goodReceipt.getGrId(),EnumStatusUpdate.REJECTED.getStatus()); // check
																											// Multiple
		grMapListData = getInVoiceRealQunatityList(goodReceipt, grMapListData, listinVoice);
		
		logger.info("grMapListData-->" + grMapListData);

		return grMapListData;
	}
	
	private Map<String, Integer> prepareMapForApprovedProductQunatityGR(InVoice goodReceipt) {
		Map<String, Integer> grMapListData = new LinkedHashMap<>();

		List<InVoice> listinVoice = inVoiceRepository.findByApproveListGrId(goodReceipt.getGrId(),EnumStatusUpdate.APPROVEED.getStatus()); // check
																											// Multiple
																											// Quantity
		grMapListData = getInVoiceRealQunatityList(goodReceipt, grMapListData, listinVoice);
		
		logger.info("grMapListData-->" + grMapListData);

		return grMapListData;
	}

	private Map<String, Integer> getInVoiceRealQunatityList(InVoice goodReceipt, Map<String, Integer> grMapListData,
			List<InVoice> listinVoice) {
		for (int i = 0; i < listinVoice.size(); i++) {
			InVoice inVoiceObj = listinVoice.get(i);
			List<InVoiceLineItems> inVoiceLineItems = inVoiceObj.getInVoiceLineItems();
			for (int j = 0; j < inVoiceLineItems.size(); j++) {
				InVoiceLineItems grlist = inVoiceLineItems.get(j);
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
		return inVoiceRepository.findByIsActive(true);
	}

	@Override
	public InVoice findById(int id) {
		return inVoiceRepository.findById(id).get();
	}
	
	
	
	
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
				InVoiceLineItems grelist = listItems.get(i);
				if(grelist.getUnitPrice()!=null) {
				addTaxAmt += UnitPriceListItems.getTaxAmt(grelist.getRequiredQuantity(),grelist.getUnitPrice(),grelist.getTaxCode());
				addAmt +=UnitPriceListItems.getTotalAmt(grelist.getRequiredQuantity(),grelist.getUnitPrice(), grelist.getTaxCode());
				grelist.setTaxTotal(""+UnitPriceListItems.getTaxAmt(grelist.getRequiredQuantity(),grelist.getUnitPrice(),grelist.getTaxCode()));
				grelist.setTotal(""+UnitPriceListItems.getTotalAmt(grelist.getRequiredQuantity(),grelist.getUnitPrice(), grelist.getTaxCode()));
				} 
				addListItems.add(grelist);
			  }
			inVoice.setInVoiceLineItems(addListItems);
			}
			
		
		inVoice.setTotalBeforeDisAmt(addAmt);
		inVoice.setTaxAmt(""+addTaxAmt);
		logger.info("addAmt-->" + addAmt); 
		logger.info("inVoice.getTotalDiscount()-->" + inVoice.getTotalDiscount());
		logger.info("inVoice.getFreight()-->" + inVoice.getFreight());
		Double total_amt=0.0;
		if(inVoice.getTotalDiscount()==null) inVoice.setTotalDiscount(0.0);
		if(inVoice.getFreight()==null) inVoice.setFreight(0);
			
			
		 total_amt= UnitPriceListItems.getTotalPaymentAmt(addAmt, inVoice.getTotalDiscount(), inVoice.getFreight());
		inVoice.setAmtRounding(UnitPriceListItems.getRoundingValue(total_amt));
		inVoice.setTotalPayment(total_amt);
	
	return inVoice;
	}
	
	
	
	
	
	
	
	
	
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
	
	
	@Override
	public Boolean checkQuantityPoGr(PurchaseOrder purchaseOrder) {
		List<InVoice> listinVoice = inVoiceRepository
				.findByListGrId(purchaseOrder.getId(),EnumStatusUpdate.REJECTED.getStatus());
		logger.info("listinVoice-->" +listinVoice);
		
		/*String status = setStatusOfPurchaseOrder(listinVoice.get(0));
		logger.info("status-->" +status); //Test the Status if you want  
*/		
		Integer prQunatity = getListPoQuantityCount(purchaseOrder);
		Integer grQunatity = getListGRQunatityCount(listinVoice);
		
		if(prQunatity > grQunatity)
			return true;
		else
			return false;
	}

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
		logger.info("grQunatity===>" +grQunatity);
		return grQunatity;
	}

	

	
	private Integer getListGoodsProductCount(List<InVoice> listinVoice, String category) {
		Integer qunatity = 0;
		logger.info("category===>" +category);
	
		for (int i = 0; i < listinVoice.size(); i++) {
			InVoice inVoiceObj = listinVoice.get(i);
			List<InVoiceLineItems> inVoiceLineItems = inVoiceObj.getInVoiceLineItems();
			for (int j = 0; j < inVoiceLineItems.size(); j++) {
				InVoiceLineItems grlist = inVoiceLineItems.get(j);
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
