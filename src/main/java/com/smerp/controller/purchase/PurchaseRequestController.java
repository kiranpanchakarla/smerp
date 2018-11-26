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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smerp.model.admin.Plant;
import com.smerp.model.inventory.Product;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.service.UserService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.master.PlantService;
import com.smerp.service.master.SacService;
import com.smerp.service.purchase.PurchaseRequestService;

@Controller
@RequestMapping("/purchaseReq")
public class PurchaseRequestController {
	
	
	private static final Logger logger = LogManager.getLogger(PurchaseRequestController.class);
	
	@Autowired
	PurchaseRequestService purchaseRequestService;
	
	@Autowired
	UserService  userService;
	
	@Autowired
	ProductService  productService;
	
	@Autowired
	PlantService plantService;
	
	
	@Autowired
	SacService  sacService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	
	@GetMapping(value = "/create")
	public String purchaseRequestCreate(Model model, PurchaseRequest purchaseRequest) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("purchaseReq", new PurchaseRequest());
		model.addAttribute("planMap", plantMap());
		model.addAttribute("productList", mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("usersList",  mapper.writeValueAsString(userService.findAllUsername()));
		model.addAttribute("sacList",  mapper.writeValueAsString(sacService.findAllSacCodes()));
		PurchaseRequest purchaseRequests =purchaseRequestService.findLastDocumentNumber();
		if (purchaseRequests!=null && purchaseRequests.getDocNumber()!=null) {
			purchaseRequest.setDocNumber(documentNumberGeneration(purchaseRequests.getDocNumber()));
		}else {
			purchaseRequest.setDocNumber("doc_1");
		}
		model.addAttribute("purchaseRequest",purchaseRequest);
		return "/purchaseReq/create";
	}
	
	
	@PostMapping(value = "/save")
	public String save(PurchaseRequest purchaseRequest,Model model,BindingResult result) {
		logger.info(purchaseRequest + "purchaseRequest");
		purchaseRequestService.save(purchaseRequest);
		return "redirect:list";
	}
	
	
	@GetMapping(value = "/list")
	public String list(Model model) {
		List<PurchaseRequest> purchaseRequestsList = purchaseRequestService.findByIsActive();
		model.addAttribute("purchaseRequestsList", purchaseRequestsList);
		return "/purchaseReq/list";
	}
	
	
	@GetMapping(value = "/view")
	public String view(String purchaseReqId , Model model) {
		PurchaseRequest purchaseRequest=purchaseRequestService.getInfo(Integer.parseInt(purchaseReqId));
		model.addAttribute("purchaseRequestsList",purchaseRequest);
		
		
		//model.addAttribute("uomList",  uomService.uomList();
		return "purchaseReq/view";
	}
	
	
	@GetMapping(value = "/getInfo")
	public String getInfo(String purchaseReqId, Model model, PurchaseRequest purchaseRequest)throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		//model.addAttribute("purchaseReq", new PurchaseRequest());
		model.addAttribute("planMap", plantMap());
		model.addAttribute("productList", mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("usersList",  mapper.writeValueAsString(userService.findAllUsername()));
		model.addAttribute("sacList",  mapper.writeValueAsString(sacService.findAllSacCodes()));
		PurchaseRequest purchaseRequests =purchaseRequestService.findLastDocumentNumber();
		if (purchaseRequests!=null && purchaseRequests.getDocNumber()!=null) {
			purchaseRequest.setDocNumber(documentNumberGeneration(purchaseRequests.getDocNumber()));
		}else {
			purchaseRequest.setDocNumber("doc_1");
		}
		
		 purchaseRequest=purchaseRequestService.getInfo(Integer.parseInt(purchaseReqId));
		 model.addAttribute("purchaseRequestLists",purchaseRequest.getPurchaseRequestLists());
		model.addAttribute("purchaseRequest", purchaseRequestService.getInfo(Integer.parseInt(purchaseReqId)));
		return "purchaseReq/create";
	}
	
	
	@PostMapping(value = "/delete")
	public String deletePurchaseReq(@RequestParam("id") int id) {
		
		logger.info("Delete msg");
		purchaseRequestService.delete(id);
		return "redirect:list";
	}
	

	
	private String documentNumberGeneration(String documentNumber) {
		String[] parts = documentNumber.split("_");
		String prefix = parts[0]; // 004
		String suffix = parts[1];
		int docno=Integer.parseInt(suffix)+1;
		
		String docNumber=prefix+'_'+docno;
		return docNumber;
	}
	
	
	public Map<Integer, Object> plantMap(){
		return plantService.findAll().stream().collect(Collectors.toMap(Plant::getId, Plant::getPlantName));
	}
	
}
