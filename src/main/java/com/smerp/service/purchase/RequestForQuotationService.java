package com.smerp.service.purchase;

import java.util.List;

import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.util.EnumStatusUpdate;

public interface RequestForQuotationService {
	

	RequestForQuotation save(RequestForQuotation requestForQuotation);
	
	RequestForQuotation saveRFQ(String purchaseId);
	
	RequestForQuotation saveCancelStage(String purchaseId);

	RequestForQuotation findLastDocumentNumber();

	List<RequestForQuotation> findAll();

	RequestForQuotation findById(int id);
	
	RequestForQuotation delete(int id);
	
	List<RequestForQuotation> findByIsActive();
	
	List<RequestForQuotation> rfqApprovedList();
	
	boolean findByDocNumber(String rfqDocNum);
	
	boolean isVendorNameExistWithDocNum(String vendorName, String refDocNum);
	
	List<RequestForQuotation> getRFQListById(PurchaseRequest purchaseId);

	Integer getRFQListCount(PurchaseRequest prId);
}
