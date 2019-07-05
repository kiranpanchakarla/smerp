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
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.smerp.model.admin.User;
import com.smerp.model.inventory.TaxCode;
import com.smerp.model.inventorytransactions.InventoryGoodsIssue;
import com.smerp.model.inventorytransactions.InventoryGoodsReceipt;
import com.smerp.model.inventorytransactions.InventoryGoodsTransfer;
import com.smerp.model.search.SearchFilter;
import com.smerp.repository.admin.TaxCodeRepository;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.inventorytransactions.InventoryGoodsTransferService;
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
@RequestMapping("/invgt")
public class InventoryGoodsTransferController {

	private static final Logger logger = LogManager.getLogger(InventoryGoodsTransferController.class);

	private static String pdfUploadedPath;
	
	@Autowired
	PlantService plantService;

	@Autowired
	TaxCodeRepository taxCodeRepository;
	
	@Autowired
	ProductService productService;

	@Autowired
	private HTMLToPDFGenerator hTMLToPDFGenerator;
	
	@Autowired
	InventoryGoodsTransferService inventoryGoodsTransferService;
	
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
	public String create(Model model, InventoryGoodsTransfer invGoodsTransfer) throws JsonProcessingException {
		logger.info("Inside InventoryGoodsTransferController Create Method");
		ObjectMapper mapper = new ObjectMapper();
		 mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		model.addAttribute("user", getUser());
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("plantMapSize", plantMap().size());
		
		model.addAttribute("findPlantAll", findPlantAll());
		
		model.addAttribute("taxCodeMap", taxCode());
		
		Integer count = docNumberGenerator.getDocNoCountByDocType(EnumStatusUpdate.IGT.getStatus());
		
		InventoryGoodsTransfer invgr = inventoryGoodsTransferService.findLastDocumentNumber();
		if (invgr != null && invgr.getDocNumber() != null) {
			invGoodsTransfer.setDocNumber(GenerateDocNumber.documentNumberGeneration(invgr.getDocNumber(),count));
		} else {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDateTime now = LocalDateTime.now();
	    invGoodsTransfer.setDocNumber(GenerateDocNumber.documentNumberGeneration("IGT"+(String)dtf.format(now) +"0",count));
		}
		
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", new ObjectMapper().writeValueAsString(productService.findAllProductDescription("product")));
		model.addAttribute("gr", invGoodsTransfer);
		return "inv_goodsTransfer/create";
	}

	@GetMapping("/list")
	public String list(Model model, SearchFilter searchFilter) {
		List<InventoryGoodsTransfer> list = inventoryGoodsTransferService.findByIsActive();
		searchFilter.setTypeOf(EnumSearchFilter.INVGT.getStatus());
		model.addAttribute("searchFilter", searchFilter);
		model.addAttribute("list", list);
		return "inv_goodsTransfer/list";
	}
	
	@PostMapping("/save")
	public String saveInvGR(InventoryGoodsTransfer invGoodsTransfer) {
		logger.info("Inside save method");
		
		if(invGoodsTransfer.getId() == null) {
			boolean status = inventoryGoodsTransferService.findByDocNumber(invGoodsTransfer.getDocNumber());
			if(!status) {
				inventoryGoodsTransferService.save(invGoodsTransfer);
			}else {
				Integer count = docNumberGenerator.getDocNoCountByDocType(EnumStatusUpdate.IGT.getStatus());
				
				InventoryGoodsTransfer igtdetails = inventoryGoodsTransferService.findLastDocumentNumber();
				if (igtdetails != null && igtdetails.getDocNumber() != null) {
					invGoodsTransfer.setDocNumber(GenerateDocNumber.documentNumberGeneration(igtdetails.getDocNumber(),count));
				}
				inventoryGoodsTransferService.save(invGoodsTransfer);
			}
		}else {
			inventoryGoodsTransferService.save(invGoodsTransfer);	
		}
				
		return "redirect:list";
	}
	
	@PostMapping(value = "/delete")
	public String delete(@RequestParam("id") int id) {

		inventoryGoodsTransferService.delete(id);
		return "redirect:list";
	}
	
	 @GetMapping("/edit")
	public String edit(String id, Model model) throws JsonProcessingException {
		InventoryGoodsTransfer invGR = inventoryGoodsTransferService.getInventoryGoodsTransferId(Integer.parseInt(id));
		invGR = inventoryGoodsTransferService.getListAmount(invGR);
		ObjectMapper mapper = poloadData(model, invGR);
		 mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		model.addAttribute("productList", mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", mapper.writeValueAsString(productService.findAllProductDescription("product")));
		// model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("plantMapSize", plantMap().size());
		model.addAttribute("findPlantAll", findPlantAll());
		model.addAttribute("taxCodeMap", taxCode());
		 
		model.addAttribute("gr", invGR);
		return "inv_goodsTransfer/create";
	} 
	 
	 @GetMapping("/view")
		public String view(String id, Model model) throws JsonProcessingException {
			InventoryGoodsTransfer invGR = inventoryGoodsTransferService.findById(Integer.parseInt(id));
			invGR = inventoryGoodsTransferService.getListAmount(invGR);
			poloadData(model, invGR);
			model.addAttribute("gr", invGR);
			model.addAttribute("plantMap", plantMap());
			model.addAttribute("findPlantAll", findPlantAll());
			model.addAttribute("taxCodeMap", taxCode());
			return "inv_goodsTransfer/view";
		}
	 
	 private ObjectMapper poloadData(Model model, InventoryGoodsTransfer invGR) {
			ObjectMapper mapper = new ObjectMapper();
			  
			model.addAttribute("inventoryGoodsTransferList", invGR.getInventoryGoodsTransferList());
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
	
	public Map<Integer, Object> findPlantAll() {
		return plantService.findPlantAll().stream().collect(Collectors.toMap(Plant::getId, Plant::getPlantName));
	}
	
	private User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
	
	@RequestMapping("/downloadPdf")
	public void downloadHtmlPDF(HttpServletResponse response, String htmlData, HttpServletRequest request,
			HttpSession session, String regType, Model model,String orgId,String id) throws Exception {
		
		InventoryGoodsTransfer invGR = inventoryGoodsTransferService.findById(Integer.parseInt(id));
		
		RequestContext.set(ContextUtil.populateContexturl(request));
		String path = "";
		
		 path = hTMLToPDFGenerator.getOfflineSummaryToPDF(HTMLToPDFGenerator.HTML_PDF_Offline)
                .OfflineHtmlStringToPdfForInvGoodsTransfer(pdfUploadedPath,invGR);
				
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
		List<InventoryGoodsTransfer> list = inventoryGoodsTransferService.searchFilterBySelection(searchFilter);
		model.addAttribute("list", list);
		model.addAttribute("searchFilter", searchFilter);
		return "inv_goodsTransfer/list";
	}
		
		
	@GetMapping("/exportINVGTExcel")
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

		String invGTFileNameDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		List<InventoryGoodsTransfer> list = inventoryGoodsTransferService.searchFilterBySelection(searchFilter);

		ByteArrayOutputStream stream = downloadReportsXLS.INVGTReport(list);
		response.setContentType("text/html");
		OutputStream outstream = response.getOutputStream();
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"INVGT_Report_" + invGTFileNameDate + ".xlsx\"");
		stream.writeTo(outstream);
		outstream.flush();
		outstream.close();
	}
	
}
