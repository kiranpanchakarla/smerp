package com.smerp.serviceImpl.inventorytransactions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.inventory.GoodsReturn;
import com.smerp.model.inventory.GoodsReturnLineItems;
import com.smerp.model.inventorytransactions.InventoryGoodsIssue;
import com.smerp.model.inventorytransactions.InventoryGoodsIssueList;
import com.smerp.model.inventorytransactions.InventoryGoodsTransfer;
import com.smerp.model.search.SearchFilter;
import com.smerp.repository.inventorytransactions.InventoryGoodsIssueRepository;
import com.smerp.service.inventorytransactions.InventoryGoodsIssueService;
import com.smerp.service.master.PlantService;
import com.smerp.util.EmailGenerator;
import com.smerp.util.EnumSearchFilter;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GetSearchFilterResult;
import com.smerp.util.RequestContext;
import com.smerp.util.UnitPriceListItems;

@Service
@Transactional
public class InventoryGoodsIssueServiceImpl implements InventoryGoodsIssueService {

	private static final Logger logger = LogManager.getLogger(InventoryGoodsIssueServiceImpl.class);
	
	@Autowired
	InventoryGoodsIssueRepository inventoryGoodsIssueRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	EmailGenerator emailGenerator;
	
	@Autowired
	PlantService plantService;
	
	@Autowired
	private GetSearchFilterResult getSearchFilterResult;
	
