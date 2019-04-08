package com.smerp.model.inventory;

public class MinimumQuantityList {

	private String productNumber;
	private String productName;
	private String warehouse;
	private Integer minQty;
	private Double inStock;
	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public Integer getMinQty() {
		return minQty;
	}
	public void setMinQty(Integer minQty) {
		this.minQty = minQty;
	}
	public Double getInStock() {
		return inStock;
	}
	public void setInStock(Double inStock) {
		this.inStock = inStock;
	}
	@Override
	public String toString() {
		return "MinimumQuantityList [productNumber=" + productNumber + ", productName=" + productName + ", warehouse="
				+ warehouse + ", minQty=" + minQty + ", inStock=" + inStock + "]";
	}
	
	
}
