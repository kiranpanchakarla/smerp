package com.smerp.service.purchase;

import java.util.List;

import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.GoodsReturn;

public interface GoodsReturnService {

	GoodsReturn save(GoodsReturn purchaseOrder);

	GoodsReturn saveGRE(String purchaseId);

	GoodsReturn saveCancelStage(String purchaseId);

	GoodsReturn findLastDocumentNumber();

	List<GoodsReturn> findAll();

	GoodsReturn findById(int id);

	GoodsReturn delete(int id);

	List<GoodsReturn> findByIsActive();
	
	GoodsReturn getListAmount(GoodsReturn purchaseOrder);
	
	Boolean checkQuantityGr(GoodsReceipt goodsReceipt);
	

}