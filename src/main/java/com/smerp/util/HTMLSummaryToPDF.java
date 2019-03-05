package com.smerp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.smerp.controller.purchase.PurchaseOrderController;
import com.smerp.controller.purchase.PurchaseRequestController;
import com.smerp.controller.purchase.RequestForQuotationController;
import com.smerp.email.EmailerGenerator;
import com.smerp.model.admin.Department;
import com.smerp.model.admin.User;
import com.smerp.model.inventory.CreditMemo;
import com.smerp.model.inventory.GoodsReceipt;
import com.smerp.model.inventory.GoodsReturn;
import com.smerp.model.inventory.InVoice;
import com.smerp.model.inventory.LineItemsBean;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.inventorytransactions.InventoryGoodsIssue;
import com.smerp.model.inventorytransactions.InventoryGoodsReceipt;
import com.smerp.model.inventorytransactions.InventoryGoodsTransfer;
import com.smerp.model.purchase.PurchaseRequest;
import com.smerp.service.admin.DepartmentService;
import com.smerp.service.inventorytransactions.InventoryGoodsIssueService;
import com.smerp.service.inventorytransactions.InventoryGoodsReceiptService;
import com.smerp.service.inventorytransactions.InventoryGoodsTransferService;
import com.smerp.service.purchase.CreditMemoService;
import com.smerp.service.purchase.GoodsReceiptService;
import com.smerp.service.purchase.GoodsReturnService;
import com.smerp.service.purchase.InVoiceService;
import com.smerp.service.purchase.PurchaseOrderService;

import freemarker.template.TemplateException;


@Component
public class HTMLSummaryToPDF extends EmailerGenerator {

	private static final Logger logger = LogManager.getLogger(HTMLSummaryToPDF.class);
	
	 
	private static String modulePR;

	@Value(value = "${module.pr}")
	public void setPR(String prop) {
		this.modulePR = prop;
	}
	
	private static String moduleRFQ;

	@Value(value = "${module.rfq}")
	public void setRFQ(String prop) {
		this.moduleRFQ = prop;
	}
	
	private static String modulePO;

	@Value(value = "${module.po}")
	public void setPO(String prop) {
		this.modulePO = prop;
	}
	
	private static String moduleGR;

	@Value(value = "${module.grec}")
	public void setGR(String prop) {
		this.moduleGR = prop;
	}
	
	private static String moduleGRet;

	@Value(value = "${module.gret}")
	public void setGRet(String prop) {
		this.moduleGRet = prop;
	}
	
	private static String moduleInv;

	@Value(value = "${module.inv}")
	public void setInv(String prop) {
		this.moduleInv = prop;
	}
	
	private static String moduleCredit;

	@Value(value = "${module.credit}")
	public void setCredit(String prop) {
		this.moduleCredit = prop;
	}
	
	@Autowired
	HTMLToPDFGenerator hTMLToPDFGenerator;
	
	@Autowired
	DownloadUtil downloadUtil;
	
	@Autowired
	PurchaseRequestController purchaseRequestController;

	@Autowired
	RequestForQuotationController rfqController;

	@Autowired
	PurchaseOrderController purchaseOrderController;
	
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@Autowired
	GoodsReceiptService goodsReceiptService;
	
	@Autowired
	InVoiceService invoiceService;
	
	@Autowired
	GoodsReturnService goodsReturnService;
	
	@Autowired
	CreditMemoService creditMemoService;
	
	@Autowired
	InventoryGoodsReceiptService inventoryGoodsReceiptService;
	
	@Autowired
	InventoryGoodsIssueService inventoryGoodsIssueService;
	
	@Autowired
	InventoryGoodsTransferService inventoryGoodsTransferService;
	
	@Autowired
	DepartmentService departmentService;
	
