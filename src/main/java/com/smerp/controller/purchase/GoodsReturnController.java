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
import com.smerp.model.inventory.GoodsReturn;
import com.smerp.model.inventory.TaxCode;
import com.smerp.model.search.SearchFilter;
import com.smerp.repository.admin.TaxCodeRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.master.PlantService;
import com.smerp.service.master.SacService;
import com.smerp.service.purchase.GoodsReturnService;
import com.smerp.util.ContextUtil;
import com.smerp.util.DownloadReportsXLS;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.HTMLToPDFGenerator;
import com.smerp.util.RequestContext;

@Controller
@RequestMapping("/gre")
public class GoodsReturnController {

	private static final Logger logger = LogManager.getLogger(GoodsReturnController.class);

	private static String pdfUploadedPath;
	
	@Autowired
	private PlantService plantService;

	@Autowired
	private ProductService productService;

	@Autowired
	private VendorService vendorService;

	@Autowired
	private GoodsReturnService goodsReturnService;

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
		GoodsReturn gre = goodsReturnService.getGoodsReturnById(Integer.parseInt(id));
		gre = goodsReturnService.getListAmount(gre);  // set Amt Calculation  
		ObjectMapper mapper = poloadData(model, gre);
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
		model.addAttribute("gre", gre);
		return "goodsReturn/create";
	}

	private ObjectMapper poloadData(Model model, GoodsReturn gre) {
		ObjectMapper mapper = new ObjectMapper();
		VendorAddress vendorPayTypeAddress=new VendorAddress();
		VendorAddress vendorShippingAddress =new VendorAddress();
		if(gre.getVendorPayTypeAddress()!=null && gre.getVendorPayTypeAddress()!=null) {
		 vendorPayTypeAddress = gre.getVendorPayTypeAddress();
		 vendorShippingAddress = gre.getVendorShippingAddress();
		 model.addAttribute("vendorPayTypeAddressId", vendorPayTypeAddress.getId());
		 model.addAttribute("vendorShippingAddressId", vendorShippingAddress.getId());
		}
		
		model.addAttribute("goodsReturnLineItems", gre.getGoodsReturnLineItems());
		return mapper;
	}

	@GetMapping("/view")
	public String view(String id, Model model) throws JsonProcessingException {
		GoodsReturn gre = goodsReturnService.findById(Integer.parseInt(id));
		gre = goodsReturnService.getListAmount(gre);
		poloadData(model, gre);
		// model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("gre", gre);
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("taxCodeMap", taxCode());
		return "goodsReturn/view";
	}

	@PostMapping(value = "/delete")
	public String delete(@RequestParam("id") int id) {
		goodsReturnService.delete(id);
		return "redirect:list";
	}

	
	@PostMapping("/save")
	public String name(GoodsReturn goodsReturn) {
		goodsReturnService.save(goodsReturn);
		return "redirect:list";
	}
	
	@PostMapping("/saveGRtoGRE")
	public String savePRtoRFQ(HttpServletRequest request,GoodsReceipt goodsReceipt) {
		String greId = request.getParameter("greId");
		GoodsReturn gre = goodsReturnService.saveGRE(greId);
		return "redirect:edit?id="+gre.getId();
	}

	@GetMapping("/cancelStage")
	public String cancelStage(String id, Model model) throws JsonProcessingException {
		
		goodsReturnService.saveCancelStage(id);
		return "redirect:list";
	}

	@GetMapping("/list")
	public String list(Model model,SearchFilter searchFilter) {
		List<GoodsReturn> list = goodsReturnService.findByIsActive();
		model.addAttribute("searchFilter", searchFilter);
		model.addAttribute("list", list);
		return "goodsReturn/list";
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
		
		GoodsReturn gre = goodsReturnService.findById(Integer.parseInt(id));
		
		RequestContext.set(ContextUtil.populateContexturl(request));
		String path = "";
		
		path = hTMLToPDFGenerator.getOfflineSummaryToPDF(HTMLToPDFGenerator.HTML_PDF_Offline)
                .OfflineHtmlStringToPdfForGoodsReturn(pdfUploadedPath,gre);
				
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
		List<GoodsReturn> list = goodsReturnService.searchFilterBySelection(searchFilter);
		model.addAttribute("list", list);
		model.addAttribute("searchFilter", searchFilter);
		return "goodsReturn/list";
	}
		
	@GetMapping("/exportGREExcel")
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

		String greFileNameDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		List<GoodsReturn> list = goodsReturnService.searchFilterBySelection(searchFilter);

		ByteArrayOutputStream stream = downloadReportsXLS.GREReport(list);
		response.setContentType("text/html");
		OutputStream outstream = response.getOutputStream();
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"GRE_Report_" + greFileNameDate + ".xlsx\"");
		stream.writeTo(outstream);
		outstream.flush();
		outstream.close();
	}

}
