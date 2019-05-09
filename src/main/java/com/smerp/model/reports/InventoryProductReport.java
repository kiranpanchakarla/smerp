package com.smerp.model.reports;

public class InventoryProductReport {

	
	private  Integer productId;
	private  String productName;
	private  String description;
	private  String productGroup;
	private  String uomName;
	private  String plantName;
	private Integer plantId;
	private Double inStockQty;
	private Double productCost;
	private Double stockValue;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	public Integer getPlantId() {
		return plantId;
	}
	public void setPlantId(Integer plantId) {
		this.plantId = plantId;
	}
	public Double getInStockQty() {
		return inStockQty;
	}
	public void setInStockQty(Double inStockQty) {
		this.inStockQty = inStockQty;
	}
	public Double getProductCost() {
		return productCost;
	}
	public void setProductCost(Double productCost) {
		this.productCost = productCost;
	}
	public Double getStockValue() {
		return stockValue;
	}
	public void setStockValue(Double stockValue) {
		this.stockValue = stockValue;
	}
	
	
}
