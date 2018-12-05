package com.smerp.controller.purchase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.smerp.model.admin.User;
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
	UserService userService;

	@Autowired
	ProductService productService;

	@Autowired
	PlantService plantService;

	@Autowired
	SacService sacService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@GetMapping(value = "/create")
	public String purchaseRequestCreate(Model model, PurchaseRequest purchaseRequest) throws JsonProcessingException {
		logger.info("purchaseRequest create-->" + purchaseRequest);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user", user);
		model.addAttribute("purchaseReq", new PurchaseRequest());
		model.addAttribute("planMap", plantMap());
		model.addAttribute("productList", new ObjectMapper().writeValueAsString(productService.findAllProductNamesByProduct("product")));
		//gets the users first and last name
		model.addAttribute("usersList", new ObjectMapper().writeValueAsString(userService.findFirstNames()));
		model.addAttribute("sacList", new ObjectMapper().writeValueAsString(sacService.findAllSacCodes()));
		PurchaseRequest purchaseRequests = purchaseRequestService.findLastDocumentNumber();
		if (purchaseRequests != null && purchaseRequests.getDocNumber() != null) {
			purchaseRequest.setDocNumber(documentNumberGeneration(purchaseRequests.getDocNumber()));
		} else {
			purchaseRequestIdGeneration(purchaseRequest);
		}
		model.addAttribute("purchaseRequest", purchaseRequest);
		return "/purchaseReq/create";
	}

	@PostMapping(value = "/save")
	public String save(PurchaseRequest purchaseRequest, Model model, BindingResult result) {
		logger.info("purchaseRequest save-->" + purchaseRequest);
		purchaseRequestService.save(purchaseRequest);
		return "redirect:list";
	}

	@GetMapping(value = "/list")
	public String list(Model model) {
		List<PurchaseRequest> purchaseRequestsList = purchaseRequestService.findByIsActive();
		logger.info("purchaseRequest list-->" + purchaseRequestsList);
		model.addAttribute("purchaseRequestsList", purchaseRequestsList);
		return "/purchaseReq/list";
	}

	@GetMapping(value = "/view")
	public String view(Model model, String purchaseReqId) {
		PurchaseRequest purchaseRequest = purchaseRequestService.getInfo(Integer.parseInt(purchaseReqId));
		logger.info("purchaseRequest view-->" + purchaseRequest);
		model.addAttribute("purchaseRequest", purchaseRequest);
		return "purchaseReq/view";
	}

	@GetMapping(value = "/getInfo")
	public String getInfo(String purchaseReqId, Model model, PurchaseRequest purchaseRequest) throws JsonProcessingException {
		logger.info("purchaseRequest edit-->" + purchaseRequest);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user", user);
		model.addAttribute("purchaseReq", new PurchaseRequest());
		model.addAttribute("planMap", plantMap());
		//model.addAttribute("planMap", plantMap());
		model.addAttribute("productList", new ObjectMapper().writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("usersList", new ObjectMapper().writeValueAsString(userService.findAllUsername()));
		model.addAttribute("sacList", new ObjectMapper().writeValueAsString(sacService.findAllSacCodes()));
		PurchaseRequest purchaseRequests = purchaseRequestService.findLastDocumentNumber();
		if (purchaseRequests != null && purchaseRequests.getDocNumber() != null) {
			purchaseRequest.setDocNumber(documentNumberGeneration(purchaseRequests.getDocNumber()));
		} else {
			purchaseRequestIdGeneration(purchaseRequest);
		}
        
		purchaseRequest = purchaseRequestService.getInfo(Integer.parseInt(purchaseReqId));
		model.addAttribute("purchaseRequestLists", purchaseRequest.getPurchaseRequestLists());
		model.addAttribute("purchaseRequest", purchaseRequestService.getInfo(Integer.parseInt(purchaseReqId)));
		return "purchaseReq/create";
	}
	
	 
	private void purchaseRequestIdGeneration(PurchaseRequest purchaseRequest) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String[] parts2 = dateFormat.format(date).split("/");
		String type = "PR";
		String[] parts1 = parts2[2].split(" ");
		String prefix = type.concat(parts2[0]).concat(parts2[1]).concat(parts1[0]);
		String suffix = "1";
		String docNumber = prefix + suffix;
		purchaseRequest.setDocNumber(docNumber);
	}

	@PostMapping(value = "/delete")
	public String deletePurchaseReq(@RequestParam("id") int id) {
        
		logger.info("Delete msg");
		purchaseRequestService.delete(id);
		return "redirect:list";
	}

	
	private String documentNumberGeneration(String documentNumber) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String[] parts2 = dateFormat.format(date).split("/");
		String type = "PR";
		String[] parts1 = parts2[2].split(" ");
		String prefix = type.concat(parts2[0]).concat(parts2[1]).concat(parts1[0]);

		int inc_number = Integer.parseInt(documentNumber.substring(10)) + 1;
		String docNumber = prefix + inc_number;
		logger.info("docNumber" + docNumber);
		return docNumber;
	}

	public Map<Integer, Object> plantMap() {
		return plantService.findAll().stream().collect(Collectors.toMap(Plant::getId, Plant::getPlantName));
	}

	
}
