package com.smerp.service.inventorytransactions;

import java.util.List;
import com.smerp.model.inventorytransactions.InventoryGoodsTransfer;

public interface InventoryGoodsTransferService {

	InventoryGoodsTransfer save(InventoryGoodsTransfer inventoryGoodsTransfer);
	
	List<InventoryGoodsTransfer> findAll();
	
	InventoryGoodsTransfer findById(int id);
	
	InventoryGoodsTransfer delete(int id);
	
	List<InventoryGoodsTransfer>  findByIsActive();
	
	InventoryGoodsTransfer findLastDocumentNumber();
	
	InventoryGoodsTransfer getInventoryGoodsTransferId(int id);
	
	InventoryGoodsTransfer getListAmount(InventoryGoodsTransfer inventoryGoodsTransfer);
	
	boolean findByDocNumber(String docNum);
}
