package com.smerp.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smerp.model.inventory.RequestForQuotation;

public class GenerateDocNumber {
	
	private static final Logger logger = LogManager.getLogger(GenerateDocNumber.class);
	
	public static RequestForQuotation documentNumberGenerationNotInDB(RequestForQuotation rfqdetails) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String[] parts2 = dateFormat.format(date).split("/");
		String type = "RFQ";
		String[] parts1 = parts2[2].split(" ");
		String prefix = type.concat(parts2[0]).concat(parts2[1]).concat(parts1[0]);
		String suffix = "1";
		String docNumber = prefix + suffix;
		logger.info("docNumber-->" + docNumber);
		rfqdetails.setDocNumber(docNumber);
		return rfqdetails;
	}
	
	public static  String documentNumberGeneration(String documentNumber) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String[] parts2 = dateFormat.format(date).split("/");
		String type = "RFQ";
		String[] parts1 = parts2[2].split(" ");
		String prefix = type.concat(parts2[0]).concat(parts2[1]).concat(parts1[0]);

		int inc_number = Integer.parseInt(documentNumber.substring(11)) + 1;
		String docNumber = prefix + inc_number;
		logger.info("docNumber"+docNumber);
		return docNumber;
	}
	
}
