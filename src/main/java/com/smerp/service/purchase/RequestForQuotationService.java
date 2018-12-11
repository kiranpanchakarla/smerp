package com.smerp.service.purchase;

import java.util.List;

import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.purchase.PurchaseRequest;

public interface RequestForQuotationService {
	

	RequestForQuotation save(RequestForQuotation requestForQuotation);
	
	RequestForQuotation saveRFQ(String purchaseId);

	RequestForQuotation findLastDocumentNumber();

	List<RequestForQuotation> findAll();

	RequestForQuotation findById(int id);
	
	RequestForQuotation delete(int id);
	
	List<RequestForQuotation> findByIsActive();

}
