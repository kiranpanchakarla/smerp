package com.smerp.controller.reports;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import com.smerp.model.admin.Plant;
import com.smerp.model.reports.InventoryProductReport;
import com.smerp.model.reports.VendorWiseReport;
import com.smerp.model.search.SearchFilter;
import com.smerp.service.master.PlantService;
import com.smerp.service.reports.InventoryProductReportService;
import com.smerp.util.DownloadProductXLS;
import com.smerp.util.EnumSearchFilter;

@Controller
@RequestMapping("/invReport")
public class InventoryProductReportController {

	private static final Logger logger = LogManager.getLogger(InventoryProductReportController.class);
	
	@Autowired
	InventoryProductReportService inventoryProductReportService;
	
	@Autowired
	private DownloadProductXLS downloadProductXLS;
	
	@Autowired
	PlantService plantService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@GetMapping(value = "/prolist")
	public String poList(Model model,SearchFilter searchFilter) {
		 
		searchFilter.setTypeOf(EnumSearchFilter.INVPRODUCTREPORT.getStatus());
		model.addAttribute("searchFilter", searchFilter);
		model.addAttribute("plantMap", plantMap());
		return "inventoryReports/productReport/list";
	}
	
	@GetMapping("/getSearchFilterPROList")
	public String getSearchFilterPOList(Model model, SearchFilter searchFilter) {
		searchFilter.setTypeOf(EnumSearchFilter.INVPRODUCTREPORT.getStatus());
		ArrayList<Object[]> fullList = inventoryProductReportService.searchFilterBySelection(searchFilter,EnumSearchFilter.INVPRODUCTREPORT.getStatus());
			
			List<InventoryProductReport> productList = inventoryProductReportService.productReportList(fullList);
			model.addAttribute("productList", productList);
			model.addAttribute("plantMap", plantMap());
			  
			model.addAttribute("searchFilter", searchFilter);
			return "inventoryReports/productReport/list";
		 
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
		List<InventoryProductReport> vendorList = inventoryProductReportService.productReportList(fullList);
		
		ByteArrayOutputStream stream;
	    stream = downloadProductXLS.productsListReport(vendorList);
		
		
		
		response.setContentType("text/html");
		OutputStream outstream = response.getOutputStream();
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"Product_Report_" + prFileNameDate + ".xlsx\"");
		stream.writeTo(outstream);
		outstream.flush();
		outstream.close();
	}
	
	public Map<Integer, Object> plantMap() {
		return plantService.findAll().stream().collect(Collectors.toMap(Plant::getId, Plant::getPlantName));
	}
}
