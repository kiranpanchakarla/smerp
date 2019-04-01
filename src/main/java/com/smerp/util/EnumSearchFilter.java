package com.smerp.util;

public enum EnumSearchFilter {
	
	PRTABLE("PurchaseRequest"),
	RFQTABLE("RequestForQuotation"),
	POTABLE("PurchaseOrder"),
	GRTABLE("GoodsReceipt"),
	GRETABLE("GoodsReturn"),
	INVTABLE("InVoice"),
	CMTABLE("CreditMemo"),
	INVGR("InventoryGoodsReceipt"),
	INVGI("InventoryGoodsIssue"),
	INVGT("InventoryGoodsTransfer"),
	CREATEDDATE("createdDate"),
	BETWEEN("between"),
	STATUS("status"),
	MULTIAPPORVEDTABLES("PurchaseRequest,GoodsReceipt,GoodsReturn,InventoryGoodsReceipt,InventoryGoodsIssue,InventoryGoodsTransfer"),
	AND("and");
	

	private String status;
	
	private EnumSearchFilter(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	
}
