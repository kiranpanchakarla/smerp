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
import com.smerp.model.reports.ProductWiseReport;
import com.smerp.model.search.SearchFilter;
import com.smerp.service.reports.ProductWiseReportService;
import com.smerp.util.EnumSearchFilter;
import com.smerp.util.MonthlyReportsXLS;
import com.smerp.util.MonthsBetweenDates;

@Controller
@RequestMapping("/productReport")
public class ProductWiseReportController {

	private static final Logger logger = LogManager.getLogger(ProductWiseReportController.class);
	
	@Autowired
	ProductWiseReportService productWiseReportService;
	
	@Autowired
	private MonthlyReportsXLS monthlyReportsXLS;
	
	@Autowired
	private MonthsBetweenDates monthsBetweenDates;
	
	@GetMapping(value = "/list")
	public String getList(Model model) {
		 
		return "reports/productWise/list";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@GetMapping(value = "/polist")
	public String poList(Model model,SearchFilter searchFilter) {
		 
		searchFilter.setTypeOf(EnumSearchFilter.POREPORTPRODUCT.getStatus());
		model.addAttribute("searchFilter", searchFilter);
		
		return "reports/productWise/po/list";
	}
	
	@GetMapping(value = "/invlist")
	public String invList(Model model,SearchFilter searchFilter) {
		 
		searchFilter.setTypeOf(EnumSearchFilter.INVREPORTVENDOR.getStatus());
		model.addAttribute("searchFilter", searchFilter);
		
		return "reports/productWise/inv/list";
	}
	
	
	@GetMapping("/getSearchFilterPOList")
	public String getSearchFilterPOList(Model model, SearchFilter searchFilter) {
		searchFilter.setTypeOf(EnumSearchFilter.POREPORTPRODUCT.getStatus());
		ArrayList<Object[]> fullList = productWiseReportService.searchFilterBySelection(searchFilter,EnumSearchFilter.POREPORTPRODUCT.getStatus());
			logger.info("fullList" + fullList);
			
			List<ProductWiseReport> vendorList = productWiseReportService.vendorReportList(fullList);
			model.addAttribute("vendorList", vendorList);
			logger.info("vendorList" +  vendorList);
			
			 if(searchFilter.getFromDate() != null) {
				 List<String> months = monthsBetweenDates.getMonthsBetweenDates(searchFilter.getFromDate(), searchFilter.getToDate());
					logger.info("Months" +  months);
					model.addAttribute("searchListMonths", months);
			 }
			 
			model.addAttribute("searchFilter", searchFilter);
			return "reports/productWise/po/list";
			
		 
	}
	
	@GetMapping("/getSearchFilterINVList")
	public String getSearchFilterINVList(Model model, SearchFilter searchFilter) {
		searchFilter.setTypeOf(EnumSearchFilter.INVREPORTPRODUCT.getStatus());
		ArrayList<Object[]> fullList = productWiseReportService.searchFilterBySelection(searchFilter,EnumSearchFilter.INVREPORTPRODUCT.getStatus());
			logger.info("fullList" + fullList);
			
			List<ProductWiseReport> vendorList = productWiseReportService.vendorReportList(fullList);
			model.addAttribute("vendorList", vendorList);
			logger.info("vendorList" +  vendorList);
			
			 if(searchFilter.getFromDate() != null) {
				 List<String> months = monthsBetweenDates.getMonthsBetweenDates(searchFilter.getFromDate(), searchFilter.getToDate());
					logger.info("Months" +  months);
					model.addAttribute("searchListMonths", months);
			 }
			 
			model.addAttribute("searchFilter", searchFilter);
			return "reports/productWise/inv/list";
			
		 
	}
	
	@GetMapping("/exportPOExcel")
	public void download(HttpServletResponse response, Model model, HttpServletRequest request, String searchBy,
			String fieldName, String sortBy, String dateSelect, String fromDateString, String toDateString,String id)
			throws Exception {
		
		logger.info("id---->" + id);
		id = id.substring( 0, id.indexOf("?"));
		logger.info("id---->" + id);
		SearchFilter searchFilter = new SearchFilter();
		searchFilter.setSearchBy(searchBy);
		searchFilter.setFieldName(fieldName);
		searchFilter.setSortBy(sortBy);
		searchFilter.setDateSelect(dateSelect);
		searchFilter.setTypeOf(id);
		
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
		ArrayList<Object[]> fullList = productWiseReportService.searchFilterBySelection(searchFilter,id);
		List<ProductWiseReport> vendorList = productWiseReportService.vendorReportList(fullList);
		
		ByteArrayOutputStream stream;
		
		 if(searchFilter.getFromDate() != null) {
			 List<String> months = monthsBetweenDates.getMonthsBetweenDates(searchFilter.getFromDate(), searchFilter.getToDate());
			// stream = monthlyReportsXLS.vendorSearchReport(vendorList,months); 
		 }else {
			 // stream = monthlyReportsXLS.VendorReport(vendorList);
		 }
		
		
		response.setContentType("text/html");
		OutputStream outstream = response.getOutputStream();
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"Vendor_Report_" + prFileNameDate + ".xlsx\"");
		//stream.writeTo(outstream);
		outstream.flush();
		outstream.close();
	}
	
}
