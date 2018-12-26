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
import com.smerp.model.inventory.LineItems;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.PurchaseOrderLineItems;
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.repository.purchase.PurchaseOrderLineItemsRepository;
import com.smerp.repository.purchase.PurchaseOrderRepository;
import com.smerp.repository.purchase.RequestForQuotationRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.VendorAddressService;
import com.smerp.service.inventory.VendorsContactDetailsService;
import com.smerp.service.purchase.PurchaseOrderService;
import com.smerp.service.purchase.RequestForQuotationService;
import com.smerp.util.EmailGenerator;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.RequestContext;
import com.smerp.util.UnitPriceListItems;

@Service
@Transactional
public class PurchaseOrderServiceImpl  implements PurchaseOrderService {

	private static final Logger logger = LogManager.getLogger(RequestForQuotationServiceImpl.class);

	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	PurchaseOrderLineItemsRepository purchaseOrderLineItemsRepository;

	@Autowired
	VendorService vendorService;
	
	@Autowired
	VendorAddressService vendorAddressService;
	
	@Autowired
	VendorsContactDetailsService vendorsContactDetailsService;

	@Autowired
	RequestForQuotationService requestForQuotationService;
	
	@Autowired
	RequestForQuotationRepository requestForQuotationRepository;
	
	@Autowired
	EmailGenerator emailGenerator;

