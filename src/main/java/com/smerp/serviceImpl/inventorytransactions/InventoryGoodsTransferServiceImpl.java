package com.smerp.serviceImpl.inventorytransactions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smerp.model.inventorytransactions.InventoryGoodsTransfer;
import com.smerp.model.inventorytransactions.InventoryGoodsTransferList;
import com.smerp.repository.inventorytransactions.InventoryGoodsTransferRepository;
import com.smerp.service.inventorytransactions.InventoryGoodsIssueService;
import com.smerp.service.inventorytransactions.InventoryGoodsTransferService;
import com.smerp.service.master.PlantService;
import com.smerp.util.EmailGenerator;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.RequestContext;
import com.smerp.util.UnitPriceListItems;

@Service
@Transactional
public class InventoryGoodsTransferServiceImpl implements InventoryGoodsTransferService {

	private static final Logger logger = LogManager.getLogger(InventoryGoodsTransferServiceImpl.class);
	
	@Autowired
	InventoryGoodsTransferRepository inventoryGoodsTransferRepository;
	
	@Autowired
	EmailGenerator emailGenerator;
	
	@Autowired
	InventoryGoodsIssueService inventoryGoodsIssueService;
	
	@Autowired
	PlantService plantService;
	
	@Override
	public InventoryGoodsTransfer save(InventoryGoodsTransfer inventoryGoodsTransfer) {
		switch (inventoryGoodsTransfer.getStatusType()) {
		case "DR":
			inventoryGoodsTransfer.setStatus(EnumStatusUpdate.DRAFT.getStatus());
			break;
		case "SA":
			inventoryGoodsTransfer.setStatus(EnumStatusUpdate.OPEN.getStatus());
			break;

		case "RE":
			inventoryGoodsTransfer.setStatus(EnumStatusUpdate.REJECTED.getStatus());
			break;
		case "APP":
			inventoryGoodsTransfer.setStatus(EnumStatusUpdate.APPROVEED.getStatus());
			break;
		case "CA":
			inventoryGoodsTransfer.setStatus(EnumStatusUpdate.CANCELED.getStatus());
			break;
		default:
			logger.info("Type Not Matched:" + inventoryGoodsTransfer.getStatusType());
			break;
		}
		
		 List<InventoryGoodsTransferList> listItems = inventoryGoodsTransfer.getInventoryGoodsTransferList();
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				if (listItems.get(i).getProductNumber() == null  && listItems.get(i).getRequiredQuantity() == null) {
					listItems.remove(i);
				}
			}
			inventoryGoodsTransfer.setInventoryGoodsTransferList(listItems);
		} 
		
		if(inventoryGoodsTransfer.getStatus()!=null &&  !inventoryGoodsTransfer.getStatus().equals(EnumStatusUpdate.DRAFT.getStatus())) {
			try {
				 if(inventoryGoodsTransfer.getId()!=null) {
						InventoryGoodsTransfer inventoryGoodsTransferObj = inventoryGoodsTransferRepository.findById(inventoryGoodsTransfer.getId()).get();
						logger.info(inventoryGoodsTransferObj.getCreatedBy().getUserEmail());
						inventoryGoodsTransfer.setCreatedBy(inventoryGoodsTransferObj.getCreatedBy());
					 }
				inventoryGoodsTransfer =getListAmount(inventoryGoodsTransfer);
    			 RequestContext.initialize();
    		     RequestContext.get().getConfigMap().put("mail.template", "invgoodsTransferEmail.ftl");  //Sending Email
    		      emailGenerator.sendEmailToUser(EmailGenerator.Sending_Email).sendInvGoodsTransferEmail(inventoryGoodsTransfer);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		}
		
		inventoryGoodsTransfer = inventoryGoodsTransferRepository.save(inventoryGoodsTransfer);
		
		return inventoryGoodsTransfer;
	}

	@Override
	public List<InventoryGoodsTransfer> findAll() {
		// TODO Auto-generated method stub
		return inventoryGoodsTransferRepository.findAll();
	}

	@Override
	public InventoryGoodsTransfer findById(int id) {
		// TODO Auto-generated method stub
		return inventoryGoodsTransferRepository.findById(id).get();
	}
	
	@Override
	public InventoryGoodsTransfer getInventoryGoodsTransferId(int id) {
		InventoryGoodsTransfer inventoryGoodsIssue = inventoryGoodsTransferRepository.findById(id).get();
	    
	     List<InventoryGoodsTransferList> listItems = inventoryGoodsIssue.getInventoryGoodsTransferList();
	     for (int i = 0; i < listItems.size(); i++) {
	    	 InventoryGoodsTransferList invlist = listItems.get(i);
	        String productNo = invlist.getProductNumber();
	        Integer wareHose = invlist.getFromWarehouse();
	        invlist.setTempRequiredQuantity(Integer.parseInt(inventoryGoodsIssueService.getInStock(productNo,wareHose)));    
	    }
	     inventoryGoodsIssue.setInventoryGoodsTransferList(listItems);
	     logger.info(" inventoryGoodsIssue--------->" + inventoryGoodsIssue);
	    return inventoryGoodsIssue;
	}
	
	

	@Override
	public InventoryGoodsTransfer delete(int id) {
		InventoryGoodsTransfer inventoryGoodsTransfer = inventoryGoodsTransferRepository.findById(id).get();
		inventoryGoodsTransfer.setIsActive(false);
		inventoryGoodsTransferRepository.save(inventoryGoodsTransfer);
		return inventoryGoodsTransfer;
	}

	@Override
	public List<InventoryGoodsTransfer> findByIsActive() {
		// TODO Auto-generated method stub
		return inventoryGoodsTransferRepository.findByIsActive(true,plantService.findPlantIds());
	}

	@Override
	public InventoryGoodsTransfer findLastDocumentNumber() {
		// TODO Auto-generated method stub
		return inventoryGoodsTransferRepository.findTopByOrderByIdDesc();
	}

	@Override
	public InventoryGoodsTransfer getListAmount(InventoryGoodsTransfer inventoryGoodsTransfer) {
		logger.info("getListAmount-->");
		List<InventoryGoodsTransferList> listItems = inventoryGoodsTransfer.getInventoryGoodsTransferList();
		List<InventoryGoodsTransferList> addListItems = new ArrayList<InventoryGoodsTransferList>();
		
		
		Double addAmt=0.0;
		Double addTaxAmt=0.0;
		if (listItems != null) {
			for (int i = 0; i < listItems.size(); i++) {
				InventoryGoodsTransferList grlist = listItems.get(i);
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
			inventoryGoodsTransfer.setInventoryGoodsTransferList(addListItems);
		}
		inventoryGoodsTransfer.setTotalBeforeDisAmt(addAmt);
		inventoryGoodsTransfer.setTaxAmt(""+addTaxAmt);
		logger.info("addAmt-->" + addAmt); 
		logger.info("goodsTransfer.getTotalDiscount()-->" + inventoryGoodsTransfer.getTotalDiscount());
		logger.info("goodsTransfer.getFreight()-->" + inventoryGoodsTransfer.getFreight());
		Double total_amt=0.0;
		if(inventoryGoodsTransfer.getTotalDiscount()==null) inventoryGoodsTransfer.setTotalDiscount(0.0);
		if(inventoryGoodsTransfer.getFreight()==null) inventoryGoodsTransfer.setFreight(0.0);
		 total_amt= UnitPriceListItems.getTotalAmtPayment(addAmt, inventoryGoodsTransfer.getTotalDiscount(), inventoryGoodsTransfer.getFreight(),addTaxAmt);
		 inventoryGoodsTransfer.setAmtRounding("" + total_amt);
		 inventoryGoodsTransfer.setTotalPayment(inventoryGoodsTransfer.getTotalPayment());
		 inventoryGoodsTransfer.setRoundedOff("" + df2.format(inventoryGoodsTransfer.getTotalPayment() - total_amt));
	
	return inventoryGoodsTransfer;
	}

	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	@Override
	public boolean findByDocNumber(String docNum) {
		List<InventoryGoodsTransfer> igt = inventoryGoodsTransferRepository.findByDocNumber(docNum);
		if(igt.size()>0) {
			return true;
		}else {
			return false;
		}
	}
}