 /*    
	public String OfflineHtmlStringToPdf(String pdfFilePath) throws TemplateException, IOException, DocumentException {
		File sourceFolder = null;
			sourceFolder = new File(downloadUtil.getDownloadPath());
		if (!sourceFolder.exists()) {
			sourceFolder.mkdirs();
		}
		File file = null;
		String fileStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		file = new File(sourceFolder + File.separator + "CompanyView" + fileStr + ".pdf");
		
		FileOutputStream os = new FileOutputStream(file.getAbsolutePath());
		RequestContext.get().getConfigMap().put("mail.template", WebConstants.Offline_Gstr1_Summary);
		Writer out = new StringWriter();
		Map<String, Object> input = new HashMap<String, Object>(1);
		input.put("contextPath", RequestContext.get().getContextPath());
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		input.put("generatedDate", dateFormat1.format(new Date()));
		input.put("generatedBy", "Ganapathi");
		input.put("organization", "");
		input.put("gstr1List", "");
		input.put("format", new CurrencyFormatter());
		getTemplate().process(input, out);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(out.toString());
		renderer.layout();
		renderer.createPDF(os);
		os.flush();
		os.close();
		return file.getAbsolutePath();
	}*/

	public String OfflineHtmlStringToPdfForPurchaseReq(String pdfFilePath,PurchaseRequest purchaseRequest) throws TemplateException, IOException, DocumentException {
		File sourceFolder = null;
			sourceFolder = new File(downloadUtil.getDownloadPath());
		if (!sourceFolder.exists()) {
			sourceFolder.mkdirs();
		}
		File file = null;
		String fileStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		file = new File(sourceFolder + File.separator + purchaseRequest.getDocNumber() + ".pdf");
		FileOutputStream os = new FileOutputStream(file.getAbsolutePath());
		RequestContext.get().getConfigMap().put("mail.template", WebConstants.Offline_Purchase_Request);
		Writer out = new StringWriter();
		Map<String, Object> input = new HashMap<String, Object>(1);
		input.put("contextPath", RequestContext.get().getContextPath());
		input.put("pr", purchaseRequest);
		input.put("user", getUser());
		input.put("moduleName", modulePR);
		logger.info("plantMap-->" + purchaseRequestController.plantMap());
		input.put("plantMap", purchaseRequestController.plantMap());
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		getTemplate().process(input, out);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(out.toString());
		renderer.layout();
		renderer.createPDF(os);
		os.flush();
		os.close();
		return file.getAbsolutePath();
	}
	
	public String OfflineHtmlStringToPdfForRFQ(String pdfFilePath,RequestForQuotation forQuotation) throws TemplateException, IOException, DocumentException {
		File sourceFolder = null;
			sourceFolder = new File(downloadUtil.getDownloadPath());
		if (!sourceFolder.exists()) {
			sourceFolder.mkdirs();
		}
		File file = null;
		String fileStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		file = new File(sourceFolder + File.separator + forQuotation.getDocNumber() + ".pdf");
		FileOutputStream os = new FileOutputStream(file.getAbsolutePath());
		RequestContext.get().getConfigMap().put("mail.template", WebConstants.Offline_Request_For_Quotation);
		Writer out = new StringWriter();
		Map<String, Object> input = new HashMap<String, Object>(1);
		input.put("contextPath", RequestContext.get().getContextPath());
		input.put("rfq", forQuotation);
		logger.info("plantMap-->" + rfqController.plantMap());
		input.put("plantMap", rfqController.plantMap());
		input.put("user", getUser());
		input.put("moduleName", moduleRFQ);
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		getTemplate().process(input, out);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(out.toString());
		renderer.layout();
		renderer.createPDF(os);
		os.flush();
		os.close();
		return file.getAbsolutePath();
	}
	
