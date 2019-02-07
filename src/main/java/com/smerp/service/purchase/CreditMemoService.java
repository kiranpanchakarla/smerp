package com.smerp.service.purchase;

import java.util.List;

import com.smerp.model.inventory.InVoice;
import com.smerp.model.inventory.CreditMemo;

public interface CreditMemoService {

	CreditMemo save(CreditMemo purchaseOrder);

	CreditMemo saveCM(String purchaseId);

	CreditMemo saveCancelStage(String purchaseId);

	CreditMemo findLastDocumentNumber();

	List<CreditMemo> findAll();

	CreditMemo findById(int id);
	
	CreditMemo getCreditMemoById(int id);

	CreditMemo delete(int id);

	List<CreditMemo> findByIsActive();
	
	CreditMemo getListAmount(CreditMemo purchaseOrder);
	
	Boolean checkQuantityInv(InVoice InVoice);
	

}
