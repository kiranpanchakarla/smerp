package com.smerp.serviceImpl.purchase;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.model.purchase.PurchaseRequestList;
import com.smerp.repository.purchase.PurchaseRequestListRepository;
import com.smerp.repository.purchase.PurchaseRequestRepository;
import com.smerp.service.purchase.PurchaseRequestService;
import com.smerp.util.EnumStatusUpdate;

@Service
public class PurchaseRequestServiceImpl implements PurchaseRequestService {

	
	@Autowired
	PurchaseRequestRepository purchaseRequestRepository;

	@Autowired
	PurchaseRequestListRepository purchaseRequestListRepository;
	
	private static final Logger logger = LogManager.getLogger(PurchaseRequestServiceImpl.class);
	@Override
	public PurchaseRequest save(PurchaseRequest purchaseRequest) {
		
		switch (purchaseRequest.getStatusType()) { 
        case "DR": 
        	purchaseRequest.setStatus(EnumStatusUpdate.DRAFT.getStatus());
            break; 
        case "SA": 
        	purchaseRequest.setStatus(EnumStatusUpdate.OPEN.getStatus());
            break; 
        case "RE":  
        	purchaseRequest.setStatus(EnumStatusUpdate.REJECTED.getStatus());
            break; 
        case "APP": 
        	purchaseRequest.setStatus(EnumStatusUpdate.APPROVEED.getStatus());
            break; 
        case "CA":
        	purchaseRequest.setStatus(EnumStatusUpdate.CANCELED.getStatus());
			break;
        default: 
        	logger.info("Type Not Matched:"+purchaseRequest.getStatusType());
            break; 
        } 
		
		
		if (purchaseRequest.getId() != null) {
			PurchaseRequest purchaseRequest123 = purchaseRequestRepository.findById(purchaseRequest.getId()).get();
			List<PurchaseRequestList> purchaseRequestLists = purchaseRequest123.getPurchaseRequestLists();
			for (PurchaseRequestList purchaseRequest1 : purchaseRequestLists) {
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
	public PurchaseRequest saveCancelStage(String prId) {
		PurchaseRequest prq = purchaseRequestRepository.findById(Integer.parseInt(prId)).get();
		prq.setStatus(EnumStatusUpdate.CANCELED.getStatus());
		purchaseRequestRepository.save(prq);
		return prq;
	}
	
	
	@Override
	public List<PurchaseRequest> findByIsActive() {

		return purchaseRequestRepository.findByIsActive(true);
	}

	@Override
	public List<PurchaseRequest> prApprovedList() {

		return purchaseRequestRepository.prApprovedList(EnumStatusUpdate.APPROVEED.getStatus());
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
        
		return purchaseRequestRepository.findById(purchaseReqId).get();
	}

	

 
}