	public String OfflineHtmlStringToPdfForPO(String pdfFilePath,PurchaseOrder purchaseOrder) throws TemplateException, IOException, DocumentException {
		
		purchaseOrder = purchaseOrderService.getListAmount(purchaseOrder);
		
		File sourceFolder = null;
			sourceFolder = new File(downloadUtil.getDownloadPath());
		if (!sourceFolder.exists()) {
			sourceFolder.mkdirs();
		}
		File file = null;
		String fileStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		file = new File(sourceFolder + File.separator + purchaseOrder.getDocNumber() + ".pdf");
		FileOutputStream os = new FileOutputStream(file.getAbsolutePath());
		RequestContext.get().getConfigMap().put("mail.template", WebConstants.Offline_Purchase_Order);
		Writer out = new StringWriter();
		Map<String, Object> input = new HashMap<String, Object>(1);
		input.put("contextPath", RequestContext.get().getContextPath());
		input.put("po", purchaseOrder);
		input.put("moduleName", modulePO);
		logger.info("plantMap-->" + purchaseOrderController.plantMap());
		input.put("plantMap", purchaseOrderController.plantMap());
		input.put("taxCodeMap", purchaseOrderController.taxCode());
		input.put("user", getUser());
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		getTemplate().process(input, out);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(out.toString());
		renderer.layout();
		renderer.createPDF(os);
		os.flush();
		os.close();
		return file.getAbsolutePath();
	}

public String OfflineHtmlStringToPdfForGoodsReceipt(String pdfFilePath,GoodsReceipt goodsReceipt) throws TemplateException, IOException, DocumentException {
		
	    goodsReceipt = goodsReceiptService.getListAmount(goodsReceipt);
	    GoodsReceipt gr = goodsReceiptService.getGoodsReceiptViewById(goodsReceipt.getId());
	    List<LineItemsBean> lineItemsBean = goodsReceiptService.getLineItemsBean(goodsReceipt.getId());
		File sourceFolder = null;
			sourceFolder = new File(downloadUtil.getDownloadPath());
		if (!sourceFolder.exists()) {
			sourceFolder.mkdirs();
		}
		File file = null;
		String fileStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		file = new File(sourceFolder + File.separator + goodsReceipt.getDocNumber() + ".pdf");
		FileOutputStream os = new FileOutputStream(file.getAbsolutePath());
		RequestContext.get().getConfigMap().put("mail.template", WebConstants.offline_Goods_Receipt);
		Writer out = new StringWriter();
		Map<String, Object> input = new HashMap<String, Object>(1);
		input.put("contextPath", RequestContext.get().getContextPath());
		input.put("gr", goodsReceipt);
		input.put("grqty", gr);
		input.put("grList", lineItemsBean);
		input.put("moduleName", moduleGR);
		logger.info("plantMap-->" + purchaseOrderController.plantMap());
		input.put("plantMap", purchaseOrderController.plantMap());
		input.put("taxCodeMap", purchaseOrderController.taxCode());
		input.put("user", getUser());
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		getTemplate().process(input, out);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(out.toString());
		renderer.layout();
		renderer.createPDF(os);
		os.flush();
		os.close();
		return file.getAbsolutePath();
	}

public String OfflineHtmlStringToPdfForGoodsReturn(String pdfFilePath,GoodsReturn goodsReturn) throws TemplateException, IOException, DocumentException {
	
	goodsReturn = goodsReturnService.getListAmount(goodsReturn);
		
		File sourceFolder = null;
			sourceFolder = new File(downloadUtil.getDownloadPath());
		if (!sourceFolder.exists()) {
			sourceFolder.mkdirs();
		}
		File file = null;
		String fileStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		file = new File(sourceFolder + File.separator + goodsReturn.getDocNumber() + ".pdf");
		FileOutputStream os = new FileOutputStream(file.getAbsolutePath());
		RequestContext.get().getConfigMap().put("mail.template", WebConstants.offline_Goods_Return);
		Writer out = new StringWriter();
		Map<String, Object> input = new HashMap<String, Object>(1);
		input.put("contextPath", RequestContext.get().getContextPath());
		input.put("gr", goodsReturn);
		input.put("moduleName", moduleGRet);
		logger.info("plantMap-->" + purchaseOrderController.plantMap());
		input.put("plantMap", purchaseOrderController.plantMap());
		input.put("taxCodeMap", purchaseOrderController.taxCode());
		input.put("user", getUser());
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		getTemplate().process(input, out);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(out.toString());
		renderer.layout();
		renderer.createPDF(os);
		os.flush();
		os.close();
		return file.getAbsolutePath();
	}

public String OfflineHtmlStringToPdfForInvoice(String pdfFilePath,InVoice invoice) throws TemplateException, IOException, DocumentException {
	
	    invoice = invoiceService.getListAmount(invoice);
	    InVoice inv = invoiceService.getInVoiceById(invoice.getId());
	    List<LineItemsBean> lineItemsBean = invoiceService.getLineItemsBean(invoice.getId());
		File sourceFolder = null;
			sourceFolder = new File(downloadUtil.getDownloadPath());
		if (!sourceFolder.exists()) {
			sourceFolder.mkdirs();
		}
		File file = null;
		String fileStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		file = new File(sourceFolder + File.separator + invoice.getDocNumber() + ".pdf");
		FileOutputStream os = new FileOutputStream(file.getAbsolutePath());
		RequestContext.get().getConfigMap().put("mail.template", WebConstants.offline_Invoice);
		Writer out = new StringWriter();
		Map<String, Object> input = new HashMap<String, Object>(1);
		input.put("contextPath", RequestContext.get().getContextPath());
		input.put("inv", invoice);
		input.put("invQty", inv);
		input.put("invList", lineItemsBean);
		input.put("moduleName", moduleInv);
		logger.info("plantMap-->" + purchaseOrderController.plantMap());
		input.put("plantMap", purchaseOrderController.plantMap());
		input.put("taxCodeMap", purchaseOrderController.taxCode());
		input.put("user", getUser());
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		getTemplate().process(input, out);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(out.toString());
		renderer.layout();
		renderer.createPDF(os);
		os.flush();
		os.close();
		return file.getAbsolutePath();
	}

public String OfflineHtmlStringToPdfForCreditMemo(String pdfFilePath,CreditMemo creditMemo) throws TemplateException, IOException, DocumentException {
	
	creditMemo = creditMemoService.getListAmount(creditMemo);
		
		File sourceFolder = null;
			sourceFolder = new File(downloadUtil.getDownloadPath());
		if (!sourceFolder.exists()) {
			sourceFolder.mkdirs();
		}
		File file = null;
		String fileStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		file = new File(sourceFolder + File.separator + creditMemo.getDocNumber() + ".pdf");
		FileOutputStream os = new FileOutputStream(file.getAbsolutePath());
		RequestContext.get().getConfigMap().put("mail.template", WebConstants.offline_Credit_Memo);
		Writer out = new StringWriter();
		Map<String, Object> input = new HashMap<String, Object>(1);
		input.put("contextPath", RequestContext.get().getContextPath());
		input.put("credit", creditMemo);
		input.put("moduleName", moduleCredit);
		logger.info("plantMap-->" + purchaseOrderController.plantMap());
		input.put("plantMap", purchaseOrderController.plantMap());
		input.put("taxCodeMap", purchaseOrderController.taxCode());
		input.put("user", getUser());
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		getTemplate().process(input, out);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(out.toString());
		renderer.layout();
		renderer.createPDF(os);
		os.flush();
		os.close();
		return file.getAbsolutePath();
	}

