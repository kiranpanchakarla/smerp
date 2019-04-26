package com.smerp.service.reports;

import java.util.ArrayList;
import java.util.List;
import com.smerp.model.reports.ProductWiseReport;
import com.smerp.model.search.SearchFilter;

public interface ProductWiseReportService {

    ArrayList<Object[]> getPOList();
	
	ArrayList<Object[]> getINVList();
	
	ArrayList<Object[]> searchFilterBySelection(SearchFilter searchFilter,String typeOf);
	
	List<ProductWiseReport> vendorReportList(ArrayList<Object[]> arrayList);
}
