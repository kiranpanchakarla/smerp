package com.smerp.controller.purchase;

import java.io.File;
import java.io.FileInputStream;
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
import com.smerp.model.admin.Plant;
import com.smerp.model.admin.VendorAddress;
import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.TaxCode;
import com.smerp.repository.admin.TaxCodeRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.master.PlantService;
import com.smerp.service.master.SacService;
import com.smerp.service.purchase.GoodsReceiptService;
import com.smerp.service.purchase.GoodsReturnService;
import com.smerp.util.ContextUtil;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.HTMLToPDFGenerator;
import com.smerp.util.RequestContext;

@Controller
@RequestMapping("/gr")
public class GoodsReceiptController {

	private static final Logger logger = LogManager.getLogger(GoodsReceiptController.class);

	private static String pdfUploadedPath;
	
	@Autowired
	PlantService plantService;

	@Autowired
	ProductService productService;

	@Autowired
	private VendorService vendorService;

	@Autowired
	GoodsReceiptService goodsReceiptService;
	
	@Autowired
	GoodsReturnService goodsReturnService;
	

	@Autowired
	SacService sacService;
	
	@Autowired
	TaxCodeRepository taxCodeRepository;
	
	@Autowired
	private HTMLToPDFGenerator hTMLToPDFGenerator;
	
	


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@GetMapping("/create")
	public String create(Model model, GoodsReceipt gr) throws JsonProcessingException {
		// model.addAttribute("categoryMap", categoryMap());
		logger.info("gr-->" + gr);
		logger.info("taxCode()-->" + taxCode());
		logger.info("plantMap()-->" + plantMap());
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("plantMapSize", plantMap().size());
		model.addAttribute("taxCodeMap", taxCode());
		model.addAttribute("sacList", mapper.writeValueAsString(sacService.findAllSacCodes()));
       
		GoodsReceipt grDetails = goodsReceiptService.findLastDocumentNumber();
		if (grDetails != null && grDetails.getDocNumber() != null) {
			gr.setDocNumber(GenerateDocNumber.documentNumberGeneration(grDetails.getDocNumber()));
		} else {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDateTime now = LocalDateTime.now();
	    gr.setDocNumber(GenerateDocNumber.documentNumberGeneration("GR"+(String)dtf.format(now) +"0"));
		}
		logger.info("grDetails-->" + grDetails);
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", new ObjectMapper().writeValueAsString(productService.findAllProductDescription("product")));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		logger.info("mapper-->" + mapper);

		model.addAttribute("gr", gr);
		return "goodsReceipt/create";
	}

	

	@GetMapping("/edit")
	public String edit(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		GoodsReceipt gr = goodsReceiptService.findById(Integer.parseInt(id));
		if(gr.getPoId() != null) {
		 gr = goodsReceiptService.getGoodsReceiptById(Integer.parseInt(id));
		}
		logger.info("11111 gr-->");
		logger.info("New gr-->" + gr);
		gr = goodsReceiptService.getListAmount(gr);  // set Amt Calculation  
		logger.info("gr-->" + gr);
		ObjectMapper mapper = poloadData(model, gr);
		
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", new ObjectMapper().writeValueAsString(productService.findAllProductDescription("product")));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
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
		logger.info("vendorPayTypeAddress-->" + vendorPayTypeAddress);
		logger.info("vendorShippingAddress-->" + vendorShippingAddress);
	
		
		model.addAttribute("goodsReceiptLineItems", gr.getGoodsReceiptLineItems());
		return mapper;
	}

	@GetMapping("/view")
	public String view(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		GoodsReceipt gr = goodsReceiptService.getGoodsReceiptViewById(Integer.parseInt(id));
		gr = goodsReceiptService.getListAmount(gr);
		logger.info("gr-->" + gr);
		poloadData(model, gr);
		// model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("checkStatusGr", goodsReturnService.checkQuantityGr(gr));
		model.addAttribute("gr", gr);
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("taxCodeMap", taxCode());
		return "goodsReceipt/view";
	}

	@PostMapping(value = "/delete")
	public String delete(@RequestParam("id") int id) {

		logger.info("Delete msg");
		goodsReceiptService.delete(id);
		return "redirect:list";
	}

	
	@PostMapping("/save")
	public String name(GoodsReceipt goodsReceipt) {
		logger.info("Inside save method" + goodsReceipt);
		logger.info("gr details" + goodsReceiptService.save(goodsReceipt));
		return "redirect:list";
	}
	
	@PostMapping("/savePOtoGR")
	public String savePRtoRFQ(HttpServletRequest request) {
		String poId = request.getParameter("poId");
		logger.info("poId" + poId);
		logger.info("poId view-->" + poId);
		GoodsReceipt gr = goodsReceiptService.saveGR(poId);
		return "redirect:edit?id="+gr.getId();
	}

	@GetMapping("/cancelStage")
	public String cancelStage(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		
		logger.info("gr details" + goodsReceiptService.saveCancelStage(id));
		return "redirect:list";
	}

	@GetMapping("/list")
	public String list(Model model) {
		List<GoodsReceipt> list = goodsReceiptService.findByIsActive();
		logger.info("list"+list);
		model.addAttribute("list", list);
		return "goodsReceipt/list";
	}
	
	
	@GetMapping(value = "/approvedList")
	public String approvedList(Model model) {
		List<GoodsReceipt> list = goodsReceiptService.grApprovedList();
		logger.info("goodsReceiptService list-->" + list);
		model.addAttribute("list", list);
		return "/goodsReceipt/approvedList";
	}

	public Map<Integer, Object> plantMap() {
		return plantService.findAll().stream().collect(Collectors.toMap(Plant::getId, Plant::getPlantName));
	}
	
	public Map<Double, Object> taxCode() {
		
		//return taxCodeRepository.findAllByOrderByTaxCodeAsc().stream().collect(Collectors.toMap(TaxCode::getTaxCode, TaxCode::getTaxCode));
		
		return taxCodeRepository.findAllByOrderByTaxCodeAsc().stream().collect(Collectors.toMap(TaxCode::getTaxCode, TaxCode::getTaxCode,
                (v1,v2) ->{ throw new RuntimeException(String.format("Duplicate key for values %s and %s", v1, v2));},
                TreeMap::new));  // for Sorted Values
	}
	
	@RequestMapping("/downloadPdf")
	public void downloadHtmlPDF(HttpServletResponse response, String htmlData, HttpServletRequest request,
			HttpSession session, String regType, Model model,String orgId,String id) throws Exception {
		
		logger.info("id -->" + id);
		GoodsReceipt gr = goodsReceiptService.findById(Integer.parseInt(id));
		logger.info("goodsReceipt -->" + gr);
		
		RequestContext.set(ContextUtil.populateContexturl(request));
		String path = "";
		
		path = hTMLToPDFGenerator.getOfflineSummaryToPDF(HTMLToPDFGenerator.HTML_PDF_Offline)
                .OfflineHtmlStringToPdfForGoodsReceipt(pdfUploadedPath,gr);
				
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

}
