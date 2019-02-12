package com.smerp.serviceImpl.purchase;

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
import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.InVoiceLineItems;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.PurchaseOrderLineItems;
import com.smerp.repository.purchase.GoodsReceiptLineItemsRepository;
import com.smerp.repository.purchase.GoodsReceiptRepository;
import com.smerp.repository.purchase.GoodsReturnRepository;
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
	GoodsReturnRepository goodsReturnRepository;
	

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
	
	@PersistenceContext    
	private EntityManager entityManager;
	
	@Autowired
	EmailGenerator emailGenerator;

	@Override
	public GoodsReceipt save(GoodsReceipt goodsReceipt) {
		goodsReceipt.setCategory("Item");
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

		List<GoodsReceiptLineItems> listItems = goodsReceipt.getGoodsReceiptLineItems();
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				if (listItems.get(i).getProdouctNumber() == null && listItems.get(i).getSacCode() == null && listItems.get(i).getRequiredQuantity() == null) {
					listItems.remove(i);
					i--;
				}
			}
			goodsReceipt.setGoodsReceiptLineItems(listItems);
		}
		
		if (goodsReceipt.getId() == null) {
			Vendor vendor = vendorService.findById(goodsReceipt.getVendor().getId());
			VendorAddress vendorShippingAddress = vendorAddressService.findById(goodsReceipt.getVendorShippingAddress().getId());
			VendorAddress vendorPayAddress = vendorAddressService.findById(goodsReceipt.getVendorPayTypeAddress().getId());
			VendorsContactDetails vendorsContactDetails =vendorsContactDetailsService.findById(goodsReceipt.getVendorContactDetails().getId());

			goodsReceipt.setVendor(vendor);
			goodsReceipt.setVendorContactDetails(vendorsContactDetails);
			goodsReceipt.setVendorShippingAddress(vendorShippingAddress);
			goodsReceipt.setVendorPayTypeAddress(vendorPayAddress);
		}
		
		if (goodsReceipt.getId() != null) { // delete List Of Items.
			GoodsReceipt goodsReceiptObj = goodsReceiptRepository
					.findById(goodsReceipt.getId()).get();
			List<GoodsReceiptLineItems> requestLists = goodsReceiptObj.getGoodsReceiptLineItems();
			
			if(requestLists.size()>0 && requestLists!=null) {
				goodsReceiptLineItemsRepository.deleteAll(requestLists);  // Delete All list items 
				}
			
			
			if(goodsReceipt.getPoId()==null) {  // if PoId null remove list items 
				
			
			}else {/*
				List<GoodsReceiptLineItems> header_listItems =  goodsReceipt.getGoodsReceiptLineItems();
				if (requestLists != null) {
					List<GoodsReceiptLineItems> lineItems =new ArrayList<GoodsReceiptLineItems>();
					for (int i = 0; i < requestLists.size(); i++) {
						GoodsReceiptLineItems line = new GoodsReceiptLineItems();
						line.setProdouctNumber(requestLists.get(i).getProdouctNumber());
						line.setProductGroup(requestLists.get(i).getProductGroup());
						line.setDescription(requestLists.get(i).getDescription());
						line.setHsn(requestLists.get(i).getHsn());
						line.setRequiredQuantity(requestLists.get(i).getRequiredQuantity());
						line.setSacCode(requestLists.get(i).getSacCode());
						line.setSku(requestLists.get(i).getSku());
						line.setUnitPrice(requestLists.get(i).getUnitPrice());
						line.setUom(requestLists.get(i).getUom());
						line.setWarehouse(requestLists.get(i).getWarehouse());
						line.setProductId(requestLists.get(i).getProductId());
						//line.setUnitPrice(header_listItems.get(i).getUnitPrice());
						line.setTaxCode(requestLists.get(i).getTaxCode());
						//line.setTaxCode(requestLists.get(i).getTaxCode());
						
						line.setRequiredQuantity(header_listItems.get(i).getRequiredQuantity());
						lineItems.add(line);
					}
				
					goodsReceipt.setGoodsReceiptLineItems(lineItems);
				}
				 logger.info("convertd Po to GR Data -->" +goodsReceipt);
			*/}
			
			if(goodsReceipt.getPoId()!=null) {
				PurchaseOrder po = goodsReceipt.getPoId();
				
				Vendor vendor = vendorService.findById(po.getVendor().getId());
				VendorAddress vendorShippingAddress = vendorAddressService.findById(po.getVendorShippingAddress().getId());
				VendorAddress vendorPayAddress = vendorAddressService.findById(po.getVendorPayTypeAddress().getId());
				VendorsContactDetails vendorsContactDetails =vendorsContactDetailsService.findById(po.getVendorContactDetails().getId());

				goodsReceipt.setVendor(vendor);
				goodsReceipt.setVendorContactDetails(vendorsContactDetails);
				goodsReceipt.setVendorShippingAddress(vendorShippingAddress);
				goodsReceipt.setVendorPayTypeAddress(vendorPayAddress);
			}
			
		}
         logger.info("goodsReceipt -->" +goodsReceipt);
		
		
		 if(goodsReceipt.getStatus()!=null &&  goodsReceipt.getStatus().equals(EnumStatusUpdate.APPROVEED.getStatus())) {
			try {
			   	goodsReceipt =getListAmount(goodsReceipt);
    			 RequestContext.initialize();
    		     RequestContext.get().getConfigMap().put("mail.template", "goodsReceiptEmail.ftl");  //Sending Email
    		     emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendGoodsReceiptEmail(goodsReceipt);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		}else if(goodsReceipt.getStatus()!=null &&  goodsReceipt.getStatus().equals(EnumStatusUpdate.REJECTED.getStatus())) {
			 
		 }
		
		/*if(goodsReceipt.getPoId()!=null) {
		PurchaseOrder po = purchaseOrderService.findById(goodsReceipt.getPoId());
		if(!checkQuantityPoGr(po)) {
			
			 logger.info("purchaseOrder    COMPLETED -->");
		} }*/
		
		
		//goodsReceipt= goodsReceiptRepository.save(goodsReceipt);
		
		if(goodsReceipt.getPoId()!=null) {
			PurchaseOrder purchaseOrder = setStatusOfPurchaseOrder(goodsReceipt); // Status Update
			 logger.info("goodsReceipt -->" +goodsReceipt);
		}
		
		return goodsReceiptRepository.save(goodsReceipt);
		 
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
			gr.setPoId(po);
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
					line.setSku(poItms.get(i).getSku());
					line.setTaxTotal(poItms.get(i).getTaxTotal());
					line.setTotal(poItms.get(i).getTotal());
					
					lineItems.add(line);
				}
			}
			
			gr.setGoodsReceiptLineItems(lineItems);
			
			gr= getListAmount(gr); // Set Amount....Like Tax Total Amt
			
			
		}
		logger.info("gr" + gr);
		gr.setCategory("Item");
		gr = goodsReceiptRepository.save(gr);
		
		po.setCategory("Item");
		po.setStatus(EnumStatusUpdate.PARTIALLY_RECEIVED.getStatus());  // Set Partial Complted
		purchaseOrderRepository.save(po);
		
		return gr;
     
	}
	
	
	
	@Override
	public PurchaseOrder  setStatusOfPurchaseOrder(GoodsReceipt goodsReceipt) {
		logger.info("set Status-->");
		String status="";
		/*PurchaseOrder purchaseOrder = purchaseOrderService.findById(goodsReceipt.getPoId());
		
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
        }*/
		
		
		String sqlList= "select * from vw_purchase_order_pending_qty where id=  "+goodsReceipt.getPoId().getId();
		Integer pendingQuantity=0;
		Integer prQuantity=0;
		Integer grQuantity=0;
		Integer returnQuantity=0;
		Integer creditQuantity=0;
		
	 	logger.info("sqlList ----> " + sqlList);
		Query queryList = entityManager.createNativeQuery(sqlList);
		 List<Object[]>	list = queryList.getResultList();
			
			logger.info("List Size -----> " + list.size());
		     for(Object[] tuple : list) {
		    	 prQuantity +=(Integer)(tuple[0] == null  ? 0 : (Integer.parseInt((tuple[3].toString()))));
		    	 grQuantity +=(Integer)(tuple[0] == null  ? 0 : (Integer.parseInt((tuple[4].toString()))));
		    	 returnQuantity += (Integer)(tuple[0] == null  ? 0 : (Integer.parseInt((tuple[5].toString()))));
		    	 creditQuantity += (Integer)(tuple[0] == null  ? 0 : (Integer.parseInt((tuple[6].toString()))));
		    	 pendingQuantity += (Integer)(tuple[0] == null  ? 0 : (Integer.parseInt((tuple[8].toString()))));
		     }
	     
		 	logger.info("prQuantity ----> " + prQuantity);
		 	logger.info("grQuantity ----> " + grQuantity);
		 	logger.info("returnQuantity ----> " + returnQuantity);
		 	logger.info("creditQuantity ----> " + creditQuantity);
		 	logger.info("pendingQuantity ----> " + pendingQuantity);
		    if(grQuantity!=0) {
			if(pendingQuantity > 0)
				 status = EnumStatusUpdate.PARTIALLY_RECEIVED.getStatus();
			else
				 status = EnumStatusUpdate.COMPLETED.getStatus();
		    }else {
		    	status = EnumStatusUpdate.APPROVEED.getStatus();
		    }
    	logger.info("status-->" + status);
    	
        PurchaseOrder po = goodsReceipt.getPoId();
		po.setStatus(status);
		
		return purchaseOrderRepository.save(po);
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
	
	public Map<String, Integer> prepareMapForProductQunatityGR(GoodsReceipt goodReceipt) {
		Map<String, Integer> grMapListData = new LinkedHashMap<>();

		List<GoodsReceipt> listGoodsReceipt = goodsReceiptRepository.findByListPoId(goodReceipt.getPoId(),EnumStatusUpdate.REJECTED.getStatus()); // check
																											// Multiple
		grMapListData = getGoodsReceiptRealQunatityList(goodReceipt, grMapListData, listGoodsReceipt);
		
		logger.info("grMapListData-->" + grMapListData);

		return grMapListData;
	}
	
	private Map<String, Integer> prepareMapForApprovedProductQunatityGR(GoodsReceipt goodReceipt) {
		Map<String, Integer> grMapListData = new LinkedHashMap<>();

		List<GoodsReceipt> listGoodsReceipt = goodsReceiptRepository.findByApproveListPoId(goodReceipt.getPoId(),EnumStatusUpdate.APPROVEED.getStatus()); // check
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
		gr.setCategory("Item");
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
	public List<GoodsReceipt> grApprovedList() {
		return goodsReceiptRepository.grApprovedList(EnumStatusUpdate.APPROVEED.getStatus(),EnumStatusUpdate.GOODS_RETURN.getStatus());
	}

	@Override
	public GoodsReceipt findById(int id) {
		return goodsReceiptRepository.findById(id).get();
	}
	
	
	@Override
	public GoodsReceipt getGoodsReceiptById(int id) {
		GoodsReceipt goodsReceipt = goodsReceiptRepository.findById(id).get();
	     
	 	String sqlList= " select product_number,pending_quantity from vw_purchase_order_pending_qty where id= " + goodsReceipt.getPoId().getId();
		String productNumber =""; 
		logger.info("sqlList ----> " + sqlList);
		Query queryList = entityManager.createNativeQuery(sqlList);
		  List<Object[]>	invoiceList = queryList.getResultList();
		  
		  List<GoodsReceiptLineItems> listItems = goodsReceipt.getGoodsReceiptLineItems();
			
			List<GoodsReceiptLineItems> addListItems = new ArrayList<GoodsReceiptLineItems>();
		logger.info("invoiceList Size -----> " + invoiceList.size());
		int j=0;
	     for(Object[] tuple : invoiceList) {
	    	 GoodsReceiptLineItems greList = listItems.get(j);
	    	 productNumber = tuple[0] == null ? "0" : ( tuple[0]).toString();
	    	 
	    		for (int i = 0; i < listItems.size(); i++) {
	    			GoodsReceiptLineItems invlist = listItems.get(i);
	    			if(productNumber.equals(invlist.getProdouctNumber())) {
	    			greList.setTempRequiredQuantity(tuple[1] == null  ? 0 : (Integer.parseInt(tuple[1].toString())));
					break;
					}
	    		}
	    		addListItems.add(greList);
	    		j++;
	     }
	     
	     goodsReceipt.setGoodsReceiptLineItems(addListItems);
	     
		return goodsReceipt;
	}
	
	
	
	
	@Override
	public GoodsReceipt getGoodsReceiptViewById(int id) {
		GoodsReceipt goodsReceipt = goodsReceiptRepository.findById(id).get();
		/*Set Headers*/
		
		String sql= " select total_gr_amount_product_tax,total_gr_amount_before_discount,total_discount,freight,total_gr_amount_after_discount"
				+ " ,total_gr_amount_after_discount_rounding from vw_goods_received_amount where id= " +id;
		
		logger.info("sql ----> " + sql);
		Query query = entityManager.createNativeQuery(sql);
		  List<Object[]>	list = query.getResultList();
		
		logger.info("List Size -----> " + list.size());
	     for(Object[] tuple : list) {
	    	 goodsReceipt.setTaxAmt(tuple[0] == null ? "0" : ( tuple[0]).toString());
	    	 goodsReceipt.setTotalBeforeDisAmt(tuple[1] == null ? 0: (Double.parseDouble(tuple[1].toString())));
	    	// goodsReceipt.setTotalDiscount(tuple[2] == null ?  0: (Double.parseDouble(tuple[2].toString())));
	    	 //goodsReceipt.setFreight(tuple[3] == null  ? 0 : (Integer.parseInt(tuple[3].toString())));
	    	 goodsReceipt.setTotalPayment(tuple[4] == null ? 0: (Double.parseDouble(tuple[4].toString())));
	    	 goodsReceipt.setAmtRounding(tuple[5] == null ? "0" : ( tuple[5]).toString());
	     }
	     
	     /*--Set Headers--*/
	     
	     
	     /*--Set Lists--*/
	     
	 	String sqlList= " select product_number,creditmemo_quantity,current_quantity,product_tax,product_cost_tax from vw_goods_received_lineitems_amount where id= " +id;
		String productNumber =""; 
		Integer creditmemoQuantity=0;
		logger.info("sqlList ----> " + sqlList);
		Query queryList = entityManager.createNativeQuery(sqlList);
		  List<Object[]>	invoiceList = queryList.getResultList();
		  
		  List<GoodsReceiptLineItems> listItems = goodsReceipt.getGoodsReceiptLineItems();
			
			List<GoodsReceiptLineItems> addListItems = new ArrayList<GoodsReceiptLineItems>();
		logger.info("invoiceList Size -----> " + invoiceList.size());
		int j=0;
	     for(Object[] tuple : invoiceList) {
	    	 GoodsReceiptLineItems grelist = listItems.get(j);
	    	 productNumber = tuple[0] == null ? "0" : ( tuple[0]).toString();
	    	 creditmemoQuantity = tuple[1] == null  ? 0 : (Integer.parseInt(tuple[1].toString()));
	    	 
	    		for (int i = 0; i < listItems.size(); i++) {
	    			GoodsReceiptLineItems invlist = listItems.get(i);
	    			
	    			if(productNumber.equals(invlist.getProdouctNumber())) {
	    			grelist.setTempRequiredQuantity(tuple[2] == null  ? 0 : (Integer.parseInt(tuple[2].toString())));
	    			grelist.setTaxTotal(tuple[3] == null ? "0" : ( tuple[3]).toString());
					grelist.setTotal(tuple[4] == null ? "0" : ( tuple[4]).toString());
					break;
					}
	    		}
	    		addListItems.add(grelist);
	    		j++;
	     }
	     
	 	goodsReceipt.setGoodsReceiptLineItems(addListItems);
	     
	     /*Set Lists*/
		return goodsReceipt;
	}
	
	
	@Override
	public GoodsReceipt getListAmount(GoodsReceipt goodsReceipt) {
		logger.info("getListAmount-->");
		List<GoodsReceiptLineItems> listItems = goodsReceipt.getGoodsReceiptLineItems();
		List<GoodsReceiptLineItems> addListItems = new ArrayList<GoodsReceiptLineItems>();
		
		PurchaseOrder po = null;
		List<PurchaseOrderLineItems> poItms =null;
		 List<GoodsReceipt> listGoodsReceipt =null;
		if(goodsReceipt.getPoId()!=null) {
		 po = goodsReceipt.getPoId();
		 poItms = po.getPurchaseOrderlineItems();
		listGoodsReceipt = goodsReceiptRepository
					.findByListPoId(po,EnumStatusUpdate.REJECTED.getStatus());  // check Multiple  Quantity
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
				
				/*if(goodsReceipt.getPoId()!=null) {
				if(poItms.get(i).getProdouctNumber()!=null ) {
					 grQunatity = getListGoodsProductCount(listGoodsReceipt,  poItms.get(i).getProdouctNumber());
				}else if(poItms.get(i).getSacCode()!=null ) {
					 grQunatity = getListGoodsProductCount(listGoodsReceipt,  poItms.get(i).getSacCode());
				} 
				
				logger.info("poItms.get(i).getRequiredQuantity()-->" + poItms.get(i).getRequiredQuantity());
				logger.info("grQunatity-->" + grQunatity);
				grlist.setTempRequiredQuantity(poItms.get(i).getRequiredQuantity() - grQunatity);
				
				
				}*/
				
			
				
				}else {
				grlist.setTaxTotal("");
				grlist.setTotal("");	
				}
				addListItems.add(grlist);
			}
			goodsReceipt.setGoodsReceiptLineItems(addListItems);
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
	/*	List<GoodsReceipt> listGoodsReceipt = goodsReceiptRepository
				.findByListPoId(purchaseOrder.getId(),EnumStatusUpdate.REJECTED.getStatus());
		logger.info("listGoodsReceipt-->" +listGoodsReceipt);*/
		
		/*String status = setStatusOfPurchaseOrder(listGoodsReceipt.get(0));
		logger.info("status-->" +status); //Test the Status if you want  
	
		//Integer prQunatity = getListPoQuantityCount(purchaseOrder);
		//Integer grQunatity = getListGRQunatityCount(listGoodsReceipt);
		
		
		/*String sqlList= " select pending_quantity from vw_purchase_order_pending_qty where id= " +purchaseOrder.getId();
		logger.info("sqlList ----> " + sqlList);
		Query queryList = entityManager.createNativeQuery(sqlList);
		  List<Object[]>	invoiceList = queryList.getResultList();
		  
			
		Integer pendingQuantity=0;
	     for(Object[] tuple : invoiceList) {
	    	 pendingQuantity += (Integer)(tuple[0] == null  ? 0 : (Integer.parseInt((tuple[0].toString()))));
	     }*/
	     
		String sqlList= "select * from vw_purchase_order_pending_qty where id=  "+purchaseOrder.getId();
		Integer pendingQuantity=0;
		Integer prQuantity=0;
		Integer grQuantity=0;
		Integer returnQuantity=0;
		Integer creditQuantity=0;
		
	 	logger.info("sqlList ----> " + sqlList);
		Query queryList = entityManager.createNativeQuery(sqlList);
		 List<Object[]>	list = queryList.getResultList();
			
			logger.info("List Size -----> " + list.size());
		     for(Object[] tuple : list) {
		    	 prQuantity +=(Integer)(tuple[0] == null  ? 0 : (Integer.parseInt((tuple[3].toString()))));
		    	 grQuantity +=(Integer)(tuple[0] == null  ? 0 : (Integer.parseInt((tuple[4].toString()))));
		    	 returnQuantity += (Integer)(tuple[0] == null  ? 0 : (Integer.parseInt((tuple[5].toString()))));
		    	 creditQuantity += (Integer)(tuple[0] == null  ? 0 : (Integer.parseInt((tuple[6].toString()))));
		    	 pendingQuantity += (Integer)(tuple[0] == null  ? 0 : (Integer.parseInt((tuple[8].toString()))));
		     }
	     
		 	logger.info("prQuantity ----> " + prQuantity);
		 	logger.info("grQuantity ----> " + grQuantity);
		 	logger.info("returnQuantity ----> " + returnQuantity);
		 	logger.info("creditQuantity ----> " + creditQuantity);
		 	logger.info("pendingQuantity ----> " + pendingQuantity);
		
		if(pendingQuantity > 0)
			return true;
		else
			return false;
	}

	/*private Integer getListGRQunatityCount(List<GoodsReceipt> listGoodsReceipt) {
	
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
	}*/

	

	
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
	
	
	
	
	
	
	
	/*private Integer getListPoQuantityCount(PurchaseOrder purchaseOrder) {
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
	}*/
	
	
	
	
	
	
	
	
	
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