	@Override
	public InventoryGoodsIssue save(InventoryGoodsIssue inventoryGoodsIssue) {
		switch (inventoryGoodsIssue.getStatusType()) {
		case "DR":
			inventoryGoodsIssue.setStatus(EnumStatusUpdate.DRAFT.getStatus());
			break;
		case "SA":
			inventoryGoodsIssue.setStatus(EnumStatusUpdate.OPEN.getStatus());
			break;

		case "RE":
			inventoryGoodsIssue.setStatus(EnumStatusUpdate.REJECTED.getStatus());
			break;
		case "APP":
			inventoryGoodsIssue.setStatus(EnumStatusUpdate.APPROVEED.getStatus());
			break;
		case "CA":
			inventoryGoodsIssue.setStatus(EnumStatusUpdate.CANCELED.getStatus());
			break;
		default:
			logger.info("Type Not Matched:" + inventoryGoodsIssue.getStatusType());
			break;
		}
		
		 List<InventoryGoodsIssueList> listItems = inventoryGoodsIssue.getInventoryGoodsIssueList();
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				if (listItems.get(i).getProductNumber() == null  && listItems.get(i).getRequiredQuantity() == null) {
					listItems.remove(i);
				}
			}
			inventoryGoodsIssue.setInventoryGoodsIssueList(listItems);
		} 
		
		if(inventoryGoodsIssue.getStatus()!=null &&  !inventoryGoodsIssue.getStatus().equals(EnumStatusUpdate.DRAFT.getStatus())) {
			try {
				 if(inventoryGoodsIssue.getId()!=null) {
						InventoryGoodsIssue inventoryGoodsIssueObj = inventoryGoodsIssueRepository.findById(inventoryGoodsIssue.getId()).get();
						logger.info(inventoryGoodsIssueObj.getCreatedBy().getUserEmail());
						inventoryGoodsIssue.setCreatedBy(inventoryGoodsIssueObj.getCreatedBy());
					 }
				inventoryGoodsIssue =getListAmount(inventoryGoodsIssue);
    			 RequestContext.initialize();
    		     RequestContext.get().getConfigMap().put("mail.template", "invgoodsIssueEmail.ftl");  //Sending Email
    		     emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendInvGoodsIssueEmail(inventoryGoodsIssue);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		}
		
		inventoryGoodsIssue = inventoryGoodsIssueRepository.save(inventoryGoodsIssue);
		
		return inventoryGoodsIssue;
	}

	@Override
	public List<InventoryGoodsIssue> findAll() {
		// TODO Auto-generated method stub
		return inventoryGoodsIssueRepository.findAll();
	}

	@Override
	public InventoryGoodsIssue findById(int id) {
		// TODO Auto-generated method stub
		return inventoryGoodsIssueRepository.findById(id).get();
	}

	@Override
	public InventoryGoodsIssue delete(int id) {
		InventoryGoodsIssue inventoryGoodsIssue = inventoryGoodsIssueRepository.findById(id).get();
		inventoryGoodsIssue.setIsActive(false);
		inventoryGoodsIssueRepository.save(inventoryGoodsIssue);
		return inventoryGoodsIssue;
	}

	@Override
	public List<InventoryGoodsIssue> findByIsActive() {
		// TODO Auto-generated method stub
		return inventoryGoodsIssueRepository.findByIsActive(true,plantService.findPlantIds());
	}

	@Override
	public InventoryGoodsIssue findLastDocumentNumber() {
		// TODO Auto-generated method stub
		return inventoryGoodsIssueRepository.findTopByOrderByIdDesc();
	}

	@Override
	public InventoryGoodsIssue getListAmount(InventoryGoodsIssue inventoryGoodsIssue) {
		logger.info("getListAmount-->");
		List<InventoryGoodsIssueList> listItems = inventoryGoodsIssue.getInventoryGoodsIssueList();
		List<InventoryGoodsIssueList> addListItems = new ArrayList<InventoryGoodsIssueList>();
		
		
		Double addAmt=0.0;
		Double addTaxAmt=0.0;
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				InventoryGoodsIssueList grlist = listItems.get(i);
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
			inventoryGoodsIssue.setInventoryGoodsIssueList(addListItems);
		}
		inventoryGoodsIssue.setTotalBeforeDisAmt(addAmt);
		inventoryGoodsIssue.setTaxAmt(""+addTaxAmt);
		logger.info("addAmt-->" + addAmt); 
		logger.info("goodsIssue.getTotalDiscount()-->" + inventoryGoodsIssue.getTotalDiscount());
		logger.info("goodsIssue.getFreight()-->" + inventoryGoodsIssue.getFreight());
		Double total_amt=0.0;
		if(inventoryGoodsIssue.getTotalDiscount()==null) inventoryGoodsIssue.setTotalDiscount(0.0);
		if(inventoryGoodsIssue.getFreight()==null) inventoryGoodsIssue.setFreight(0.0);
			
	     
		 total_amt= UnitPriceListItems.getTotalAmtPayment(addAmt, inventoryGoodsIssue.getTotalDiscount(), inventoryGoodsIssue.getFreight(),addTaxAmt);
		 inventoryGoodsIssue.setAmtRounding("" + total_amt);
		 inventoryGoodsIssue.setTotalPayment(inventoryGoodsIssue.getTotalPayment());
		 inventoryGoodsIssue.setRoundedOff("" + df2.format(inventoryGoodsIssue.getTotalPayment() - total_amt));
	
	return inventoryGoodsIssue;
	}

	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	@Override
	public boolean findByDocNumber(String docNum) {
		List<InventoryGoodsIssue> igi = inventoryGoodsIssueRepository.findByDocNumber(docNum);
		if(igi.size()>0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	@Override
	public String getInStock(String productNo, Integer warehouse) {

		String productsql = " select * from vw_inventory_product_quantity where product_no ='" + productNo
				+ "' and plant_id='" + warehouse + "'";

		Query query1 = entityManager.createNativeQuery(productsql);

		logger.info("Product ordered ----> " + query1);

		logger.info("Product ordered SQL ----> " + productsql);

		String instockQuantity = "";
		ArrayList<Object[]> arrayList = new ArrayList<>();

		arrayList.addAll(query1.getResultList());
		logger.info("Product List Size ----> " + arrayList.size());

		for (Object[] tuple : arrayList) {

			instockQuantity = "" + ((Integer) (tuple[4] == null ? 0 : (Integer.parseInt((tuple[4].toString())))));

		}

		logger.info(" In Stock Count--------->" + instockQuantity);
        if(!instockQuantity.isEmpty()) {
		return instockQuantity;
        }else {
        	return "0";
        }
	}
	
	@Override
	public InventoryGoodsIssue getinventoryGoodsIssueId(int id) {
		InventoryGoodsIssue inventoryGoodsIssue = inventoryGoodsIssueRepository.findById(id).get();
	    
	     List<InventoryGoodsIssueList> listItems = inventoryGoodsIssue.getInventoryGoodsIssueList();
	     for (int i = 0; i < listItems.size(); i++) {
	    	 InventoryGoodsIssueList invlist = listItems.get(i);
	        String productNo = invlist.getProductNumber();
	        Integer wareHose = invlist.getWarehouse();
	        invlist.setTempRequiredQuantity(Integer.parseInt(getInStock(productNo,wareHose)));    
	    }
	     inventoryGoodsIssue.setInventoryGoodsIssueList(listItems);
	     logger.info(" inventoryGoodsIssue--------->" + inventoryGoodsIssue);
	    return inventoryGoodsIssue;
	}
	
	@Override
	public List<InventoryGoodsIssue> searchFilterBySelection(SearchFilter searchFilter){
		if(searchFilter.getToDate()==null) {
			searchFilter.setToDate(new Date());
		}
		searchFilter.setTypeOf(EnumSearchFilter.INVGI.getStatus());
		
		if(searchFilter.getSortBy()!=null) {
			if((!searchFilter.getSearchBy().equals("select") && !searchFilter.getFieldName().isEmpty()) || (searchFilter.getFromDate()!=null && searchFilter.getToDate()!=null )) {
				
				String resultQuery = getSearchFilterResult.getQueryBysearchFilterSelection(searchFilter);
				logger.info(resultQuery);
				
				Query query = entityManager.createQuery(resultQuery);
				List<InventoryGoodsIssue> list = query.getResultList();
				logger.info(list);
				return list;
			}else {
			List<InventoryGoodsIssue> list = findByIsActive();
			return list;
		}
		}else {
			List<InventoryGoodsIssue> list = findByIsActive();
			return list;
		}
		
	}
	
	
}
