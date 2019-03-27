package com.smerp.controller.purchase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.smerp.model.admin.Plant;
import com.smerp.model.admin.User;
import com.smerp.model.admin.VendorAddress;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.PurchaseOrderActivityHistory;
import com.smerp.model.inventory.TaxCode;
import com.smerp.model.search.SearchFilter;
import com.smerp.repository.admin.TaxCodeRepository;
import com.smerp.service.admin.VendorService;
import com.smerp.service.inventory.ProductService;
import com.smerp.service.master.PlantService;
import com.smerp.service.master.SacService;
import com.smerp.service.purchase.GoodsReceiptService;
import com.smerp.service.purchase.PurchaseOrderActivityHistoryService;
import com.smerp.service.purchase.PurchaseOrderService;
import com.smerp.util.ContextUtil;
import com.smerp.util.DocNumberGenerator;
import com.smerp.util.DownloadReportsXLS;
import com.smerp.util.EnumStatusUpdate;
import com.smerp.util.GenerateDocNumber;
import com.smerp.util.HTMLToPDFGenerator;
import com.smerp.util.RequestContext;

@Controller
@RequestMapping("/po")
public class PurchaseOrderController {

	private static final Logger logger = LogManager.getLogger(PurchaseOrderController.class);

	private static String pdfUploadedPath;
	
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
	
	@Autowired
	GoodsReceiptService goodsReceiptService;
	
	@Autowired
	private HTMLToPDFGenerator hTMLToPDFGenerator;
	
	@Autowired
	private PurchaseOrderActivityHistoryService purchaseOrderActivityHistoryService;
	
	@Autowired
	private DocNumberGenerator docNumberGenerator;
	
	@Autowired
	private DownloadReportsXLS downloadReportsXLS;
	
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
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("plantMapSize", plantMap().size());
		model.addAttribute("taxCodeMap", taxCode());
		model.addAttribute("sacList", mapper.writeValueAsString(sacService.findAllSacCodes()));
       		
		Integer count = docNumberGenerator.getCountByDocType(EnumStatusUpdate.PO.getStatus());
		logger.info("PO count-->" + count);
		
		PurchaseOrder podetails = purchaseOrderService.findLastDocumentNumber();
		if (podetails != null && podetails.getDocNumber() != null) {
			//po.setDocNumber(GenerateDocNumber.documentNumberGeneration(podetails.getDocNumber()));
			po.setDocNumber(GenerateDocNumber.documentNumberGeneration(podetails.getDocNumber(),count));
			logger.info("podetails-->" + po);
		} else {
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	    LocalDateTime now = LocalDateTime.now();
		po.setDocNumber(GenerateDocNumber.documentNumberGeneration("PO"+(String)dtf.format(now) +"0",count));
		}
		logger.info("podetails-->" + podetails);
		model.addAttribute("productList", mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", mapper.writeValueAsString(productService.findAllProductDescription("product")));
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
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		model.addAttribute("productList", mapper.writeValueAsString(productService.findAllProductNamesByProduct("product")));
		model.addAttribute("descriptionList", mapper.writeValueAsString(productService.findAllProductDescription("product")));
		model.addAttribute("vendorNamesList", mapper.writeValueAsString(vendorService.findAllVendorNames()));
		// model.addAttribute("categoryMap", categoryMap());
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("plantMapSize", plantMap().size());
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
		boolean check = goodsReceiptService.checkQuantityPoGr(po);
		logger.info("check-->" + check);
		model.addAttribute("checkStatusPoGr", check);
		
		model.addAttribute("po", po);
		model.addAttribute("plantMap", plantMap());
		model.addAttribute("taxCodeMap", taxCode());
		try {
			List<PurchaseOrderActivityHistory> poahList = purchaseOrderActivityHistoryService.findByHistoryListByPOId(Integer.parseInt(id));
			
			if(poahList.size()>0)
			model.addAttribute("poahList", poahList);
			
		}catch(Exception e) {
			logger.error("Error with ActivityHistory in PurchaseOrderController while view():"+e);
		}
		return "po/view";
	}

