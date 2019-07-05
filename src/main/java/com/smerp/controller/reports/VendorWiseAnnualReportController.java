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
import com.smerp.model.reports.VendorWiseReport;
import com.smerp.model.search.SearchFilter;
import com.smerp.service.reports.VendorWiseReportService;
import com.smerp.util.AnnualReportsXLS;
import com.smerp.util.EnumSearchFilter;

@Controller
@RequestMapping("/annualVendorReport")
public class VendorWiseAnnualReportController {

	private static final Logger logger = LogManager.getLogger(VendorWiseAnnualReportController.class);
	
	@Autowired
	VendorWiseReportService  vendorWiseReportService;
	
	@Autowired
	private AnnualReportsXLS annualReportsXLS;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@GetMapping(value = "/polist")
	public String poAnnualList(Model model,SearchFilter searchFilter) {
		 
		searchFilter.setTypeOf(EnumSearchFilter.POREPORTVENDOR.getStatus());
		model.addAttribute("searchFilter", searchFilter);
		
		return "annualReports/vendorWise/po/list";
	}
	
	@GetMapping(value = "/invlist")
	public String invAnnualList(Model model,SearchFilter searchFilter) {
		 
		searchFilter.setTypeOf(EnumSearchFilter.POREPORTVENDOR.getStatus());
		model.addAttribute("searchFilter", searchFilter);
		
		return "annualReports/vendorWise/inv/list";
	}
	
	@GetMapping("/getSearchFilterPOList")
	public String getSearchFilterPOAnnualList(Model model, SearchFilter searchFilter) {
		searchFilter.setTypeOf(EnumSearchFilter.POREPORTVENDOR.getStatus());
		ArrayList<Object[]> fullList = vendorWiseReportService.searchFilterBySelectionForAnnualReports(searchFilter,EnumSearchFilter.POREPORTVENDOR.getStatus());
			
			List<VendorWiseReport> vendorList = vendorWiseReportService.vendorReportAnnualList(fullList);
			model.addAttribute("vendorList", vendorList);
			 
			model.addAttribute("searchFilter", searchFilter);
			return "annualReports/vendorWise/po/list";
			
		 
	}
	
	@GetMapping("/getSearchFilterINVList")
	public String getSearchFilterINVList(Model model, SearchFilter searchFilter) {
		searchFilter.setTypeOf(EnumSearchFilter.INVREPORTVENDOR.getStatus());
		ArrayList<Object[]> fullList = vendorWiseReportService.searchFilterBySelectionForAnnualReports(searchFilter,EnumSearchFilter.INVREPORTVENDOR.getStatus());
			
			List<VendorWiseReport> vendorList = vendorWiseReportService.vendorReportAnnualList(fullList);
			model.addAttribute("vendorList", vendorList);
			 
			model.addAttribute("searchFilter", searchFilter);
			return "annualReports/vendorWise/inv/list";
			
		 
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
		ArrayList<Object[]> fullList = vendorWiseReportService.searchFilterBySelectionForAnnualReports(searchFilter,typeOf);
		List<VendorWiseReport> vendorList = vendorWiseReportService.vendorReportAnnualList(fullList);
		
		ByteArrayOutputStream stream;
		stream = annualReportsXLS.VendorReport(vendorList);
		  
		response.setContentType("text/html");
		OutputStream outstream = response.getOutputStream();
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"Vendor_Report_" + prFileNameDate + ".xlsx\"");
		stream.writeTo(outstream);
		outstream.flush();
		outstream.close();
	}
	
}
