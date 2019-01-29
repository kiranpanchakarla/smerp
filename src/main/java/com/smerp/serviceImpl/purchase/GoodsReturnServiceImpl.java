package com.smerp.serviceImpl.purchase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.smerp.model.inventory.GoodsReturn;
import com.smerp.model.inventory.GoodsReturnLineItems;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.PurchaseOrderLineItems;
import com.smerp.repository.purchase.GoodsReceiptRepository;
import com.smerp.repository.purchase.GoodsReturnLineItemsRepository;
import com.smerp.repository.purchase.GoodsReturnRepository;
import com.smerp.repository.purchase.PurchaseOrderRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.VendorAddressService;
import com.smerp.service.inventory.VendorsContactDetailsService;
import com.smerp.service.purchase.GoodsReceiptService;
import com.smerp.service.purchase.GoodsReturnService;
import com.smerp.service.purchase.PurchaseOrderService;
import com.smerp.util.EmailGenerator;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.RequestContext;
import com.smerp.util.UnitPriceListItems;

@Service
@Transactional
public class GoodsReturnServiceImpl  implements GoodsReturnService {

	private static final Logger logger = LogManager.getLogger(RequestForQuotationServiceImpl.class);

	@Autowired
	GoodsReturnRepository goodsReturnRepository;

	@Autowired
	GoodsReturnLineItemsRepository goodsReturnLineItemsRepository;

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
	GoodsReceiptService goodsReceiptService;
	
	
	@Autowired
	GoodsReceiptRepository goodsReceiptRepository;

	
	
	
	
	@Autowired
	EmailGenerator emailGenerator;

