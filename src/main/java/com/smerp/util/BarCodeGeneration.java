package com.smerp.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code128.Code128Constants;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Component;

@Component
public class BarCodeGeneration {
	
	private static final Logger logger = LogManager.getLogger(BarCodeGeneration.class);
	
	public String downloadbarcodeImpge(String message, String barcodePath) throws IOException {
		Code128Bean barcode128Bean = new Code128Bean();
		barcode128Bean.setCodeset(Code128Constants.CODESET_B);
		final int dpi = 100;
		//barcode128Bean.setCodeset(Code128Constants.CODESET_C);
		barcode128Bean.setModuleWidth(1.0);
		barcode128Bean.setBarHeight(40.0);
		barcode128Bean.setFontSize(10.0);
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
}
