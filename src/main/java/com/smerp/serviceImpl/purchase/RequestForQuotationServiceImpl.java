package com.smerp.serviceImpl.purchase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.admin.Vendor;
import com.smerp.model.admin.VendorAddress;
import com.smerp.model.admin.VendorsContactDetails;
import com.smerp.model.inventory.LineItems;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.model.purchase.PurchaseRequestList;
import com.smerp.model.search.SearchFilter;
import com.smerp.repository.purchase.LineitemsRepositoryRepository;
import com.smerp.repository.purchase.PurchaseRequestListRepository;
import com.smerp.repository.purchase.PurchaseRequestRepository;
import com.smerp.repository.purchase.RequestForQuotationRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.VendorAddressService;
import com.smerp.service.inventory.VendorsContactDetailsService;
import com.smerp.service.master.PlantService;
import com.smerp.service.purchase.PurchaseRequestService;
import com.smerp.service.purchase.RequestForQuotationService;
import com.smerp.util.DocNumberGenerator;
import com.smerp.util.EmailGenerator;
import com.smerp.util.EnumSearchFilter;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.GetConvertedDocStatusList;
import com.smerp.util.GetSearchFilterResult;
import com.smerp.util.RequestContext;

@Service
@Transactional
public class RequestForQuotationServiceImpl implements RequestForQuotationService {

	private static final Logger logger = LogManager.getLogger(RequestForQuotationServiceImpl.class);

	@Autowired
	private RequestForQuotationRepository requestForQuotationRepository;

	@Autowired
	private LineitemsRepositoryRepository lineitemsRepository;

	@Autowired
	private VendorService vendorService;
	
	@Autowired
	private VendorAddressService vendorAddressService;
	
	@Autowired
	private VendorsContactDetailsService vendorsContactDetailsService;

	@Autowired
	private PurchaseRequestService purchaseRequestService;
	
	@Autowired
	private PurchaseRequestRepository purchaseRequestRepository;
	
	@Autowired
	private PurchaseRequestListRepository purchaseRequestListRepository;
	
	@Autowired
	private EmailGenerator emailGenerator;
	
	@Autowired
	private DocNumberGenerator docNumberGenerator;
	
	@Autowired
	private PlantService plantService;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private GetSearchFilterResult getSearchFilterResult;
	
	@Autowired
	private GetConvertedDocStatusList getConvertedDocStatusList;
	
	@Override
	public RequestForQuotation save(RequestForQuotation requestForQuotation) {
		requestForQuotation.setCategory("Item");
		switch (requestForQuotation.getStatusType()) {
		case "DR":
			requestForQuotation.setStatus(EnumStatusUpdate.DRAFT.getStatus());
			break;
		case "SA":
			requestForQuotation.setStatus(EnumStatusUpdate.OPEN.getStatus());
			break;

		case "RE":
			requestForQuotation.setStatus(EnumStatusUpdate.REJECTED.getStatus());
			break;
		case "APP":
			requestForQuotation.setStatus(EnumStatusUpdate.APPROVEED.getStatus());
			break;
		case "CA":
			requestForQuotation.setStatus(EnumStatusUpdate.CANCELED.getStatus());
			break;
		default:
			logger.info("Type Not Matched:" + requestForQuotation.getStatusType());
			break;
		}
		
		 List<LineItems> listItems = requestForQuotation.getLineItems();
		    if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				if (listItems.get(i).getProdouctNumber() == null) {
					listItems.remove(i);
					i--;
				}
			}
			
			for (int i = 0; i < listItems.size(); i++) {
				listItems.get(i).setRequestForQuotationId(requestForQuotation);
			}
			
			requestForQuotation.setLineItems(listItems);
			
