package com.smerp.controller.purchase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.inventory.CreditMemo;
import com.smerp.model.inventory.TaxCode;
import com.smerp.model.search.SearchFilter;
import com.smerp.repository.admin.TaxCodeRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.master.PlantService;
import com.smerp.service.master.SacService;
import com.smerp.service.purchase.CreditMemoService;
import com.smerp.util.ContextUtil;
import com.smerp.util.DownloadReportsXLS;
import com.smerp.util.HTMLToPDFGenerator;
import com.smerp.util.RequestContext;

@Controller
@RequestMapping("/creditMemo")
public class CreditMemoController {

	private static final Logger logger = LogManager.getLogger(CreditMemoController.class);

	private static String pdfUploadedPath;
	
	@Autowired
	private PlantService plantService;

	@Autowired
	private ProductService productService;

	@Autowired
	private VendorService vendorService;

	@Autowired
	private CreditMemoService creditMemoService;

	@Autowired
	private SacService sacService;
	
	@Autowired
	private TaxCodeRepository taxCodeRepository;
	
	@Autowired
	private HTMLToPDFGenerator hTMLToPDFGenerator;
	
	@Autowired
	private DownloadReportsXLS downloadReportsXLS;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@GetMapping("/edit")
	public String edit(String id, Model model) throws JsonProcessingException {
		CreditMemo cre = creditMemoService.getCreditMemoById(Integer.parseInt(id));
		cre = creditMemoService.getListAmount(cre);  // set Amt Calculation  
		ObjectMapper mapper = poloadData(model, cre);
	        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", mapper.writeValueAsString(productService.findAllProductDescription("product")));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		// model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("plantMapSize", plantMap().size());
		model.addAttribute("taxCodeMap", taxCode());
		model.addAttribute("sacList", mapper.writeValueAsString(sacService.findAllSacCodes()));
		model.addAttribute("cre", cre);
		return "creditMemo/create";
	}

	private ObjectMapper poloadData(Model model, CreditMemo cre) {
		ObjectMapper mapper = new ObjectMapper();
		VendorAddress vendorPayTypeAddress=new VendorAddress();
		VendorAddress vendorShippingAddress =new VendorAddress();
		if(cre.getVendorPayTypeAddress()!=null && cre.getVendorPayTypeAddress()!=null) {
		 vendorPayTypeAddress = cre.getVendorPayTypeAddress();
		 vendorShippingAddress = cre.getVendorShippingAddress();
		 model.addAttribute("vendorPayTypeAddressId", vendorPayTypeAddress.getId());
		 model.addAttribute("vendorShippingAddressId", vendorShippingAddress.getId());
		}
		model.addAttribute("creditMemoLineItems", cre.getCreditMemoLineItems());
		return mapper;
	}

	@GetMapping("/view")
	public String view(String id, Model model) throws JsonProcessingException {
		CreditMemo cre = creditMemoService.findById(Integer.parseInt(id));
		cre = creditMemoService.getListAmount(cre);
		poloadData(model, cre);
		// model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("cre", cre);
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("taxCodeMap", taxCode());
		return "creditMemo/view";
	}

	@PostMapping(value = "/delete")
	public String delete(@RequestParam("id") int id) {
		creditMemoService.delete(id);
		return "redirect:list";
	}

	@PostMapping("/save")
	public String name(CreditMemo creditMemo) {
		logger.info("Inside save method");
		creditMemoService.save(creditMemo);
		return "redirect:list";
	}
	
	@PostMapping("/saveInvtoCre")
	public String savePRtoRFQ(HttpServletRequest request,InVoice inVoice) {
		String invId = request.getParameter("invId");
		CreditMemo cre = creditMemoService.saveCM(invId);
		return "redirect:edit?id="+cre.getId();
	}

	@GetMapping("/cancelStage")
	public String cancelStage(String id, Model model) throws JsonProcessingException {
		logger.info("cre details" + creditMemoService.saveCancelStage(id));
		return "redirect:list";
	}

	@GetMapping("/list")
	public String list(Model model, SearchFilter searchFilter) {
		List<CreditMemo> list = creditMemoService.findByIsActive();
		model.addAttribute("searchFilter", searchFilter);
		model.addAttribute("list", list);
		return "creditMemo/list";
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
		
		CreditMemo cre = creditMemoService.findById(Integer.parseInt(id));
		
		RequestContext.set(ContextUtil.populateContexturl(request));
		String path = "";
		
		path = hTMLToPDFGenerator.getOfflineSummaryToPDF(HTMLToPDFGenerator.HTML_PDF_Offline)
                .OfflineHtmlStringToPdfForCreditMemo(pdfUploadedPath,cre);
				
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
		List<CreditMemo> list = creditMemoService.searchFilterBySelection(searchFilter);
		model.addAttribute("list", list);
		model.addAttribute("searchFilter", searchFilter);
		return "creditMemo/list";
	}
	
	@GetMapping("/exportCMExcel")
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
			String cmFileNameDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());	
	       List<CreditMemo> list = creditMemoService.searchFilterBySelection(searchFilter);
	       
	       ByteArrayOutputStream stream = downloadReportsXLS.creditMemoReport(list);
	       response.setContentType("text/html");
	       OutputStream outstream = response.getOutputStream();
	       response.setContentType("APPLICATION/OCTET-STREAM");
	       response.setHeader("Content-Disposition", "attachment; filename=\"CM_Report_"+cmFileNameDate+".xlsx\"");
	       stream.writeTo(outstream);
	       outstream.flush();
	       outstream.close();
	   }

}
