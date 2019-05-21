package com.smerp.serviceImpl.inventorytransactions;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventorytransactions.InventoryGoodsReceipt;
import com.smerp.model.inventorytransactions.InventoryGoodsReceiptList;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.model.search.SearchFilter;
import com.smerp.repository.inventorytransactions.InventoryGoodsReceiptRepository;
import com.smerp.service.inventorytransactions.InventoryGoodsReceiptService;
import com.smerp.service.master.PlantService;
import com.smerp.util.CheckUserPermissionUtil;
import com.smerp.util.EmailGenerator;
import com.smerp.util.EnumSearchFilter;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GetSearchFilterResult;
import com.smerp.util.RequestContext;
import com.smerp.util.UnitPriceListItems;

@Service
@Transactional
public class InventoryGoodsReceiptServiceImpl implements InventoryGoodsReceiptService {

	private static final Logger logger = LogManager.getLogger(InventoryGoodsReceiptServiceImpl.class);
			
	@Autowired
	private InventoryGoodsReceiptRepository inventoryGoodsReceiptRepository;
	
	@Autowired
	private EmailGenerator emailGenerator;
	
	@Autowired
	private PlantService plantService;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private GetSearchFilterResult getSearchFilterResult;
	
	@Autowired
	private CheckUserPermissionUtil checkUserPermissionUtil;
	
