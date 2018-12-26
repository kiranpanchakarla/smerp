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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.smerp.controller.purchase.PurchaseOrderController;
import com.smerp.controller.purchase.PurchaseRequestController;
import com.smerp.controller.purchase.RequestForQuotationController;
import com.smerp.email.EmailerGenerator;
import com.smerp.model.inventory.PurchaseOrder;
import com.smerp.model.inventory.PurchaseOrderLineItems;
import com.smerp.model.inventory.RequestForQuotation;
import com.smerp.model.purchase.PurchaseRequest;

import freemarker.template.TemplateException;


@Component
public class HTMLSummaryToPDF extends EmailerGenerator {

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
	}

	public String OfflineHtmlStringToPdfForPurchaseReq(String pdfFilePath,PurchaseRequest purchaseRequest) throws TemplateException, IOException, DocumentException {
		File sourceFolder = null;
			sourceFolder = new File(downloadUtil.getDownloadPath());
		if (!sourceFolder.exists()) {
			sourceFolder.mkdirs();
		}
		File file = null;
		String fileStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		file = new File(sourceFolder + File.separator + "prView" + fileStr + ".pdf");
		
		FileOutputStream os = new FileOutputStream(file.getAbsolutePath());
		RequestContext.get().getConfigMap().put("mail.template", WebConstants.Offline_Purchase_Request);
		Writer out = new StringWriter();
		Map<String, Object> input = new HashMap<String, Object>(1);
		input.put("contextPath", RequestContext.get().getContextPath());
		input.put("pr", purchaseRequest);
		System.out.println("plantMap-->" + purchaseRequestController.plantMap());
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
		file = new File(sourceFolder + File.separator + "rfqView" + fileStr + ".pdf");
		
		FileOutputStream os = new FileOutputStream(file.getAbsolutePath());
		RequestContext.get().getConfigMap().put("mail.template", WebConstants.Offline_Request_For_Quotation);
		Writer out = new StringWriter();
		Map<String, Object> input = new HashMap<String, Object>(1);
		input.put("contextPath", RequestContext.get().getContextPath());
		input.put("rfq", forQuotation);
		System.out.println("plantMap-->" + rfqController.plantMap());
		input.put("plantMap", rfqController.plantMap());
		
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
		try {
			List<PurchaseOrderLineItems> listItems = purchaseOrder.getPurchaseOrderlineItems();
			Double addAmt=0.0;
			Double addTaxAmt=0.0;
			if (listItems != null) {
				for (int i = 0; i < listItems.size(); i++) {
					PurchaseOrderLineItems polist = listItems.get(i);
					if(polist.getUnitPrice()!=null) {
					addTaxAmt += UnitPriceListItems.getTaxAmt(polist.getRequiredQuantity(),polist.getUnitPrice(),polist.getTaxCode());
					addAmt +=UnitPriceListItems.getTotalAmt(polist.getRequiredQuantity(),polist.getUnitPrice(), polist.getTaxCode());
					polist.setTaxTotal(""+UnitPriceListItems.getTaxAmt(polist.getRequiredQuantity(),polist.getUnitPrice(),polist.getTaxCode()));
					polist.setTotal(""+UnitPriceListItems.getTotalAmt(polist.getRequiredQuantity(),polist.getUnitPrice(), polist.getTaxCode()));
					}else {
					polist.setTaxTotal("");
					polist.setTotal("");	
					}
				}
				purchaseOrder.setPurchaseOrderlineItems(listItems);
			}
			purchaseOrder.setTotalBeforeDisAmt(addAmt);
			purchaseOrder.setTaxAmt(""+addTaxAmt);
			if(purchaseOrder.getTotalPayment()!=null) {
			purchaseOrder.setAmtRounding(""+purchaseOrder.getTotalPayment());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		File sourceFolder = null;
			sourceFolder = new File(downloadUtil.getDownloadPath());
		if (!sourceFolder.exists()) {
			sourceFolder.mkdirs();
		}
		File file = null;
		String fileStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		file = new File(sourceFolder + File.separator + "POView" + fileStr + ".pdf");
		
		
		
		FileOutputStream os = new FileOutputStream(file.getAbsolutePath());
		RequestContext.get().getConfigMap().put("mail.template", WebConstants.Offline_Purchase_Order);
		Writer out = new StringWriter();
		Map<String, Object> input = new HashMap<String, Object>(1);
		input.put("contextPath", RequestContext.get().getContextPath());
		input.put("po", purchaseOrder);
		System.out.println("plantMap-->" + purchaseOrderController.plantMap());
		input.put("plantMap", purchaseOrderController.plantMap());
		input.put("taxCodeMap", purchaseOrderController.taxCode());
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

	@Override
	protected MimeMessagePreparator createPRMessage(PurchaseRequest purchaseRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected MimeMessagePreparator createRFQMessage(RequestForQuotation requestForQuotation) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
