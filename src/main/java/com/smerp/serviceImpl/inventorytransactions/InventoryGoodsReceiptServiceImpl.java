package com.smerp.serviceImpl.inventorytransactions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smerp.model.inventorytransactions.InventoryGoodsReceipt;
import com.smerp.model.inventorytransactions.InventoryGoodsReceiptList;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.repository.inventorytransactions.InventoryGoodsReceiptRepository;
import com.smerp.service.inventorytransactions.InventoryGoodsReceiptService;
import com.smerp.util.EmailGenerator;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.RequestContext;
import com.smerp.util.UnitPriceListItems;

@Service
@Transactional
public class InventoryGoodsReceiptServiceImpl implements InventoryGoodsReceiptService {

	private static final Logger logger = LogManager.getLogger(InventoryGoodsReceiptServiceImpl.class);
			
	@Autowired
	InventoryGoodsReceiptRepository inventoryGoodsReceiptRepository;
	
	@Autowired
	EmailGenerator emailGenerator;
	
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
				if (listItems.get(i).getProductNumber() == null  && listItems.get(i).getRequiredQuantity() == null) {
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
		return inventoryGoodsReceiptRepository.findByIsActive(true);
	}

	@Override
	public InventoryGoodsReceipt findLastDocumentNumber() {
		// TODO Auto-generated method stub
		return inventoryGoodsReceiptRepository.findTopByOrderByIdDesc();
	}

	@Override
	public InventoryGoodsReceipt getListAmount(InventoryGoodsReceipt inventoryGoodsReceipt) {
		logger.info("getListAmount-->");
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
		logger.info("addAmt-->" + addAmt); 
		logger.info("goodsReceipt.getTotalDiscount()-->" + inventoryGoodsReceipt.getTotalDiscount());
		logger.info("goodsReceipt.getFreight()-->" + inventoryGoodsReceipt.getFreight());
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
}
