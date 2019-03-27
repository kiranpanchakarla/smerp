package com.smerp.service.purchase;

import java.util.List;

import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.search.SearchFilter;

public interface PurchaseOrderService {

	PurchaseOrder save(PurchaseOrder purchaseOrder);

	PurchaseOrder savePO(String purchaseId);

	PurchaseOrder saveCancelStage(String purchaseId);

	PurchaseOrder findLastDocumentNumber();

	List<PurchaseOrder> findAll();

	PurchaseOrder findById(int id);

	PurchaseOrder delete(int id);

	List<PurchaseOrder> findByIsActive();
	
	PurchaseOrder getListAmount(PurchaseOrder purchaseOrder);

	List<PurchaseOrder> poApprovedList();
	
	boolean findByDocNumber(String docNum);
	
	List<PurchaseOrder> searchFilterBySelection(SearchFilter searchFilter);

}