	@PostMapping(value = "/delete")
	public String delete(@RequestParam("id") int id) {

		logger.info("Delete msg");
		PurchaseOrder po = purchaseOrderService.delete(id);
		/*try {
			//String message = 
			savePoActivityHistory(po);
		}catch(Exception e) {
			logger.error("Error with ActivityHistory in PurchaseOrderController while delete():"+e);
		}*/
		return "redirect:list";
	}
	
	@PostMapping("/save")
	public String name(PurchaseOrder purchaseOrder) {
		logger.info("Inside save method" + purchaseOrder);
		PurchaseOrder po = null;
		if(purchaseOrder.getId()==null) {
			boolean status = purchaseOrderService.findByDocNumber(purchaseOrder.getDocNumber());
			if(!status) {
			po = purchaseOrderService.save(purchaseOrder);
			logger.info("po details" + po);
			}else {
				Integer count = docNumberGenerator.getCountByDocType(EnumStatusUpdate.PO.getStatus());
				logger.info("count-->" + count);
				
				PurchaseOrder podetails = purchaseOrderService.findLastDocumentNumber();
				if (podetails != null && podetails.getDocNumber() != null) {
					//po.setDocNumber(GenerateDocNumber.documentNumberGeneration(podetails.getDocNumber()));
					purchaseOrder.setDocNumber(GenerateDocNumber.documentNumberGeneration(podetails.getDocNumber(),count));
				}
				po = purchaseOrderService.save(purchaseOrder);
			}
		}else {
			po = purchaseOrderService.save(purchaseOrder);
			logger.info("po details" + po);
		}
		try {
			savePoActivityHistory(po);
		}catch(Exception e) {
			logger.error("Error with ActivityHistory in PurchaseOrderController while save():"+e);
		}
		return "redirect:list";
	}
	
	@PostMapping("/saveRFQtoPO")
	public String savePRtoRFQ(HttpServletRequest request) {
		String rfqId = request.getParameter("rfqId");
		logger.info("rfqId" + rfqId);
		logger.info("rfqId view-->" + rfqId);
		PurchaseOrder po = purchaseOrderService.savePO(rfqId);
		try {
			String message = EnumStatusUpdate.CONVERTRFQTOPO.getStatus()+"@@"+po.getReferenceDocNumber();
			savePoActivityHistory(po,message);
		}catch(Exception e) {
			logger.error("Error with ActivityHistory in PurchaseOrderController while saveRFQtoPO():"+e);
		}
	   return "redirect:edit?id="+po.getId();
	}

	@GetMapping(value = "/approvedList")
	public String approvedList(Model model, SearchFilter searchFilter) {
		List<PurchaseOrder> purchaseOrderList = purchaseOrderService.poApprovedList();
		logger.info("purchaseOrder list-->" + purchaseOrderService);
		searchFilter.setIsConvertedDoc("true");
		model.addAttribute("searchFilter", searchFilter);
		model.addAttribute("purchaseOrderList", purchaseOrderList);
		return "/po/approvedList";
	}
	
	@GetMapping("/cancelStage")
	public String cancelStage(String id, Model model) throws JsonProcessingException {
		logger.info("id-->" + id);
		PurchaseOrder po = purchaseOrderService.saveCancelStage(id);
		logger.info("po details" + po);
		try {
			savePoActivityHistory(po);
		}catch(Exception e) {
			logger.error("Error with ActivityHistory in PurchaseOrderController while cancelStage():"+e);
		}
		return "redirect:list";
	}

	@GetMapping("/list")
	public String list(Model model,SearchFilter searchFilter) {
		List<PurchaseOrder> list = purchaseOrderService.findByIsActive();
		logger.info("list"+list);
		model.addAttribute("list", list);
		model.addAttribute("searchFilter", searchFilter);
		return "po/list";
	}

	public Map<Integer, Object> plantMap() {
		return plantService.findAll().stream().collect(Collectors.toMap(Plant::getId, Plant::getPlantName));
	}
	