	@Override
	public InventoryGoodsReceipt save(InventoryGoodsReceipt inventoryGoodsReceipt) {
		switch (inventoryGoodsReceipt.getStatusType()) {
		case "DR":
			inventoryGoodsReceipt.setStatus(EnumStatusUpdate.DRAFT.getStatus());
			break;
		case "SA":
			inventoryGoodsReceipt.setStatus(EnumStatusUpdate.OPEN.getStatus());
			break;

		case "RE":
			inventoryGoodsReceipt.setStatus(EnumStatusUpdate.REJECTED.getStatus());
			break;
		case "APP":
			inventoryGoodsReceipt.setStatus(EnumStatusUpdate.APPROVEED.getStatus());
			break;
		case "CA":
			inventoryGoodsReceipt.setStatus(EnumStatusUpdate.CANCELED.getStatus());
			break;
		default:
			logger.info("Type Not Matched:" + inventoryGoodsReceipt.getStatusType());
			break;
		}
		
		 List<InventoryGoodsReceiptList> listItems = inventoryGoodsReceipt.getInventoryGoodsReceiptList();
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				if (listItems.get(i).getProductNumber() == null) {
					listItems.remove(i);
				}
			}
			inventoryGoodsReceipt.setInventoryGoodsReceiptList(listItems);
		} 
		
		if(inventoryGoodsReceipt.getStatus()!=null &&  !inventoryGoodsReceipt.getStatus().equals(EnumStatusUpdate.DRAFT.getStatus())) {
			try {
				 if(inventoryGoodsReceipt.getId()!=null) {
						InventoryGoodsReceipt inventoryGoodsReceiptObj = inventoryGoodsReceiptRepository.findById(inventoryGoodsReceipt.getId()).get();
						logger.info(inventoryGoodsReceiptObj.getCreatedBy().getUserEmail());
						inventoryGoodsReceipt.setCreatedBy(inventoryGoodsReceiptObj.getCreatedBy());
					 }
				inventoryGoodsReceipt =getListAmount(inventoryGoodsReceipt);
    			 RequestContext.initialize();
    		     RequestContext.get().getConfigMap().put("mail.template", "invgoodsReceiptEmail.ftl");  //Sending Email
    		     emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendInvGoodsReceiptEmail(inventoryGoodsReceipt);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		}
		
		/*  Multi Level Approved .. Start*/
		
		 if(inventoryGoodsReceipt.getPlant().getId()==2) {   //FOR Yelamanchili
			 
			 boolean checkMultiApp = checkUserPermissionUtil.checkMultiAppPermission();
			 
		 if(inventoryGoodsReceipt.getStatus().equals(EnumStatusUpdate.APPROVEED.getStatus()) ) {  
			 User user = checkUserPermissionUtil.getUser();
			 
			 if(checkMultiApp) { // Final Approved
				 if(inventoryGoodsReceipt.getFirstApproveId()!=null) {
					 inventoryGoodsReceipt.setSecondApproveId(user.getUserId());
					 inventoryGoodsReceipt.setSecondLevelEnable(true);}
				 else {
					 inventoryGoodsReceipt.setFirstApproveId(user.getUserId());
					 inventoryGoodsReceipt.setSecondApproveId(user.getUserId());
					 inventoryGoodsReceipt.setSecondLevelEnable(true);
				 }
				 inventoryGoodsReceipt.setStatus(EnumStatusUpdate.APPROVEED.getStatus());
			 }else {            // First Approved
				 inventoryGoodsReceipt.setFirstApproveId(user.getUserId());
				 inventoryGoodsReceipt.setStatus(EnumStatusUpdate.PARTIALLY_APPROVEED.getStatus());
				 inventoryGoodsReceipt.setSecondLevelEnable(true);
			 }
			 
		 }else {
			 if(checkMultiApp) {
				 inventoryGoodsReceipt.setSecondLevelEnable(true); 
			 }
		 }
		 
		 }else {
			 inventoryGoodsReceipt.setSecondLevelEnable(true); 
		 }
		 
		 /*  Multi Level Approved .. End*/
		
		inventoryGoodsReceipt = inventoryGoodsReceiptRepository.save(inventoryGoodsReceipt);
		
		return inventoryGoodsReceipt;
	}

	@Override
	public List<InventoryGoodsReceipt> findAll() {
		// TODO Auto-generated method stub
		return inventoryGoodsReceiptRepository.findAll();
	}

	@Override
	public InventoryGoodsReceipt findById(int id) {
		// TODO Auto-generated method stub
		return inventoryGoodsReceiptRepository.findById(id).get();
	}

	@Override
	public InventoryGoodsReceipt delete(int id) {
		InventoryGoodsReceipt inventoryGoodsReceipt = inventoryGoodsReceiptRepository.findById(id).get();
		inventoryGoodsReceipt.setIsActive(false);
		inventoryGoodsReceiptRepository.save(inventoryGoodsReceipt);
		return inventoryGoodsReceipt;
	}

	@Override
	public List<InventoryGoodsReceipt> findByIsActive() {
		// TODO Auto-generated method stub
		Boolean [] secondApp = checkUserPermissionUtil.getMultiAppPermission();
		return inventoryGoodsReceiptRepository.findByIsActive(true,plantService.findPlantIds(),secondApp);
	}

	@Override
	public InventoryGoodsReceipt findLastDocumentNumber() {
		// TODO Auto-generated method stub
		return inventoryGoodsReceiptRepository.findTopByOrderByIdDesc();
	}

	@Override
	public InventoryGoodsReceipt getListAmount(InventoryGoodsReceipt inventoryGoodsReceipt) {
		List<InventoryGoodsReceiptList> listItems = inventoryGoodsReceipt.getInventoryGoodsReceiptList();
		List<InventoryGoodsReceiptList> addListItems = new ArrayList<InventoryGoodsReceiptList>();
		
		
		Double addAmt=0.0;
		Double addTaxAmt=0.0;
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				InventoryGoodsReceiptList grlist = listItems.get(i);
				if(grlist.getUnitPrice()!=null) {
				addTaxAmt += UnitPriceListItems.getTaxAmt(grlist.getRequiredQuantity(),grlist.getUnitPrice(),grlist.getTaxCode());
				addAmt +=UnitPriceListItems.getTotalINVAmt(grlist.getRequiredQuantity(),grlist.getUnitPrice());
				grlist.setTaxTotal(""+UnitPriceListItems.getTaxAmt(grlist.getRequiredQuantity(),grlist.getUnitPrice(),grlist.getTaxCode()));
				grlist.setTotal(""+UnitPriceListItems.getTotalINVAmt(grlist.getRequiredQuantity(),grlist.getUnitPrice()));
				 
				
				}else {
				grlist.setTaxTotal("");
				grlist.setTotal("");	
				}
				addListItems.add(grlist);
			}
			inventoryGoodsReceipt.setInventoryGoodsReceiptList(addListItems);
		}
		inventoryGoodsReceipt.setTotalBeforeDisAmt(addAmt);
		inventoryGoodsReceipt.setTaxAmt(""+addTaxAmt);
		Double total_amt=0.0;
		if(inventoryGoodsReceipt.getTotalDiscount()==null) inventoryGoodsReceipt.setTotalDiscount(0.0);
		if(inventoryGoodsReceipt.getFreight()==null) inventoryGoodsReceipt.setFreight(0.0);
			
			
		 total_amt= UnitPriceListItems.getTotalAmtPayment(addAmt, inventoryGoodsReceipt.getTotalDiscount(), inventoryGoodsReceipt.getFreight(),addTaxAmt);
		 inventoryGoodsReceipt.setAmtRounding("" + total_amt);
		 inventoryGoodsReceipt.setTotalPayment(inventoryGoodsReceipt.getTotalPayment());
		 inventoryGoodsReceipt.setRoundedOff("" + df2.format(inventoryGoodsReceipt.getTotalPayment() - total_amt));
	
	return inventoryGoodsReceipt;
	}

	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	@Override
	public boolean findByDocNumber(String docNum) {
		List<InventoryGoodsReceipt> invGR = inventoryGoodsReceiptRepository.findByDocNumber(docNum);
		if(invGR.size()>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public List<InventoryGoodsReceipt> searchFilterBySelection(SearchFilter searchFilter){
		if(searchFilter.getToDate()==null) {
			searchFilter.setToDate(new Date());
		}
		searchFilter.setTypeOf(EnumSearchFilter.INVGR.getStatus());
		
		if(searchFilter.getSortBy()!=null) {
			if((!searchFilter.getSearchBy().equals("select") && !searchFilter.getFieldName().isEmpty()) || (searchFilter.getFromDate()!=null && searchFilter.getToDate()!=null )) {
				
				String resultQuery = getSearchFilterResult.getQueryBysearchFilterSelection(searchFilter);
				
				Query query = entityManager.createQuery(resultQuery);
				List<InventoryGoodsReceipt> list = query.getResultList();
				return list;
			}else {
			List<InventoryGoodsReceipt> list = findByIsActive();
			return list;
		}
		}else {
			List<InventoryGoodsReceipt> list = findByIsActive();
			return list;
		}
	}
	
}
