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
import com.smerp.model.inventory.GoodsReturn;
import com.smerp.model.inventory.TaxCode;
import com.smerp.repository.admin.TaxCodeRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.master.PlantService;
import com.smerp.service.master.SacService;
import com.smerp.service.purchase.GoodsReturnService;
import com.smerp.util.ContextUtil;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.HTMLToPDFGenerator;
import com.smerp.util.RequestContext;

@Controller
@RequestMapping("/gre")
public class GoodsReturnController {

	private static final Logger logger = LogManager.getLogger(GoodsReturnController.class);

	private static String pdfUploadedPath;
	
	@Autowired
	PlantService plantService;

	@Autowired
	ProductService productService;

	@Autowired
	private VendorService vendorService;

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


	@GetMapping("/edit")
	public String edit(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		GoodsReturn gre = goodsReturnService.findById(Integer.parseInt(id));
		logger.info("11111 gre-->");
		logger.info("New gre-->" + gre);
		gre = goodsReturnService.getListAmount(gre);  // set Amt Calculation  
		logger.info("gre-->" + gre);
		ObjectMapper mapper = poloadData(model, gre);
		
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", new ObjectMapper().writeValueAsString(productService.findAllProductDescription("product")));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		// model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("plantMap", plantMap());
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
		logger.info("vendorPayTypeAddress-->" + vendorPayTypeAddress);
		logger.info("vendorShippingAddress-->" + vendorShippingAddress);
	
		
		model.addAttribute("goodsReturnLineItems", gre.getGoodsReturnLineItems());
		return mapper;
	}

	@GetMapping("/view")
	public String view(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		GoodsReturn gre = goodsReturnService.findById(Integer.parseInt(id));
		gre = goodsReturnService.getListAmount(gre);
		logger.info("gre-->" + gre);
		poloadData(model, gre);
		// model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("gre", gre);
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("taxCodeMap", taxCode());
		return "goodsReturn/view";
	}

	@PostMapping(value = "/delete")
	public String delete(@RequestParam("id") int id) {
		logger.info("Delete msg");
		goodsReturnService.delete(id);
		return "redirect:list";
	}

	
	@PostMapping("/save")
	public String name(GoodsReturn goodsReturn) {
		logger.info("Inside save method" + goodsReturn);
		logger.info("gre details" + goodsReturnService.save(goodsReturn));
		return "redirect:list";
	}
	
	@PostMapping("/saveGRtoGRE")
	public String savePRtoRFQ(HttpServletRequest request) {
		String greId = request.getParameter("greId");
		logger.info("greId" + greId);
		logger.info("greId view-->" + greId);
		GoodsReturn gre = goodsReturnService.saveGRE(greId);
		return "redirect:edit?id="+gre.getId();
	}

	@GetMapping("/cancelStage")
	public String cancelStage(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		
		logger.info("gre details" + goodsReturnService.saveCancelStage(id));
		return "redirect:list";
	}

	@GetMapping("/list")
	public String list(Model model) {
		List<GoodsReturn> list = goodsReturnService.findByIsActive();
		logger.info("list"+list);
		model.addAttribute("list", list);
		return "goodsReturn/list";
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
		GoodsReturn gre = goodsReturnService.findById(Integer.parseInt(id));
		logger.info("goodsReturn -->" + gre);
		
		RequestContext.set(ContextUtil.populateContexturl(request));
		String path = "";
		
		path = hTMLToPDFGenerator.getOfflineSummaryToPDF(HTMLToPDFGenerator.HTML_PDF_Offline)
                .OfflineHtmlStringToPdfForGoodsReturn(pdfUploadedPath,gre);
				
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