			if(requestForQuotation.getId() != null) {
				RequestForQuotation rfqPrevObj = findById(requestForQuotation.getId());
				List<LineItems> prevListItems = rfqPrevObj.getLineItems();
				
				List<Integer> prevListIds = new ArrayList<Integer>();
				for(int i=0;i<prevListItems.size();i++) {
					prevListIds.add(prevListItems.get(i).getProductId());
				}
				
				List<Integer> latestListIds = new ArrayList<Integer>();
				for(int i=0;i<listItems.size();i++) {
					latestListIds.add(listItems.get(i).getProductId());
				}
			
			
			PurchaseRequest prList = requestForQuotation.getPurchaseReqId();
			if(prList != null) {
				
				Map<Integer,PurchaseRequest> prevMap = new HashMap<Integer, PurchaseRequest>();
				for(int i=0;i<prevListItems.size();i++) {
					prevMap.put(prevListItems.get(i).getProductId(), prevListItems.get(i).getRequestForQuotationId().getPurchaseReqId());
				}
				
				Map<Integer,PurchaseRequest> latestMap = new HashMap<Integer, PurchaseRequest>();
				for(int i=0;i<listItems.size();i++) {
					latestMap.put(listItems.get(i).getProductId(), listItems.get(i).getRequestForQuotationId().getPurchaseReqId());
				}
				
				if(prevListItems != null) {
					if(prevListItems.size() != listItems.size()) {
						
						prevListIds.removeAll(latestListIds);
						
						if(!requestForQuotation.getStatus().equals(EnumStatusUpdate.REJECTED.getStatus())) {
							updateStatusAtPRList(prevListIds,prevMap,EnumStatusUpdate.DEALLOCATE.getStatus());
							updateStatusAtPRList(latestListIds,latestMap,EnumStatusUpdate.ALLOCATE.getStatus());
						
						}else {
							updateStatusAtPRList(prevListIds,prevMap,EnumStatusUpdate.DEALLOCATE.getStatus());
							updateStatusAtPRList(latestListIds,latestMap,EnumStatusUpdate.DEALLOCATE.getStatus());
						}
						
					}else {
						if(!requestForQuotation.getStatus().equals(EnumStatusUpdate.REJECTED.getStatus())) {
							updateStatusAtPRList(latestListIds,latestMap,EnumStatusUpdate.ALLOCATE.getStatus());
						}else {
							updateStatusAtPRList(latestListIds,latestMap,EnumStatusUpdate.DEALLOCATE.getStatus());
						}
					}
				}
		    }
		}
			
