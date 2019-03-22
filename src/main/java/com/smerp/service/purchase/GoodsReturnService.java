package com.smerp.service.purchase;

import java.util.List;

import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.GoodsReturn;

public interface GoodsReturnService {

	GoodsReturn save(GoodsReturn goodsReturn);

	GoodsReturn saveGRE(String goodsReturnId);

	GoodsReturn saveCancelStage(String goodsReturnId);

	GoodsReturn findLastDocumentNumber();

	List<GoodsReturn> findAll();

	GoodsReturn findById(int id);
	
	GoodsReturn getGoodsReturnById(int id);

	GoodsReturn delete(int id);

	List<GoodsReturn> findByIsActive();
	
	GoodsReturn getListAmount(GoodsReturn goodsReturn);
	
	Boolean checkQuantityGr(GoodsReceipt goodsReceipt);
	
	List<GoodsReturn> findByGoodsReceiptId(GoodsReceipt gr,String status);

}
