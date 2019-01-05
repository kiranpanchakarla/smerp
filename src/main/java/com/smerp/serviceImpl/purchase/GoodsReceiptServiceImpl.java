package com.smerp.serviceImpl.purchase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
			gr.setPoId(po.getId());
			gr.setIsActive(true);
			gr.setVendor(po.getVendor());
			gr.setVendorContactDetails(po.getVendorContactDetails());
			gr.setVendorPayTypeAddress(po.getVendorPayTypeAddress());
			gr.setVendorShippingAddress(po.getVendorShippingAddress());
			
		
			gr.setFreight(po.getFreight());
			gr.setTotalDiscount(po.getTotalDiscount());
		
			List<GoodsReceipt> listGoodsReceipt = goodsReceiptRepository
					.findByListPoId(po.getId());  // check Multiple  Quantity
			Integer grQunatity =0;
			
			List<PurchaseOrderLineItems> poItms = po.getPurchaseOrderlineItems();
			List<GoodsReceiptLineItems> lineItems =new ArrayList<GoodsReceiptLineItems>();
			if (poItms != null) {
				for (int i = 0; i < poItms.size(); i++) {
					GoodsReceiptLineItems line = new GoodsReceiptLineItems();
					line.setProdouctNumber(poItms.get(i).getProdouctNumber());
					line.setProductGroup(poItms.get(i).getProductGroup());
					line.setDescription(poItms.get(i).getDescription());
					line.setHsn(poItms.get(i).getHsn());
					
					if(poItms.get(i).getProdouctNumber()!=null) {
						 grQunatity = getListGoodsProductCount(listGoodsReceipt,  poItms.get(i).getProdouctNumber());
					}else if(poItms.get(i).getSacCode()!=null) {
						 grQunatity = getListGoodsProductCount(listGoodsReceipt,  poItms.get(i).getSacCode());
					}
					
				
					line.setRequiredQuantity(poItms.get(i).getRequiredQuantity() - grQunatity);
					
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
		 
		/*** Here check Quantity  set status ***/
		
		//po.setStatus(EnumStatusUpdate.CONVERTRFQTOPO.getStatus());
		//purchaseOrderRepository.save(po);
		
		return gr;
       /* }else {
        	return dup_gr;
        }*/
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
					.findByListPoId(po.getId());  // check Multiple  Quantity
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
				} }
				
				logger.info("poItms.get(i).getRequiredQuantity()-->" + poItms.get(i).getRequiredQuantity());
				logger.info("grQunatity-->" + grQunatity);
				grlist.setTempRequiredQuantity(poItms.get(i).getRequiredQuantity() - grQunatity);
				
				}else {
				grlist.setTaxTotal("");
				grlist.setTotal("");	
				}
			}
			goodsReceipt.setGoodsReceiptLineItems(listItems);
		}
		goodsReceipt.setTotalBeforeDisAmt(addAmt);
		goodsReceipt.setTaxAmt(""+addTaxAmt);
		
		Double total_amt= UnitPriceListItems.getTotalPaymentAmt(addAmt, goodsReceipt.getTotalDiscount(), goodsReceipt.getFreight());
		goodsReceipt.setAmtRounding(UnitPriceListItems.getRoundingValue(total_amt));
		goodsReceipt.setTotalPayment(total_amt);
		
		
	
	return goodsReceipt;
	}
	
	
	
	@Override
	public Boolean checkQuantityPoGr(PurchaseOrder purchaseOrder) {
		List<GoodsReceipt> listGoodsReceipt = goodsReceiptRepository
				.findByListPoId(purchaseOrder.getId());
		logger.info("listGoodsReceipt-->" +listGoodsReceipt);
		Integer prQunatity =  getListPoQuantityCount(purchaseOrder);
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



}