	public Map<Object,Double> taxCode() {
		
		//return taxCodeRepository.findAllByOrderByTaxCodeAsc().stream().collect(Collectors.toMap(TaxCode::getTaxCode, TaxCode::getTaxCode));
		
		return taxCodeRepository.findAll().stream().collect(Collectors.toMap(TaxCode::getDescription, TaxCode::getTaxCode));
		
		/*return taxCodeRepository.findAllByOrderByTaxCodeAsc().stream().collect(Collectors.toMap(TaxCode::getDescription, TaxCode::getTaxCode,
                (v1,v2) ->{ throw new RuntimeException(String.format("Duplicate key for values %s and %s", v1, v2));},
                TreeMap::new));*/  // for Sorted Values
	}
	
	@RequestMapping("/downloadPdf")
	public void downloadHtmlPDF(HttpServletResponse response, String htmlData, HttpServletRequest request,
			HttpSession session, String regType, Model model,String orgId,String id) throws Exception {
		
		PurchaseOrder po = purchaseOrderService.findById(Integer.parseInt(id));
		logger.info("RequestForQuotation view-->" + po);
		
		RequestContext.set(ContextUtil.populateContexturl(request));
		String path = "";
		
		path = hTMLToPDFGenerator.getOfflineSummaryToPDF(HTMLToPDFGenerator.HTML_PDF_Offline)
				.OfflineHtmlStringToPdfForPO(pdfUploadedPath,po);
				
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
		try {
			String message = EnumStatusUpdate.PDFDOWNLOAD.getStatus();
			savePoActivityHistory(po,message);
		}catch(Exception e) {
			logger.error("Error with ActivityHistory in PurchaseOrderController while downloadHtmlPDF():"+e);
		}
		
	} 
	
	private void savePoActivityHistory(PurchaseOrder po) {
		PurchaseOrderActivityHistory poah = new PurchaseOrderActivityHistory();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		poah.setPurchaseOrder(po);
		boolean status = purchaseOrderActivityHistoryService.ifPOAvailableOrNot(po.getId());
		if (!status) {
			poah.setActivity(EnumStatusUpdate.CREATE.getStatus());

			if (po.getStatus().equalsIgnoreCase(EnumStatusUpdate.DRAFT.getStatus())) {
				poah.setLog(EnumStatusUpdate.DRAFTED.getStatus());
			} else if (po.getStatus().equalsIgnoreCase(EnumStatusUpdate.OPEN.getStatus())) {
				poah.setLog(EnumStatusUpdate.SAVED.getStatus());
			} else if (po.getStatus().equalsIgnoreCase(EnumStatusUpdate.APPROVEED.getStatus())) {
				poah.setLog(po.getStatus());
			} else if (po.getStatus().equalsIgnoreCase(EnumStatusUpdate.REJECTED.getStatus())) {
				poah.setLog(po.getStatus());
			}
		} else {
			if (po.getStatus().equalsIgnoreCase(EnumStatusUpdate.DRAFT.getStatus())) {
				poah.setLog(EnumStatusUpdate.DRAFTED.getStatus());
				poah.setActivity(po.getStatus());
			} else if (po.getStatus().equalsIgnoreCase(EnumStatusUpdate.OPEN.getStatus())) {
				poah.setLog(EnumStatusUpdate.UPDATED.getStatus());
				poah.setActivity(EnumStatusUpdate.UPDATE.getStatus());
			} else if (po.getStatus().equalsIgnoreCase(EnumStatusUpdate.APPROVEED.getStatus())) {
				poah.setLog(po.getStatus());
				poah.setActivity(EnumStatusUpdate.APPROVE.getStatus());
			} else if (po.getStatus().equalsIgnoreCase(EnumStatusUpdate.REJECTED.getStatus())) {
				poah.setLog(po.getStatus());
				poah.setActivity(EnumStatusUpdate.REJECT.getStatus());
			} else if (po.getStatus().equalsIgnoreCase(EnumStatusUpdate.CANCELED.getStatus())) {
				poah.setLog(po.getStatus());
				poah.setActivity(EnumStatusUpdate.CANCEL.getStatus());
			}
		}

		poah.setCreatedBy(user.getUsername());

		logger.info("PurchaseOrderActivityHistory view-->" + poah);
		purchaseOrderActivityHistoryService.save(poah);
	}
		
