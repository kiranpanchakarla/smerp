package com.smerp.controller.inventorytransactions;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.smerp.model.admin.Department;
import com.smerp.model.admin.Plant;
import com.smerp.model.inventory.Product;
import com.smerp.model.inventory.TaxCode;
import com.smerp.model.inventorytransactions.InventoryGoodsIssue;
import com.smerp.model.inventorytransactions.InventoryGoodsReceipt;
import com.smerp.model.search.SearchFilter;
import com.smerp.repository.admin.TaxCodeRepository;
import com.smerp.service.admin.DepartmentService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.inventorytransactions.InventoryGoodsIssueService;
import com.smerp.service.master.PlantService;
import com.smerp.util.ContextUtil;
import com.smerp.util.DocNumberGenerator;
import com.smerp.util.EnumSearchFilter;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.HTMLToPDFGenerator;
import com.smerp.util.RequestContext;

@Controller
@RequestMapping("/invgi")
public class InventoryGoodsIssueController {

	private static final Logger logger = LogManager.getLogger(InventoryGoodsIssueController.class);

	private static String pdfUploadedPath;
	
	@Autowired
	InventoryGoodsIssueService inventoryGoodsIssueService;

	@Autowired
	PlantService plantService;

	@Autowired
	TaxCodeRepository taxCodeRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	DepartmentService departmentService;

	@Autowired
	private HTMLToPDFGenerator hTMLToPDFGenerator;
	
	@Autowired
	private DocNumberGenerator docNumberGenerator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
public Map<Object,Double> taxCode() {
		
		//return taxCodeRepository.findAllByOrderByTaxCodeAsc().stream().collect(Collectors.toMap(TaxCode::getTaxCode, TaxCode::getTaxCode));
		
		return taxCodeRepository.findAll().stream().collect(Collectors.toMap(TaxCode::getDescription, TaxCode::getTaxCode));
		
		/*return taxCodeRepository.findAllByOrderByTaxCodeAsc().stream().collect(Collectors.toMap(TaxCode::getDescription, TaxCode::getTaxCode,
                (v1,v2) ->{ throw new RuntimeException(String.format("Duplicate key for values %s and %s", v1, v2));},
                TreeMap::new));*/  // for Sorted Values
	}

	public Map<Integer, Object> plantMap() {
		return plantService.findAll().stream().collect(Collectors.toMap(Plant::getId, Plant::getPlantName));
	}
	
	public Map<Integer, Object> deptMap() {
		return departmentService.findAll().stream().collect(Collectors.toMap(Department::getId, Department::getName));
	}

	@GetMapping(value = "/create")
	public String create(Model model, InventoryGoodsIssue invGoodsIssue) throws JsonProcessingException {
		logger.info("Inside InventoryGoodsIssueController Create Method");
		logger.info("gr-->" + invGoodsIssue);
		logger.info("taxCode()-->" + taxCode());
		logger.info("plantMap()-->" + plantMap());
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("deptMap",deptMap());
		model.addAttribute("plantMapSize", plantMap().size());
		model.addAttribute("departmentListSize", deptMap().size());
		model.addAttribute("taxCodeMap", taxCode());
		
		Integer count = docNumberGenerator.getDocNoCountByDocType(EnumStatusUpdate.IGI.getStatus());
		logger.info("IGI count-->" + count);
		
		InventoryGoodsIssue invgr = inventoryGoodsIssueService.findLastDocumentNumber();
		if (invgr != null && invgr.getDocNumber() != null) {
			invGoodsIssue.setDocNumber(GenerateDocNumber.documentNumberGeneration(invgr.getDocNumber(),count));
		} else {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDateTime now = LocalDateTime.now();
	    invGoodsIssue.setDocNumber(GenerateDocNumber.documentNumberGeneration("IGI"+(String)dtf.format(now) +"0",count));
		}
		logger.info("IGR Details-->" + invGoodsIssue);
		 mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", mapper.writeValueAsString(productService.findAllProductDescription("product")));
		model.addAttribute("gr", invGoodsIssue);
		return "inv_goodsIssue/create";
	}
	
	@GetMapping("/list")
	public String list(Model model, SearchFilter searchFilter) {
		List<InventoryGoodsIssue> list = inventoryGoodsIssueService.findByIsActive();
		logger.info("list" + list);
		searchFilter.setTypeOf(EnumSearchFilter.INVGI.getStatus());
		model.addAttribute("searchFilter", searchFilter);
		model.addAttribute("list", list);
		return "inv_goodsIssue/list";
	}
	
	@PostMapping("/save")
	public String saveInvGI(InventoryGoodsIssue invGoodsIssue) {
		logger.info("Inside save method" + invGoodsIssue);
		
		if(invGoodsIssue.getId() == null) {
			boolean status = inventoryGoodsIssueService.findByDocNumber(invGoodsIssue.getDocNumber());
			if(!status) {
				logger.info("igi details" + inventoryGoodsIssueService.save(invGoodsIssue));
			}else {
				Integer count = docNumberGenerator.getDocNoCountByDocType(EnumStatusUpdate.IGI.getStatus());
				logger.info("count-->" + count);
				
				InventoryGoodsIssue igidetails = inventoryGoodsIssueService.findLastDocumentNumber();
				if (igidetails != null && igidetails.getDocNumber() != null) {
					invGoodsIssue.setDocNumber(GenerateDocNumber.documentNumberGeneration(igidetails.getDocNumber(),count));
				}
				logger.info("igi details" + inventoryGoodsIssueService.save(invGoodsIssue));
			}
		}else {
			logger.info("igi details" + inventoryGoodsIssueService.save(invGoodsIssue));	
		}
		return "redirect:list";
	}
	
