package com.smerp.controller.purchase;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpRequest;
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
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.TaxCode;
import com.smerp.repository.admin.TaxCodeRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.master.PlantService;
import com.smerp.service.master.SacService;
import com.smerp.service.purchase.PurchaseOrderService;
import com.smerp.util.GenerateDocNumber;

@Controller
@RequestMapping("/po")
public class PurchaseOrderController {

	private static final Logger logger = LogManager.getLogger(PurchaseOrderController.class);

	
	@Autowired
	PlantService plantService;

	@Autowired
	ProductService productService;

	@Autowired
	private VendorService vendorService;

	@Autowired
	PurchaseOrderService purchaseOrderService;

	@Autowired
	SacService sacService;
	
	@Autowired
	TaxCodeRepository taxCodeRepository;
	
	


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@GetMapping("/create")
	public String create(Model model, PurchaseOrder po) throws JsonProcessingException {
		// model.addAttribute("categoryMap", categoryMap());
		logger.info("po-->" + po);
		logger.info("taxCode()-->" + taxCode());
		logger.info("plantMap()-->" + plantMap());
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("taxCodeMap", taxCode());
		model.addAttribute("sacList", mapper.writeValueAsString(sacService.findAllSacCodes()));
       
		PurchaseOrder podetails = purchaseOrderService.findLastDocumentNumber();
		if (podetails != null && podetails.getDocNumber() != null) {
			po.setDocNumber(GenerateDocNumber.documentNumberGeneration(podetails.getDocNumber()));
		} else {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDateTime now = LocalDateTime.now();
		po.setDocNumber(GenerateDocNumber.documentNumberGeneration("PO"+(String)dtf.format(now) +"0"));
		}
		logger.info("podetails-->" + podetails);
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		logger.info("mapper-->" + mapper);

		model.addAttribute("po", po);
		return "po/create";
	}

	

	@GetMapping("/edit")
	public String edit(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		PurchaseOrder po = purchaseOrderService.findById(Integer.parseInt(id));
		po = purchaseOrderService.getListAmount(po);  // set Amt Calculation  
		logger.info("po-->" + po);
		ObjectMapper mapper = poloadData(model, po);
		
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		// model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("taxCodeMap", taxCode());
		model.addAttribute("sacList", mapper.writeValueAsString(sacService.findAllSacCodes()));
		model.addAttribute("po", po);
		return "po/create";
	}

	private ObjectMapper poloadData(Model model, PurchaseOrder po) {
		ObjectMapper mapper = new ObjectMapper();
		VendorAddress vendorPayTypeAddress=new VendorAddress();
		VendorAddress vendorShippingAddress =new VendorAddress();
		if(po.getVendorPayTypeAddress()!=null && po.getVendorPayTypeAddress()!=null) {
		 vendorPayTypeAddress = po.getVendorPayTypeAddress();
		 vendorShippingAddress = po.getVendorShippingAddress();
		 model.addAttribute("vendorPayTypeAddressId", vendorPayTypeAddress.getId());
		 model.addAttribute("vendorShippingAddressId", vendorShippingAddress.getId());
		}
		logger.info("vendorPayTypeAddress-->" + vendorPayTypeAddress);
		logger.info("vendorShippingAddress-->" + vendorShippingAddress);
	
		
		model.addAttribute("purchaseOrderlineItems", po.getPurchaseOrderlineItems());
		return mapper;
	}

	@GetMapping("/view")
	public String view(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		PurchaseOrder po = purchaseOrderService.findById(Integer.parseInt(id));
		po = purchaseOrderService.getListAmount(po);
		logger.info("po-->" + po);
		poloadData(model, po);
		// model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("po", po);
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("taxCodeMap", taxCode());
		return "po/view";
	}

	@PostMapping(value = "/delete")
	public String delete(@RequestParam("id") int id) {

		logger.info("Delete msg");
		purchaseOrderService.delete(id);
		return "redirect:list";
	}

	
	@PostMapping("/save")
	public String name(PurchaseOrder requestForQuotation) {
		logger.info("Inside save method" + requestForQuotation);
		logger.info("po details" + purchaseOrderService.save(requestForQuotation));
		return "redirect:list";
	}
	
	@PostMapping("/saveRFQtoPO")
	public String savePRtoRFQ(HttpServletRequest request) {
		String rfqId = request.getParameter("rfqId");
		logger.info("rfqId" + rfqId);
		logger.info("rfqId view-->" + rfqId);
		PurchaseOrder po = purchaseOrderService.savePO(rfqId);
	   return "redirect:edit?id="+po.getId();
	}

	@GetMapping("/cancelStage")
	public String cancelStage(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		
		logger.info("po details" + purchaseOrderService.saveCancelStage(id));
		return "redirect:list";
	}

	@GetMapping("/list")
	public String list(Model model) {
		List<PurchaseOrder> list = purchaseOrderService.findByIsActive();
		logger.info("list"+list);
		model.addAttribute("list", list);
		return "po/list";
	}

	public Map<Integer, Object> plantMap() {
		return plantService.findAll().stream().collect(Collectors.toMap(Plant::getId, Plant::getPlantName));
	}
	
	public Map<Integer, Object> taxCode() {
		
		//return taxCodeRepository.findAllByOrderByTaxCodeAsc().stream().collect(Collectors.toMap(TaxCode::getTaxCode, TaxCode::getTaxCode));
		
		return taxCodeRepository.findAllByOrderByTaxCodeAsc().stream().collect(Collectors.toMap(TaxCode::getTaxCode, TaxCode::getTaxCode,
                (v1,v2) ->{ throw new RuntimeException(String.format("Duplicate key for values %s and %s", v1, v2));},
                TreeMap::new));  // for Sorted Values
	}

}
