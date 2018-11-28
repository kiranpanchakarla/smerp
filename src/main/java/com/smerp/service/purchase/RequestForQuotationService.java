package com.smerp.service.purchase;

import java.util.List;

import com.smerp.model.inventory.RequestForQuotation;

public interface RequestForQuotationService {
	

	RequestForQuotation save(RequestForQuotation requestForQuotation);

	RequestForQuotation findLastDocumentNumber();

	List<RequestForQuotation> findAll();

	RequestForQuotation findById(int id);
	
	RequestForQuotation delete(int id);
	
	List<RequestForQuotation> findByIsActive();

}
