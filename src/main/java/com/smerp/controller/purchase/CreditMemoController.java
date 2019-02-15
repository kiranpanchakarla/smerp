package com.smerp.controller.purchase;

import java.io.File;
import java.io.FileInputStream;
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
import com.smerp.model.admin.Plant;
import com.smerp.model.admin.VendorAddress;
import com.smerp.model.inventory.InVoice;
import com.smerp.model.inventory.CreditMemo;
import com.smerp.model.inventory.TaxCode;
import com.smerp.repository.admin.TaxCodeRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.master.PlantService;
import com.smerp.service.master.SacService;
import com.smerp.service.purchase.CreditMemoService;
import com.smerp.util.ContextUtil;
import com.smerp.util.HTMLToPDFGenerator;
import com.smerp.util.RequestContext;

@Controller
@RequestMapping("/creditMemo")
public class CreditMemoController {

	private static final Logger logger = LogManager.getLogger(GoodsReturnController.class);

	private static String pdfUploadedPath;
	
	@Autowired
	PlantService plantService;

	@Autowired
	ProductService productService;

	@Autowired
	VendorService vendorService;

	@Autowired
	CreditMemoService creditMemoService;

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
		CreditMemo cre = creditMemoService.getCreditMemoById(Integer.parseInt(id));
		logger.info("11111 cre-->");
		logger.info("New cre-->" + cre);
		cre = creditMemoService.getListAmount(cre);  // set Amt Calculation  
		logger.info("cre-->" + cre);
		ObjectMapper mapper = poloadData(model, cre);
		
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", new ObjectMapper().writeValueAsString(productService.findAllProductDescription("product")));
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
		logger.info("vendorPayTypeAddress-->" + vendorPayTypeAddress);
		logger.info("vendorShippingAddress-->" + vendorShippingAddress);
		model.addAttribute("creditMemoLineItems", cre.getCreditMemoLineItems());
		return mapper;
	}

	@GetMapping("/view")
	public String view(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		CreditMemo cre = creditMemoService.findById(Integer.parseInt(id));
		cre = creditMemoService.getListAmount(cre);
		logger.info("cre-->" + cre);
		poloadData(model, cre);
		// model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("cre", cre);
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("taxCodeMap", taxCode());
		return "creditMemo/view";
	}

	@PostMapping(value = "/delete")
	public String delete(@RequestParam("id") int id) {
		logger.info("Delete msg");
		creditMemoService.delete(id);
		return "redirect:list";
	}

	@PostMapping("/save")
	public String name(CreditMemo creditMemo) {
		logger.info("Inside save method" + creditMemo);
		logger.info("cre details" + creditMemoService.save(creditMemo));
		return "redirect:list";
	}
	
	@PostMapping("/saveInvtoCre")
	public String savePRtoRFQ(HttpServletRequest request,InVoice inVoice) {
		String invId = request.getParameter("invId");
		logger.info("inVoice" + inVoice);
		logger.info("invId view-->" + invId);
		CreditMemo cre = creditMemoService.saveCM(invId);
		return "redirect:edit?id="+cre.getId();
	}

	@GetMapping("/cancelStage")
	public String cancelStage(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		
		logger.info("cre details" + creditMemoService.saveCancelStage(id));
		return "redirect:list";
	}

	@GetMapping("/list")
	public String list(Model model) {
		List<CreditMemo> list = creditMemoService.findByIsActive();
		logger.info("list"+list);
		model.addAttribute("list", list);
		return "creditMemo/list";
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
		CreditMemo cre = creditMemoService.findById(Integer.parseInt(id));
		logger.info("creditMemo -->" + cre);
		
		RequestContext.set(ContextUtil.populateContexturl(request));
		String path = "";
		
		path = hTMLToPDFGenerator.getOfflineSummaryToPDF(HTMLToPDFGenerator.HTML_PDF_Offline)
                .OfflineHtmlStringToPdfForCreditMemo(pdfUploadedPath,cre);
				
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
