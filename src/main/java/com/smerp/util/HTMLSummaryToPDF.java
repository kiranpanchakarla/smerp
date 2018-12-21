package com.smerp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.smerp.controller.purchase.PurchaseRequestController;
import com.smerp.email.EmailerGenerator;
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




	@Override
	protected MimeMessagePreparator createMessage(String mailTo) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
