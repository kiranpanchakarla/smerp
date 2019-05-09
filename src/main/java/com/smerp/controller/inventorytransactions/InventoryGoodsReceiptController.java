package com.smerp.controller.inventorytransactions;

import java.io.ByteArrayOutputStream;
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
import java.util.TreeMap;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.smerp.model.admin.Plant;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.TaxCode;
import com.smerp.model.inventorytransactions.InventoryGoodsReceipt;
import com.smerp.model.search.SearchFilter;
import com.smerp.repository.admin.TaxCodeRepository;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.inventorytransactions.InventoryGoodsReceiptService;
import com.smerp.service.master.PlantService;
import com.smerp.util.ContextUtil;
import com.smerp.util.DocNumberGenerator;
import com.smerp.util.DownloadReportsXLS;
import com.smerp.util.EnumSearchFilter;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.HTMLToPDFGenerator;
import com.smerp.util.RequestContext;

@Controller
@RequestMapping("/invgr")
public class InventoryGoodsReceiptController {

	private static final Logger logger = LogManager.getLogger(InventoryGoodsReceiptController.class);

	private static String pdfUploadedPath;
	
	@Autowired
	InventoryGoodsReceiptService inventoryGoodsReceiptService;

	@Autowired
	PlantService plantService;

	@Autowired
	TaxCodeRepository taxCodeRepository;
	
	@Autowired
	ProductService productService;

	@Autowired
	private HTMLToPDFGenerator hTMLToPDFGenerator;
	
	@Autowired
	private DocNumberGenerator docNumberGenerator;
	
	@Autowired
	private DownloadReportsXLS downloadReportsXLS;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@GetMapping(value = "/create")
	public String create(Model model, InventoryGoodsReceipt invGoodsReceipt) throws JsonProcessingException {
		logger.info("Inside InventoryGoodsReceiptController Create Method");
		ObjectMapper mapper = new ObjectMapper();
		 mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("plantMapSize", plantMap().size());
		model.addAttribute("taxCodeMap", taxCode());
		
		Integer count = docNumberGenerator.getDocNoCountByDocType(EnumStatusUpdate.IGR.getStatus());
		
		InventoryGoodsReceipt invgr = inventoryGoodsReceiptService.findLastDocumentNumber();
		if (invgr != null && invgr.getDocNumber() != null) {
			invGoodsReceipt.setDocNumber(GenerateDocNumber.documentNumberGeneration(invgr.getDocNumber(),count));
		} else {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDateTime now = LocalDateTime.now();
	    invGoodsReceipt.setDocNumber(GenerateDocNumber.documentNumberGeneration("IGR"+(String)dtf.format(now) +"0",count));
		}
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", mapper.writeValueAsString(productService.findAllProductDescription("product")));
		model.addAttribute("gr", invGoodsReceipt);
		return "inv_goodsReceipt/create";
	}

	@GetMapping("/list")
	public String list(Model model, SearchFilter searchFilter) {
		List<InventoryGoodsReceipt> list = inventoryGoodsReceiptService.findByIsActive();
		searchFilter.setTypeOf(EnumSearchFilter.INVGR.getStatus());
		model.addAttribute("searchFilter", searchFilter);
		model.addAttribute("list", list);
		return "inv_goodsReceipt/list";
	}
	
	@PostMapping("/save")
	public String saveInvGR(InventoryGoodsReceipt invGoodsReceipt) {
		logger.info("Inside save method");
		
		if(invGoodsReceipt.getId() == null) {
			boolean status = inventoryGoodsReceiptService.findByDocNumber(invGoodsReceipt.getDocNumber());
			if(!status) {
				inventoryGoodsReceiptService.save(invGoodsReceipt);
			}else {
				Integer count = docNumberGenerator.getDocNoCountByDocType(EnumStatusUpdate.IGR.getStatus());
				
				InventoryGoodsReceipt grdetails = inventoryGoodsReceiptService.findLastDocumentNumber();
				if (grdetails != null && grdetails.getDocNumber() != null) {
					invGoodsReceipt.setDocNumber(GenerateDocNumber.documentNumberGeneration(grdetails.getDocNumber(),count));
				}
				inventoryGoodsReceiptService.save(invGoodsReceipt);
			}
		}else {
			inventoryGoodsReceiptService.save(invGoodsReceipt);	
		}
		return "redirect:list";
	}
	
	@PostMapping(value = "/delete")
	public String delete(@RequestParam("id") int id) {

		inventoryGoodsReceiptService.delete(id);
		return "redirect:list";
	}
	
	 @GetMapping("/edit")
	public String edit(String id, Model model) throws JsonProcessingException {
		InventoryGoodsReceipt invGR = inventoryGoodsReceiptService.findById(Integer.parseInt(id));
		invGR = inventoryGoodsReceiptService.getListAmount(invGR);
		ObjectMapper mapper = poloadData(model, invGR);
		 mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		model.addAttribute("productList", mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", mapper.writeValueAsString(productService.findAllProductDescription("product")));
		// model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("plantMapSize", plantMap().size());
		model.addAttribute("taxCodeMap", taxCode());
		 
		model.addAttribute("gr", invGR);
		return "inv_goodsReceipt/create";
	} 
	 
	 @GetMapping("/view")
		public String view(String id, Model model) throws JsonProcessingException {
			InventoryGoodsReceipt invGR = inventoryGoodsReceiptService.findById(Integer.parseInt(id));
			invGR = inventoryGoodsReceiptService.getListAmount(invGR);
			poloadData(model, invGR);
			model.addAttribute("gr", invGR);
			model.addAttribute("plantMap", plantMap());
			model.addAttribute("taxCodeMap", taxCode());
			return "inv_goodsReceipt/view";
		}
	 
	 private ObjectMapper poloadData(Model model, InventoryGoodsReceipt invGR) {
			ObjectMapper mapper = new ObjectMapper();
			  
			model.addAttribute("inventoryGoodsReceiptList", invGR.getInventoryGoodsReceiptList());
			return mapper;
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
	
	@RequestMapping("/downloadPdf")
	public void downloadHtmlPDF(HttpServletResponse response, String htmlData, HttpServletRequest request,
			HttpSession session, String regType, Model model,String orgId,String id) throws Exception {
		
		logger.info("id -->" + id);
		InventoryGoodsReceipt invGR = inventoryGoodsReceiptService.findById(Integer.parseInt(id));
		logger.info("Inventory goods Receipt -->" + invGR);
		
		RequestContext.set(ContextUtil.populateContexturl(request));
		String path = "";
		
		 path = hTMLToPDFGenerator.getOfflineSummaryToPDF(HTMLToPDFGenerator.HTML_PDF_Offline)
                .OfflineHtmlStringToPdfForInvGoodsReceipt(pdfUploadedPath,invGR); 
				
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
		List<InventoryGoodsReceipt> list = inventoryGoodsReceiptService.searchFilterBySelection(searchFilter);
		model.addAttribute("list", list);
		model.addAttribute("searchFilter", searchFilter);
		return "inv_goodsReceipt/list";
	}
		
	@GetMapping("/exportINVGRExcel")
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

		String invGRFileNameDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		List<InventoryGoodsReceipt> list = inventoryGoodsReceiptService.searchFilterBySelection(searchFilter);

		//ByteArrayOutputStream stream = downloadReportsXLS.POReport(list);
		response.setContentType("text/html");
		OutputStream outstream = response.getOutputStream();
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"INVGR_Report_" + invGRFileNameDate + ".xlsx\"");
		//stream.writeTo(outstream);
		outstream.flush();
		outstream.close();
	}
}
