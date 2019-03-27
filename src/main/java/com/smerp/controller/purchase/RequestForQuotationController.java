package com.smerp.controller.purchase;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.smerp.model.admin.Department;
import com.smerp.model.admin.Plant;
import com.smerp.model.admin.VendorAddress;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.search.SearchFilter;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.master.PlantService;
import com.smerp.service.master.SacService;
import com.smerp.service.purchase.RequestForQuotationService;
import com.smerp.util.ContextUtil;
import com.smerp.util.DocNumberGenerator;
import com.smerp.util.DownloadReportsXLS;
import com.smerp.util.EnumSearchFilter;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.HTMLToPDFGenerator;
import com.smerp.util.RequestContext;

@Controller
@RequestMapping("/rfq")
public class RequestForQuotationController {

	private static final Logger logger = LogManager.getLogger(RequestForQuotationController.class);

	private static String pdfUploadedPath;
	
	@Autowired
	PlantService plantService;

	@Autowired
	ProductService productService;

	@Autowired
	private VendorService vendorService;

	@Autowired
	RequestForQuotationService requestForQuotationService;

	@Autowired
	SacService sacService;
	
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

	@GetMapping("/create")
	public String createPage(Model model, RequestForQuotation rfq) throws JsonProcessingException {
		// model.addAttribute("categoryMap", categoryMap());
		logger.info("rfq-->" + rfq);
		ObjectMapper mapper = new ObjectMapper();
	        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		model.addAttribute("planMap", plantMap());
		model.addAttribute("plantMapSize", plantMap().size());
		model.addAttribute("sacList", mapper.writeValueAsString(sacService.findAllSacCodes()));
       
		Integer count = docNumberGenerator.getCountByDocType(EnumStatusUpdate.RFQ.getStatus());
		logger.info("PO count-->" + count);
		
		RequestForQuotation rfqdetails = requestForQuotationService.findLastDocumentNumber();
		if (rfqdetails != null && rfqdetails.getDocNumber() != null) {
			rfq.setDocNumber(GenerateDocNumber.documentNumberGeneration(rfqdetails.getDocNumber(),count));
		} else {
			  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
			    LocalDateTime now = LocalDateTime.now();
			    rfq.setDocNumber(GenerateDocNumber.documentNumberGeneration("RFQ"+(String)dtf.format(now) +"0",count));
		}
		logger.info("rfqdetails-->" + rfqdetails);
		model.addAttribute("productList", mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", new ObjectMapper().writeValueAsString(productService.findAllProductDescription("product")));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		logger.info("mapper-->" + mapper);

		model.addAttribute("rfq", rfq);
		return "rfq/create";
	}

	

	@GetMapping("/edit")
	public String edit(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		RequestForQuotation rfq = requestForQuotationService.findById(Integer.parseInt(id));
		logger.info("rfq-->" + rfq);
		ObjectMapper mapper = rfqloadData(model, rfq);
	        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		model.addAttribute("productList",mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList",mapper.writeValueAsString(productService.findAllProductDescription("product")));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		// model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("planMap", plantMap());
		model.addAttribute("plantMapSize", plantMap().size());
		model.addAttribute("sacList", mapper.writeValueAsString(sacService.findAllSacCodes()));
		model.addAttribute("rfq", rfq);
		return "rfq/create";
	}

	private ObjectMapper rfqloadData(Model model, RequestForQuotation rfq) {
		ObjectMapper mapper = new ObjectMapper();
		VendorAddress vendorPayTypeAddress=new VendorAddress();
		VendorAddress vendorShippingAddress =new VendorAddress();
		if(rfq.getVendorPayTypeAddress()!=null && rfq.getVendorPayTypeAddress()!=null) {
		 vendorPayTypeAddress = rfq.getVendorPayTypeAddress();
		 vendorShippingAddress = rfq.getVendorShippingAddress();
		 model.addAttribute("vendorPayTypeAddressId", vendorPayTypeAddress.getId());
		 model.addAttribute("vendorShippingAddressId", vendorShippingAddress.getId());
		}
		logger.info("vendorPayTypeAddress-->" + vendorPayTypeAddress);
		logger.info("vendorShippingAddress-->" + vendorShippingAddress);
	
		
		model.addAttribute("lineItems", rfq.getLineItems());
		return mapper;
	}

	@GetMapping("/view")
	public String view(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		RequestForQuotation rfq = requestForQuotationService.findById(Integer.parseInt(id));
		logger.info("rfq-->" + rfq);
		rfqloadData(model, rfq);
		// model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("rfq", rfq);
		model.addAttribute("plantMap", plantMap());
		
		Integer noOfRfqs = requestForQuotationService.getRFQListCount(rfq.getPurchaseReqId());
		model.addAttribute("noOfRfqs", noOfRfqs);
		return "rfq/view";
	}

	@PostMapping(value = "/delete")
	public String delete(@RequestParam("id") int id) {

		logger.info("Delete msg");
		requestForQuotationService.delete(id);
		return "redirect:list";
	}

	@PostMapping("/save")
	public String name(RequestForQuotation requestForQuotation) {
		logger.info("Inside save method" + requestForQuotation);
		
		if(requestForQuotation.getId()==null) {
			boolean status = requestForQuotationService.findByDocNumber(requestForQuotation.getDocNumber());
			if(!status) {
				requestForQuotation = requestForQuotationService.save(requestForQuotation);
				logger.info("rfq details" +requestForQuotation);
			}else {
				Integer count = docNumberGenerator.getCountByDocType(EnumStatusUpdate.RFQ.getStatus());
				logger.info("count-->" + count);
				
				RequestForQuotation rfq = requestForQuotationService.findLastDocumentNumber();
				if (rfq != null && rfq.getDocNumber() != null) {
					//po.setDocNumber(GenerateDocNumber.documentNumberGeneration(rfq.getDocNumber()));
					requestForQuotation.setDocNumber(GenerateDocNumber.documentNumberGeneration(rfq.getDocNumber(),count));
				}
				requestForQuotation = requestForQuotationService.save(requestForQuotation);
			}
		}else {
			logger.info("rfq details" + requestForQuotationService.save(requestForQuotation));
		}
		
		return "redirect:list";
	}
	
	@PostMapping("/savePRtoRFQ")
	public String savePRtoRFQ(@RequestParam String purchaseId) {
		logger.info("purchaseId" + purchaseId);
		logger.info("purchaseRequest view-->" + purchaseId);
		RequestForQuotation rfq = requestForQuotationService.saveRFQ(purchaseId);
		return "redirect:edit?id="+rfq.getId();
	}
	
	
	@GetMapping(value = "/approvedList")
	public String approvedList(Model model, SearchFilter searchFilter) {
		List<RequestForQuotation> requestForQuotationList = requestForQuotationService.rfqApprovedList();
		logger.info("requestForQuotation list-->" + requestForQuotationList);
		searchFilter.setIsConvertedDoc("true");
		searchFilter.setTypeOf(EnumSearchFilter.RFQTABLE.getStatus());
		model.addAttribute("searchFilter", searchFilter);
		model.addAttribute("requestForQuotationList", requestForQuotationList);
		return "/rfq/approvedList";
	}


	@GetMapping("/cancelStage")
	public String cancelStage(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		
		logger.info("rfq details" + requestForQuotationService.saveCancelStage(id));
		return "redirect:list";
	}

	@GetMapping("/list")
	public String list(Model model, SearchFilter searchFilter) {
		List<RequestForQuotation> list = requestForQuotationService.findByIsActive();
		logger.info("list"+list);
		model.addAttribute("searchFilter", searchFilter);
		model.addAttribute("list", list);
		return "rfq/list";
	}

	public Map<Integer, Object> plantMap() {
		return plantService.findAll().stream().collect(Collectors.toMap(Plant::getId, Plant::getPlantName));
	}
	
	@RequestMapping("/downloadPdf")
	public void downloadHtmlPDF(HttpServletResponse response, String htmlData, HttpServletRequest request,
			HttpSession session, String regType, Model model,String orgId,String id) throws Exception {
		
		RequestForQuotation rfq = requestForQuotationService.findById(Integer.parseInt(id));
		logger.info("RequestForQuotation view-->" + rfq);
		
		RequestContext.set(ContextUtil.populateContexturl(request));
		String path = "";
		
		path = hTMLToPDFGenerator.getOfflineSummaryToPDF(HTMLToPDFGenerator.HTML_PDF_Offline)
				.OfflineHtmlStringToPdfForRFQ(pdfUploadedPath,rfq);
				
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
	
	@GetMapping(value = "/isVendorNameExistWithDocNum")
	@ResponseBody
	public boolean isVendorNameExistWithDocNum(String vendorName,String refDocNum) {
		logger.info("Vendor Name" + vendorName);
		boolean isExist = requestForQuotationService.isVendorNameExistWithDocNum(vendorName,refDocNum);
		return isExist;
	}
	
	@GetMapping("/getSearchFilterList")
	public String getSearchFilterList(Model model, SearchFilter searchFilter) {
		if(searchFilter.getIsConvertedDoc().equals("true")) {
			List<RequestForQuotation> requestForQuotationList = requestForQuotationService.searchFilterBySelection(searchFilter);
			model.addAttribute("requestForQuotationList", requestForQuotationList);
			model.addAttribute("searchFilter", searchFilter);
			return "rfq/approvedList";
		}else {
			List<RequestForQuotation> list = requestForQuotationService.searchFilterBySelection(searchFilter);
			logger.info("list"+list);
			model.addAttribute("list", list);
			model.addAttribute("searchFilter", searchFilter);
			return "rfq/list";
		}
	}
	
	@GetMapping("/exportRFQExcel")
	   public void download(HttpServletResponse response, Model model, HttpServletRequest request, String searchBy, String fieldName, String sortBy,
			   String dateSelect, String fromDateString, String toDateString) throws Exception {
		
		SearchFilter searchFilter = new SearchFilter();
		searchFilter.setSearchBy(searchBy);
		searchFilter.setFieldName(fieldName);
		searchFilter.setSortBy(sortBy);
		searchFilter.setDateSelect(dateSelect);
		
		if(!fromDateString.equals("null")) {
			Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromDateString);
			Date toDate = new Date();
			if(!toDateString.equals("null")) {
				toDate = new SimpleDateFormat("yyyy-MM-dd").parse(toDateString);
			} else {
				String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());	
				toDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
			}
			
			searchFilter.setFromDate(fromDate);
			searchFilter.setToDate(toDate);
		}
			String RFQFileNameDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());	
	       List<RequestForQuotation> list = requestForQuotationService.searchFilterBySelection(searchFilter);
	       
	       ByteArrayOutputStream stream = downloadReportsXLS.RFQReport(list);
	       response.setContentType("text/html");
	       OutputStream outstream = response.getOutputStream();
	       response.setContentType("APPLICATION/OCTET-STREAM");
	       response.setHeader("Content-Disposition", "attachment; filename=\"RFQ_Report_"+RFQFileNameDate+".xlsx\"");
	       stream.writeTo(outstream);
	       outstream.flush();
	       outstream.close();
	   }
	
}
