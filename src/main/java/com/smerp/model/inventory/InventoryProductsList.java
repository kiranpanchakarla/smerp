package com.smerp.model.inventory;

public class InventoryProductsList {

	private Integer productNo;
	private String productName;
	private Integer plantId;
	private String plantName;
	private String productDescription;
	private String productGroupDescription;
	private String uomName;
	private Double instockQty;
	public Integer getProductNo() {
		return productNo;
	}
	public void setProductNo(Integer productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getPlantId() {
		return plantId;
	}
	public void setPlantId(Integer plantId) {
		this.plantId = plantId;
	}
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductGroupDescription() {
		return productGroupDescription;
	}
	public void setProductGroupDescription(String productGroupDescription) {
		this.productGroupDescription = productGroupDescription;
	}
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	public Double getInstockQty() {
		return instockQty;
	}
	public void setInstockQty(Double instockQty) {
		this.instockQty = instockQty;
	}
	
	@Override
	public String toString() {
		return "InventoryProductsList [productNo=" + productNo + ", productName=" + productName + ", plantId=" + plantId
				+ ", plantName=" + plantName + ", productDescription=" + productDescription
				+ ", productGroupDescription=" + productGroupDescription + ", uomName=" + uomName + ", instockQty="
				+ instockQty + "]";
	}
	
	
	
}
