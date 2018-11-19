package com.smerp.service.inventory;

import java.util.List;

import com.smerp.model.inventory.RequestForQuotation;

public interface RequestForQuotationService {

	RequestForQuotation save(RequestForQuotation requestForQuotation);

	RequestForQuotation findLastDocumentNumber();

	List<RequestForQuotation> findAll();

	RequestForQuotation findById(int id);

}