	@Override
	public PurchaseOrder save(PurchaseOrder purchaseOrder) {

		switch (purchaseOrder.getStatusType()) {
		case "DR":
			purchaseOrder.setStatus(EnumStatusUpdate.DRAFT.getStatus());
			break;
		case "SA":
			purchaseOrder.setStatus(EnumStatusUpdate.OPEN.getStatus());
			break;

		case "RE":
			purchaseOrder.setStatus(EnumStatusUpdate.REJECTED.getStatus());
			break;
		case "APP":
			purchaseOrder.setStatus(EnumStatusUpdate.APPROVEED.getStatus());
			break;
		case "CA":
			purchaseOrder.setStatus(EnumStatusUpdate.CANCELED.getStatus());
			break;
		default:
			logger.info("Type Not Matched:" + purchaseOrder.getStatusType());
			break;
		}

		if (purchaseOrder.getId() != null) { // delete List Of Items.
			PurchaseOrder purchaseOrderlineItems = purchaseOrderRepository
					.findById(purchaseOrder.getId()).get();
			List<PurchaseOrderLineItems> requestLists = purchaseOrderlineItems.getPurchaseOrderlineItems();
			
			if(requestLists.size()>0 && requestLists!=null) {
				purchaseOrderLineItemsRepository.deleteAll(requestLists);  // Delete All list items 
				}
			
			
			if(purchaseOrder.getRfqId()==null) {  // if RfqId null remove list items 
			List<PurchaseOrderLineItems> listItems = purchaseOrder.getPurchaseOrderlineItems();
			if (listItems != null) {
				for (int i = 0; i < listItems.size(); i++) {
					if (listItems.get(i).getProdouctNumber() == null && listItems.get(i).getSacCode() == null) {
						listItems.remove(i);
					}
				}
				purchaseOrder.setPurchaseOrderlineItems(listItems);
			}
			
			}else {  // Convert mode set Amount
				List<PurchaseOrderLineItems> header_listItems = purchaseOrder.getPurchaseOrderlineItems();
				if (requestLists != null) {
					List<PurchaseOrderLineItems> lineItems =new ArrayList<PurchaseOrderLineItems>();
					for (int i = 0; i < requestLists.size(); i++) {
						PurchaseOrderLineItems line = new PurchaseOrderLineItems();
						line.setProdouctNumber(requestLists.get(i).getProdouctNumber());
						line.setProductGroup(requestLists.get(i).getProductGroup());
						line.setDescription(requestLists.get(i).getDescription());
						line.setHsn(requestLists.get(i).getHsn());
						line.setRequiredQuantity(requestLists.get(i).getRequiredQuantity());
						line.setSacCode(requestLists.get(i).getSacCode());
						line.setUom(requestLists.get(i).getUom());
						line.setWarehouse(requestLists.get(i).getWarehouse());
						line.setProductId(requestLists.get(i).getProductId());
						line.setUnitPrice(header_listItems.get(i).getUnitPrice());
						line.setTaxCode(header_listItems.get(i).getTaxCode());
						lineItems.add(line);
					}
				
					purchaseOrder.setPurchaseOrderlineItems(lineItems);
				}
			}
		}

		Vendor vendor = vendorService.findById(purchaseOrder.getVendor().getId());
		VendorAddress vendorShippingAddress = vendorAddressService.findById(purchaseOrder.getVendorShippingAddress().getId());
		VendorAddress vendorPayAddress = vendorAddressService.findById(purchaseOrder.getVendorPayTypeAddress().getId());
		
		VendorsContactDetails vendorsContactDetails =vendorsContactDetailsService.findById(purchaseOrder.getVendorContactDetails().getId());

		purchaseOrder.setVendor(vendor);
		purchaseOrder.setVendorContactDetails(vendorsContactDetails);
		purchaseOrder.setVendorShippingAddress(vendorShippingAddress);
		purchaseOrder.setVendorPayTypeAddress(vendorPayAddress);
		
		if(purchaseOrder.getStatusType()!=null &&  purchaseOrder.getStatusType().equals("APP")) {
			try {
    			 RequestContext.initialize();
    		     RequestContext.get().getConfigMap().put("mail.template", "purchaseOrderEmail.ftl");  //Sending Email
    		     emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendPOEmail(purchaseOrder);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		}
		/*return purchaseOrderRepository.save(purchaseOrder);*/
		return  purchaseOrder ;
	}

	@Override
	public PurchaseOrder savePO(String rfqId) {

		PurchaseOrder po = new PurchaseOrder();
		RequestForQuotation rfq = requestForQuotationService.findById((Integer.parseInt(rfqId)));
		PurchaseOrder dup_po =purchaseOrderRepository.findByRfqId(rfq.getId());  // check RFQ exist in PO
        if(dup_po==null) { 
		PurchaseOrder podetails = findLastDocumentNumber();
		if (podetails != null && podetails.getDocNumber() != null) {
			po.setDocNumber(GenerateDocNumber.documentNumberGeneration(podetails.getDocNumber()));
		} else {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDateTime now = LocalDateTime.now();
		po.setDocNumber(GenerateDocNumber.documentNumberGeneration("PO"+(String)dtf.format(now) +"0"));
		}

		if (rfq != null) {
			po.setDocumentDate(rfq.getDocumentDate());
			po.setStatus(EnumStatusUpdate.OPEN.getStatus());
			po.setPostingDate(rfq.getPostingDate());
			po.setCategory(rfq.getCategory());
			po.setRemark(rfq.getRemark());
			po.setReferenceDocNumber(rfq.getDocNumber());
			po.setRequiredDate(rfq.getRequiredDate());
			po.setRfqId(rfq.getId());
			po.setIsActive(true);
			po.setVendor(rfq.getVendor());
			po.setVendorContactDetails(rfq.getVendorContactDetails());
			po.setVendorPayTypeAddress(rfq.getVendorPayTypeAddress());
			po.setVendorShippingAddress(rfq.getVendorShippingAddress());
			
			List<LineItems> prItms = rfq.getLineItems();
			List<PurchaseOrderLineItems> lineItems =new ArrayList<PurchaseOrderLineItems>();
			if (prItms != null) {
				for (int i = 0; i < prItms.size(); i++) {
					PurchaseOrderLineItems line = new PurchaseOrderLineItems();
					line.setProdouctNumber(prItms.get(i).getProdouctNumber());
					line.setProductGroup(prItms.get(i).getProductGroup());
					line.setDescription(prItms.get(i).getDescription());
					line.setHsn(prItms.get(i).getHsn());
					line.setRequiredQuantity(prItms.get(i).getRequiredQuantity());
					line.setSacCode(prItms.get(i).getSacCode());
					line.setUom(prItms.get(i).getUom());
					line.setWarehouse(prItms.get(i).getWarehouse());
					line.setProductId(prItms.get(i).getProductId());
					lineItems.add(line);
				}
			}
			
			po.setPurchaseOrderlineItems(lineItems);
			
		}
		
		po = purchaseOrderRepository.save(po);
		
		rfq.setStatus(EnumStatusUpdate.CONVERTRFQTOPO.getStatus());
		requestForQuotationRepository.save(rfq);
		
		return po;
        }else {
        	return dup_po;
        }
	}
	
	
	
	@Override
	public PurchaseOrder saveCancelStage(String rfqId) {
		PurchaseOrder po = purchaseOrderRepository.findById(Integer.parseInt(rfqId)).get();
		po.setStatus(EnumStatusUpdate.CANCELED.getStatus());
		purchaseOrderRepository.save(po);
		return po;
		
	}

	@Override
	public PurchaseOrder findLastDocumentNumber() {
		return purchaseOrderRepository.findTopByOrderByIdDesc();
	}

	@Override
	public List<PurchaseOrder> findAll() {
		return purchaseOrderRepository.findAll();
	}

	@Override
	public List<PurchaseOrder> findByIsActive() {
		return purchaseOrderRepository.findByIsActive(true);
	}

	@Override
	public PurchaseOrder findById(int id) {
		return purchaseOrderRepository.findById(id).get();
	}
	
	@Override
	public PurchaseOrder getListAmount(PurchaseOrder purchaseOrder) {
		
		List<PurchaseOrderLineItems> listItems = purchaseOrder.getPurchaseOrderlineItems();
		Double addAmt=0.0;
		Double addTaxAmt=0.0;
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				PurchaseOrderLineItems polist = listItems.get(i);
				if(polist.getUnitPrice()!=null) {
				addTaxAmt += UnitPriceListItems.getTaxAmt(polist.getRequiredQuantity(),polist.getUnitPrice(),polist.getTaxCode());
				addAmt +=UnitPriceListItems.getTotalAmt(polist.getRequiredQuantity(),polist.getUnitPrice(), polist.getTaxCode());
				polist.setTaxTotal(""+UnitPriceListItems.getTaxAmt(polist.getRequiredQuantity(),polist.getUnitPrice(),polist.getTaxCode()));
				polist.setTotal(""+UnitPriceListItems.getTotalAmt(polist.getRequiredQuantity(),polist.getUnitPrice(), polist.getTaxCode()));
				}else {
				polist.setTaxTotal("");
				polist.setTotal("");	
				}
			}
			purchaseOrder.setPurchaseOrderlineItems(listItems);
		}
		purchaseOrder.setTotalBeforeDisAmt(addAmt);
		purchaseOrder.setTaxAmt(""+addTaxAmt);
		if(purchaseOrder.getTotalPayment()!=null) {
		purchaseOrder.setAmtRounding(""+purchaseOrder.getTotalPayment());
		}
	
	return purchaseOrder;
	}
	
	
	@Override
	public PurchaseOrder delete(int id) {
		PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).get();
		purchaseOrder.setIsActive(false);
		purchaseOrderRepository.save(purchaseOrder);
		return purchaseOrder;
	}



}
