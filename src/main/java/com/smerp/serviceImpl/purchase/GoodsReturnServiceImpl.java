package com.smerp.serviceImpl.purchase;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.User;
import com.smerp.model.admin.Vendor;
import com.smerp.model.admin.VendorAddress;
import com.smerp.model.admin.VendorsContactDetails;
import com.smerp.model.inventory.GoodsReturn;
import com.smerp.model.inventory.CreditMemoLineItems;
import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.GoodsReceiptLineItems;
import com.smerp.model.inventory.GoodsReturn;
import com.smerp.model.inventory.GoodsReturnLineItems;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.PurchaseOrderLineItems;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.model.search.SearchFilter;
import com.smerp.repository.purchase.GoodsReceiptRepository;
import com.smerp.repository.purchase.GoodsReturnLineItemsRepository;
import com.smerp.repository.purchase.GoodsReturnRepository;
import com.smerp.repository.purchase.PurchaseOrderRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.VendorAddressService;
import com.smerp.service.inventory.VendorsContactDetailsService;
import com.smerp.service.master.PlantService;
import com.smerp.service.purchase.GoodsReceiptService;
import com.smerp.service.purchase.GoodsReturnService;
import com.smerp.service.purchase.PurchaseOrderService;
import com.smerp.util.CheckUserPermissionUtil;
import com.smerp.util.DocNumberGenerator;
import com.smerp.util.EmailGenerator;
import com.smerp.util.EnumSearchFilter;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.GetSearchFilterResult;
import com.smerp.util.RequestContext;
import com.smerp.util.UnitPriceListItems;

@Service
@Transactional
public class GoodsReturnServiceImpl  implements GoodsReturnService {

	private static final Logger logger = LogManager.getLogger(GoodsReturnServiceImpl.class);

	@Autowired
	private GoodsReturnRepository goodsReturnRepository;

	@Autowired
	private GoodsReturnLineItemsRepository goodsReturnLineItemsRepository;

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
	private GoodsReceiptService goodsReceiptService;
		
	@Autowired
	private GoodsReceiptRepository goodsReceiptRepository;
	
	@PersistenceContext    
	private EntityManager entityManager;
		
	@Autowired
	private EmailGenerator emailGenerator;
	
	@Autowired
	private DocNumberGenerator docNumberGenerator;
	
	@Autowired
	private PlantService plantService;
	
	@Autowired
	private GetSearchFilterResult getSearchFilterResult;
	
	@Autowired
	private CheckUserPermissionUtil checkUserPermissionUtil;

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
					i--;
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
			
			
			
