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
import com.smerp.model.admin.VendorAddress;
import com.smerp.model.inventory.InVoice;
import com.smerp.model.inventory.LineItemsBean;
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.inventory.TaxCode;
import com.smerp.model.search.SearchFilter;
import com.smerp.repository.admin.TaxCodeRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.inventory.UomService;
import com.smerp.service.master.PlantService;
import com.smerp.service.master.SacService;
import com.smerp.service.purchase.CreditMemoService;
import com.smerp.service.purchase.InVoiceService;
import com.smerp.util.ContextUtil;
import com.smerp.util.DocNumberGenerator;
import com.smerp.util.DownloadReportsXLS;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.HTMLToPDFGenerator;
import com.smerp.util.RequestContext;

@Controller
@RequestMapping("/inv")
public class InvoiceController {

	private static final Logger logger = LogManager.getLogger(InvoiceController.class);

	private static String pdfUploadedPath;
	
	@Autowired
	private PlantService plantService;

	@Autowired
	private ProductService productService;

	@Autowired
	private VendorService vendorService;

		
	@Autowired
	private InVoiceService inVoiceService;
	
	@Autowired
	private CreditMemoService creditMemoService;
	
	@Autowired
	private SacService sacService;
	
	@Autowired
	private TaxCodeRepository taxCodeRepository;
	
	@Autowired
	UomService uomService;
	
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
	public String create(Model model, InVoice inv) throws JsonProcessingException {
		// model.addAttribute("categoryMap", categoryMap());
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("plantMapSize", plantMap().size());
		model.addAttribute("taxCodeMap", taxCode());
		model.addAttribute("sacList", mapper.writeValueAsString(sacService.findAllSacCodes()));
       
		Integer count = docNumberGenerator.getDocCountByDocType(EnumStatusUpdate.INV.getStatus());
		
		InVoice invDetails = inVoiceService.findLastDocumentNumber();
		if (invDetails != null && invDetails.getDocNumber() != null) {
			inv.setDocNumber(GenerateDocNumber.documentNumberGeneration(invDetails.getDocNumber(),count));
		} else {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDateTime now = LocalDateTime.now();
	    inv.setDocNumber(GenerateDocNumber.documentNumberGeneration("INV"+(String)dtf.format(now) +"0",count));
		}
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", mapper.writeValueAsString(productService.findAllProductDescription("product")));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		model.addAttribute("uomList", mapper.writeValueAsString(uomService.getUOM()));

		model.addAttribute("inv", inv);
		return "inv/create";
	}

	@GetMapping("/edit")
	public String edit(String id, Model model) throws JsonProcessingException {
		InVoice inv = inVoiceService.findById(Integer.parseInt(id));
		inv = inVoiceService.getListAmount(inv);  // set Amt Calculation  
		ObjectMapper mapper = poloadData(model, inv);
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", mapper.writeValueAsString(productService.findAllProductDescription("product")));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		model.addAttribute("uomList", mapper.writeValueAsString(uomService.getUOM()));
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("taxCodeMap", taxCode());
		model.addAttribute("plantMapSize", plantMap().size());
		model.addAttribute("sacList", mapper.writeValueAsString(sacService.findAllSacCodes()));
		model.addAttribute("inv", inv);
		return "inv/create";
	}

	private ObjectMapper poloadData(Model model, InVoice inv) {
		ObjectMapper mapper = new ObjectMapper();
		VendorAddress vendorPayTypeAddress=new VendorAddress();
		VendorAddress vendorShippingAddress =new VendorAddress();
		if(inv.getVendorPayTypeAddress()!=null && inv.getVendorPayTypeAddress()!=null) {
		 vendorPayTypeAddress = inv.getVendorPayTypeAddress();
		 vendorShippingAddress = inv.getVendorShippingAddress();
		 model.addAttribute("vendorPayTypeAddressId", vendorPayTypeAddress.getId());
		 model.addAttribute("vendorShippingAddressId", vendorShippingAddress.getId());
		}
		model.addAttribute("inVoiceLineItems", inv.getInVoiceLineItems());
		return mapper;
	}

	@GetMapping("/view")
	public String view(String id, Model model) throws JsonProcessingException {
		InVoice inv = inVoiceService.getInVoiceById(Integer.parseInt(id));
		
		List<LineItemsBean> lineItemsBean = inVoiceService.getLineItemsBean(Integer.parseInt(id));
		//inv = inVoiceService.getListAmount(inv);
		poloadData(model, inv);
		// model.addAttribute("categoryMap", categoryMap());
		//model.addAttribute("checkStatusInv", creditMemoService.checkQuantityInv(inv));
		model.addAttribute("inv", inv);
		model.addAttribute("lineItemsBean",lineItemsBean);
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("taxCodeMap", taxCode());
		return "inv/view";
	}

