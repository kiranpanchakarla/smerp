package com.smerp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.DataFormatException;

import org.springframework.expression.ParseException;

public class DateFormat {
	
	public static final String PMS_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	
	public static final String APP_DATE_FORMAT = "dd/MM/yyyy";

	public static final String NOTE_DATE_FORMAT = "MMMM dd, yyyy";

	public static synchronized Date StringToDate(String inString)
			   throws DataFormatException {
		SimpleDateFormat[] formats = new SimpleDateFormat[2];
		formats[0] = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		formats[1] = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		formats[2] = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		formats[3] = new SimpleDateFormat("MM-dd-yyyy HH:mm");
		formats[4] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formats[5] = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		formats[6] = new SimpleDateFormat("dd/MM/yyyy");
		formats[7] = new SimpleDateFormat("dd-MM-yyyy");
		if (inString == null || inString.equals("")) {
			return null;
		}
		if (inString.length() < 11) {
			inString = inString + " 00:00:00";
		}
		for (int i = 0; i < formats.length; i++) {
			try {
				Date date = formats[i].parse(inString);
				int year = formats[i].getCalendar().get(Calendar.YEAR);
				/** Based on the Oracle docs, the valid date range for
					8i and above is: from 1/1/4712 BC to 12/31/9999 AC.
					So we set our meaningful year range as following. **/
				if (year > 9999 || year <= 0) {
					throw new ParseException(0, "Year out of range");
				}
				return date;
			} catch (Exception e) {
				continue;
			}
		}
		throw new DataFormatException("Input data " + inString
				+ " is not a valid date format");
	}
	
	 public static synchronized Date stringToDate(String inString, String format) throws DataFormatException {		 
		 SimpleDateFormat sdf = new SimpleDateFormat(format);     
	      try {
	         if (inString == null || inString.equals("")) {
	            return null;
	         }
	         return sdf.parse(inString);
	      } catch (java.text.ParseException e) {
	         throw new DataFormatException(
	            "DataFormat.stringToDate for Format : " + format + " Error:" + e);
	      }
	 }
	 
	 public static synchronized Date stringToAppDate(String inString) throws DataFormatException {		 
		 SimpleDateFormat sdf = new SimpleDateFormat(DateFormat.APP_DATE_FORMAT);     
	      try {
	         if (inString == null || inString.equals("")) {
	            return null;
	         }
	         return sdf.parse(inString);
	      } catch (java.text.ParseException e) {
	         throw new DataFormatException(
	            "DataFormat.stringToDate for Format : " + DateFormat.APP_DATE_FORMAT + " Error:" + e);
	      }
	 }
	 
	 public final static synchronized String dateToString(Date inDate,String format) {
	      SimpleDateFormat sdf = new SimpleDateFormat(format);
	      String dateS = "";
	      try {
	         return sdf.format(inDate);
	      } catch (Exception e) {
	         return dateS;
	      }

	 }
	 
	 public final static synchronized String updateFormat(String source,String fromFormat, String toFormat){
		Date fromDate = null;
		if(source == null || "".equals(source)){
			return source;
		}
		try {
			fromDate = stringToDate(source,fromFormat);
			return dateToString(fromDate,toFormat);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	 }
	 
	 public final static synchronized int daysBetween(Date d1, Date d2){
	        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	 }

	 public static String dateToStringFormat(Date convertDate){
	        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	        String dateToString = format1.format(convertDate);
	        return dateToString;
	 }
	 
	 public static Date formatDateTime(String dateString, String dateFormat) {
			SimpleDateFormat format1 = new SimpleDateFormat(dateFormat);
			Date date;
			try {
				date = format1.parse(dateString);
				return date;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
		
	public static String dateToString(Date convertDate){
	        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	        String dateToString = format1.format(convertDate);
	        return dateToString;
	}

	public static String todayDateInString() {
		Date todayDate = new Date();
		return DateFormat.dateToString(todayDate);
	}
	
	public static String getTime() {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}
	
	public static Integer getPreviousMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		return month;
	}
	
public static Integer getMonthFromDate(Date date)    {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	int month = cal.get(Calendar.MONTH);
	return month;

    }
}
	
	
	 