			requestForQuotation.setLineItems(listItems);
		}
		    
		if (requestForQuotation.getId() != null) { // delete List Of Items.
			RequestForQuotation requestForListOfItems = requestForQuotationRepository
					.findById(requestForQuotation.getId()).get();
			List<LineItems> requestLists = requestForListOfItems.getLineItems();
			
			if(requestForQuotation.getPurchaseReqId()==null) {  // if PurchaseReqId null delete list items 
				lineitemsRepository.deleteAll(requestLists);
			}else {
				lineitemsRepository.deleteAll(requestLists);
				requestForQuotation.setLineItems(listItems);
				requestForQuotation.setPlant(requestForQuotation.getPurchaseReqId().getPlant());
			}
		}
		
		PurchaseRequest pr = requestForQuotation.getPurchaseReqId();
		if(pr != null) {
			PurchaseRequest prq = requestForQuotation.getPurchaseReqId();
			if(prq != null) {
				List<PurchaseRequestList> prAllcoatedlist =  purchaseRequestRepository.getPurchaseRequestListById(requestForQuotation.getPurchaseReqId(),EnumStatusUpdate.ALLOCATE.getStatus());
				if(pr.getPurchaseRequestLists().size() == prAllcoatedlist.size()) {
					prq.setStatus(EnumStatusUpdate.CONVERTPRTORFQ.getStatus());
				}else {
					prq.setStatus(EnumStatusUpdate.APPROVEED.getStatus());
				}
				purchaseRequestRepository.save(prq);
			}
		}

		Vendor vendor = vendorService.findById(requestForQuotation.getVendor().getId());
		VendorAddress vendorShippingAddress = vendorAddressService.findById(requestForQuotation.getVendorShippingAddress().getId());
		VendorAddress vendorPayAddress = vendorAddressService.findById(requestForQuotation.getVendorPayTypeAddress().getId());
		
		VendorsContactDetails vendorsContactDetails =vendorsContactDetailsService.findById(requestForQuotation.getVendorContactDetails().getId());

		requestForQuotation.setVendor(vendor);
		requestForQuotation.setVendorContactDetails(vendorsContactDetails);
		requestForQuotation.setVendorShippingAddress(vendorShippingAddress);
		requestForQuotation.setVendorPayTypeAddress(vendorPayAddress);
		
	 if(requestForQuotation.getStatus()!=null &&  !requestForQuotation.getStatus().equals(EnumStatusUpdate.DRAFT.getStatus())) {
			try {
				if(requestForQuotation.getId()!=null) {
					RequestForQuotation requestForQuotationObj = requestForQuotationRepository.findById(requestForQuotation.getId()).get();
					logger.info(requestForQuotationObj.getCreatedBy().getUserEmail());
					requestForQuotation.setCreatedBy(requestForQuotationObj.getCreatedBy());
				 }
    			 RequestContext.initialize();
    		     RequestContext.get().getConfigMap().put("mail.template", "requestForQuotationEmail.ftl");  //Sending Email
    		     emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendRFQEmail(requestForQuotation);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
	}   

		return requestForQuotationRepository.save(requestForQuotation);
	}
	
	private void updateStatusAtPRList(List<Integer> listObj, Map<Integer,PurchaseRequest> mapObj,String status) {
		for(int i=0;i<listObj.size();i++) {
			if(mapObj.containsKey(listObj.get(i))) {
				PurchaseRequest li = mapObj.get(listObj.get(i));
				List<PurchaseRequestList> prList = li.getPurchaseRequestLists();
				for(int k=0;k<prList.size();k++) {
					if(prList.get(k).getProductId() == listObj.get(i)) {
						PurchaseRequestList prl = purchaseRequestListRepository.getOne(prList.get(k).getId());
						prl.setActiveStatus(status);
						purchaseRequestListRepository.save(prl);
						break;
					}
				}
			}
		}
	}

	@Override
	public RequestForQuotation saveRFQ(String purchaseId) {

		RequestForQuotation rfq = new RequestForQuotation();
		PurchaseRequest prq = purchaseRequestService.getInfo(Integer.parseInt(purchaseId));
		
		RequestForQuotation dup_rfq = null; //requestForQuotationRepository.findByPurchaseReqId(prq);  // check PR exist in RFQ
        if(dup_rfq==null) {
        	Integer count = docNumberGenerator.getCountByDocType(EnumStatusUpdate.RFQ.getStatus());
        	RequestForQuotation rfqdetails = findLastDocumentNumber();
		if (rfqdetails != null && rfqdetails.getDocNumber() != null) {
			rfq.setDocNumber(GenerateDocNumber.documentNumberGeneration(rfqdetails.getDocNumber(),count));
		} else {
			  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
			  LocalDateTime now = LocalDateTime.now();
			  rfq.setDocNumber(GenerateDocNumber.documentNumberGeneration("RFQ"+(String)dtf.format(now) +"0",count));
		}

		if (prq != null) {

			rfq.setDocumentDate(prq.getDocumentDate());
			rfq.setStatus(EnumStatusUpdate.OPEN.getStatus());
			rfq.setPostingDate(prq.getPostingDate());
			rfq.setCategory(prq.getType());
			rfq.setRemark(prq.getRemarks());
			rfq.setPlant(prq.getPlant());
			rfq.setReferenceDocNumber(prq.getDocNumber());
			rfq.setRequiredDate(prq.getRequiredDate());
			rfq.setPurchaseReqId(prq);
			rfq.setIsActive(true);
			
			List<PurchaseRequestList> prItms = prq.getPurchaseRequestLists();
			
			List<PurchaseRequestList> prlist =  purchaseRequestRepository.getPurchaseRequestListById(prq,EnumStatusUpdate.DEALLOCATE.getStatus());
			
			List<LineItems> lineItems =new ArrayList<LineItems>();
			if (prItms != null) {
				for (int i = 0; i < prItms.size(); i++) {
					if(prlist != null) {
						for(int j=0; j < prlist.size(); j++) {
							if(prItms.get(i).getActiveStatus().equals(prlist.get(j).getActiveStatus()) && 
									prItms.get(i).getProductId() == prlist.get(j).getProductId()) {
								LineItems line = new LineItems();
								line.setProdouctNumber(prItms.get(i).getProdouctNumber());
								line.setProductGroup(prItms.get(i).getProductGroup());
								line.setDescription(prItms.get(i).getDescription());
								line.setHsn(prItms.get(i).getHsn());
								line.setSku(prItms.get(i).getSku());
								line.setRequiredQuantity(prItms.get(i).getRequiredQuantity());
								line.setSacCode(prItms.get(i).getSacCode());
								line.setUom(prItms.get(i).getUom());
								line.setWarehouse(prItms.get(i).getWarehouse());
								line.setProductId(prItms.get(i).getProductId());
								line.setUnitPrice(prItms.get(i).getUnitPrice());
								lineItems.add(line);
								prq.getPurchaseRequestLists().get(i).setActiveStatus(EnumStatusUpdate.ALLOCATE.getStatus());
								break;
							}
						}
					}
					
					
				}
				
			}
			purchaseRequestRepository.save(prq);
			rfq.setLineItems(lineItems);
			
		}
		
		rfq.setCategory("Item");
		rfq = requestForQuotationRepository.save(rfq);
		
		List<PurchaseRequestList> allocatedlist =  purchaseRequestRepository.getPurchaseRequestListById(prq,EnumStatusUpdate.ALLOCATE.getStatus());
		if(allocatedlist.size() == rfq.getPurchaseReqId().getPurchaseRequestLists().size()) {
			prq.setStatus(EnumStatusUpdate.CONVERTPRTORFQ.getStatus());
		}
		prq.setType("Item");
		purchaseRequestRepository.save(prq);
		
		return rfq;
        }else {
        	return dup_rfq;
        }
	}
	
	
	@Override
	public RequestForQuotation saveCancelStage(String rfqId) {
		RequestForQuotation rfq = requestForQuotationRepository.findById(Integer.parseInt(rfqId)).get();
		rfq.setStatus(EnumStatusUpdate.CANCELED.getStatus());
		rfq.setCategory("Item");
		requestForQuotationRepository.save(rfq);
		return rfq;
		
	}

	@Override
	public List<RequestForQuotation> rfqApprovedList() {

		return requestForQuotationRepository.rfqApprovedList(EnumStatusUpdate.APPROVEED.getStatus(),plantService.findPlantIds());
	}
	
	
	@Override
	public RequestForQuotation findLastDocumentNumber() {
		return requestForQuotationRepository.findTopByOrderByIdDesc();
	}

	@Override
	public List<RequestForQuotation> findAll() {
		return requestForQuotationRepository.findAll();
	}

	@Override
	public List<RequestForQuotation> findByIsActive() {
		return requestForQuotationRepository.findByIsActive(true,plantService.findPlantIds());
	}

	@Override
	public RequestForQuotation findById(int id) {
		return requestForQuotationRepository.findById(id).get();
	}
	
	
	@Override
	public RequestForQuotation delete(int id) {
		RequestForQuotation requestForQuotation = requestForQuotationRepository.findById(id).get();
		requestForQuotation.setIsActive(false);
		requestForQuotationRepository.save(requestForQuotation);
		return requestForQuotation;
	}
	
	@Override
	public boolean findByDocNumber(String rfqDocNum) {
		List<RequestForQuotation> requestForQuotation = requestForQuotationRepository.findByDocNumber(rfqDocNum);
		if(requestForQuotation.size()>0) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public boolean isVendorNameExistWithDocNum(String vendorName, String refDocNum) {
		List<RequestForQuotation> requestForQuotation = requestForQuotationRepository.findByDocNumber(vendorName,refDocNum);
		if(requestForQuotation.size()>0) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public List<RequestForQuotation> getRFQListById(PurchaseRequest prId){
		List<RequestForQuotation> rfqList = requestForQuotationRepository.getRFQListById(prId);
		return rfqList;
	}
	
	@Override
	public Integer getRFQListCount(PurchaseRequest prId){
		List<RequestForQuotation> rfqList = requestForQuotationRepository.getRFQListById(prId);
		Integer noOfRfq = rfqList.size();
		logger.info("No of RFQ's are:"+noOfRfq);
		return noOfRfq;
	}
	
	@Override
	public List<RequestForQuotation> searchFilterBySelection(SearchFilter searchFilter){
		if(searchFilter.getToDate()==null) {
			searchFilter.setToDate(new Date());
		}
		
		searchFilter.setTypeOf(EnumSearchFilter.RFQTABLE.getStatus());
		if(searchFilter.getIsConvertedDoc()!= null && searchFilter.getIsConvertedDoc().equals("true")){
			List<String> statusList = getConvertedDocStatusList.getRFQStatusList();
			searchFilter.setStatusList(statusList);
			if(searchFilter.getSortBy()!=null) {
				 if((!searchFilter.getSearchBy().equals("select") && !searchFilter.getFieldName().isEmpty()) || (searchFilter.getFromDate()!=null && searchFilter.getToDate()!=null )) {
				
				String resultQuery = getSearchFilterResult.getQueryBysearchFilterSelection(searchFilter);
				
				logger.info(resultQuery);
				
				Query query = entityManager.createQuery(resultQuery);
				List<RequestForQuotation> list = query.getResultList();
				logger.info(list);
				return list;
				}else {
					List<RequestForQuotation> list = rfqApprovedList();
					return list;
				}
				}else {
					List<RequestForQuotation> list = rfqApprovedList();
					return list;
				}
			
			
		}else if(searchFilter.getSortBy()!=null) {
		 if((!searchFilter.getSearchBy().equals("select") && !searchFilter.getFieldName().isEmpty()) || (searchFilter.getFromDate()!=null && searchFilter.getToDate()!=null )) {
		
		String resultQuery = getSearchFilterResult.getQueryBysearchFilterSelection(searchFilter);
		
		logger.info(resultQuery);
		
		Query query = entityManager.createQuery(resultQuery);
		List<RequestForQuotation> list = query.getResultList();
		logger.info(list);
		return list;
		}else {
			List<RequestForQuotation> list = findByIsActive();
			return list;
		}
		}else {
			List<RequestForQuotation> list = findByIsActive();
			return list;
		}
	}
	
}