	private void savePoActivityHistory(PurchaseOrder po, String message) {
		PurchaseOrderActivityHistory poah = new PurchaseOrderActivityHistory();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		poah.setPurchaseOrder(po);

		if (message.equalsIgnoreCase(EnumStatusUpdate.PDFDOWNLOAD.getStatus())) {
			poah.setLog(message);
			poah.setActivity(message);
		} else if (message.startsWith(EnumStatusUpdate.CONVERTRFQTOPO.getStatus())
				&& po.getStatus().equalsIgnoreCase(EnumStatusUpdate.OPEN.getStatus())) {
			String a[] = message.split("@@");
			poah.setLog(a[0]);
			poah.setActivity("RFQ# " + a[1]);
		}
		poah.setCreatedBy(user.getUsername());
		logger.info("PurchaseOrderActivityHistory view-->" + poah);
		purchaseOrderActivityHistoryService.save(poah);
	}
	
		/*@GetMapping(value ="/showHistoryById")
		@ResponseBody
		public List<PurchaseOrderActivityHistory> showHistory(String id) {
			try {
				List<PurchaseOrderActivityHistory> poahList = purchaseOrderActivityHistoryService.findByHistoryListByPOId(Integer.parseInt(id));
				
				if(poahList.size()>0)
					//model.addAttribute("poahList", poahList);
				return poahList;
			}catch(Exception e) {
				logger.error("Error with ActivityHistory in PurchaseOrderController while view():"+e);
			}
			return null;
		} */
		
	@GetMapping("/getSearchFilterList")
	public String getSearchFilterList(Model model, SearchFilter searchFilter) {
		if(searchFilter.getIsConvertedDoc().equals("true")) {
			List<PurchaseOrder> purchaseOrderList = purchaseOrderService.searchFilterBySelection(searchFilter);
			logger.info("purchaseOrderList" + purchaseOrderList);
			model.addAttribute("purchaseOrderList", purchaseOrderList);
			model.addAttribute("searchFilter", searchFilter);
			return "po/approvedList";
		}else {
			List<PurchaseOrder> list = purchaseOrderService.searchFilterBySelection(searchFilter);
			logger.info("list" + list);
			model.addAttribute("list", list);
			model.addAttribute("searchFilter", searchFilter);
			return "po/list";
		}
	}
		
	@GetMapping("/exportPOExcel")
	public void download(HttpServletResponse response, Model model, HttpServletRequest request, String searchBy,
			String fieldName, String sortBy, String dateSelect, String fromDateString, String toDateString)
			throws Exception {

		SearchFilter searchFilter = new SearchFilter();
		searchFilter.setSearchBy(searchBy);
		searchFilter.setFieldName(fieldName);
		searchFilter.setSortBy(sortBy);
		searchFilter.setDateSelect(dateSelect);

		if (!fromDateString.equals("null")) {
			Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromDateString);
			Date toDate = new Date();
			if (!toDateString.equals("null")) {
				toDate = new SimpleDateFormat("yyyy-MM-dd").parse(toDateString);
			} else {
				String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				toDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
			}
			searchFilter.setFromDate(fromDate);
			searchFilter.setToDate(toDate);
		}

		String poFileNameDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		List<PurchaseOrder> list = purchaseOrderService.searchFilterBySelection(searchFilter);

		ByteArrayOutputStream stream = downloadReportsXLS.POReport(list);
		response.setContentType("text/html");
		OutputStream outstream = response.getOutputStream();
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"PO_Report_" + poFileNameDate + ".xlsx\"");
		stream.writeTo(outstream);
		outstream.flush();
		outstream.close();
	}
		
}
