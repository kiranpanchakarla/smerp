package com.smerp.service.purchase;

import java.util.List;

import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.LineItemsBean;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.search.SearchFilter;

public interface GoodsReceiptService {

	GoodsReceipt save(GoodsReceipt goodsReceipt);

	GoodsReceipt saveGR(String goodsReceiptId);

	GoodsReceipt saveCancelStage(String goodsReceiptId);

	GoodsReceipt findLastDocumentNumber();

	List<GoodsReceipt> findAll();

	GoodsReceipt findById(int id);
	
	GoodsReceipt getGoodsReceiptById(int id);
	
	GoodsReceipt getGoodsReceiptViewById(int id);

	GoodsReceipt delete(int id);

	List<GoodsReceipt> findByIsActive();
	
	GoodsReceipt getListAmount(GoodsReceipt goodsReceipt);
	
	GoodsReceipt setStatusOfGoodsReceipt(GoodsReceipt goodsReceipt);
	
	Boolean checkQuantityPoGr(PurchaseOrder purchaseOrder);
	
	PurchaseOrder setStatusOfPurchaseOrder(GoodsReceipt goodsReceipt);
	
	List<GoodsReceipt> grApprovedList();
	
	List<LineItemsBean> getLineItemsBean(int id);
	
	boolean findByDocNumber(String grDocNum);
	
	List<GoodsReceipt> searchFilterBySelection(SearchFilter searchFilter);
	
	boolean getGoodsReturn(GoodsReceipt goodsReceipt);
}
