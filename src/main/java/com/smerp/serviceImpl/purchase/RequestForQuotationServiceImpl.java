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
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.model.purchase.PurchaseRequestList;
import com.smerp.repository.purchase.LineitemsRepositoryRepository;
import com.smerp.repository.purchase.PurchaseRequestRepository;
import com.smerp.repository.purchase.RequestForQuotationRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.VendorAddressService;
import com.smerp.service.inventory.VendorsContactDetailsService;
import com.smerp.service.purchase.PurchaseRequestService;
import com.smerp.service.purchase.RequestForQuotationService;
import com.smerp.util.EmailGenerator;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.RequestContext;

@Service
@Transactional
public class RequestForQuotationServiceImpl implements RequestForQuotationService {

	private static final Logger logger = LogManager.getLogger(RequestForQuotationServiceImpl.class);

	@Autowired
	RequestForQuotationRepository requestForQuotationRepository;

	@Autowired
	LineitemsRepositoryRepository lineitemsRepository;

	@Autowired
	VendorService vendorService;
	
	@Autowired
	VendorAddressService vendorAddressService;
	
	@Autowired
	VendorsContactDetailsService vendorsContactDetailsService;

	@Autowired
	PurchaseRequestService purchaseRequestService;
	
	@Autowired
	PurchaseRequestRepository purchaseRequestRepository;
	
	@Autowired
	EmailGenerator emailGenerator;
	
