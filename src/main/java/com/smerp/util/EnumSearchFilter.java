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
	CREATEDAT("createdAt"),
	BETWEEN("between"),
	STATUS("status"),
	AND("and");
	

	private String status;
	
	private EnumSearchFilter(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	
}