    public String OfflineHtmlStringToPdfForInvGoodsReceipt(String pdfFilePath,InventoryGoodsReceipt inventoryGoodsReceipt) throws TemplateException, IOException, DocumentException {
	
	InventoryGoodsReceipt invGR = inventoryGoodsReceiptService.getListAmount(inventoryGoodsReceipt);
		
		File sourceFolder = null;
			sourceFolder = new File(downloadUtil.getDownloadPath());
		if (!sourceFolder.exists()) {
			sourceFolder.mkdirs();
		}
		File file = null;
		String fileStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		file = new File(sourceFolder + File.separator + inventoryGoodsReceipt.getDocNumber() + ".pdf");
		FileOutputStream os = new FileOutputStream(file.getAbsolutePath());
		RequestContext.get().getConfigMap().put("mail.template", WebConstants.offline_Inv_Goods_Receipt);
		Writer out = new StringWriter();
		Map<String, Object> input = new HashMap<String, Object>(1);
		input.put("contextPath", RequestContext.get().getContextPath());
		input.put("gr", invGR);
		input.put("moduleName", moduleCredit);
		logger.info("plantMap-->" + purchaseOrderController.plantMap());
		input.put("plantMap", purchaseOrderController.plantMap());
		input.put("taxCodeMap", purchaseOrderController.taxCode());
		input.put("user", getUser());
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		getTemplate().process(input, out);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(out.toString());
		renderer.layout();
		renderer.createPDF(os);
		os.flush();
		os.close();
		return file.getAbsolutePath();
	}

