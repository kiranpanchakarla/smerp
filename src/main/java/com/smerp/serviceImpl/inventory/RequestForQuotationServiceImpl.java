package com.smerp.serviceImpl.inventory;

import java.util.List;

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
	public RequestForQuotation findById(int id) {
		return requestForQuotationRepository.findById(id).get();
	}

}
