package com.smerp.service.purchase;

import java.util.List;

import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.model.search.SearchFilter;

public interface PurchaseRequestService {

	PurchaseRequest save(PurchaseRequest purchaseRequest);
	
	PurchaseRequest saveCancelStage(String prId);

	List<PurchaseRequest> findByIsActive();
	
	List<PurchaseRequest> prApprovedList();

	PurchaseRequest delete(int id);

	PurchaseRequest findLastDocumentNumber();

	PurchaseRequest getInfo(int purchaseReqId);
	
	boolean findByDocNumber(String pr);
	
	List<PurchaseRequest> searchFilterBySelection(SearchFilter searchFilter);

}
