package com.smerp.serviceImpl.purchase;

import java.text.DecimalFormat;
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
import com.smerp.model.inventory.GoodsReceiptLineItems;
import com.smerp.model.inventory.LineItems;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.PurchaseOrderLineItems;
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.repository.purchase.PurchaseOrderLineItemsRepository;
import com.smerp.repository.purchase.PurchaseOrderRepository;
import com.smerp.repository.purchase.PurchaseRequestRepository;
import com.smerp.repository.purchase.RequestForQuotationRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.VendorAddressService;
import com.smerp.service.inventory.VendorsContactDetailsService;
import com.smerp.service.purchase.PurchaseOrderService;
import com.smerp.service.purchase.RequestForQuotationService;
import com.smerp.util.DocNumberGenerator;
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
	
	@Autowired
	private DocNumberGenerator docNumberGenerator;
	
	@Autowired
	private PurchaseRequestRepository purchaseRequestRepository;

	@Override
	public PurchaseOrder save(PurchaseOrder purchaseOrder) {
		purchaseOrder.setCategory("Item");
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

		List<PurchaseOrderLineItems> listItems = purchaseOrder.getPurchaseOrderlineItems();
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				if (listItems.get(i).getProdouctNumber() == null && listItems.get(i).getTaxCode() == null) {
					listItems.remove(i);
					i--;
				}
			}
			purchaseOrder.setPurchaseOrderlineItems(listItems);
		}
		
		if(purchaseOrder.getRfqId()==null) {
			Vendor vendor = vendorService.findById(purchaseOrder.getVendor().getId());
			VendorAddress vendorShippingAddress = vendorAddressService.findById(purchaseOrder.getVendorShippingAddress().getId());
			VendorAddress vendorPayAddress = vendorAddressService.findById(purchaseOrder.getVendorPayTypeAddress().getId());
			VendorsContactDetails vendorsContactDetails =vendorsContactDetailsService.findById(purchaseOrder.getVendorContactDetails().getId());

			purchaseOrder.setVendor(vendor);
			purchaseOrder.setVendorContactDetails(vendorsContactDetails);
			purchaseOrder.setVendorShippingAddress(vendorShippingAddress);
			purchaseOrder.setVendorPayTypeAddress(vendorPayAddress);
		}
		
		if (purchaseOrder.getId() != null) { // delete List Of Items.
			PurchaseOrder purchaseOrderlineItems = purchaseOrderRepository
					.findById(purchaseOrder.getId()).get();
			List<PurchaseOrderLineItems> requestLists = purchaseOrderlineItems.getPurchaseOrderlineItems();
			
			if(requestLists.size()>0 && requestLists!=null) {
				purchaseOrderLineItemsRepository.deleteAll(requestLists);  // Delete All list items 
				}
			
			
			if(purchaseOrder.getRfqId()==null) {  // if RfqId null remove list items 
				
			
			}else {/*  // Convert mode set Amount
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
						line.setSku(requestLists.get(i).getSku());
						line.setUnitPrice(requestLists.get(i).getUnitPrice());
						line.setUom(requestLists.get(i).getUom());
						line.setWarehouse(requestLists.get(i).getWarehouse());
						line.setProductId(requestLists.get(i).getProductId());
						//line.setUnitPrice(header_listItems.get(i).getUnitPrice());
						line.setTaxCode(header_listItems.get(i).getTaxCode());
						//line.setTaxCode(requestLists.get(i).getTaxCode());
						lineItems.add(line);
					}
				
					purchaseOrder.setPurchaseOrderlineItems(lineItems);
				}
			*/}
		
			
			if(purchaseOrder.getRfqId()!=null) {
			RequestForQuotation rfq = purchaseOrder.getRfqId();  
			//requestForQuotationService.findById(purchaseOrder.getRfqId().getId())
			Vendor vendor = vendorService.findById(rfq.getVendor().getId());
			VendorAddress vendorShippingAddress = vendorAddressService.findById(rfq.getVendorShippingAddress().getId());
			VendorAddress vendorPayAddress = vendorAddressService.findById(rfq.getVendorPayTypeAddress().getId());
			VendorsContactDetails vendorsContactDetails =vendorsContactDetailsService.findById(rfq.getVendorContactDetails().getId());

			purchaseOrder.setVendor(vendor);
			purchaseOrder.setVendorContactDetails(vendorsContactDetails);
			purchaseOrder.setVendorShippingAddress(vendorShippingAddress);
			purchaseOrder.setVendorPayTypeAddress(vendorPayAddress);
			}
		}

		 if(purchaseOrder.getStatus()!=null &&  !purchaseOrder.getStatus().equals(EnumStatusUpdate.DRAFT.getStatus())) {
			try {
				
				
			   	purchaseOrder =getListAmount(purchaseOrder);
			   	if(purchaseOrder.getId()!=null) {
					PurchaseOrder purchaseOrderObj = purchaseOrderRepository.findById(purchaseOrder.getId()).get();
					logger.info(purchaseOrderObj.getCreatedBy().getUserEmail());
					purchaseOrder.setCreatedBy(purchaseOrderObj.getCreatedBy());
				 }
    			 RequestContext.initialize();
    		     RequestContext.get().getConfigMap().put("mail.template", "purchaseOrderEmail.ftl");  //Sending Email
    		     emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendPOEmail(purchaseOrder);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		} 
		 
		return purchaseOrderRepository.save(purchaseOrder); 
		 
	}

	@Override
	public PurchaseOrder savePO(String rfqId) {

		PurchaseOrder po = new PurchaseOrder();
		RequestForQuotation rfq = requestForQuotationService.findById((Integer.parseInt(rfqId)));
		PurchaseOrder dup_po =purchaseOrderRepository.findByRfqId(rfq);  // check RFQ exist in PO
        if(dup_po==null) {
	        Integer count = docNumberGenerator.getCountByDocType(EnumStatusUpdate.PO.getStatus());
			PurchaseOrder podetails = findLastDocumentNumber();
		if (podetails != null && podetails.getDocNumber() != null) {
			po.setDocNumber(GenerateDocNumber.documentNumberGeneration(podetails.getDocNumber(),count));
		} else {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDateTime now = LocalDateTime.now();
		po.setDocNumber(GenerateDocNumber.documentNumberGeneration("PO"+(String)dtf.format(now) +"0",count));
		}

		if (rfq != null) {
			po.setDocumentDate(rfq.getDocumentDate());
			po.setStatus(EnumStatusUpdate.OPEN.getStatus());
			po.setPostingDate(rfq.getPostingDate());
			po.setCategory(rfq.getCategory());
			po.setRemark(rfq.getRemark());
			po.setDeliverTo(rfq.getDeliverTo());
			po.setReferenceDocNumber(rfq.getDocNumber());
			po.setRequiredDate(rfq.getRequiredDate());
			po.setRfqId(rfq);
			po.setIsActive(true);
			po.setVendor(rfq.getVendor());
			po.setVendorContactDetails(rfq.getVendorContactDetails());
			po.setVendorPayTypeAddress(rfq.getVendorPayTypeAddress());
			po.setVendorShippingAddress(rfq.getVendorShippingAddress());
			
			
			
			List<LineItems> rfqItms = rfq.getLineItems();
			List<PurchaseOrderLineItems> lineItems =new ArrayList<PurchaseOrderLineItems>();
			if (rfqItms != null) {
				for (int i = 0; i < rfqItms.size(); i++) {
					PurchaseOrderLineItems line = new PurchaseOrderLineItems();
					line.setProdouctNumber(rfqItms.get(i).getProdouctNumber());
					line.setProductGroup(rfqItms.get(i).getProductGroup());
					line.setDescription(rfqItms.get(i).getDescription());
					line.setHsn(rfqItms.get(i).getHsn());
					line.setRequiredQuantity(rfqItms.get(i).getRequiredQuantity());
					line.setSacCode(rfqItms.get(i).getSacCode());
					line.setUom(rfqItms.get(i).getUom());
					line.setSku(rfqItms.get(i).getSku());
					line.setUnitPrice(rfqItms.get(i).getUnitPrice()); // Set Unit Price
					
					//line.setSacCode(rfqItms.get(i).getSku());
					
					line.setWarehouse(rfqItms.get(i).getWarehouse());
					line.setProductId(rfqItms.get(i).getProductId());
					lineItems.add(line);
				}
			}
			
			po.setPurchaseOrderlineItems(lineItems);
			
		}
		po.setCategory("Item");
		po = purchaseOrderRepository.save(po);
		
		rfq.setCategory("Item");
		rfq.setStatus(EnumStatusUpdate.CONVERTRFQTOPO.getStatus());
		requestForQuotationRepository.save(rfq);
		
		PurchaseRequest pr = new PurchaseRequest();
		pr = po.getRfqId().getPurchaseReqId();
		if(pr!=null) {
			pr.setStatus(EnumStatusUpdate.CONVERTPRTORFQ.getStatus());
			purchaseRequestRepository.save(pr);
			
			PurchaseRequest prRfqRef = po.getRfqId().getPurchaseReqId();
			
			List<RequestForQuotation> rfqList =  requestForQuotationService.getRFQListById(prRfqRef);
			
			for(RequestForQuotation rfqItem:rfqList) {
				if(!rfqItem.getStatus().equalsIgnoreCase(EnumStatusUpdate.CONVERTRFQTOPO.getStatus())) {
					rfqItem.setStatus(EnumStatusUpdate.CANCELED.getStatus());
					logger.info("rfq-->" + rfq);
					rfqItem.setIsActive(false);
				}
			}
			
		}
		
		return po;
        }else {
        	return dup_po;
        }
	}
	
	
	
	@Override
	public PurchaseOrder saveCancelStage(String rfqId) {
		PurchaseOrder po = purchaseOrderRepository.findById(Integer.parseInt(rfqId)).get();
		po.setStatus(EnumStatusUpdate.CANCELED.getStatus());
		po.setCategory("Item");
		purchaseOrderRepository.save(po);
		return po;
		
	}

	
	@Override
	public List<PurchaseOrder> poApprovedList() {

		return purchaseOrderRepository.poApprovedList(EnumStatusUpdate.APPROVEED.getStatus());
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
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");

	@Override
	public PurchaseOrder getListAmount(PurchaseOrder purchaseOrder) {
		
		List<PurchaseOrderLineItems> listItems = purchaseOrder.getPurchaseOrderlineItems();
		List<PurchaseOrderLineItems> addListItems = new ArrayList<PurchaseOrderLineItems>();
		Double addAmt=0.00;
		Double addTaxAmt=0.00;
		Double total = 0.00;
		Double total_payment = 0.00;
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				PurchaseOrderLineItems polist = listItems.get(i);
				if(polist.getUnitPrice()!=null  && polist.getTaxCode()!=null ) {
				addTaxAmt += UnitPriceListItems.getTaxAmt(polist.getRequiredQuantity(),polist.getUnitPrice(),polist.getTaxCode());
				addAmt +=UnitPriceListItems.getTotalINVAmt(polist.getRequiredQuantity(),polist.getUnitPrice());
				polist.setTaxTotal(""+UnitPriceListItems.getTaxAmt(polist.getRequiredQuantity(),polist.getUnitPrice(),polist.getTaxCode()));
				polist.setTotal(""+UnitPriceListItems.getTotalINVAmt(polist.getRequiredQuantity(),polist.getUnitPrice()));
				}else {
				polist.setTaxTotal("");
				polist.setTotal("");	
				}
				addListItems.add(polist);
				
			}
			purchaseOrder.setPurchaseOrderlineItems(addListItems);
			
		}
		
		purchaseOrder.setTotalBeforeDisAmt(addAmt);
		purchaseOrder.setTaxAmt(""+addTaxAmt);
		
		if(purchaseOrder.getTotalPayment()!=null && purchaseOrder.getTotalPayment() != 0) {
			//total = ((addAmt - ( (addAmt * purchaseOrder.getTotalDiscount())/100 )) + purchaseOrder.getFreight());
		  total = UnitPriceListItems.getTotalAmtPayment(addAmt, purchaseOrder.getTotalDiscount(), purchaseOrder.getFreight(),addTaxAmt);
		  logger.info("total ---> " + total);
		  logger.info("(purchaseOrder.getTotalPayment() ---> " + df2.format(purchaseOrder.getTotalPayment()));
		  purchaseOrder.setAmtRounding(""+ total);
		  purchaseOrder.setRoundedOff(""+ df2.format(purchaseOrder.getTotalPayment() - total));
		}else {
			purchaseOrder.setAmtRounding(""+ 0.0);
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

	@Override
	public boolean findByDocNumber(String docNum) {
		List<PurchaseOrder> poList = purchaseOrderRepository.findByDocNumber(docNum);
		if(poList.size()>0) {
			return true;
		}else {
			return false;
		}
	}

}
