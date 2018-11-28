package com.smerp.serviceImpl.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.model.purchase.PurchaseRequestList;
import com.smerp.repository.purchase.PurchaseRequestListRepository;
import com.smerp.repository.purchase.PurchaseRequestRepository;
import com.smerp.service.purchase.PurchaseRequestService;

@Service
public class PurchaseRequestServiceImpl  implements PurchaseRequestService {
	
	
	@Autowired
	PurchaseRequestRepository  purchaseRequestRepository;
	
	
	@Autowired
	PurchaseRequestListRepository  purchaseRequestListRepository;


	@Override
	public PurchaseRequest save(PurchaseRequest purchaseRequest) {
		
		if(purchaseRequest.getId() !=null) {
		PurchaseRequest purchaseRequest123 = purchaseRequestRepository.findById(purchaseRequest.getId()).get();
		List<PurchaseRequestList>	purchaseRequestLists = purchaseRequest123.getPurchaseRequestLists();
		   	 for (PurchaseRequestList purchaseRequest1: purchaseRequestLists) { 
		   		purchaseRequestListRepository.deleteAll(purchaseRequestLists);
		     }
		}
		List<PurchaseRequestList> ltms = purchaseRequest.getPurchaseRequestLists();
		if (ltms != null) {
			for (int i = 0; i < ltms.size(); i++) {
				if (ltms.get(i).getDescription() == null) {
					ltms.remove(i);
				}
			}
			purchaseRequest.setPurchaseRequestLists(ltms);
		}
		
		return purchaseRequestRepository.save(purchaseRequest);
	}

	@Override
	public List<PurchaseRequest> findByIsActive() {
		
		return purchaseRequestRepository.findByIsActive(true);
	}

	@Override
	public PurchaseRequest delete(int id) {
		
		PurchaseRequest purchaseRequest = purchaseRequestRepository.findById(id).get();
		purchaseRequest.setIsActive(false);
		return purchaseRequestRepository.save(purchaseRequest);
	}

	@Override
	public PurchaseRequest findLastDocumentNumber() {
		
		return purchaseRequestRepository.findTopByOrderByIdDesc();
	}

	@Override
	public PurchaseRequest getInfo(int purchaseReqId) {
		
		return  purchaseRequestRepository.findById(purchaseReqId).get();
	}
	
}
