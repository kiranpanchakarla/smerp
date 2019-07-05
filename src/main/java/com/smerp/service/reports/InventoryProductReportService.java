package com.smerp.service.reports;

import java.util.ArrayList;
import java.util.List;

import com.smerp.model.reports.InventoryGoodsIssueReport;
import com.smerp.model.reports.InventoryProductReport;
import com.smerp.model.reports.InventoryWarehouseReport;
import com.smerp.model.search.SearchFilter;

public interface InventoryProductReportService {

	ArrayList<Object[]> getProductList();
	
	ArrayList<Object[]> searchFilterBySelection(SearchFilter searchFilter,String typeOf);
	
	List<InventoryProductReport> productReportList(ArrayList<Object[]> arrayList);
	
	List<InventoryWarehouseReport> warehouseReportList(ArrayList<Object[]> arrayList);
	
	List<InventoryGoodsIssueReport> invGIReportList(ArrayList<Object[]> arrayList);
}
