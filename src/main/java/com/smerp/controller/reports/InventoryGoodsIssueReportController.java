package com.smerp.controller.reports;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smerp.model.reports.InventoryGoodsIssueReport;
import com.smerp.model.reports.InventoryWarehouseReport;
import com.smerp.model.search.SearchFilter;
import com.smerp.service.reports.InventoryProductReportService;
import com.smerp.util.DownloadProductXLS;
import com.smerp.util.EnumSearchFilter;

@Controller
@RequestMapping("/invGIReport")
public class InventoryGoodsIssueReportController {
	
	private static final Logger logger = LogManager.getLogger(InventoryGoodsIssueReportController.class);

	@Autowired
	InventoryProductReportService inventoryProductReportService;
	
	@Autowired
	private DownloadProductXLS downloadProductXLS;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@GetMapping(value = "/prolist")
	public String poList(Model model,SearchFilter searchFilter) {
		 
		searchFilter.setTypeOf(EnumSearchFilter.INVGIREPORT.getStatus());
		model.addAttribute("searchFilter", searchFilter);
		
		return "inventoryReports/giReport/list";
	}
	
	@GetMapping("/getSearchFilterPROList")
	public String getSearchFilterPOList(Model model, SearchFilter searchFilter) {
		searchFilter.setTypeOf(EnumSearchFilter.INVGIREPORT.getStatus());
		ArrayList<Object[]> fullList = inventoryProductReportService.searchFilterBySelection(searchFilter,EnumSearchFilter.INVGIREPORT.getStatus());
			
			List<InventoryGoodsIssueReport> productList = inventoryProductReportService.invGIReportList(fullList);
			model.addAttribute("productList", productList);
			
			  
			model.addAttribute("searchFilter", searchFilter);
			return "inventoryReports/giReport/list";
		 
	}
	
	@GetMapping("/exportPROExcel")
	public void download(HttpServletResponse response, Model model, HttpServletRequest request, String searchBy,
			String fieldName, String sortBy, String dateSelect, String fromDateString, String toDateString,String id)
			throws Exception {
		
		String typeOf = id.substring( 0, id.indexOf("?"));
		searchBy = id.substring(id.lastIndexOf("=") + 1);
		
		SearchFilter searchFilter = new SearchFilter();
		searchFilter.setSearchBy(searchBy);
		searchFilter.setFieldName(fieldName);
		searchFilter.setSortBy(sortBy);
		searchFilter.setDateSelect(dateSelect);
		searchFilter.setTypeOf(typeOf);
		
		if (!fromDateString.equals("null")) {
			Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromDateString);
			Date toDate = new Date();
			if (!toDateString.equals("null")) {
				toDate = new SimpleDateFormat("yyyy-MM-dd").parse(toDateString);
			} else {
				String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				toDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
			}
			searchFilter.setFromDate(fromDate);
			searchFilter.setToDate(toDate);
		}
		 
		
		String prFileNameDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		ArrayList<Object[]> fullList = inventoryProductReportService.searchFilterBySelection(searchFilter,typeOf);
		List<InventoryGoodsIssueReport> vendorList = inventoryProductReportService.invGIReportList(fullList);
		
		ByteArrayOutputStream stream;
	    stream = downloadProductXLS.invGIReport(vendorList);
		
		
		response.setContentType("text/html");
		OutputStream outstream = response.getOutputStream();
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"Product_Report_" + prFileNameDate + ".xlsx\"");
		stream.writeTo(outstream);
		outstream.flush();
		outstream.close();
	}

}
