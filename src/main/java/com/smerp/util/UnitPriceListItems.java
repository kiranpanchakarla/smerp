package com.smerp.util;

public class UnitPriceListItems {

	public static Double getTaxAmt(Integer quantity,double unitPrice,double taxCode) {
		return (double) ((quantity * unitPrice) * taxCode / 100);
	}

	public static Double getTotalAmt(Integer quantity,double unitPrice, double taxCode) {
		Double totalValue = (double) ((quantity * unitPrice) + ((quantity * unitPrice) * taxCode /100));
		return totalValue;
	}
	
	public static Double getTotalINVAmt(Integer quantity,double unitPrice) {
		Double totalValue = (double) (quantity * unitPrice);
		return totalValue;
	}

	public static Double getTotalPaymentAmt(Double totalAmt, Double discount, Double freight ) {
		Double disc_amt = new Double(0.00);
		Double totalValue  = new Double(0.00);
		Double freight_amt = new Double(0.00);
		if(discount != null) {
			disc_amt = discount / 100;
		}
		totalValue =  (totalAmt - (totalAmt * disc_amt)) + (freight_amt);		
		return totalValue;
	}
	
	public static Double getTotalAmtPayment(Double totalAmt, Double discount, Double freight,Double taxTotal ) {
		Double disc_amt = new Double(0.00);
		Double totalValue  = new Double(0.00);
		 
		if(discount != null) {
			disc_amt = discount / 100;
		}
		totalValue =  (totalAmt - (totalAmt * disc_amt) + (freight != null ? freight : new Double(0.0)) + (taxTotal != null ? taxTotal : new Double(0.0)));	
		return totalValue;
	}
	
	public static String getRoundingValue(Double number) {
		//String formatedValue = String.format("%,.2f", number);
		 int intVal = (int)Math.ceil(number);
		String formatedValue = ""+ intVal;
		return formatedValue;
	}
	
	
	

}
