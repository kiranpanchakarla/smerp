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
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.smerp.util.ContextUtil;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.HTMLToPDFGenerator;
import com.smerp.util.RequestContext;

@Controller
@RequestMapping("/purchaseReq")
public class PurchaseRequestController {

	private static String pdfUploadedPath;
	
	@Value(value = "${file.upload.pdf.path}")
	public void setPropPDF(String pdf) {
		this.pdfUploadedPath = pdf;
	}
	
	
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
	
	@Autowired
	private HTMLToPDFGenerator hTMLToPDFGenerator;

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
		purchaseRequest.setReferenceUser(user);
		model.addAttribute("planMap", plantMap());
		model.addAttribute("productList", new ObjectMapper().writeValueAsString(productService.findAllProductNamesByProduct("product")));
		//gets the users first and last name
		model.addAttribute("usersList", new ObjectMapper().writeValueAsString(userService.findFirstNames()));
		model.addAttribute("sacList", new ObjectMapper().writeValueAsString(sacService.findAllSacCodes()));
		PurchaseRequest purchaseRequests = purchaseRequestService.findLastDocumentNumber();
		if (purchaseRequests != null && purchaseRequests.getDocNumber() != null) {
			purchaseRequest.setDocNumber(GenerateDocNumber.documentNumberGeneration(purchaseRequests.getDocNumber()));
		} else {
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
			 LocalDateTime now = LocalDateTime.now();
			 purchaseRequest.setDocNumber(GenerateDocNumber.documentNumberGeneration("PR"+(String)dtf.format(now) +"0"));
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

	@GetMapping("/cancelStage")
	public String cancelStage(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		logger.info("rfq details" + purchaseRequestService.saveCancelStage(id));
		return "redirect:list";
	}
	
	
	@GetMapping(value = "/list")
	public String list(Model model) {
		List<PurchaseRequest> purchaseRequestsList = purchaseRequestService.findByIsActive();
		logger.info("purchaseRequest list-->" + purchaseRequestsList);
		model.addAttribute("purchaseRequestsList", purchaseRequestsList);
		return "/purchaseReq/list";
	}
	
	@GetMapping(value = "/approvedList")
	public String approvedList(Model model) {
		List<PurchaseRequest> purchaseRequestsList = purchaseRequestService.prApprovedList();
		logger.info("purchaseRequest list-->" + purchaseRequestsList);
		model.addAttribute("purchaseRequestsList", purchaseRequestsList);
		return "/purchaseReq/approvedList";
	}

	@GetMapping(value = "/view")
	public String view(Model model, String purchaseReqId) {
		
		PurchaseRequest purchaseRequest = purchaseRequestService.getInfo(Integer.parseInt(purchaseReqId));
		logger.info("purchaseRequest view-->" + purchaseRequest);
		model.addAttribute("purchaseRequest", purchaseRequest);
		model.addAttribute("plantMap", plantMap());
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
		model.addAttribute("usersList", new ObjectMapper().writeValueAsString(userService.findFirstNames()));
		model.addAttribute("sacList", new ObjectMapper().writeValueAsString(sacService.findAllSacCodes()));
		
		purchaseRequest = purchaseRequestService.getInfo(Integer.parseInt(purchaseReqId));
		model.addAttribute("purchaseRequestLists", purchaseRequest.getPurchaseRequestLists());
		model.addAttribute("purchaseRequest", purchaseRequestService.getInfo(Integer.parseInt(purchaseReqId)));
		return "purchaseReq/create";
	}
	
	@RequestMapping("/downloadPdf")
	public void downloadHtmlPDF(HttpServletResponse response, String htmlData, HttpServletRequest request,
			HttpSession session, String regType, Model model,String orgId,String purchaseReqId) throws Exception {
		
		PurchaseRequest purchaseRequest = purchaseRequestService.getInfo(Integer.parseInt(purchaseReqId));
		logger.info("purchaseRequest view-->" + purchaseRequest);
		
		RequestContext.set(ContextUtil.populateContexturl(request));
		String path = "";
		
		path = hTMLToPDFGenerator.getOfflineSummaryToPDF(HTMLToPDFGenerator.HTML_PDF_Offline)
				.OfflineHtmlStringToPdfForPurchaseReq(pdfUploadedPath,purchaseRequest);
				
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
	
	
	

	@PostMapping(value = "/delete")
	public String deletePurchaseReq(@RequestParam("id") int id) {
        
		logger.info("Delete msg");
		purchaseRequestService.delete(id);
		return "redirect:list";
	}


	public Map<Integer, Object> plantMap() {
		return plantService.findAll().stream().collect(Collectors.toMap(Plant::getId, Plant::getPlantName));
	}

	
}
