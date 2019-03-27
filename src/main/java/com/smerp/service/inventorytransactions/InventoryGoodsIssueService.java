package com.smerp.service.inventorytransactions;

import java.util.List;
import com.smerp.model.inventorytransactions.InventoryGoodsIssue;
import com.smerp.model.search.SearchFilter;

public interface InventoryGoodsIssueService {

    InventoryGoodsIssue save(InventoryGoodsIssue inventoryGoodsIssue);
	
	List<InventoryGoodsIssue> findAll();
	
	InventoryGoodsIssue findById(int id);
	
	InventoryGoodsIssue delete(int id);
	
	List<InventoryGoodsIssue>  findByIsActive();
	
	InventoryGoodsIssue findLastDocumentNumber();
	
	InventoryGoodsIssue getListAmount(InventoryGoodsIssue inventoryGoodsIssue);
	
	boolean findByDocNumber(String docNum);
	
	String getInStock(String productNo,Integer warehouse);
	
	InventoryGoodsIssue getinventoryGoodsIssueId(int id);
	
	List<InventoryGoodsIssue> searchFilterBySelection(SearchFilter searchFilter);
}
