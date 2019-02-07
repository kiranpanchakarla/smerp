package com.smerp.controller.inventorytransactions;

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
import com.smerp.model.inventory.TaxCode;
import com.smerp.model.inventorytransactions.InventoryGoodsIssue;
import com.smerp.model.inventorytransactions.InventoryGoodsReceipt;
import com.smerp.repository.admin.TaxCodeRepository;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.inventorytransactions.InventoryGoodsIssueService;
import com.smerp.service.master.PlantService;
import com.smerp.util.ContextUtil;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.HTMLToPDFGenerator;
import com.smerp.util.RequestContext;

@Controller
@RequestMapping("/invgi")
public class InventoryGoodsIssueController {

	private static final Logger logger = LogManager.getLogger(InventoryGoodsIssueController.class);

	private static String pdfUploadedPath;
	
	@Autowired
	InventoryGoodsIssueService inventoryGoodsIssueService;

	@Autowired
	PlantService plantService;

	@Autowired
	TaxCodeRepository taxCodeRepository;
	
	@Autowired
	ProductService productService;

	@Autowired
	private HTMLToPDFGenerator hTMLToPDFGenerator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	public Map<Double, Object> taxCode() {

		// return
		// taxCodeRepository.findAllByOrderByTaxCodeAsc().stream().collect(Collectors.toMap(TaxCode::getTaxCode,
		// TaxCode::getTaxCode));

		return taxCodeRepository.findAllByOrderByTaxCodeAsc().stream()
				.collect(Collectors.toMap(TaxCode::getTaxCode, TaxCode::getTaxCode, (v1, v2) -> {
					throw new RuntimeException(String.format("Duplicate key for values %s and %s", v1, v2));
				}, TreeMap::new)); // for Sorted Values
	}

	public Map<Integer, Object> plantMap() {
		return plantService.findAll().stream().collect(Collectors.toMap(Plant::getId, Plant::getPlantName));
	}

	@GetMapping(value = "/create")
	public String create(Model model, InventoryGoodsIssue invGoodsIssue) throws JsonProcessingException {
		logger.info("Inside InventoryGoodsIssueController Create Method");
		logger.info("gr-->" + invGoodsIssue);
		logger.info("taxCode()-->" + taxCode());
		logger.info("plantMap()-->" + plantMap());
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("plantMapSize", plantMap().size());
		model.addAttribute("taxCodeMap", taxCode());
		InventoryGoodsIssue invgr = inventoryGoodsIssueService.findLastDocumentNumber();
		if (invgr != null && invgr.getDocNumber() != null) {
			invGoodsIssue.setDocNumber(GenerateDocNumber.documentNumberGeneration(invgr.getDocNumber()));
		} else {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDateTime now = LocalDateTime.now();
	    invGoodsIssue.setDocNumber(GenerateDocNumber.documentNumberGeneration("IGI"+(String)dtf.format(now) +"0"));
		}
		logger.info("IGR Details-->" + invGoodsIssue);
		model.addAttribute("productList",
				mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", new ObjectMapper().writeValueAsString(productService.findAllProductDescription("product")));
		model.addAttribute("gr", invGoodsIssue);
		return "inv_goodsIssue/create";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<InventoryGoodsIssue> list = inventoryGoodsIssueService.findByIsActive();
		logger.info("list" + list);
		model.addAttribute("list", list);
		return "inv_goodsIssue/list";
	}
	
	@PostMapping("/save")
	public String saveInvGI(InventoryGoodsIssue invGoodsIssue) {
		logger.info("Inside save method" + invGoodsIssue);
		logger.info("gr details" + inventoryGoodsIssueService.save(invGoodsIssue));		
		return "redirect:list";
	}
	
	@PostMapping(value = "/delete")
	public String delete(@RequestParam("id") int id) {

		logger.info("Delete msg");
		inventoryGoodsIssueService.delete(id);
		return "redirect:list";
	}
	
	 @GetMapping("/edit")
		public String edit(String id, Model model) throws JsonProcessingException {
			logger.info("id-->" + id);
			InventoryGoodsIssue invGR = inventoryGoodsIssueService.findById(Integer.parseInt(id));
			invGR = inventoryGoodsIssueService.getListAmount(invGR);
			ObjectMapper mapper = poloadData(model, invGR);
			model.addAttribute("productList", mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
			model.addAttribute("descriptionList", new ObjectMapper().writeValueAsString(productService.findAllProductDescription("product")));
			model.addAttribute("plantMap", plantMap());
			model.addAttribute("plantMapSize", plantMap().size());
			model.addAttribute("taxCodeMap", taxCode());
			 
			model.addAttribute("gr", invGR);
			return "inv_goodsIssue/create";
		} 
		 
		 @GetMapping("/view")
			public String view(String id, Model model) throws JsonProcessingException {
				logger.info("id-->" + id);
				InventoryGoodsIssue invGR = inventoryGoodsIssueService.findById(Integer.parseInt(id));
				invGR = inventoryGoodsIssueService.getListAmount(invGR);
				poloadData(model, invGR);
				model.addAttribute("gr", invGR);
				model.addAttribute("plantMap", plantMap());
				model.addAttribute("taxCodeMap", taxCode());
				return "inv_goodsIssue/view";
			}
		 
		 private ObjectMapper poloadData(Model model, InventoryGoodsIssue invGR) {
				ObjectMapper mapper = new ObjectMapper();
				  
				model.addAttribute("inventoryGoodsIssueList", invGR.getInventoryGoodsIssueList());
				return mapper;
			}
		 
		 @RequestMapping("/downloadPdf")
			public void downloadHtmlPDF(HttpServletResponse response, String htmlData, HttpServletRequest request,
					HttpSession session, String regType, Model model,String orgId,String id) throws Exception {
				
				logger.info("id -->" + id);
				InventoryGoodsIssue invGR = inventoryGoodsIssueService.findById(Integer.parseInt(id));
				logger.info("Inventory goods Receipt -->" + invGR);
				
				RequestContext.set(ContextUtil.populateContexturl(request));
				String path = "";
				
				 path = hTMLToPDFGenerator.getOfflineSummaryToPDF(HTMLToPDFGenerator.HTML_PDF_Offline)
		                .OfflineHtmlStringToPdfForInvGoodsIssue(pdfUploadedPath,invGR); 
						
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
