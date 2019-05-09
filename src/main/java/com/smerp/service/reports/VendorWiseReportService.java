package com.smerp.service.reports;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.smerp.model.reports.MonthsForReport;
import com.smerp.model.reports.VendorWiseReport;
import com.smerp.model.search.SearchFilter;

public interface VendorWiseReportService {

	ArrayList<Object[]> getPOList();
	
	ArrayList<Object[]> getINVList();
	
	ArrayList<Object[]> searchFilterBySelection(SearchFilter searchFilter,String typeOf);
	
	ArrayList<Object[]> searchFilterBySelectionForAnnualReports(SearchFilter searchFilter,String typeOf);
	
	List<VendorWiseReport> vendorReportList(ArrayList<Object[]> arrayList);
	
	List<VendorWiseReport> vendorReportAnnualList(ArrayList<Object[]> arrayList);
	
}