	@Override
	public GoodsReturn save(GoodsReturn goodsReturn) {
		goodsReturn.setCategory("Item");
		switch (goodsReturn.getStatusType()) {
		case "DR":
			goodsReturn.setStatus(EnumStatusUpdate.DRAFT.getStatus());
			break;
		case "SA":
			goodsReturn.setStatus(EnumStatusUpdate.OPEN.getStatus());
			break;

		case "RE":
			goodsReturn.setStatus(EnumStatusUpdate.REJECTED.getStatus());
			break;
		case "APP":
			goodsReturn.setStatus(EnumStatusUpdate.APPROVEED.getStatus());
			break;
		case "CA":
			goodsReturn.setStatus(EnumStatusUpdate.CANCELED.getStatus());
			break;
		default:
			logger.info("Type Not Matched:" + goodsReturn.getStatusType());
			break;
		}
		List<GoodsReturnLineItems> listItems = goodsReturn.getGoodsReturnLineItems();
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				if (listItems.get(i).getProdouctNumber() == null && listItems.get(i).getSacCode() == null) {
					listItems.remove(i);
				}
			}
			goodsReturn.setGoodsReturnLineItems(listItems);
		}
		if (goodsReturn.getId() != null) { // delete List Of Items.
			GoodsReturn goodsReturnObj = goodsReturnRepository
					.findById(goodsReturn.getId()).get();
			List<GoodsReturnLineItems> requestLists = goodsReturnObj.getGoodsReturnLineItems();
			
			if(requestLists.size()>0 && requestLists!=null) {
				goodsReturnLineItemsRepository.deleteAll(requestLists);  // Delete All list items 
				}
			
			
			if(goodsReturn.getGrId()==null) {  // if RfqId null remove list items 
			
			
			}else {
				 logger.info("Goods Return Data -->" +goodsReturn);
			}
		}
         logger.info("goodsReturn -->" +goodsReturn);
		Vendor vendor = vendorService.findById(goodsReturn.getVendor().getId());
		VendorAddress vendorShippingAddress = vendorAddressService.findById(goodsReturn.getVendorShippingAddress().getId());
		VendorAddress vendorPayAddress = vendorAddressService.findById(goodsReturn.getVendorPayTypeAddress().getId());
		
		VendorsContactDetails vendorsContactDetails =vendorsContactDetailsService.findById(goodsReturn.getVendorContactDetails().getId());

		goodsReturn.setVendor(vendor);
		goodsReturn.setVendorContactDetails(vendorsContactDetails);
		goodsReturn.setVendorShippingAddress(vendorShippingAddress);
		goodsReturn.setVendorPayTypeAddress(vendorPayAddress);
		
		if(goodsReturn.getStatusType()!=null &&  goodsReturn.getStatusType().equals("APP")) {
			try {
			   	goodsReturn =getListAmount(goodsReturn);
    			 RequestContext.initialize();
    		     RequestContext.get().getConfigMap().put("mail.template", "goodsReturnEmail.ftl");  //Sending Email
    		     emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendGoodsReturnEmail(goodsReturn);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		
			GoodsReceipt updategr = updateGoodsReceiptQunatity(goodsReturn.getGrId(),goodsReturn);
		
			if(updategr.getPoId()!=null) {
				PurchaseOrder po = purchaseOrderService.findById(updategr.getPoId());
				
				String status = goodsReceiptService.setStatusOfPurchaseOrder(updategr);
				po.setStatus(status);
				purchaseOrderRepository.save(po);
				}
			
		}
		else if(goodsReturn.getStatusType()!=null &&  goodsReturn.getStatusType().equals("RE")) {
			try {
			   	goodsReturn =getListAmount(goodsReturn);
    			 RequestContext.initialize();
    		     RequestContext.get().getConfigMap().put("mail.template", "goodsReturnEmail.ftl");  //Sending Email
    		     emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendGoodsReturnRejectEmail(goodsReturn);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		}
	
		
		
		
		
		// update goods Recipt Qunatity.
		// change status PO
		
		
		/*GoodsReceipt updategr = updateQunatity(goodsReturn.getGrId(),goodsReturn);
		
		if(updategr.getPoId()!=null) {
		PurchaseOrder po = purchaseOrderService.findById(updategr.getPoId());
		
		String status = goodsReceiptService.setStatusOfPurchaseOrder(updategr);
		po.setStatus(status);
		purchaseOrderRepository.save(po);
		}*/
		
		goodsReturn= goodsReturnRepository.save(goodsReturn);
		
		return goodsReturn; 
		 
	}

	@Override
	public GoodsReturn saveGRE(String grId) {

		GoodsReturn gre = new GoodsReturn();
		GoodsReceipt gr = goodsReceiptService.findById((Integer.parseInt(grId)));
		logger.info("grId" + grId);
		/*GoodsReturn dup_gre =goodsReturnRepository.findByPoId(po.getId());  // check PO exist in  GR
        if(dup_gre==null) { */
		GoodsReturn greDetails = findLastDocumentNumber();
		if (greDetails != null && greDetails.getDocNumber() != null) {
			gre.setDocNumber(GenerateDocNumber.documentNumberGeneration(greDetails.getDocNumber()));
		} else {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDateTime now = LocalDateTime.now();
		gre.setDocNumber(GenerateDocNumber.documentNumberGeneration("GRE"+(String)dtf.format(now) +"0"));
		}

		if (gr != null) {
			gre.setDocumentDate(gr.getDocumentDate());
			gre.setStatus(EnumStatusUpdate.OPEN.getStatus());
			gre.setPostingDate(gr.getPostingDate());
			gre.setCategory(gr.getCategory());
			gre.setRemark(gr.getRemark());
			gre.setReferenceDocNumber(gr.getDocNumber());
			gre.setRequiredDate(gr.getRequiredDate());
			gre.setGrId(gr.getId());
			gre.setIsActive(true);
			gre.setVendor(gr.getVendor());
			gre.setVendorContactDetails(gr.getVendorContactDetails());
			gre.setVendorPayTypeAddress(gr.getVendorPayTypeAddress());
			gre.setVendorShippingAddress(gr.getVendorShippingAddress());
			
		
			gre.setFreight(gr.getFreight());
			gre.setTotalDiscount(gr.getTotalDiscount());
		
			/*List<GoodsReturn> listGoodsReturn = goodsReturnRepository
					.findByListPoId(po.getId());  // check Multiple  Quantity
			Integer greQunatity =0;*/
			
			List<GoodsReceiptLineItems> grItms = gr.getGoodsReceiptLineItems();
			
			List<GoodsReturnLineItems> lineItems =new ArrayList<GoodsReturnLineItems>();
			if (grItms != null) {
				for (int i = 0; i < grItms.size(); i++) {
					GoodsReturnLineItems line = new GoodsReturnLineItems();
					line.setProdouctNumber(grItms.get(i).getProdouctNumber());
					line.setProductGroup(grItms.get(i).getProductGroup());
					line.setDescription(grItms.get(i).getDescription());
					line.setHsn(grItms.get(i).getHsn());
					line.setSku(grItms.get(i).getSku());
					
				/*	if(poItms.get(i).getProdouctNumber()!=null) {
						 greQunatity = getListGoodsProductCount(listGoodsReturn,  poItms.get(i).getProdouctNumber());
					}else if(poItms.get(i).getSacCode()!=null) {
						 greQunatity = getListGoodsProductCount(listGoodsReturn,  poItms.get(i).getSacCode());
					}*/
					
				
					//line.setRequiredQuantity(poItms.get(i).getRequiredQuantity() - greQunatity);
					line.setRequiredQuantity(0);
					
					line.setSacCode(grItms.get(i).getSacCode());
					line.setUom(grItms.get(i).getUom());
					line.setWarehouse(grItms.get(i).getWarehouse());
					line.setProductId(grItms.get(i).getProductId());
					line.setTaxCode(grItms.get(i).getTaxCode());
					line.setUnitPrice(grItms.get(i).getUnitPrice());
					
					line.setTaxTotal(grItms.get(i).getTaxTotal());
					
					line.setTotal(grItms.get(i).getTotal());
					
					lineItems.add(line);
				}
			}
			
			gre.setGoodsReturnLineItems(lineItems);
			
			gre= getListAmount(gre); // Set Amount....Like Tax Total Amt
			
			
		}
		logger.info("gre" + gre);
		gre.setCategory("Item");
		gre = goodsReturnRepository.save(gre);
		
	/*	gr.setStatus(EnumStatusUpdate.PARTIALLY_RECEIVED.getStatus());  // Set Partial Complted
		goodsReceiptRepository.save(gr);*/
		
		return gre;
     
	}
	
	
	
	
	public String  setStatusOfPurchaseOrder(GoodsReturn goodsReturn) {
		logger.info("set Status-->");
		String status="";
		//PurchaseOrder purchaseOrder = purchaseOrderService.findById(goodsReturn.getPoId());
		PurchaseOrder purchaseOrder =null;
		List<GoodsReturnLineItems> greListItems = goodsReturn.getGoodsReturnLineItems();
		List<PurchaseOrderLineItems> poListItems = purchaseOrder.getPurchaseOrderlineItems();
		
		Map<String, Integer> poListData = prepareMapForProductQunatityPR(purchaseOrder, poListItems); // get po list
		
		Map<String, Integer> greListData = prepareMapForProductQunatityGR(goodsReturn);   // get !Rejected  list
		
		Map<String, Integer> greApproveListData = prepareMapForApprovedProductQunatityGR(goodsReturn);  // get Approved list
	  
		if(greListData.size()>0) {   // check gre  when !Rejected list
        if(poListData.keySet().equals( greListData.keySet())) {  // check keys pr and gre  when !Rejected list
        	    List<Integer> values1 = new ArrayList<Integer>(poListData.values());
        	    List<Integer> values2 = new ArrayList<Integer>(greListData.values());
        	    Collections.sort(values1);
        	    Collections.sort(values2);
        	  if(values1.equals(values2)) {  // check values  pr and gre
        		  if(poListData.keySet().equals( greApproveListData.keySet())) {  // check keys pr and gre  when Approved list
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
		}else {  // No gre list
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
	
	private Map<String, Integer> prepareMapForProductQunatityGR(GoodsReturn goodReceipt) {
		Map<String, Integer> greMapListData = new LinkedHashMap<>();

		List<GoodsReturn> listGoodsReturn = goodsReturnRepository.findByListgrId(goodReceipt.getGrId(),EnumStatusUpdate.REJECTED.getStatus()); // check
																											// Multiple
		greMapListData = getGoodsReturnRealQunatityList(goodReceipt, greMapListData, listGoodsReturn);
		
		logger.info("greMapListData-->" + greMapListData);

		return greMapListData;
	}
	
	private Map<String, Integer> prepareMapForApprovedProductQunatityGR(GoodsReturn goodReceipt) {
		Map<String, Integer> greMapListData = new LinkedHashMap<>();

		List<GoodsReturn> listGoodsReturn = goodsReturnRepository.findByListgrId(goodReceipt.getGrId(),EnumStatusUpdate.APPROVEED.getStatus()); // check
																											// Multiple
																											// Quantity
		greMapListData = getGoodsReturnRealQunatityList(goodReceipt, greMapListData, listGoodsReturn);
		
		logger.info("greMapListData-->" + greMapListData);

		return greMapListData;
	}

	private Map<String, Integer> getGoodsReturnRealQunatityList(GoodsReturn goodReceipt, Map<String, Integer> greMapListData,
			List<GoodsReturn> listGoodsReturn) {
		for (int i = 0; i < listGoodsReturn.size(); i++) {
			GoodsReturn goodsReturnObj = listGoodsReturn.get(i);
			List<GoodsReturnLineItems> goodsReturnLineItems = goodsReturnObj.getGoodsReturnLineItems();
			for (int j = 0; j < goodsReturnLineItems.size(); j++) {
				GoodsReturnLineItems grelist = goodsReturnLineItems.get(j);
				logger.info("grelist===>" + grelist);
				String key = "";
				
				if( grelist.getProdouctNumber()!=null) {
					key = goodReceipt.getReferenceDocNumber() + "$" + grelist.getProdouctNumber();
					}else {
					key = goodReceipt.getReferenceDocNumber() + "$" + grelist.getSacCode();
					}
				
				if (!greMapListData.containsKey(key)) {

					if (grelist.getProdouctNumber() != null && grelist.getRequiredQuantity() != 0) {
						greMapListData.put(key,
								grelist.getRequiredQuantity());
					} else if (grelist.getSacCode() != null && grelist.getRequiredQuantity() != 0) {
						greMapListData.put(key,
								grelist.getRequiredQuantity());
					}
				} else {
					Integer tempQunatity = greMapListData.get(key);

					if (grelist.getProdouctNumber() != null && grelist.getRequiredQuantity() != 0) {
						greMapListData.put(key,
								tempQunatity + grelist.getRequiredQuantity());
					} else if (grelist.getSacCode() != null && grelist.getRequiredQuantity() != 0) {
						greMapListData.put(key,
								tempQunatity + grelist.getRequiredQuantity());
					}
				}

			}
		}
		
		return greMapListData;
	}
	
	
	
	
	
	@Override
	public GoodsReturn saveCancelStage(String rfqId) {
		GoodsReturn gre = goodsReturnRepository.findById(Integer.parseInt(rfqId)).get();
		gre.setStatus(EnumStatusUpdate.CANCELED.getStatus());
		goodsReturnRepository.save(gre);
		return gre;
		
	}

	@Override
	public GoodsReturn findLastDocumentNumber() {
		return goodsReturnRepository.findTopByOrderByIdDesc();
	}

	@Override
	public List<GoodsReturn> findAll() {
		return goodsReturnRepository.findAll();
	}

	@Override
	public List<GoodsReturn> findByIsActive() {
		return goodsReturnRepository.findByIsActive(true);
	}

	@Override
	public GoodsReturn findById(int id) {
		return goodsReturnRepository.findById(id).get();
	}
	
	
	
	
	@Override
	public GoodsReturn getListAmount(GoodsReturn goodsReturn) {
		logger.info("getListAmount-->");
		List<GoodsReturnLineItems> listItems = goodsReturn.getGoodsReturnLineItems();
		
		List<GoodsReturnLineItems> addListItems = new ArrayList<GoodsReturnLineItems>();
		
		GoodsReceipt gr = null;
		List<GoodsReceiptLineItems> grItms =null;
		 List<GoodsReturn> listGoodsReturn =null;
		if(goodsReturn.getGrId()!=null) {
			gr = goodsReceiptService.findById(goodsReturn.getGrId());
			grItms = gr.getGoodsReceiptLineItems();
		listGoodsReturn = goodsReturnRepository
					.findByListgrId(gr.getId(),EnumStatusUpdate.REJECTED.getStatus());  // check Multiple  Quantity
		}
		
		
		Double addAmt=0.0;
		Double addTaxAmt=0.0;
		Integer greQunatity=0;
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				GoodsReturnLineItems grelist = listItems.get(i);
				if(grelist.getUnitPrice()!=null) {
				addTaxAmt += UnitPriceListItems.getTaxAmt(grelist.getRequiredQuantity(),grelist.getUnitPrice(),grelist.getTaxCode());
				addAmt +=UnitPriceListItems.getTotalAmt(grelist.getRequiredQuantity(),grelist.getUnitPrice(), grelist.getTaxCode());
				grelist.setTaxTotal(""+UnitPriceListItems.getTaxAmt(grelist.getRequiredQuantity(),grelist.getUnitPrice(),grelist.getTaxCode()));
				grelist.setTotal(""+UnitPriceListItems.getTotalAmt(grelist.getRequiredQuantity(),grelist.getUnitPrice(), grelist.getTaxCode()));
				
				if(goodsReturn.getGrId()!=null) {
				if(grItms.get(i).getProdouctNumber()!=null ) {
					 greQunatity = getListGoodsProductCount(listGoodsReturn,  grItms.get(i).getProdouctNumber());
				}else if(grItms.get(i).getSacCode()!=null ) {
					 greQunatity = getListGoodsProductCount(listGoodsReturn,  grItms.get(i).getSacCode());
				} }
				
				logger.info("GRItms.get(i).getRequiredQuantity()-->" + grItms.get(i).getRequiredQuantity());
				logger.info("greQunatity-->" + greQunatity);
				if(goodsReturn.getStatus().equals(EnumStatusUpdate.APPROVEED.getStatus())) {
					grelist.setTempRequiredQuantity(grItms.get(i).getRequiredQuantity() - greQunatity);
					}else 
					{
						grelist.setTempRequiredQuantity(grItms.get(i).getRequiredQuantity());
					}
				
				}else {
				grelist.setTaxTotal("");
				grelist.setTotal("");	
				}
				
				addListItems.add(grelist);
				
			}
			goodsReturn.setGoodsReturnLineItems(addListItems);
		}
		goodsReturn.setTotalBeforeDisAmt(addAmt);
		goodsReturn.setTaxAmt(""+addTaxAmt);
		logger.info("addAmt-->" + addAmt); 
		logger.info("goodsReturn.getTotalDiscount()-->" + goodsReturn.getTotalDiscount());
		logger.info("goodsReturn.getFreight()-->" + goodsReturn.getFreight());
		Double total_amt=0.0;
		if(goodsReturn.getTotalDiscount()==null) goodsReturn.setTotalDiscount(0.0);
		if(goodsReturn.getFreight()==null) goodsReturn.setFreight(0);
			
			
		 total_amt= UnitPriceListItems.getTotalPaymentAmt(addAmt, goodsReturn.getTotalDiscount(), goodsReturn.getFreight());
		goodsReturn.setAmtRounding(UnitPriceListItems.getRoundingValue(total_amt));
		goodsReturn.setTotalPayment(total_amt);
	
	return goodsReturn;
	}
	
	
	
	
	
	
	
	
	
	/*@Override
	public String checkStatusPoGr(PurchaseOrder purchaseOrder) {
	    String status ="";
		GoodsReturn  goodsReturn = goodsReturnRepository.findByPoId(purchaseOrder.getId());
		if(goodsReturn!=null) {
		List<PurchaseOrderLineItems> poListItems = purchaseOrder.getPurchaseOrderlineItems();
		
		Map<String, Integer> poListData = prepareMapForProductQunatityPR(purchaseOrder, poListItems);
		
		Map<String, Integer> greListData = prepareMapForProductQunatityGR(goodsReturn);
		
      
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
	
	
	@Override
	public Boolean checkQuantityGr(GoodsReceipt goodsReceipt) {
		List<GoodsReturn> listGoodsReturn = goodsReturnRepository
				.findByListgrId(goodsReceipt.getId(),EnumStatusUpdate.REJECTED.getStatus());
		logger.info("listGoodsReturn-->" +listGoodsReturn);
		
	/*	String status = setStatusOfPurchaseOrder(listGoodsReturn.get(0));
		logger.info("status-->" +status); //Test the Status if you want  */
		
		Integer grQunatity = getListGRQuantityCount(goodsReceipt);
		Integer greQunatity = getListGREQunatityCount(listGoodsReturn);
		
		if(grQunatity > greQunatity)
			return true;
		else
			return false;
	}

	private Integer getListGREQunatityCount(List<GoodsReturn> listGoodsReturn) {
	
		Integer greQunatity=0;
		if (listGoodsReturn != null) {
			for (int i = 0; i < listGoodsReturn.size(); i++) {
				GoodsReturn  goodsReturn = listGoodsReturn.get(i);
				logger.info("goodsReturn-->" +goodsReturn);
				List<GoodsReturnLineItems> listItems = goodsReturn.getGoodsReturnLineItems();
			for (int j = 0; j < listItems.size(); j++) {
				GoodsReturnLineItems grelist = listItems.get(j);
				if(grelist.getRequiredQuantity()!=null) {
					greQunatity +=  grelist.getRequiredQuantity();
				}
			}
		  }
		}
		logger.info("greQunatity===>" +greQunatity);
		return greQunatity;
	}

	

	
	private Integer getListGoodsProductCount(List<GoodsReturn> listGoodsReturn, String category) {
		Integer qunatity = 0;
		logger.info("category===>" +category);
	
		for (int i = 0; i < listGoodsReturn.size(); i++) {
			GoodsReturn goodsReturnObj = listGoodsReturn.get(i);
			List<GoodsReturnLineItems> goodsReturnLineItems = goodsReturnObj.getGoodsReturnLineItems();
			for (int j = 0; j < goodsReturnLineItems.size(); j++) {
				GoodsReturnLineItems grelist = goodsReturnLineItems.get(j);
				logger.info("grelist===>" +grelist);
				if (grelist.getRequiredQuantity() != null) {
					if (category.equals(grelist.getProdouctNumber()) || category.equals(grelist.getSacCode()))
						qunatity += grelist.getRequiredQuantity();
					    logger.info("greQunatity===>" +qunatity);
				}
			}
		}
		
		return qunatity;
	}
	
	
	
	
	
	
	
	private Integer getListGRQuantityCount(GoodsReceipt goodsReceipt) {
		List<GoodsReceiptLineItems> listItems = goodsReceipt.getGoodsReceiptLineItems();
		Integer grQunatity=0;
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				GoodsReceiptLineItems grlist = listItems.get(i);
				if(grlist.getRequiredQuantity()!=null) {
					grQunatity += grlist.getRequiredQuantity();
				}
			}
		}
		
		logger.info("grQunatity===>" +grQunatity);
		return grQunatity;
	}
	
	
	
	
	
	
	
	
	
	@Override
	public GoodsReturn delete(int id) {
		GoodsReturn goodsReturn = goodsReturnRepository.findById(id).get();
		goodsReturn.setIsActive(false);
		goodsReturnRepository.save(goodsReturn);
		return goodsReturn;
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
			
			
public GoodsReceipt updateGoodsReceiptQunatity(Integer grId,GoodsReturn goodsReturnObj) {
				
				GoodsReceipt goodsReceiptObj = goodsReceiptRepository.findById(grId).get();
				
				List<GoodsReceiptLineItems> goodsReceipLists = goodsReceiptObj.getGoodsReceiptLineItems();  // Total Qunatity List.
				
				List<GoodsReturnLineItems> goodsReturnLists = goodsReturnObj.getGoodsReturnLineItems();    // Return Qunatity List.
				
				List<GoodsReceiptLineItems> goodsReceiptRemLists  = new ArrayList<GoodsReceiptLineItems>();
				
				if (goodsReceipLists != null) {
					for (int i = 0; i < goodsReceipLists.size(); i++) {
						GoodsReturnLineItems grelist = goodsReturnLists.get(i);
						GoodsReceiptLineItems grlist = new GoodsReceiptLineItems();
						
							 grlist = goodsReceipLists.get(i);
							if (grelist.getProdouctNumber() != null  && grlist.getProdouctNumber()!=null && ( grlist.getProdouctNumber().equals(grelist.getProdouctNumber()) ||  grlist.getSacCode().equals(grelist.getSacCode()) ) ) {
								
								grlist.setRequiredQuantity(grlist.getRequiredQuantity()-grelist.getRequiredQuantity());
							
						}
						goodsReceiptRemLists.add(grlist);
					}
				}
				goodsReceiptObj.setGoodsReceiptLineItems(goodsReceiptRemLists);
				goodsReceiptObj.setStatus(EnumStatusUpdate.GOODS_RETURN.getStatus());
				goodsReceiptObj= goodsReceiptRepository.save(goodsReceiptObj);
				return goodsReceiptObj;
			}

}



