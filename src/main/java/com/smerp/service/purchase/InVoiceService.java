package com.smerp.service.purchase;

import java.util.List;

import com.smerp.model.inventory.InVoice;
import com.smerp.model.inventory.LineItemsBean;

public interface InVoiceService {

	InVoice save(InVoice inVoice);

	InVoice saveInv(String invoiceId);

	InVoice saveCancelStage(String invoiceId);

	InVoice findLastDocumentNumber();

	List<InVoice> findAll();

	InVoice findById(int id);
	
	InVoice getInVoiceById(int id);

	InVoice delete(int id);

	List<InVoice> findByIsActive();
	
	InVoice getListAmount(InVoice inVoice);
	
	/*Boolean checkQuantityPoGr(PurchaseOrder purchaseOrder);*/
	
	List<InVoice> invApprovedList();
	
	boolean findByDocNumber(String invDocNum);
	
	List<LineItemsBean> getLineItemsBean (int id);

}
