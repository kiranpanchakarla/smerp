package com.smerp.service.admin;


import java.util.List;

import com.smerp.model.admin.DashboardCount;
import com.smerp.model.inventory.InventoryGoodsIssueList;
import com.smerp.model.inventory.InventoryProductsList;
import com.smerp.model.inventory.MinimumQuantityList;

public interface DashboardCountService {

	/*DashboardCount findAll();
	
	DashboardCount findRFQCount();
	
	DashboardCount findPOCount();
	
	DashboardCount findGoodsReceiptCount();*/
	
	List<MinimumQuantityList> minProductQtyList();
	
	List<InventoryProductsList> inventoryQtyList(int id);
	
	List<InventoryGoodsIssueList> inventoryGoodsIssueList(int id);
	
	List<DashboardCount> getCount();
}
