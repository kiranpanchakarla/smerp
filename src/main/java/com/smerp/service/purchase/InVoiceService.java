package com.smerp.service.purchase;

import java.util.List;

import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.InVoice;
import com.smerp.model.inventory.PurchaseOrder;

public interface InVoiceService {

	InVoice save(InVoice purchaseOrder);

	InVoice saveInv(String purchaseId);

	InVoice saveCancelStage(String purchaseId);

	InVoice findLastDocumentNumber();

	List<InVoice> findAll();

	InVoice findById(int id);

	InVoice delete(int id);

	List<InVoice> findByIsActive();
	
	InVoice getListAmount(InVoice purchaseOrder);
	
	Boolean checkQuantityPoGr(PurchaseOrder purchaseOrder);
	
	String setStatusOfPurchaseOrder(InVoice InVoice);
	
	List<InVoice> invApprovedList();
	

}
