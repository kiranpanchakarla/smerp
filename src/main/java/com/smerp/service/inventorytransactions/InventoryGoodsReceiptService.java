package com.smerp.service.inventorytransactions;

import java.util.List;

import com.smerp.model.inventorytransactions.InventoryGoodsReceipt;

public interface InventoryGoodsReceiptService {

	InventoryGoodsReceipt save(InventoryGoodsReceipt inventoryGoodsReceipt);
	
	List<InventoryGoodsReceipt> findAll();
	
	InventoryGoodsReceipt findById(int id);
	
	InventoryGoodsReceipt delete(int id);
	
	List<InventoryGoodsReceipt>  findByIsActive();
	
	InventoryGoodsReceipt findLastDocumentNumber();
	
	InventoryGoodsReceipt getListAmount(InventoryGoodsReceipt inventoryGoodsReceipt);
	
	}
