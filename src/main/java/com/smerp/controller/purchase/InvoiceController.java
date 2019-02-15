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
import com.smerp.model.inventory.InVoice;
import com.smerp.model.inventory.LineItemsBean;
import com.smerp.model.inventory.TaxCode;
import com.smerp.repository.admin.TaxCodeRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.master.PlantService;
import com.smerp.service.master.SacService;
import com.smerp.service.purchase.CreditMemoService;
import com.smerp.service.purchase.InVoiceService;
import com.smerp.util.ContextUtil;
import com.smerp.util.DocNumberGenerator;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.HTMLToPDFGenerator;
import com.smerp.util.RequestContext;

@Controller
@RequestMapping("/inv")
public class InvoiceController {

	private static final Logger logger = LogManager.getLogger(GoodsReceiptController.class);

	private static String pdfUploadedPath;
	
	@Autowired
	PlantService plantService;

	@Autowired
	ProductService productService;

	@Autowired
	private VendorService vendorService;

		
	@Autowired
	InVoiceService inVoiceService;
	
	@Autowired
	CreditMemoService creditMemoService;
	
	@Autowired
	SacService sacService;
	
	@Autowired
	TaxCodeRepository taxCodeRepository;
	
	@Autowired
	private HTMLToPDFGenerator hTMLToPDFGenerator;
	
	@Autowired
	private DocNumberGenerator docNumberGenerator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@GetMapping("/create")
	public String create(Model model, InVoice inv) throws JsonProcessingException {
		// model.addAttribute("categoryMap", categoryMap());
		logger.info("inv-->" + inv);
		logger.info("taxCode()-->" + taxCode());
		logger.info("plantMap()-->" + plantMap());
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("plantMapSize", plantMap().size());
		model.addAttribute("taxCodeMap", taxCode());
		model.addAttribute("sacList", mapper.writeValueAsString(sacService.findAllSacCodes()));
       
		Integer count = docNumberGenerator.getDocCountByDocType(EnumStatusUpdate.INV.getStatus());
		logger.info("Inv count-->" + count);
		
		InVoice invDetails = inVoiceService.findLastDocumentNumber();
		if (invDetails != null && invDetails.getDocNumber() != null) {
			inv.setDocNumber(GenerateDocNumber.documentNumberGeneration(invDetails.getDocNumber(),count));
		} else {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDateTime now = LocalDateTime.now();
	    inv.setDocNumber(GenerateDocNumber.documentNumberGeneration("INV"+(String)dtf.format(now) +"0",count));
		}
		logger.info("invDetails-->" + invDetails);
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", new ObjectMapper().writeValueAsString(productService.findAllProductDescription("product")));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		logger.info("mapper-->" + mapper);

		model.addAttribute("inv", inv);
		return "inv/create";
	}

	@GetMapping("/edit")
	public String edit(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		InVoice inv = inVoiceService.findById(Integer.parseInt(id));
		logger.info("11111 inv-->");
		logger.info("New inv-->" + inv);
		inv = inVoiceService.getListAmount(inv);  // set Amt Calculation  
		logger.info("inv-->" + inv);
		ObjectMapper mapper = poloadData(model, inv);
		
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", new ObjectMapper().writeValueAsString(productService.findAllProductDescription("product")));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		// model.addAttribute("categoryMap", categoryMap());
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
		logger.info("vendorPayTypeAddress-->" + vendorPayTypeAddress);
		logger.info("vendorShippingAddress-->" + vendorShippingAddress);
	
		
		model.addAttribute("inVoiceLineItems", inv.getInVoiceLineItems());
		return mapper;
	}

	@GetMapping("/view")
	public String view(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		InVoice inv = inVoiceService.getInVoiceById(Integer.parseInt(id));
		logger.info("inv-->" + inv);
		
		
		List<LineItemsBean> lineItemsBean = inVoiceService.getLineItemsBean(Integer.parseInt(id));
		logger.info("lineItemsBean-->" + lineItemsBean);
		//inv = inVoiceService.getListAmount(inv);
		logger.info("inv-->" + inv);
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

		logger.info("Delete msg");
		inVoiceService.delete(id);
		return "redirect:list";
	}

	
	@PostMapping("/save")
	public String name(InVoice invoice) {
		logger.info("Inside save method" + invoice);
		
		if(invoice.getId() == null) {
			boolean status = inVoiceService.findByDocNumber(invoice.getDocNumber());
			if(!status) {
				logger.info("inv details" + inVoiceService.save(invoice));
			}else {
				Integer count = docNumberGenerator.getDocCountByDocType(EnumStatusUpdate.INV.getStatus());
				InVoice invdetails = inVoiceService.findLastDocumentNumber();
				if (invdetails != null && invdetails.getDocNumber() != null) {
					invoice.setDocNumber(GenerateDocNumber.documentNumberGeneration(invdetails.getDocNumber(),count));
				}
				logger.info("inv details" + inVoiceService.save(invoice));
			}
		}else {
			logger.info("inv details" + inVoiceService.save(invoice));
		}
		
		return "redirect:list";
	}
	
	@PostMapping("/saveGRtoInv")
	public String saveGRtoInv(HttpServletRequest request) {
		String greId = request.getParameter("greId");
		logger.info("greId" + greId);
		logger.info("greId view-->" + greId);
		InVoice inv = inVoiceService.saveInv(greId);
		return "redirect:edit?id="+inv.getId();
	}

	@GetMapping("/cancelStage")
	public String cancelStage(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		
		logger.info("inv details" + inVoiceService.saveCancelStage(id));
		return "redirect:list";
	}

	@GetMapping("/list")
	public String list(Model model) {
		List<InVoice> list = inVoiceService.findByIsActive();
		logger.info("list"+list);
		model.addAttribute("list", list);
		return "inv/list";
	}
	
	
	@GetMapping(value = "/approvedList")
	public String approvedList(Model model) {
		List<InVoice> list = inVoiceService.invApprovedList();
		logger.info("InVoice list-->" + list);
		model.addAttribute("list", list);
		return "/inv/approvedList";
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
		InVoice inv = inVoiceService.findById(Integer.parseInt(id));
		logger.info("InVoice -->" + inv);
		
		RequestContext.set(ContextUtil.populateContexturl(request));
		String path = "";
		
		path = hTMLToPDFGenerator.getOfflineSummaryToPDF(HTMLToPDFGenerator.HTML_PDF_Offline)
                .OfflineHtmlStringToPdfForInvoice(pdfUploadedPath,inv);
				
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
