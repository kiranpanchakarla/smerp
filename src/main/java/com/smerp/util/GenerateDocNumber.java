package com.smerp.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenerateDocNumber {
	
	private static final Logger logger = LogManager.getLogger(GenerateDocNumber.class);
	
	/*public static RequestForQuotation documentNumberGenerationNotInDB(RequestForQuotation rfqdetails) {
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
	}*/
	
	
	
	public  static String documentNumberGeneration(String olddocumentNumber){
        Pattern pattern = Pattern.compile("(?<=\\D)(?=\\d)", Pattern.CASE_INSENSITIVE);
        String[] result = pattern.split(olddocumentNumber);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
          LocalDateTime now = LocalDateTime.now();
        String newOrderId = result[0]+(String)dtf.format(now)+(Long.parseLong(result[1].substring(8))+1);
    return newOrderId;
  }
	
	
	
	
	public static String autoGenereater(String type, String docVal) {
        String res;
        if(type.equalsIgnoreCase(""+EnumStatusUpdate.V)  &&  docVal.equalsIgnoreCase("")) {
            return "V0001";
        }  else if(type.equalsIgnoreCase(""+EnumStatusUpdate.PG)  &&  docVal.equalsIgnoreCase("")) {
            return "PG0001";
        }else if(type.startsWith(""+EnumStatusUpdate.PGP)  &&  docVal.length()<7) {
            return docVal+"P0001";
        } else {
            String splitFirst;
            if(type.startsWith(""+EnumStatusUpdate.PGP)) {
                splitFirst = docVal.substring(5);
            }else {
                splitFirst = docVal;
            }
            Pattern pattern = Pattern.compile("(?<=\\D)(?=\\d)", Pattern.CASE_INSENSITIVE);
            String[] result = pattern.split(splitFirst);
            long num = Long.parseLong(result[1])+1;
                if(num<10) {
                    res = "000"+num;
                } else if(num<100) {
                    res = "00"+num;
                } else if(num<1000) {
                    res = "0"+num;
                } else {
                    res = String.valueOf(num);
                }
            if(type.startsWith(""+EnumStatusUpdate.PGP)) {
                res = docVal.substring(0,5) + result[0] + res;
            }else {
                res = result[0] + res;
            }
        }
        return res;
    } 
	
	public static void main(String[] args) {
		System.out.println(autoGenereater("PGP","PG0001P0001"));
	}
	
}
