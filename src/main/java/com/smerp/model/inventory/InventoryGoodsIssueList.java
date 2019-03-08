package com.smerp.model.inventory;

public class InventoryGoodsIssueList {

	private Integer docId;
	private String docNumber;
	private String docDate;
	private Integer productId;
	private String productNumber;
	private String productDescription;
	private String productGroup;
	private String uomName;
	private Integer requiredQty;
	private Integer departmentId;
	private String departmentName;
	private String remarks;
	private Integer warehouseId;
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public String getDocNumber() {
		return docNumber;
	}
	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}
	public String getDocDate() {
		return docDate;
	}
	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductGroup() {
		return productGroup;
	}
	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	public Integer getRequiredQty() {
		return requiredQty;
	}
	public void setRequiredQty(Integer requiredQty) {
		this.requiredQty = requiredQty;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	@Override
	public String toString() {
		return "InventoryGoodsIssueList [docId=" + docId + ", docNumber=" + docNumber + ", docDate=" + docDate
				+ ", productId=" + productId + ", productNumber=" + productNumber + ", productDescription="
				+ productDescription + ", productGroup=" + productGroup + ", uomName=" + uomName + ", requiredQty="
				+ requiredQty + ", departmentId=" + departmentId + ", departmentName=" + departmentName + ", remarks="
				+ remarks + ", warehouseId=" + warehouseId + "]";
	}
	
	 
	
	
}
