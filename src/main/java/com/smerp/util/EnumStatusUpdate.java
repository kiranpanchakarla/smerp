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
	CREDITMEMO("Credit Memo"),
	INVOICE("Invoiced"),
	CLOSED("Closed"),
	APPROVAL("Approved-Rejected"),
	PRODUCTQTY("Product Quantity"),
	DASHBOARD("DashBoard"),
	CREATE("Create"),
	CREATED("Created"),
	UPDATE("Update"),
	UPDATED("Updated"),
	DRAFTED("Drafted"),
	APPROVE("Approve"),
	REJECT("Reject"),
	SAVED("Saved"),
	CANCEL("Cancel"),
	PDFDOWNLOAD("PDF Downloaded"),
	
	PR("PR"),
	RFQ("RFQ"),
	PO("PO"),
	GR("GR"),
	GRE("GRE"),
	INV("INV"),
	CM("CM"),
	INVGR("INVGR"),
	INVGI("INVGI"),
	INVGT("INVGT"),
	P("P"),
	V("V"),
	PG("PG"),
	PGP("PGP");
	

	
	
	private String status;

	EnumStatusUpdate(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
