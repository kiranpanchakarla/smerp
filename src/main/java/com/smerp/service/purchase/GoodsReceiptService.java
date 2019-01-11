package com.smerp.service.purchase;

import java.util.List;

import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.GoodsReturn;
import com.smerp.model.inventory.PurchaseOrder;

public interface GoodsReceiptService {

	GoodsReceipt save(GoodsReceipt purchaseOrder);

	GoodsReceipt saveGR(String purchaseId);

	GoodsReceipt saveCancelStage(String purchaseId);

	GoodsReceipt findLastDocumentNumber();

	List<GoodsReceipt> findAll();

	GoodsReceipt findById(int id);

	GoodsReceipt delete(int id);

	List<GoodsReceipt> findByIsActive();
	
	GoodsReceipt getListAmount(GoodsReceipt purchaseOrder);
	
	Boolean checkQuantityPoGr(PurchaseOrder purchaseOrder);
	
	String setStatusOfPurchaseOrder(GoodsReceipt goodsReceipt);
	

}
