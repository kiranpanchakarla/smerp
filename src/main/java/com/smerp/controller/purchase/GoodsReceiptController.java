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
import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.LineItemsBean;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.TaxCode;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.model.search.SearchFilter;
import com.smerp.repository.admin.TaxCodeRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.inventory.UomService;
import com.smerp.service.master.PlantService;
import com.smerp.service.master.SacService;
import com.smerp.service.purchase.GoodsReceiptService;
import com.smerp.service.purchase.GoodsReturnService;
import com.smerp.util.ContextUtil;
import com.smerp.util.DocNumberGenerator;
import com.smerp.util.DownloadReportsXLS;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.HTMLToPDFGenerator;
import com.smerp.util.RequestContext;

@Controller
@RequestMapping("/gr")
public class GoodsReceiptController {

	private static final Logger logger = LogManager.getLogger(GoodsReceiptController.class);

	private static String pdfUploadedPath;
	
	@Autowired
	private PlantService plantService;

	@Autowired
	private ProductService productService;

	@Autowired
	private VendorService vendorService;

	@Autowired
	private GoodsReceiptService goodsReceiptService;
	
	@Autowired
	private GoodsReturnService goodsReturnService;
	
	@Autowired
	private SacService sacService;
	
	@Autowired
	private TaxCodeRepository taxCodeRepository;
	
	@Autowired
	private HTMLToPDFGenerator hTMLToPDFGenerator;
	
	@Autowired
	private DocNumberGenerator docNumberGenerator;
	
	@Autowired
	private DownloadReportsXLS downloadReportsXLS;
	
	@Autowired
	UomService uomService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@GetMapping("/create")
	public String create(Model model, GoodsReceipt gr) throws JsonProcessingException {
		// model.addAttribute("categoryMap", categoryMap());
		 
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("plantMapSize", plantMap().size());
		model.addAttribute("taxCodeMap", taxCode());
		model.addAttribute("sacList", mapper.writeValueAsString(sacService.findAllSacCodes()));
       
		Integer count = docNumberGenerator.getDocCountByDocType(EnumStatusUpdate.GR.getStatus());
		 
		
		GoodsReceipt grDetails = goodsReceiptService.findLastDocumentNumber();
		if (grDetails != null && grDetails.getDocNumber() != null) {
			gr.setDocNumber(GenerateDocNumber.documentNumberGeneration(grDetails.getDocNumber(),count));
		} else {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDateTime now = LocalDateTime.now();
	    gr.setDocNumber(GenerateDocNumber.documentNumberGeneration("GR"+(String)dtf.format(now) +"0",count));
		}
		 
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", mapper.writeValueAsString(productService.findAllProductDescription("product")));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		model.addAttribute("uomList", mapper.writeValueAsString(uomService.getUOM()));
		 

		model.addAttribute("gr", gr);
		return "goodsReceipt/create";
	}

	

	@GetMapping("/edit")
	public String edit(String id, Model model) throws JsonProcessingException {
		 
		GoodsReceipt gr = goodsReceiptService.findById(Integer.parseInt(id));
		if(gr.getPoId() != null) {
		 gr = goodsReceiptService.getGoodsReceiptById(Integer.parseInt(id));
		}
		 
		gr = goodsReceiptService.getListAmount(gr);  // set Amt Calculation  
		 
		ObjectMapper mapper = poloadData(model, gr);
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", mapper.writeValueAsString(productService.findAllProductDescription("product")));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		model.addAttribute("uomList", mapper.writeValueAsString(uomService.getUOM()));
		// model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("plantMapSize", plantMap().size());
		model.addAttribute("taxCodeMap", taxCode());
		model.addAttribute("sacList", mapper.writeValueAsString(sacService.findAllSacCodes()));
		model.addAttribute("gr", gr);
		return "goodsReceipt/create";
	}

	private ObjectMapper poloadData(Model model, GoodsReceipt gr) {
		ObjectMapper mapper = new ObjectMapper();
		VendorAddress vendorPayTypeAddress=new VendorAddress();
		VendorAddress vendorShippingAddress =new VendorAddress();
		if(gr.getVendorPayTypeAddress()!=null && gr.getVendorPayTypeAddress()!=null) {
		 vendorPayTypeAddress = gr.getVendorPayTypeAddress();
		 vendorShippingAddress = gr.getVendorShippingAddress();
		 model.addAttribute("vendorPayTypeAddressId", vendorPayTypeAddress.getId());
		 model.addAttribute("vendorShippingAddressId", vendorShippingAddress.getId());
		}
		 
		model.addAttribute("goodsReceiptLineItems", gr.getGoodsReceiptLineItems());
		return mapper;
	}

