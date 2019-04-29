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

import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.model.reports.MonthsForReport;
import com.smerp.model.reports.VendorWiseReport;
import com.smerp.model.search.SearchFilter;
import com.smerp.service.reports.VendorWiseReportService;
import com.smerp.util.DownloadReportsXLS;
import com.smerp.util.EnumSearchFilter;
import com.smerp.util.MonthlyReportsXLS;
import com.smerp.util.MonthsBetweenDates;

@Controller
@RequestMapping("/vendorReport")
public class VendorWiseReportController {
	
	private static final Logger logger = LogManager.getLogger(VendorWiseReportController.class);

	@Autowired
	VendorWiseReportService  vendorWiseReportService;
	
	@Autowired
	private MonthlyReportsXLS monthlyReportsXLS;
	
	@Autowired
	private MonthsBetweenDates monthsBetweenDates;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@GetMapping(value = "/polist")
	public String poList(Model model,SearchFilter searchFilter) {
		 
		searchFilter.setTypeOf(EnumSearchFilter.POREPORTVENDOR.getStatus());
		model.addAttribute("searchFilter", searchFilter);
		
		return "reports/vendorWise/po/list";
	}
	
	@GetMapping(value = "/invlist")
	public String invList(Model model,SearchFilter searchFilter) {
		 
		searchFilter.setTypeOf(EnumSearchFilter.POREPORTVENDOR.getStatus());
		model.addAttribute("searchFilter", searchFilter);
		
		return "reports/vendorWise/inv/list";
	}
	
	
	@GetMapping("/getSearchFilterPOList")
	public String getSearchFilterPOList(Model model, SearchFilter searchFilter) {
		searchFilter.setTypeOf(EnumSearchFilter.POREPORTVENDOR.getStatus());
		ArrayList<Object[]> fullList = vendorWiseReportService.searchFilterBySelection(searchFilter,EnumSearchFilter.POREPORTVENDOR.getStatus());
			logger.info("fullList" + fullList);
			
			List<VendorWiseReport> vendorList = vendorWiseReportService.vendorReportList(fullList);
			model.addAttribute("vendorList", vendorList);
			logger.info("vendorList" +  vendorList);
			
			 if(searchFilter.getFromDate() != null) {
				 List<String> months = monthsBetweenDates.getMonthsBetweenDates(searchFilter.getFromDate(), searchFilter.getToDate());
					logger.info("Months" +  months);
					model.addAttribute("searchListMonths", months);
			 }
			 
			model.addAttribute("searchFilter", searchFilter);
			return "reports/vendorWise/po/list";
			
		 
	}
	
	@GetMapping("/getSearchFilterINVList")
	public String getSearchFilterINVList(Model model, SearchFilter searchFilter) {
		searchFilter.setTypeOf(EnumSearchFilter.INVREPORTVENDOR.getStatus());
		ArrayList<Object[]> fullList = vendorWiseReportService.searchFilterBySelection(searchFilter,EnumSearchFilter.INVREPORTVENDOR.getStatus());
			logger.info("fullList" + fullList);
			
			List<VendorWiseReport> vendorList = vendorWiseReportService.vendorReportList(fullList);
			model.addAttribute("vendorList", vendorList);
			logger.info("vendorList" +  vendorList);
			
			 if(searchFilter.getFromDate() != null) {
				 List<String> months = monthsBetweenDates.getMonthsBetweenDates(searchFilter.getFromDate(), searchFilter.getToDate());
					logger.info("Months" +  months);
					model.addAttribute("searchListMonths", months);
			 }
			 
			model.addAttribute("searchFilter", searchFilter);
			return "reports/vendorWise/inv/list";
			
		 
	}
	
	@GetMapping("/exportPOExcel")
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
		ArrayList<Object[]> fullList = vendorWiseReportService.searchFilterBySelection(searchFilter,typeOf);
		List<VendorWiseReport> vendorList = vendorWiseReportService.vendorReportList(fullList);
		
		ByteArrayOutputStream stream;
		
		 if(searchFilter.getFromDate() != null) {
			 List<String> months = monthsBetweenDates.getMonthsBetweenDates(searchFilter.getFromDate(), searchFilter.getToDate());
			 stream = monthlyReportsXLS.vendorSearchReport(vendorList,months); 
		 }else {
			  stream = monthlyReportsXLS.VendorReport(vendorList);
		 }
		
		
		response.setContentType("text/html");
		OutputStream outstream = response.getOutputStream();
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"Vendor_Report_" + prFileNameDate + ".xlsx\"");
		stream.writeTo(outstream);
		outstream.flush();
		outstream.close();
	}
}