			if(goodsReturn.getGrId()!=null) {
			GoodsReceipt gr = goodsReturn.getGrId();
			
			Vendor vendor = vendorService.findById(gr.getVendor().getId());
			VendorAddress vendorShippingAddress = vendorAddressService.findById(gr.getVendorShippingAddress().getId());
			VendorAddress vendorPayAddress = vendorAddressService.findById(gr.getVendorPayTypeAddress().getId());
			VendorsContactDetails vendorsContactDetails =vendorsContactDetailsService.findById(gr.getVendorContactDetails().getId());

			goodsReturn.setVendor(vendor);
			goodsReturn.setVendorContactDetails(vendorsContactDetails);
			goodsReturn.setVendorShippingAddress(vendorShippingAddress);
			goodsReturn.setVendorPayTypeAddress(vendorPayAddress);
			
			goodsReturn.setPlant(goodsReturn.getGrId().getPlant());
			}
		}
		/*Vendor vendor = vendorService.findById(goodsReturn.getVendor().getId());
		VendorAddress vendorShippingAddress = vendorAddressService.findById(goodsReturn.getVendorShippingAddress().getId());
		VendorAddress vendorPayAddress = vendorAddressService.findById(goodsReturn.getVendorPayTypeAddress().getId());
		
		VendorsContactDetails vendorsContactDetails =vendorsContactDetailsService.findById(goodsReturn.getVendorContactDetails().getId());

		goodsReturn.setVendor(vendor);
		goodsReturn.setVendorContactDetails(vendorsContactDetails);
		goodsReturn.setVendorShippingAddress(vendorShippingAddress);
		goodsReturn.setVendorPayTypeAddress(vendorPayAddress);*/
		
         if(goodsReturn.getStatus()!=null &&  !goodsReturn.getStatus().equals(EnumStatusUpdate.DRAFT.getStatus())) {
         try {
			   	goodsReturn =getListAmount(goodsReturn);
			   	 if(goodsReturn.getId()!=null) {
			   		GoodsReturn goodsReturnObj = goodsReturnRepository.findById(goodsReturn.getId()).get();
					logger.info(goodsReturnObj.getCreatedBy().getUserEmail());
					goodsReturn.setCreatedBy(goodsReturnObj.getCreatedBy());
				 } 
 			 RequestContext.initialize();
 		     RequestContext.get().getConfigMap().put("mail.template", "goodsReturnEmail.ftl");  //Sending Email
 		     emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendGoodsReturnEmail(goodsReturn);
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
     }  
         
/*  Multi Level Approved .. Start*/
		 
		 if(goodsReturn.getPlant().getId()==2) {   //FOR Yelamanchili
			 
			 boolean checkMultiApp = checkUserPermissionUtil.checkMultiAppPermission();
			 
			 
		 if(goodsReturn.getStatus().equals(EnumStatusUpdate.APPROVEED.getStatus()) ) {  
			 User user = checkUserPermissionUtil.getUser();
			 
			 if(checkMultiApp) { // Final Approved
				 if(goodsReturn.getFirstApproveId()!=null) {
				    goodsReturn.setSecondApproveId(user.getUserId());
				    goodsReturn.setSecondLevelEnable(true);}
				 else {
					 goodsReturn.setFirstApproveId(user.getUserId());
					 goodsReturn.setSecondApproveId(user.getUserId());
					 goodsReturn.setSecondLevelEnable(true);
				 }
				 goodsReturn.setStatus(EnumStatusUpdate.APPROVEED.getStatus());
			 }else {            // First Approved
				 goodsReturn.setFirstApproveId(user.getUserId());
				 goodsReturn.setStatus(EnumStatusUpdate.PARTIALLY_APPROVEED.getStatus());
				 goodsReturn.setSecondLevelEnable(true);
			 }
			 
		 }else {
			 if(checkMultiApp) {
				 goodsReturn.setSecondLevelEnable(true); 
			 }
		 }
		 
		 }else {
			 goodsReturn.setSecondLevelEnable(true); 
		 }
		 
		 /*  Multi Level Approved .. End*/
         
         goodsReturn= goodsReturnRepository.save(goodsReturn);
         
		if(goodsReturn.getStatus()!=null &&  !goodsReturn.getStatus().equals(EnumStatusUpdate.DRAFT.getStatus())) {
			 
		
			/*GoodsReceipt updategr = updateGoodsReceiptQunatity(goodsReturn.getGrId(),goodsReturn);*/  
		
			/*if(updategr.getPoId()!=null) {
				PurchaseOrder po = purchaseOrderService.findById(updategr.getPoId());
				
				String status = goodsReceiptService.setStatusOfPurchaseOrder(updategr);
				po.setStatus(status);
				purchaseOrderRepository.save(po);
				}*/
			
			if(goodsReturn.getGrId()!=null  && goodsReturn.getStatus().equals(EnumStatusUpdate.APPROVEED.getStatus())) {
			GoodsReceipt goodsReceipt =  goodsReturn.getGrId();
			PurchaseOrder purchaseOrder = goodsReceiptService.setStatusOfPurchaseOrder(goodsReceipt);  // change status PO
			
			goodsReceipt = goodsReceiptService.setStatusOfGoodsReceipt(goodsReceipt);
			
			}
			
		}
		return goodsReturn; 
		 
	}

	@Override
	public GoodsReturn saveGRE(String grId) {

		GoodsReturn gre = new GoodsReturn();
		GoodsReceipt gr = goodsReceiptService.findById((Integer.parseInt(grId)));
		logger.info("grId" + grId);
		/*GoodsReturn dup_gre =goodsReturnRepository.findByPoId(po.getId());  // check PO exist in  GR
        if(dup_gre==null) { */
		Integer count = docNumberGenerator.getDocCountByDocType(EnumStatusUpdate.GRE.getStatus());
		GoodsReturn greDetails = findLastDocumentNumber();
		if (greDetails != null && greDetails.getDocNumber() != null) {
			gre.setDocNumber(GenerateDocNumber.documentNumberGeneration(greDetails.getDocNumber(),count));
		} else {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDateTime now = LocalDateTime.now();
		gre.setDocNumber(GenerateDocNumber.documentNumberGeneration("GRE"+(String)dtf.format(now) +"0",count));
		}

		if (gr != null) {
			gre.setDocumentDate(gr.getDocumentDate());
			gre.setStatus(EnumStatusUpdate.OPEN.getStatus());
			gre.setPostingDate(gr.getPostingDate());
			gre.setCategory(gr.getCategory());
			gre.setRemark(gr.getRemark());
			gre.setPlant(gr.getPlant());
			gre.setDeliverTo(gr.getDeliverTo());
			gre.setReferenceDocNumber(gr.getDocNumber());
			gre.setRequiredDate(gr.getRequiredDate());
			gre.setGrId(gr);
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
			
			gre.setGoodsReturnLineItems(lineItems);
			
			gre= getListAmount(gre); // Set Amount....Like Tax Total Amt
			
			
		}
		
		
		
		/*  Multi Level Approved .. Start*/
		 
		 if(gre.getPlant().getId()==2) {   //FOR Yelamanchili
			 
			 boolean checkMultiApp = checkUserPermissionUtil.checkMultiAppPermission();
			 
			 
		/* if(gre.getStatus().equals(EnumStatusUpdate.OPEN.getStatus()) ) {  
			 User user = checkUserPermissionUtil.getUser();
			 
			 if(checkMultiApp) { // Final Approved
				 if(gre.getFirstApproveId()!=null) {
				    }
				 else {
					 gre.setFirstApproveId(user.getUserId());
					 gre.setSecondApproveId(user.getUserId());
					 gre.setSecondLevelEnable(true);
				 }
				  
			 }else {            // First Approved
				 gre.setFirstApproveId(user.getUserId());
				 gre.setSecondApproveId(user.getUserId());
				 gre.setSecondLevelEnable(true);
			 }
			 
		 }*/  {
			 if(checkMultiApp) {
				 gre.setSecondLevelEnable(true); 
			 }
		 }
		 
		 }else {
			 gre.setSecondLevelEnable(true); 
		 }
		 
		 /*  Multi Level Approved .. End*/
		gre.setCategory("Item");
		gre = goodsReturnRepository.save(gre);
		
	/*	gr.setStatus(EnumStatusUpdate.PARTIALLY_RECEIVED.getStatus());  // Set Partial Complted
		goodsReceiptRepository.save(gr);*/
		
		return gre;
     
	}
	
	
	
	
	public String  setStatusOfPurchaseOrder(GoodsReturn goodsReturn) {
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
		
		return poListData;
	}
	
	private Map<String, Integer> prepareMapForProductQunatityGR(GoodsReturn goodReceipt) {
		Map<String, Integer> greMapListData = new LinkedHashMap<>();

		List<GoodsReturn> listGoodsReturn = goodsReturnRepository.findByListgrId(goodReceipt.getGrId(),EnumStatusUpdate.REJECTED.getStatus()); // check
																											// Multiple
		greMapListData = getGoodsReturnRealQunatityList(goodReceipt, greMapListData, listGoodsReturn);
		

		return greMapListData;
	}
	
	private Map<String, Integer> prepareMapForApprovedProductQunatityGR(GoodsReturn goodReceipt) {
		Map<String, Integer> greMapListData = new LinkedHashMap<>();

		List<GoodsReturn> listGoodsReturn = goodsReturnRepository.findByListgrId(goodReceipt.getGrId(),EnumStatusUpdate.APPROVEED.getStatus()); // check
																											// Multiple
																											// Quantity
		greMapListData = getGoodsReturnRealQunatityList(goodReceipt, greMapListData, listGoodsReturn);
		

		return greMapListData;
	}

	private Map<String, Integer> getGoodsReturnRealQunatityList(GoodsReturn goodReceipt, Map<String, Integer> greMapListData,
			List<GoodsReturn> listGoodsReturn) {
		for (int i = 0; i < listGoodsReturn.size(); i++) {
			GoodsReturn goodsReturnObj = listGoodsReturn.get(i);
			List<GoodsReturnLineItems> goodsReturnLineItems = goodsReturnObj.getGoodsReturnLineItems();
			for (int j = 0; j < goodsReturnLineItems.size(); j++) {
				GoodsReturnLineItems grelist = goodsReturnLineItems.get(j);
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
		
		Boolean [] secondApp = checkUserPermissionUtil.getMultiAppPermission();
		
		return goodsReturnRepository.findByIsActive(true,plantService.findPlantIds(),secondApp);
	}

	@Override
	public GoodsReturn findById(int id) {
		return goodsReturnRepository.findById(id).get();
	}
	
	
	
	
	@Override
	public GoodsReturn getGoodsReturnById(int id) {
		GoodsReturn goodsReturn = goodsReturnRepository.findById(id).get();
	     
	 	String sqlList= " select product_number,current_quantity,creditmemo_quantity,product_tax,product_cost_tax from vw_goods_received_lineitems_amount where id= " +goodsReturn.getGrId().getId();
	 	String productNumber ="";
	    Query queryList = entityManager.createNativeQuery(sqlList);
	      List<Object[]>    invoiceList = queryList.getResultList();
	        
	    
	     Map<String, Integer> grListData = new LinkedHashMap<>();
	     for(Object[] tuple : invoiceList) {
	         productNumber = tuple[0] == null ? "0" : ( tuple[0]).toString();
	         grListData.put(productNumber, Integer.parseInt(tuple[1].toString()));
	     }
	    
	     List<GoodsReturnLineItems> listItems = goodsReturn.getGoodsReturnLineItems();
	     for (int i = 0; i < listItems.size(); i++) {
	    	 GoodsReturnLineItems invlist = listItems.get(i);
	        
	        for(Map.Entry m:grListData.entrySet()){
	               if(invlist.getProdouctNumber().equals(m.getKey())) {
	                   invlist.setTempRequiredQuantity((Integer)m.getValue());    
	                 }
	        }
	    }
	     goodsReturn.setGoodsReturnLineItems(listItems);
	    return goodsReturn;
	}
	
	@Override
	public GoodsReturn getListAmount(GoodsReturn goodsReturn) {
		List<GoodsReturnLineItems> listItems = goodsReturn.getGoodsReturnLineItems();
		
		List<GoodsReturnLineItems> addListItems = new ArrayList<GoodsReturnLineItems>();
		
		GoodsReceipt gr = null;
		List<GoodsReceiptLineItems> grItms =null;
		 List<GoodsReturn> listGoodsReturn =null;
		if(goodsReturn.getGrId()!=null) {
			gr = goodsReturn.getGrId();
			grItms = gr.getGoodsReceiptLineItems();
		listGoodsReturn = goodsReturnRepository
					.findByListgrId(gr,EnumStatusUpdate.REJECTED.getStatus());  // check Multiple  Quantity
		}
		
		
		Double addAmt=0.0;
		Double addTaxAmt=0.0;
		Integer greQunatity=0;
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				GoodsReturnLineItems grelist = listItems.get(i);
				if(grelist.getRequiredQuantity()!=null && grelist.getUnitPrice()!=null ) {
				addTaxAmt += UnitPriceListItems.getTaxAmt(grelist.getRequiredQuantity(),grelist.getUnitPrice(),grelist.getTaxCode());
				addAmt +=UnitPriceListItems.getTotalINVAmt(grelist.getRequiredQuantity(),grelist.getUnitPrice());
				grelist.setTaxTotal(""+UnitPriceListItems.getTaxAmt(grelist.getRequiredQuantity(),grelist.getUnitPrice(),grelist.getTaxCode()));
				grelist.setTotal(""+UnitPriceListItems.getTotalINVAmt(grelist.getRequiredQuantity(),grelist.getUnitPrice()));
				
				if(goodsReturn.getGrId()!=null) {
				if(grItms.get(i).getProdouctNumber()!=null ) {
					 greQunatity = getListGoodsProductCount(listGoodsReturn,  grItms.get(i).getProdouctNumber());
				}else if(grItms.get(i).getSacCode()!=null ) {
					 greQunatity = getListGoodsProductCount(listGoodsReturn,  grItms.get(i).getSacCode());
				} }
				
				/*if(goodsReturn.getStatus().equals(EnumStatusUpdate.APPROVEED.getStatus())) {
					grelist.setTempRequiredQuantity(grItms.get(i).getRequiredQuantity() - greQunatity);
					}else 
					{
						grelist.setTempRequiredQuantity(grItms.get(i).getRequiredQuantity());
					}*/
				
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
		Double total_amt=0.0;
		Double total_payment = 0.0;
		if(goodsReturn.getTotalDiscount()==null) goodsReturn.setTotalDiscount(0.0);
		if(goodsReturn.getFreight()==null) goodsReturn.setFreight(0.0);
			
			
		 total_amt= UnitPriceListItems.getTotalAmtPayment(addAmt, goodsReturn.getTotalDiscount(), goodsReturn.getFreight(),addTaxAmt);
		 if(goodsReturn.getGrId() != null) {
				total_payment = (double) Math.round(total_amt);
			}else {
				total_payment = goodsReturn.getTotalPayment();
			}
		goodsReturn.setAmtRounding(""+df2.format(total_amt));
		goodsReturn.setTotalPayment(total_payment);
		goodsReturn.setRoundedOff("" + df2.format(total_payment - total_amt));
	
	return goodsReturn;
	}
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	
	
	
	
	
	
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
				.findByListgrId(goodsReceipt,EnumStatusUpdate.REJECTED.getStatus());
		
	/*	String status = setStatusOfPurchaseOrder(listGoodsReturn.get(0));
		logger.info("status-->" +status); //Test the Status if you want  */
		
		Integer grQunatity = getListGRQuantityCount(goodsReceipt);
		Integer greQunatity = getListGREQunatityCount(listGoodsReturn);
		
		if(grQunatity > 0)
			return true;
		else
			return false;
	}

	private Integer getListGREQunatityCount(List<GoodsReturn> listGoodsReturn) {
	
		Integer greQunatity=0;
		if (listGoodsReturn != null) {
			for (int i = 0; i < listGoodsReturn.size(); i++) {
				GoodsReturn  goodsReturn = listGoodsReturn.get(i);
				List<GoodsReturnLineItems> listItems = goodsReturn.getGoodsReturnLineItems();
			for (int j = 0; j < listItems.size(); j++) {
				GoodsReturnLineItems grelist = listItems.get(j);
				if(grelist.getRequiredQuantity()!=null) {
					greQunatity +=  grelist.getRequiredQuantity();
				}
			}
		  }
		}
		return greQunatity;
	}

	

	
	private Integer getListGoodsProductCount(List<GoodsReturn> listGoodsReturn, String category) {
		Integer qunatity = 0;
	
		for (int i = 0; i < listGoodsReturn.size(); i++) {
			GoodsReturn goodsReturnObj = listGoodsReturn.get(i);
			List<GoodsReturnLineItems> goodsReturnLineItems = goodsReturnObj.getGoodsReturnLineItems();
			for (int j = 0; j < goodsReturnLineItems.size(); j++) {
				GoodsReturnLineItems grelist = goodsReturnLineItems.get(j);
				if (grelist.getRequiredQuantity() != null) {
					if (category.equals(grelist.getProdouctNumber()) || category.equals(grelist.getSacCode()))
						qunatity += grelist.getRequiredQuantity();
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
				
			  
				logger.info("is Vaild-->" +poListData.keySet().equals( greListData.keySet()));
		        
				logger.info("is Vaild-->" +poListData.keySet().equals( greListData.keySet()));
		        
		       /* if(key1.containsAll(key2)) {
		        	    List<Integer> values1 = new ArrayList<Integer>(poListData.values());
		        	    List<Integer> values2 = new ArrayList<Integer>(greListData.values());
		        	    Collections.sort(values1);
		        	    Collections.sort(values2);
				
			   }*/
	
			}

			@Override
			public List<GoodsReturn> findByGoodsReceiptId(GoodsReceipt gr,String status) {
				
				return goodsReturnRepository.getListBygrId(gr,status);
			}
			
			/*
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
			}*/

			
	@Override
	public List<GoodsReturn> searchFilterBySelection(SearchFilter searchFilter){
		if(searchFilter.getToDate()==null) {
			searchFilter.setToDate(new Date());
		}
		
		searchFilter.setTypeOf(EnumSearchFilter.GRETABLE.getStatus());
		
		if(searchFilter.getSortBy()!=null) {
			if((!searchFilter.getSearchBy().equals("select") && !searchFilter.getFieldName().isEmpty()) || (searchFilter.getFromDate()!=null && searchFilter.getToDate()!=null )) {
				
				String resultQuery = getSearchFilterResult.getQueryBysearchFilterSelection(searchFilter);
				
				Query query = entityManager.createQuery(resultQuery);
				List<GoodsReturn> list = query.getResultList();
				return list;
			}else {
			List<GoodsReturn> list = findByIsActive();
			return list;
		}
		}else {
			List<GoodsReturn> list = findByIsActive();
			return list;
		}
		
	}
}



