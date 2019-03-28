package com.smerp.serviceImpl.purchase;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.User;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.model.purchase.PurchaseRequestList;
import com.smerp.model.search.SearchFilter;
import com.smerp.repository.purchase.PurchaseRequestListRepository;
import com.smerp.repository.purchase.PurchaseRequestRepository;
import com.smerp.service.master.PlantService;
import com.smerp.service.purchase.PurchaseRequestService;
import com.smerp.util.CheckUserPermissionUtil;
import com.smerp.util.EmailGenerator;
import com.smerp.util.EnumSearchFilter;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GetConvertedDocStatusList;
import com.smerp.util.GetSearchFilterResult;
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
	
	@Autowired
	CheckUserPermissionUtil checkUserPermissionUtil;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private GetSearchFilterResult getSearchFilterResult;
	
	@Autowired
	private GetConvertedDocStatusList getConvertedDocStatusList;
	
	
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
					 PurchaseRequest purchaseRequestObj  = purchaseRequestRepository.findById(purchaseRequest.getId()).get();
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
		 
		/*  Multi Level Approved .. Start*/
		
		 boolean checkMultiApp = checkUserPermissionUtil.checkMultiAppPermission();
		 logger.info("checkMultiApp-->" +checkMultiApp);
		
		 
		 if(purchaseRequest.getPlant().getId()==2) {   //FOR Yelamanchili
		 if(purchaseRequest.getStatus().equals(EnumStatusUpdate.APPROVEED.getStatus()) ) {  
			 User user = checkUserPermissionUtil.getUser();
			 
			 if(checkMultiApp) { // Final Approved
				 if(purchaseRequest.getFirstApproveId()!=null) {
				    purchaseRequest.setSecondApproveId(user.getUserId());
				    purchaseRequest.setSecondLevelEnable(true);}
				 else {
					 purchaseRequest.setFirstApproveId(user.getUserId());
					 purchaseRequest.setSecondApproveId(user.getUserId());
					 purchaseRequest.setSecondLevelEnable(true);
				 }
				 purchaseRequest.setStatus(EnumStatusUpdate.APPROVEED.getStatus());
			 }else {            // First Approved
				 purchaseRequest.setFirstApproveId(user.getUserId());
				 purchaseRequest.setStatus(EnumStatusUpdate.PARTIALLY_APPROVEED.getStatus());
				 purchaseRequest.setSecondLevelEnable(true);
			 }
			 
		 }else {
			 if(checkMultiApp) {
				 purchaseRequest.setSecondLevelEnable(true); 
			 }
		 }
		 
		 }else {
			 purchaseRequest.setSecondLevelEnable(true); 
		 }
		 
		 
		 /*  Multi Level Approved .. End*/
		 
		 
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
		
		Boolean [] secondApp = new Boolean [2];
		boolean MultiApp = checkUserPermissionUtil.checkMultiAppPermission();
		if(MultiApp==true) {
			secondApp[0] = true;
		}else {
			secondApp[0] = true;secondApp[1] = false;
		}
		
		return purchaseRequestRepository.findByIsActive(true,plantService.findPlantIds(),secondApp);
	}

	@Override
	public List<PurchaseRequest> prApprovedList() {

		return purchaseRequestRepository.prApprovedList(EnumStatusUpdate.APPROVEED.getStatus(),plantService.findPlantIds());
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

	@Override
	public List<PurchaseRequest> searchFilterBySelection(SearchFilter searchFilter){
		if(searchFilter.getToDate()==null) {
			searchFilter.setToDate(new Date());
		}
		
		searchFilter.setTypeOf(EnumSearchFilter.PRTABLE.getStatus());
		if(searchFilter.getIsConvertedDoc()!= null && searchFilter.getIsConvertedDoc().equals("true")){
			List<String> statusList = getConvertedDocStatusList.getPRStatusList();
			searchFilter.setStatusList(statusList);
			
			if(searchFilter.getSortBy()!=null) {
				if((!searchFilter.getSearchBy().equals("select") && !searchFilter.getFieldName().isEmpty()) || (searchFilter.getFromDate()!=null && searchFilter.getToDate()!=null )) {
					
					String resultQuery = getSearchFilterResult.getQueryBysearchFilterSelection(searchFilter);
					logger.info(resultQuery);
					
					Query query = entityManager.createQuery(resultQuery);
					List<PurchaseRequest> list = query.getResultList();
					logger.info(list);
					return list;
				}else {
				List<PurchaseRequest> list = prApprovedList();
				return list;
			}
			}else {
				List<PurchaseRequest> list = prApprovedList();
				return list;
			}
			
	}else if(searchFilter.getSortBy()!=null) {
			if((!searchFilter.getSearchBy().equals("select") && !searchFilter.getFieldName().isEmpty()) || (searchFilter.getFromDate()!=null && searchFilter.getToDate()!=null )) {
				
				String resultQuery = getSearchFilterResult.getQueryBysearchFilterSelection(searchFilter);
				logger.info(resultQuery);
				
				Query query = entityManager.createQuery(resultQuery);
				List<PurchaseRequest> list = query.getResultList();
				logger.info(list);
				return list;
			}else {
			List<PurchaseRequest> list = findByIsActive();
			return list;
		}
		}else {
			List<PurchaseRequest> list = findByIsActive();
			return list;
		}
	}
		
 
}
