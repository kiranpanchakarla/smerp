package com.smerp.service.reports;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.smerp.model.reports.WarehouseWiseReport;
import com.smerp.model.search.SearchFilter;

public interface WarehouseWiseReportService {

	ArrayList<Object[]> getPOList();

	ArrayList<Object[]> getINVList();

	ArrayList<Object[]> searchFilterBySelection(SearchFilter searchFilter, String typeOf);
	
	ArrayList<Object[]> searchFilterBySelectionForAnnualReports(SearchFilter searchFilter, String typeOf);

	List<WarehouseWiseReport> plantReportList(ArrayList<Object[]> arrayList);
	
	List<WarehouseWiseReport> annualReportList(ArrayList<Object[]> arrayList);
}
