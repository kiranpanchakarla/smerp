package com.smerp.serviceImpl.purchase;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.model.purchase.PurchaseRequestList;
import com.smerp.repository.purchase.PurchaseRequestListRepository;
import com.smerp.repository.purchase.PurchaseRequestRepository;
import com.smerp.service.master.PlantService;
import com.smerp.service.purchase.PurchaseRequestService;
import com.smerp.util.EmailGenerator;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.RequestContext;

@Service
@Transactional
public class PurchaseRequestServiceImpl implements PurchaseRequestService {

	
	@Autowired
	PurchaseRequestRepository purchaseRequestRepository;

	@Autowired
	PurchaseRequestListRepository purchaseRequestListRepository;
	
	@Autowired
	EmailGenerator emailGenerator;
	
	@Autowired
	PlantService plantService;
	
	private static final Logger logger = LogManager.getLogger(PurchaseRequestServiceImpl.class);
	@Override
	public PurchaseRequest save(PurchaseRequest purchaseRequest) {
		purchaseRequest.setType("Item");
		
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
		
		List<PurchaseRequestList> ltms = purchaseRequest.getPurchaseRequestLists();
		if (ltms != null) {
			for (int i = 0; i < ltms.size(); i++) {
				if (ltms.get(i).getDescription() == null) {
					ltms.remove(i);
					i--;
				}
			}
			purchaseRequest.setPurchaseRequestLists(ltms);
		}
		
		if (purchaseRequest.getId() != null) {
			PurchaseRequest purchaseRequest_Obj = purchaseRequestRepository.findById(purchaseRequest.getId()).get();
			List<PurchaseRequestList> purchaseRequestLists = purchaseRequest_Obj.getPurchaseRequestLists();
				purchaseRequestListRepository.deleteAll(purchaseRequestLists);
		}
		
		 if(purchaseRequest.getStatus()!=null &&  !purchaseRequest.getStatus().equals(EnumStatusUpdate.DRAFT.getStatus())) {
			 try {
				 if(purchaseRequest.getId()!=null) {
					PurchaseRequest purchaseRequestObj = purchaseRequestRepository.findById(purchaseRequest.getId()).get();
					logger.info(purchaseRequestObj.getCreatedBy().getUserEmail());
					purchaseRequest.setCreatedBy(purchaseRequestObj.getCreatedBy());
				 }
      			 RequestContext.initialize();
      		     RequestContext.get().getConfigMap().put("mail.template", "purchaseRequestEmail.ftl");  //Sending Email
      		   emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendPREmail(purchaseRequest);
      		} catch (Exception e) {
      			e.printStackTrace();
      		}
			 
		 } 
		 
		return purchaseRequestRepository.save(purchaseRequest);
	}

	@Override
	public PurchaseRequest saveCancelStage(String prId) {
		PurchaseRequest prq = purchaseRequestRepository.findById(Integer.parseInt(prId)).get();
		prq.setStatus(EnumStatusUpdate.CANCELED.getStatus());
		prq.setType("Item");
		purchaseRequestRepository.save(prq);
		return prq;
	}
	
	
	@Override
	public List<PurchaseRequest> findByIsActive() {
		return purchaseRequestRepository.findByIsActive(true,plantService.findPlantIds());
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

	@Override
	public boolean findByDocNumber(String prNo) {
		List<PurchaseRequest> prList = purchaseRequestRepository.findByDocNumber(prNo);
		if(prList.size()>0) {
			return true;
		}else {
			return false;
		}
	}

 
}
