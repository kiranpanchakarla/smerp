package com.smerp.serviceImpl.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.Vendor;
import com.smerp.model.inventory.LineItems;
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.model.purchase.PurchaseRequestList;
import com.smerp.repository.inventory.LineitemsRepositoryRepository;
import com.smerp.repository.inventory.RequestForQuotationRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.RequestForQuotationService;

@Service
public class RequestForQuotationServiceImpl implements RequestForQuotationService {
	
	@Autowired
	RequestForQuotationRepository requestForQuotationRepository;
	
	@Autowired
	LineitemsRepositoryRepository lineitemsRepository;
	
	@Autowired
	VendorService vendorService;

	@Override
	public RequestForQuotation save(RequestForQuotation requestForQuotation) {
		
		if(requestForQuotation.getId() !=null) {   //  delete List Of Items.
	    			RequestForQuotation requestForListOfItems = requestForQuotationRepository.findById(requestForQuotation.getId()).get();
	        		List<LineItems>	 requestLists = requestForListOfItems.getLineItems();
	        		   	 for (LineItems lineObj: requestLists) { 
	        		   		lineitemsRepository.deleteAll(requestLists);
	        		     }
	        		   	List<LineItems> listItems = requestForQuotation.getLineItems();
		        		if (listItems != null) {
		        			for (int i = 0; i < listItems.size(); i++) {
		        				if (listItems.get(i).getProdouctNumber() == null && listItems.get(i).getSacCode() ==null) {
		        					listItems.remove(i);
		        				}
		        			}
		        			requestForQuotation.setLineItems(listItems);
		        		}
		}
	        		
	        		
	        		
		
		
		Vendor vendor=vendorService.findById(requestForQuotation.getVendor().getId());
		
		requestForQuotation.setVendor(vendor);
				
		return requestForQuotationRepository.save(requestForQuotation);
	}

	@Override
	public RequestForQuotation	 findLastDocumentNumber() {
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
