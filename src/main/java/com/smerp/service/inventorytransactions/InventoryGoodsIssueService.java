package com.smerp.service.inventorytransactions;

import java.util.List;
import com.smerp.model.inventorytransactions.InventoryGoodsIssue;

public interface InventoryGoodsIssueService {

    InventoryGoodsIssue save(InventoryGoodsIssue inventoryGoodsIssue);
	
	List<InventoryGoodsIssue> findAll();
	
	InventoryGoodsIssue findById(int id);
	
	InventoryGoodsIssue delete(int id);
	
	List<InventoryGoodsIssue>  findByIsActive();
	
	InventoryGoodsIssue findLastDocumentNumber();
	
	InventoryGoodsIssue getListAmount(InventoryGoodsIssue inventoryGoodsIssue);
}
