package com.smerp.service.purchase;

import java.util.List;

import com.smerp.model.inventory.PurchaseOrderActivityHistory;

public interface PurchaseOrderActivityHistoryService {
	PurchaseOrderActivityHistory save(PurchaseOrderActivityHistory poah);
	List<PurchaseOrderActivityHistory> findByHistoryListByPOId(Integer id);
	boolean ifPOAvailableOrNot(Integer id);
}