	@PostMapping(value = "/delete")
	public String delete(@RequestParam("id") int id) {

		logger.info("Delete msg");
		inventoryGoodsIssueService.delete(id);
		return "redirect:list";
	}
	
	
	
	
	 @GetMapping("/edit")
		public String edit(String id, Model model) throws JsonProcessingException {
			logger.info("id-->" + id);
			InventoryGoodsIssue invGR = inventoryGoodsIssueService.getinventoryGoodsIssueId(Integer.parseInt(id));
			invGR = inventoryGoodsIssueService.getListAmount(invGR);
			ObjectMapper mapper = poloadData(model, invGR);
			mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			model.addAttribute("productList", mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
			model.addAttribute("descriptionList", mapper.writeValueAsString(productService.findAllProductDescription("product")));
			model.addAttribute("plantMap", plantMap());
			model.addAttribute("plantMapSize", plantMap().size());
			model.addAttribute("deptMap", deptMap());
			model.addAttribute("departmentListSize", deptMap().size());
			model.addAttribute("taxCodeMap", taxCode());
			model.addAttribute("productList",
					mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
			model.addAttribute("descriptionList", mapper.writeValueAsString(productService.findAllProductDescription("product")));
			 
			model.addAttribute("gr", invGR);
			return "inv_goodsIssue/create";
		} 
		 
		 @GetMapping("/view")
			public String view(String id, Model model) throws JsonProcessingException {
				logger.info("id-->" + id);
				InventoryGoodsIssue invGR = inventoryGoodsIssueService.findById(Integer.parseInt(id));
				invGR = inventoryGoodsIssueService.getListAmount(invGR);
				poloadData(model, invGR);
				model.addAttribute("gr", invGR);
				model.addAttribute("plantMap", plantMap());
				model.addAttribute("taxCodeMap", taxCode());
				model.addAttribute("deptMap", deptMap());
				return "inv_goodsIssue/view";
			}
		 
		 private ObjectMapper poloadData(Model model, InventoryGoodsIssue invGR) {
				ObjectMapper mapper = new ObjectMapper();
				  
				model.addAttribute("inventoryGoodsIssueList", invGR.getInventoryGoodsIssueList());
				return mapper;
			}
		 
			@RequestMapping(value = "/getInStock", method = RequestMethod.GET)
		    @ResponseBody
		    private String getInStock(@RequestParam("productNo") String productNo,@RequestParam("warehouse") String warehouse) throws JsonProcessingException {
		        logger.info("warehouse-->" + warehouse );
		        if(!productNo.isEmpty() &&  !warehouse.isEmpty()) {
		       
		        	String  getInStock = inventoryGoodsIssueService.getInStock(productNo,Integer.parseInt(warehouse));
		        	
		        	return getInStock;
		        }else {
		        	return "0";
		        }
		    }
		 
		 
		 
		 @RequestMapping("/downloadPdf")
			public void downloadHtmlPDF(HttpServletResponse response, String htmlData, HttpServletRequest request,
					HttpSession session, String regType, Model model,String orgId,String id) throws Exception {
				
				logger.info("id -->" + id);
				InventoryGoodsIssue invGR = inventoryGoodsIssueService.findById(Integer.parseInt(id));
				logger.info("Inventory goods Receipt -->" + invGR);
				
				RequestContext.set(ContextUtil.populateContexturl(request));
				String path = "";
				
				 path = hTMLToPDFGenerator.getOfflineSummaryToPDF(HTMLToPDFGenerator.HTML_PDF_Offline)
		                .OfflineHtmlStringToPdfForInvGoodsIssue(pdfUploadedPath,invGR); 
						
				logger.info("path " +path);
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				File file = new File(path);
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
				FileInputStream fileInputStream = new FileInputStream(path);
				int i;
				while ((i = fileInputStream.read()) != -1) {
					out.write(i);
				}
				fileInputStream.close();
				out.close();
			} 
		 
		 @GetMapping("/getSearchFilterList")
			public String getSearchFilterList(Model model, SearchFilter searchFilter) {
				List<InventoryGoodsIssue> list = inventoryGoodsIssueService.searchFilterBySelection(searchFilter);
				logger.info("list" + list);
				model.addAttribute("list", list);
				model.addAttribute("searchFilter", searchFilter);
				return "inv_goodsIssue/list";
			}
				
			@GetMapping("/exportINVGIExcel")
			public void download(HttpServletResponse response, Model model, HttpServletRequest request, String searchBy,
					String fieldName, String sortBy, String dateSelect, String fromDateString, String toDateString)
					throws Exception {

				SearchFilter searchFilter = new SearchFilter();
				searchFilter.setSearchBy(searchBy);
				searchFilter.setFieldName(fieldName);
				searchFilter.setSortBy(sortBy);
				searchFilter.setDateSelect(dateSelect);

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

				String invGIFileNameDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				List<InventoryGoodsIssue> list = inventoryGoodsIssueService.searchFilterBySelection(searchFilter);

				//ByteArrayOutputStream stream = downloadReportsXLS.POReport(list);
				response.setContentType("text/html");
				OutputStream outstream = response.getOutputStream();
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", "attachment; filename=\"INVGI_Report_" + invGIFileNameDate + ".xlsx\"");
				//stream.writeTo(outstream);
				outstream.flush();
				outstream.close();
			}
		 
}
