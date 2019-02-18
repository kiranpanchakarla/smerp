package com.smerp.serviceImpl.inventorytransactions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smerp.model.inventorytransactions.InventoryGoodsIssue;
import com.smerp.model.inventorytransactions.InventoryGoodsIssueList;
import com.smerp.repository.inventorytransactions.InventoryGoodsIssueRepository;
import com.smerp.service.inventorytransactions.InventoryGoodsIssueService;
import com.smerp.util.EmailGenerator;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.RequestContext;
import com.smerp.util.UnitPriceListItems;

@Service
@Transactional
public class InventoryGoodsIssueServiceImpl implements InventoryGoodsIssueService {

	private static final Logger logger = LogManager.getLogger(InventoryGoodsIssueServiceImpl.class);
	
	@Autowired
	InventoryGoodsIssueRepository inventoryGoodsIssueRepository;
	
	@Autowired
	EmailGenerator emailGenerator;
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
		
		if(inventoryGoodsIssue.getStatusType()!=null &&  !inventoryGoodsIssue.getStatusType().equals("DRAFT")) {
			try {
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
		return inventoryGoodsIssueRepository.findByIsActive(true);
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
				addAmt +=UnitPriceListItems.getTotalAmt(grlist.getRequiredQuantity(),grlist.getUnitPrice(), grlist.getTaxCode());
				grlist.setTaxTotal(""+UnitPriceListItems.getTaxAmt(grlist.getRequiredQuantity(),grlist.getUnitPrice(),grlist.getTaxCode()));
				grlist.setTotal(""+UnitPriceListItems.getTotalAmt(grlist.getRequiredQuantity(),grlist.getUnitPrice(), grlist.getTaxCode()));
				 
				
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
			
	     
		 total_amt= UnitPriceListItems.getTotalPaymentAmt(addAmt, inventoryGoodsIssue.getTotalDiscount(), inventoryGoodsIssue.getFreight());
		 inventoryGoodsIssue.setAmtRounding("" + total_amt);
		 inventoryGoodsIssue.setTotalPayment(inventoryGoodsIssue.getTotalPayment());
		 inventoryGoodsIssue.setRoundedOff("" + df2.format(inventoryGoodsIssue.getTotalPayment() - total_amt));
	
	return inventoryGoodsIssue;
	}

	private static DecimalFormat df2 = new DecimalFormat("#.##");
}
