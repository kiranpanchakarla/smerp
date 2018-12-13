package com.smerp.controller.purchase;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smerp.model.admin.Plant;
import com.smerp.model.admin.VendorAddress;
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.master.PlantService;
import com.smerp.service.master.SacService;
import com.smerp.service.purchase.RequestForQuotationService;
import com.smerp.util.GenerateDocNumber;

@Controller
@RequestMapping("/rfq")
public class RequestForQuotationController {

	private static final Logger logger = LogManager.getLogger(RequestForQuotationController.class);

	
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
		model.addAttribute("planMap", plantMap());
		model.addAttribute("sacList", mapper.writeValueAsString(sacService.findAllSacCodes()));
       
		RequestForQuotation rfqdetails = requestForQuotationService.findLastDocumentNumber();
		if (rfqdetails != null && rfqdetails.getDocNumber() != null) {
			rfq.setDocNumber(GenerateDocNumber.documentNumberGeneration(rfqdetails.getDocNumber()));
		} else {
			rfq = GenerateDocNumber.documentNumberGenerationNotInDB(rfq);
		}
		logger.info("rfqdetails-->" + rfqdetails);
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
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
		
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		// model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("planMap", plantMap());
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
		logger.info("rfq details" + requestForQuotationService.save(requestForQuotation));
		return "redirect:list";
	}
	
	@PostMapping("/savePRtoRFQ")
	public String savePRtoRFQ(@RequestParam String purchaseId) {
		logger.info("purchaseId" + purchaseId);
		logger.info("purchaseRequest view-->" + purchaseId);
		RequestForQuotation rfq = requestForQuotationService.saveRFQ(purchaseId);
		return "redirect:edit?id="+rfq.getId();
	}

	@GetMapping("/cancelStage")
	public String cancelStage(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		
		logger.info("rfq details" + requestForQuotationService.saveCancelStage(id));
		return "redirect:list";
	}

	@GetMapping("/list")
	public String list(Model model) {
		List<RequestForQuotation> list = requestForQuotationService.findByIsActive();
		logger.info("list"+list);
		model.addAttribute("list", list);
		return "rfq/list";
	}

	public Map<Integer, Object> plantMap() {
		return plantService.findAll().stream().collect(Collectors.toMap(Plant::getId, Plant::getPlantName));
	}

}
