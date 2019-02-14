package com.smerp.util;

public class UnitPriceListItems {

	public static Double getTaxAmt(Integer quantity,double unitPrice,double taxCode) {
		return (double) ((quantity * unitPrice) * taxCode / 100);
	}

	public static Double getTotalAmt(Integer quantity,double unitPrice, double taxCode) {
		Double totalValue = (double) ((quantity * unitPrice) + ((quantity * unitPrice) * taxCode /100));
		return totalValue;
	}

	public static Double getTotalPaymentAmt(Double totalAmt, Double discount, Double freight) {
		Double tax_amt = discount / 100;
		Double totalValue =  (totalAmt - (totalAmt * tax_amt)) + freight;
		return totalValue;
	}
	
	public static String getRoundingValue(Double number) {
		//String formatedValue = String.format("%,.2f", number);
		 int intVal = (int)Math.ceil(number);
		String formatedValue = ""+ intVal;
		return formatedValue;
	}
	
	
	

}