    public String OfflineHtmlStringToPdfForInvGoodsIssue(String pdfFilePath,InventoryGoodsIssue inventoryGoodsIssue) throws TemplateException, IOException, DocumentException {
    	
    	InventoryGoodsIssue invGR = inventoryGoodsIssueService.getListAmount(inventoryGoodsIssue);
    		
    		File sourceFolder = null;
    			sourceFolder = new File(downloadUtil.getDownloadPath());
    		if (!sourceFolder.exists()) {
    			sourceFolder.mkdirs();
    		}
    		File file = null;
    		String fileStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    		file = new File(sourceFolder + File.separator + inventoryGoodsIssue.getDocNumber() + ".pdf");
    		FileOutputStream os = new FileOutputStream(file.getAbsolutePath());
    		RequestContext.get().getConfigMap().put("mail.template", WebConstants.offline_Inv_Goods_Issue);
    		Writer out = new StringWriter();
    		Map<String, Object> input = new HashMap<String, Object>(1);
    		input.put("contextPath", RequestContext.get().getContextPath());
    		input.put("gr", invGR);
    		input.put("moduleName", moduleCredit);
    		logger.info("plantMap-->" + purchaseOrderController.plantMap());
    		input.put("plantMap", purchaseOrderController.plantMap());
    		input.put("taxCodeMap", purchaseOrderController.taxCode());
    		input.put("deptMap", deptMap());
    		input.put("user", getUser());
    		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    		getTemplate().process(input, out);
    		ITextRenderer renderer = new ITextRenderer();
    		renderer.setDocumentFromString(out.toString());
    		renderer.layout();
    		renderer.createPDF(os);
    		os.flush();
    		os.close();
    		return file.getAbsolutePath();
    	}
    public Map<Integer, Object> deptMap() {
		return departmentService.findAll().stream().collect(Collectors.toMap(Department::getId, Department::getName));
	}
    
 public String OfflineHtmlStringToPdfForInvGoodsTransfer(String pdfFilePath,InventoryGoodsTransfer inventoryGoodsTransfer) throws TemplateException, IOException, DocumentException {
    	
	 InventoryGoodsTransfer invGR = inventoryGoodsTransferService.getListAmount(inventoryGoodsTransfer);
    		
    		File sourceFolder = null;
    			sourceFolder = new File(downloadUtil.getDownloadPath());
    		if (!sourceFolder.exists()) {
    			sourceFolder.mkdirs();
    		}
    		File file = null;
    		String fileStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    		file = new File(sourceFolder + File.separator + inventoryGoodsTransfer.getDocNumber() + ".pdf");
    		FileOutputStream os = new FileOutputStream(file.getAbsolutePath());
    		RequestContext.get().getConfigMap().put("mail.template", WebConstants.offline_Inv_Goods_Transfer);
    		Writer out = new StringWriter();
    		Map<String, Object> input = new HashMap<String, Object>(1);
    		input.put("contextPath", RequestContext.get().getContextPath());
    		input.put("gr", invGR);
    		input.put("moduleName", moduleCredit);
    		logger.info("plantMap-->" + purchaseOrderController.plantMap());
    		input.put("plantMap", purchaseOrderController.plantMap());
    		input.put("taxCodeMap", purchaseOrderController.taxCode());
    		input.put("deptMap", deptMap());
    		input.put("user", getUser());
    		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    		getTemplate().process(input, out);
    		ITextRenderer renderer = new ITextRenderer();
    		renderer.setDocumentFromString(out.toString());
    		renderer.layout();
    		renderer.createPDF(os);
    		os.flush();
    		os.close();
    		return file.getAbsolutePath();
    	}
	@Override
	protected MimeMessagePreparator createMessage(String mailTo) {
		// TODO Auto-generated method stub
		return null;
	}


	private User getUser() {
	User user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	String logoPath = user.getCompany().getLogo();
	user.getCompany().setLogo(FilePathUtil.convertPath(logoPath));
	return user;
	}
	
    
}
