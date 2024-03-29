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
	WHERE("where"),
	DATE("created_date"),
	PLANT("plant_name"),
	IN("in"),
	MULTIAPPORVEDTABLES("PurchaseRequest,GoodsReceipt,GoodsReturn,InventoryGoodsReceipt,InventoryGoodsIssue,InventoryGoodsTransfer"),
	AND("and"),
	REQUESTERNAME("requesterName"),
	REQUESTERFNAME("requesterFName"),
	REQUESTERLNAME("requesterLName"),
	SECONDLEVELENABLE("secondLevelEnable"),
	TRUE("true"),
	SELECT("select"),
	POREPORTVENDOR("vw_vendor_wise_po_report"),
	INVREPORTVENDOR("vw_vendor_wise_inv_report"),
	POREPORTPRODUCT("vw_product_wise_po_report"),
	INVREPORTPRODUCT("vw_product_wise_inv_report"),
	POREPORTPLANT("vw_plant_wise_po_report"),
	INVREPORTPLANT("vw_plant_wise_inv_report"),
	INVPRODUCTREPORT("vw_inv_stock_qty_report"),
	INVPLANTREPORT("vw_inv_warehouse_report"),
	INVGIREPORT("vw_inventory_goods_issue_report");
	

	private String status;
	
	private EnumSearchFilter(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	
}
