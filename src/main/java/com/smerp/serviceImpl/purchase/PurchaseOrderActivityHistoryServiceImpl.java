package com.smerp.serviceImpl.purchase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.inventory.PurchaseOrderActivityHistory;
import com.smerp.repository.purchase.PurchaseOrderActivityHistoryRepository;
import com.smerp.service.purchase.PurchaseOrderActivityHistoryService;

@Service
public class PurchaseOrderActivityHistoryServiceImpl implements PurchaseOrderActivityHistoryService {

	@Autowired
	private PurchaseOrderActivityHistoryRepository purchaseOrderActivityHistoryRepository;
	
	@Override
	public PurchaseOrderActivityHistory save(PurchaseOrderActivityHistory poah) {
		return purchaseOrderActivityHistoryRepository.save(poah);
	}
	
	@Override
	public List<PurchaseOrderActivityHistory> findByHistoryListByPOId(Integer id){
		return purchaseOrderActivityHistoryRepository.findByHistoryListByPOId(id);
	}
	
	@Override
	public boolean ifPOAvailableOrNot(Integer id) {
		List<PurchaseOrderActivityHistory> poahList = new ArrayList<PurchaseOrderActivityHistory>();
		poahList = purchaseOrderActivityHistoryRepository.findByHistoryListByPOId(id);
		if(poahList.size()>0) {
			return true;
		}else {
			return false;
		}
	}
}
