package com.smerp.service.inventory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smerp.model.admin.Plant;
import com.smerp.model.admin.VendorAddress;
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.service.admin.VendorService;
import com.smerp.service.master.PlantService;
import com.smerp.service.master.SacService;

@Controller
@RequestMapping("/rfq")
public class RequestForQuotationController {
	
	private static final Logger logger = LogManager.getLogger(RequestForQuotationController.class);

	@Autowired
	PlantService plantService;
	
	@Autowired
	ProductService  productService;
	
	@Autowired
	private VendorService vendorService;
	
	@Autowired
	RequestForQuotationService requestForQuotationService;
	
	@Autowired
	SacService sacService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	

	@GetMapping("/create")
	public String createPage(Model model,RequestForQuotation rfq) throws JsonProcessingException {
		//model.addAttribute("categoryMap", categoryMap());
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("planMap", plantMap());
		model.addAttribute("sacList",  mapper.writeValueAsString(sacService.findAllSacCodes()));
		
		RequestForQuotation rfqdetails=requestForQuotationService.findLastDocumentNumber();
		if (rfqdetails!=null && rfqdetails.getDocNumber()!=null) {
			rfq.setDocNumber(documentNumberGeneration(rfqdetails.getDocNumber()));
		}else {
			rfq.setDocNumber("doc_1");
		}
		model.addAttribute("productList", mapper.writeValueAsString(productService.findAllProductNames()));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		model.addAttribute("rfq",rfq);
		return "rfq/create";
	}
	
	@GetMapping("/edit")
	public String edit(String id,Model model) throws JsonProcessingException {
		RequestForQuotation rfq=requestForQuotationService.findById(Integer.parseInt(id));
		ObjectMapper mapper = new ObjectMapper();
		VendorAddress vendorPayTypeAddress=rfq.getVendorPayTypeAddress();
		VendorAddress  vendorShippingAddress=rfq.getVendorShippingAddress();
		model.addAttribute("sacList",  mapper.writeValueAsString(sacService.findAllSacCodes()));
		model.addAttribute("vendorPayTypeAddressId", vendorPayTypeAddress.getId());
		model.addAttribute("vendorShippingAddressId", vendorShippingAddress.getId());
		model.addAttribute("lineItems",rfq.getLineItems());
		
		
		model.addAttribute("productList", mapper.writeValueAsString(productService.findAllProductNames()));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
	//	model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("planMap", plantMap());
		
		model.addAttribute("rfq",rfq);
		return "rfq/create";
	}

	@GetMapping("/view")
	public String view(String id,Model model) throws JsonProcessingException {
		RequestForQuotation rfq=requestForQuotationService.findById(Integer.parseInt(id));
		
		VendorAddress vendorPayTypeAddress=rfq.getVendorPayTypeAddress();
		VendorAddress  vendorShippingAddress=rfq.getVendorShippingAddress();
		
		model.addAttribute("vendorPayTypeAddressId", vendorPayTypeAddress.getId());
		model.addAttribute("vendorShippingAddressId", vendorShippingAddress.getId());
		model.addAttribute("lineItems",rfq.getLineItems());
		
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("productList", mapper.writeValueAsString(productService.findAllProductNames()));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		//model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("planMap", plantMap());
		
		model.addAttribute("rfq",rfq);
		return "rfq/view";
	}
	private String documentNumberGeneration(String documentNumber) {
		String[] parts = documentNumber.split("_");
		String prefix = parts[0]; // 004
		String suffix = parts[1];
		int docno=Integer.parseInt(suffix)+1;
		
		String docNumber=prefix+'_'+docno;
		return docNumber;
	}
	
	@PostMapping("/save")
	public String name(RequestForQuotation requestForQuotation) {
		logger.info("Inside save method" +requestForQuotation );
		// delete rq_item_details.
		logger.info("rfq details"+requestForQuotationService.save(requestForQuotation));
		return "redirect:list";
	}
	
	
	@GetMapping("/list")
	public String list(Model model) {
	 List<RequestForQuotation>	list=requestForQuotationService.findAll();
	 model.addAttribute("list", list);
	 return "rfq/list";
	}
	

	/*public Map<String, Object> categoryMap() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("GOODS", "GOODS");
		map.put("SERVICE", "SERVICE");
		return map;
	}*/
	
	public Map<Integer, Object> plantMap(){
		return plantService.findAll().stream().collect(Collectors.toMap(Plant::getId, Plant::getPlantName));
	}
	
	
	


}
