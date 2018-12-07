package com.smerp.util;

public enum EnumStatusUpdate {

	OPEN("Open"), 
	CANCELED("Canceled"),
	DRAFT("Draft"),
	APPROVEED("Approved"),
	CONVERTPRTORFQ("ConvertedToRFQ"),
	COMPLETED("Completed"),
	REJECTED("Rejected");

	private String status;

	EnumStatusUpdate(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