	@PostMapping(value = "/delete")
	public String delete(@RequestParam("id") int id) {

		inVoiceService.delete(id);
		return "redirect:list";
	}

	
	@PostMapping("/save")
	public String name(InVoice invoice) {
		logger.info("Inside save method");
		
		if(invoice.getId() == null) {
			boolean status = inVoiceService.findByDocNumber(invoice.getDocNumber());
			if(!status) {
				inVoiceService.save(invoice);
			}else {
				Integer count = docNumberGenerator.getDocCountByDocType(EnumStatusUpdate.INV.getStatus());
				InVoice invdetails = inVoiceService.findLastDocumentNumber();
				if (invdetails != null && invdetails.getDocNumber() != null) {
					invoice.setDocNumber(GenerateDocNumber.documentNumberGeneration(invdetails.getDocNumber(),count));
				}
				inVoiceService.save(invoice);
			}
		}else {
			inVoiceService.save(invoice);
		}
		
		return "redirect:list";
	}
	
	@PostMapping("/saveGRtoInv")
	public String saveGRtoInv(HttpServletRequest request) {
		String greId = request.getParameter("greId");
		InVoice inv = inVoiceService.saveInv(greId);
		return "redirect:edit?id="+inv.getId();
	}

	@GetMapping("/cancelStage")
	public String cancelStage(String id, Model model) throws JsonProcessingException {
		inVoiceService.saveCancelStage(id);
		return "redirect:list";
	}

	@GetMapping("/list")
	public String list(Model model,SearchFilter searchFilter) {
		List<InVoice> list = inVoiceService.findByIsActive();
		model.addAttribute("searchFilter", searchFilter);
		model.addAttribute("list", list);
		return "inv/list";
	}
	
	
	@GetMapping(value = "/approvedList")
	public String approvedList(Model model, SearchFilter searchFilter) {
		List<InVoice> list = inVoiceService.invApprovedList();
		searchFilter.setIsConvertedDoc("true");
		model.addAttribute("searchFilter", searchFilter);
		model.addAttribute("list", list);
		return "/inv/approvedList";
	}

	public Map<Integer, Object> plantMap() {
		return plantService.findAll().stream().collect(Collectors.toMap(Plant::getId, Plant::getPlantName));
	}
	
	public Map<Object,Double> taxCode() {
				
		//return taxCodeRepository.findAllByOrderByTaxCodeAsc().stream().collect(Collectors.toMap(TaxCode::getTaxCode, TaxCode::getTaxCode));
		
		return taxCodeRepository.findAll().stream().collect(Collectors.toMap(TaxCode::getDescription, TaxCode::getTaxCode));
		
		/*return taxCodeRepository.findAllByOrderByTaxCodeAsc().stream().collect(Collectors.toMap(TaxCode::getTaxCode, TaxCode::getTaxCode,
                (v1,v2) ->{ throw new RuntimeException(String.format("Duplicate key for values %s and %s", v1, v2));},
                TreeMap::new));*/  // for Sorted Values
	}
	
	@RequestMapping("/downloadPdf")
	public void downloadHtmlPDF(HttpServletResponse response, String htmlData, HttpServletRequest request,
			HttpSession session, String regType, Model model,String orgId,String id) throws Exception {
		
		InVoice inv = inVoiceService.findById(Integer.parseInt(id));
		
		RequestContext.set(ContextUtil.populateContexturl(request));
		String path = "";
		
		path = hTMLToPDFGenerator.getOfflineSummaryToPDF(HTMLToPDFGenerator.HTML_PDF_Offline)
                .OfflineHtmlStringToPdfForInvoice(pdfUploadedPath,inv);
				
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
		List<InVoice> list = inVoiceService.searchFilterBySelection(searchFilter);
		model.addAttribute("list", list);
		model.addAttribute("searchFilter", searchFilter);
		if(searchFilter.getIsConvertedDoc().equals("true"))
			return "inv/approvedList";
		else
			return "inv/list";
	}
	
	@GetMapping("/exportINVExcel")
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
			String invFileNameDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());	
	       List<InVoice> list = inVoiceService.searchFilterBySelection(searchFilter);
	       
	       ByteArrayOutputStream stream = downloadReportsXLS.invReport(list);
	       response.setContentType("text/html");
	       OutputStream outstream = response.getOutputStream();
	       response.setContentType("APPLICATION/OCTET-STREAM");
	       response.setHeader("Content-Disposition", "attachment; filename=\"INV_Report_"+invFileNameDate+".xlsx\"");
	       stream.writeTo(outstream);
	       outstream.flush();
	       outstream.close();
	   }
	
}
