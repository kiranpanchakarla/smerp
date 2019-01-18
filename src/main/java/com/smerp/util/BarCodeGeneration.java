package com.smerp.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code128.Code128Constants;
import org.krysalis.barcode4j.impl.upcean.EAN8Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Element;

@Component
public class BarCodeGeneration {
	
	private static final Logger logger = LogManager.getLogger(BarCodeGeneration.class);
	
	public String downloadbarcodeImpge(String message, String barcodePath) throws IOException {
		Code128Bean barcode128Bean = new Code128Bean();
		barcode128Bean.setCodeset(Code128Constants.CODESET_B);
		final int dpi = 100;
		//barcode128Bean.setCodeset(Code128Constants.CODESET_C);
		barcode128Bean.setModuleWidth(0.8);
		barcode128Bean.setBarHeight(15.0);
		barcode128Bean.setFontSize(4.0);
		barcode128Bean.setQuietZone(10.0);
		barcode128Bean.doQuietZone(true);
		File f = new File(barcodePath);
		if (!f.exists()) {
			f.mkdirs();
		}
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		File outputFile = new File(f + File.separator + timeStamp + ".png");
		OutputStream out = new FileOutputStream(outputFile);
		String rpath = barcodePath+ File.separator + timeStamp + ".png";
		rpath = rpath.substring(3);
		
		logger.info("Bar Code file path is:"+rpath);
		try {
			BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(out, "image/x-png", dpi,
					BufferedImage.TYPE_BYTE_BINARY, false, 0);
			barcode128Bean.generateBarcode(canvasProvider, message);
			canvasProvider.finish();
		
		}catch(Exception e) {
			System.out.println("Exe.."+e);
			logger.info("Error in BarCodeGeneration"+e);
		}
		finally {
			out.close();
		}
		return rpath;
	}
	
	
	public void downloadbarcodeImpge(List<String> msgList, String barcodePath) throws IOException {
		Code128Bean barcode128Bean = new Code128Bean();
		barcode128Bean.setCodeset(Code128Constants.CODESET_B);
		final int dpi = 100;
		//barcode128Bean.setCodeset(Code128Constants.CODESET_C);
		barcode128Bean.setModuleWidth(1.0);
		barcode128Bean.setBarHeight(40.0);
		barcode128Bean.setFontSize(10.0);
		barcode128Bean.setQuietZone(1.0);
		barcode128Bean.doQuietZone(true);
		File f = new File(barcodePath);
		if (!f.exists()) {
			f.mkdirs();
		}
		
		for(int i=1;i<=msgList.size();i++) {
			String message = msgList.get(i-1);
			File outputFile = new File(f + File.separator + i + ".png");
			OutputStream out = new FileOutputStream(outputFile);
			String rpath = barcodePath+ File.separator + i + ".png";
			rpath = rpath.substring(3);
			
			logger.info("Bar Code file path is:"+rpath);
			try {
				BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(out, "image/x-png", dpi,
						BufferedImage.TYPE_BYTE_BINARY, false, 0);
				barcode128Bean.generateBarcode(canvasProvider, message);
				canvasProvider.finish();
			
			}catch(Exception e) {
				System.out.println("Exe.."+e);
				logger.info("Error in BarCodeGeneration"+e);
			}
			finally {
				out.close();
			}
		}
		
	}
	
	
	public void barcodeImgstoPDF(List<String> msgList, String barcodePath) throws IOException, DocumentException {
		Document document = new Document(PageSize.A4, 20, 20, 30, 30);
		File f = new File(barcodePath);
		if (!f.exists()) {
			f.mkdirs();
		}
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		File outputFile = new File(f + File.separator + "BarCode" + timeStamp + ".pdf");
	    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFile));
	   
	    document.open();
	    
	    PdfPTable table = new PdfPTable(3);
	    table.getDefaultCell().setFixedHeight(60.0f);
	   // table.getDefaultCell().setBorderWidth(50.0f);
	    table.setHorizontalAlignment(Element.ALIGN_CENTER);
	    
	    PdfContentByte cb = writer.getDirectContent();
	  
	    for(int i=1;i<msgList.size();i++) {
	    String message = msgList.get(i-1);
		    Barcode128 barcode128 = new Barcode128();
		    barcode128.setCode(message);
		    barcode128.setCodeType(Barcode.CODE128);
		    Image code128Image = barcode128.createImageWithBarcode(cb, null, null);  //BaseColor.BLUE
		    
		    table.getDefaultCell().setPadding(10);
		    table.addCell(code128Image);
	    }
	    document.add(table);
	    document.close();
	}
	
	
}
