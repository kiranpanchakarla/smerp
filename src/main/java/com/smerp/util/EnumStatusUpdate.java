package com.smerp.util;

public enum EnumStatusUpdate {

	OPEN("Open"), 
	CANCELED("Cancelled"),
	DRAFT("Draft"),
	APPROVEED("Approved"),
	CONVERTPRTORFQ("ConvertedToRFQ"),
	CONVERTRFQTOPO("ConvertedToPO"),
	COMPLETED("Completed"),
	REJECTED("Rejected"),
	PARTIALLY_RECEIVED("Partially_Received"),
	GOODS_RETURN("Goods_Return"),
	CLOSED("Closed");
	

	
	
	private String status;

	EnumStatusUpdate(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
