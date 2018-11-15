package com.smerp.serviceImpl.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.Vendor;
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.repository.inventory.RequestForQuotationRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.RequestForQuotationService;

@Service
public class RequestForQuotationServiceImpl implements RequestForQuotationService {
	
	@Autowired
	RequestForQuotationRepository requestForQuotationRepository;
	
	@Autowired
	VendorService vendorService;

	@Override
	public RequestForQuotation save(RequestForQuotation requestForQuotation) {
		Vendor vendor=vendorService.findById(Integer.parseInt(requestForQuotation.getVendorId()));
		requestForQuotation.setVendor(vendor);
		requestForQuotation.setStatus("Draft");
		requestForQuotation.setDocNumber("doc123");
		requestForQuotation.setReferenceDocNumber("refDocNumber");
		return requestForQuotationRepository.save(requestForQuotation);
	}

}
