package com.smerp.service.purchase;

import java.util.List;
import com.smerp.model.purchase.PurchaseRequest;

public interface PurchaseRequestService {

	PurchaseRequest save(PurchaseRequest purchaseRequest);

	List<PurchaseRequest> findByIsActive();
	
	List<PurchaseRequest> prApprovedList();

	PurchaseRequest delete(int id);

	PurchaseRequest findLastDocumentNumber();

	PurchaseRequest getInfo(int purchaseReqId);
	
	
	

}