	@GetMapping("/view")
	public String view(String id, Model model) throws JsonProcessingException {
		 
		
		GoodsReceipt gr = goodsReceiptService.getGoodsReceiptViewById(Integer.parseInt(id));
		//gr = goodsReceiptService.getListAmount(gr);
		 
		
		List<LineItemsBean> lineItemsBean = goodsReceiptService.getLineItemsBean(Integer.parseInt(id));
		 
		
		model.addAttribute("lineItemsBean",lineItemsBean);
		poloadData(model, gr);
		// model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("checkStatusGr", goodsReturnService.checkQuantityGr(gr));
		model.addAttribute("getGRE", goodsReceiptService.getGoodsReturn(gr));
		model.addAttribute("gr", gr);
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("taxCodeMap", taxCode());
		return "goodsReceipt/view";
	}

	@PostMapping(value = "/delete")
	public String delete(@RequestParam("id") int id) {

		 
		goodsReceiptService.delete(id);
		return "redirect:list";
	}

	
	@PostMapping("/save")
	public String name(GoodsReceipt goodsReceipt) {
		logger.info("Inside save method");
		
		if(goodsReceipt.getId()==null) {
			boolean status = goodsReceiptService.findByDocNumber(goodsReceipt.getDocNumber());
			if(!status) {
				goodsReceiptService.save(goodsReceipt);
			}else {
				Integer count = docNumberGenerator.getDocCountByDocType(EnumStatusUpdate.GR.getStatus());
				GoodsReceipt grdetails = goodsReceiptService.findLastDocumentNumber();
				if (grdetails != null && grdetails.getDocNumber() != null) {
					goodsReceipt.setDocNumber(GenerateDocNumber.documentNumberGeneration(grdetails.getDocNumber(),count));
				}
				goodsReceiptService.save(goodsReceipt);
			}
		}else {
			goodsReceiptService.save(goodsReceipt);
		}
		
		return "redirect:list";
	}
	
	@PostMapping("/savePOtoGR")
	public String savePRtoRFQ(HttpServletRequest request) {
		String poId = request.getParameter("poId");
		 
		GoodsReceipt gr = goodsReceiptService.saveGR(poId);
		return "redirect:edit?id="+gr.getId();
	}

	@GetMapping("/cancelStage")
	public String cancelStage(String id, Model model) throws JsonProcessingException {
		 
		goodsReceiptService.saveCancelStage(id);
		return "redirect:list";
	}

	@GetMapping("/list")
	public String list(Model model, SearchFilter searchFilter) {
		List<GoodsReceipt> list = goodsReceiptService.findByIsActive();
		 
		model.addAttribute("searchFilter", searchFilter);
		model.addAttribute("list", list);
		return "goodsReceipt/list";
	}
	
	
	@GetMapping(value = "/approvedList")
	public String approvedList(Model model, SearchFilter searchFilter) {
		List<GoodsReceipt> list = goodsReceiptService.grApprovedList();
		 
		model.addAttribute("list", list);
		searchFilter.setIsConvertedDoc("true");
		model.addAttribute("searchFilter", searchFilter);
		return "/goodsReceipt/approvedList";
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
		
		 
		GoodsReceipt gr = goodsReceiptService.findById(Integer.parseInt(id));
		 
		 
		RequestContext.set(ContextUtil.populateContexturl(request));
		String path = "";
		
		path = hTMLToPDFGenerator.getOfflineSummaryToPDF(HTMLToPDFGenerator.HTML_PDF_Offline)
                .OfflineHtmlStringToPdfForGoodsReceipt(pdfUploadedPath,gr);
				
		 
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
		List<GoodsReceipt> list = goodsReceiptService.searchFilterBySelection(searchFilter);
		 
		model.addAttribute("list", list);
		model.addAttribute("searchFilter", searchFilter);
		if(searchFilter.getIsConvertedDoc().equals("true"))
			return "goodsReceipt/approvedList";
		else 
			return "goodsReceipt/list";
	}
		
	@GetMapping("/exportGRExcel")
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

		String grFileNameDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		List<GoodsReceipt> list = goodsReceiptService.searchFilterBySelection(searchFilter);

		ByteArrayOutputStream stream = downloadReportsXLS.GRReport(list);
		response.setContentType("text/html");
		OutputStream outstream = response.getOutputStream();
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"GR_Report_" + grFileNameDate + ".xlsx\"");
		stream.writeTo(outstream);
		outstream.flush();
		outstream.close();
	}

}