	@Override
	public RequestForQuotation save(RequestForQuotation requestForQuotation) {
		requestForQuotation.setCategory("Item");
		switch (requestForQuotation.getStatusType()) {
		case "DR":
			requestForQuotation.setStatus(EnumStatusUpdate.DRAFT.getStatus());
			break;
		case "SA":
			requestForQuotation.setStatus(EnumStatusUpdate.OPEN.getStatus());
			break;

		case "RE":
			requestForQuotation.setStatus(EnumStatusUpdate.REJECTED.getStatus());
			break;
		case "APP":
			requestForQuotation.setStatus(EnumStatusUpdate.APPROVEED.getStatus());
			break;
		case "CA":
			requestForQuotation.setStatus(EnumStatusUpdate.CANCELED.getStatus());
			break;
		default:
			logger.info("Type Not Matched:" + requestForQuotation.getStatusType());
			break;
		}

		if (requestForQuotation.getId() != null) { // delete List Of Items.
			RequestForQuotation requestForListOfItems = requestForQuotationRepository
					.findById(requestForQuotation.getId()).get();
			List<LineItems> requestLists = requestForListOfItems.getLineItems();
			
			if(requestForQuotation.getPurchaseReqId()==null) {  // if PurchaseReqId null delete list items 
				lineitemsRepository.deleteAll(requestLists);
			    List<LineItems> listItems = requestForQuotation.getLineItems();
			    if (listItems != null) {
				for (int i = 0; i < listItems.size(); i++) {
					if (listItems.get(i).getProdouctNumber() == null && listItems.get(i).getSacCode() == null) {
						listItems.remove(i);
					}
				}
				requestForQuotation.setLineItems(listItems);
			}
			
			}else {
				requestForQuotation.setLineItems(requestLists);
			}
			
			
		}

		

		Vendor vendor = vendorService.findById(requestForQuotation.getVendor().getId());
		VendorAddress vendorShippingAddress = vendorAddressService.findById(requestForQuotation.getVendorShippingAddress().getId());
		VendorAddress vendorPayAddress = vendorAddressService.findById(requestForQuotation.getVendorPayTypeAddress().getId());
		
		VendorsContactDetails vendorsContactDetails =vendorsContactDetailsService.findById(requestForQuotation.getVendorContactDetails().getId());

		requestForQuotation.setVendor(vendor);
		requestForQuotation.setVendorContactDetails(vendorsContactDetails);
		requestForQuotation.setVendorShippingAddress(vendorShippingAddress);
		requestForQuotation.setVendorPayTypeAddress(vendorPayAddress);
		
		if(requestForQuotation.getStatusType()!=null &&  requestForQuotation.getStatusType().equals("APP")) {
			try {
    			 RequestContext.initialize();
    		     RequestContext.get().getConfigMap().put("mail.template", "requestForQuotationEmail.ftl");  //Sending Email
    		     emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendRFQEmail(requestForQuotation);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		}
			

		return requestForQuotationRepository.save(requestForQuotation);
	}

	@Override
	public RequestForQuotation saveRFQ(String purchaseId) {

		RequestForQuotation rfq = new RequestForQuotation();
		PurchaseRequest prq = purchaseRequestService.getInfo(Integer.parseInt(purchaseId));
		
		RequestForQuotation dup_rfq =requestForQuotationRepository.findByPurchaseReqId(prq.getId());  // check PR exist in RFQ
        if(dup_rfq==null) { 
		RequestForQuotation rfqdetails = findLastDocumentNumber();
		if (rfqdetails != null && rfqdetails.getDocNumber() != null) {
			rfq.setDocNumber(GenerateDocNumber.documentNumberGeneration(rfqdetails.getDocNumber()));
		} else {
			  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
			  LocalDateTime now = LocalDateTime.now();
			  rfq.setDocNumber(GenerateDocNumber.documentNumberGeneration("RFQ"+(String)dtf.format(now) +"0"));
		}

		if (prq != null) {

			rfq.setDocumentDate(prq.getDocumentDate());
			rfq.setStatus(EnumStatusUpdate.OPEN.getStatus());
			rfq.setPostingDate(prq.getPostingDate());
			rfq.setCategory(prq.getType());
			rfq.setRemark(prq.getRemarks());
			rfq.setReferenceDocNumber(prq.getDocNumber());
			rfq.setRequiredDate(prq.getRequiredDate());
			rfq.setPurchaseReqId(prq.getId());
			rfq.setIsActive(true);
			
			
			
			
			List<PurchaseRequestList> prItms = prq.getPurchaseRequestLists();
			List<LineItems> lineItems =new ArrayList<LineItems>();
			if (prItms != null) {
				for (int i = 0; i < prItms.size(); i++) {
					LineItems line = new LineItems();
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
			
			rfq.setLineItems(lineItems);
			
		}
		
		rfq.setCategory("Item");
		rfq = requestForQuotationRepository.save(rfq);
		
		prq.setStatus(EnumStatusUpdate.CONVERTPRTORFQ.getStatus());
		prq.setType("Item");
		purchaseRequestRepository.save(prq);
		
		return rfq;
        }else {
        	return dup_rfq;
        }
	}
	
	
	@Override
	public RequestForQuotation saveCancelStage(String rfqId) {
		RequestForQuotation rfq = requestForQuotationRepository.findById(Integer.parseInt(rfqId)).get();
		rfq.setStatus(EnumStatusUpdate.CANCELED.getStatus());
		rfq.setCategory("Item");
		requestForQuotationRepository.save(rfq);
		return rfq;
		
	}

	@Override
	public List<RequestForQuotation> rfqApprovedList() {

		return requestForQuotationRepository.rfqApprovedList(EnumStatusUpdate.APPROVEED.getStatus());
	}
	
	
	@Override
	public RequestForQuotation findLastDocumentNumber() {
		return requestForQuotationRepository.findTopByOrderByIdDesc();
	}

	@Override
	public List<RequestForQuotation> findAll() {
		return requestForQuotationRepository.findAll();
	}

	@Override
	public List<RequestForQuotation> findByIsActive() {
		return requestForQuotationRepository.findByIsActive(true);
	}

	@Override
	public RequestForQuotation findById(int id) {
		return requestForQuotationRepository.findById(id).get();
	}
	
	
	@Override
	public RequestForQuotation delete(int id) {
		RequestForQuotation requestForQuotation = requestForQuotationRepository.findById(id).get();
		requestForQuotation.setIsActive(false);
		requestForQuotationRepository.save(requestForQuotation);
		return requestForQuotation;
	}

}
