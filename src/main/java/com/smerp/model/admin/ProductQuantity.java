package com.smerp.model.admin;


public class ProductQuantity {

	private   String  productName = null;
	private   Integer  warehouseCode;
	private  String  warehouseName = null;
	private   Integer  inStock;
	private  Integer  ordered;
	private Integer poToGr;
	private Integer directGr;
	
	public Integer getPoToGr() {
		return poToGr;
	}
	public void setPoToGr(Integer poToGr) {
		this.poToGr = poToGr;
	}
	public Integer getDirectGr() {
		return directGr;
	}
	public void setDirectGr(Integer directGr) {
		this.directGr = directGr;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(Integer warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public Integer getInStock() {
		return inStock;
	}
	public void setInStock(Integer inStock) {
		this.inStock = inStock;
	}
	public Integer getOrdered() {
		return ordered;
	}
	public void setOrdered(Integer ordered) {
		this.ordered = ordered;
	}
	@Override
	public String toString() {
		return "ProductQuantity [productName=" + productName + ", warehouseCode=" + warehouseCode + ", warehouseName="
				+ warehouseName + ", inStock=" + inStock + ", ordered=" + ordered + ", poToGr=" + poToGr + ", directGr="
				+ directGr + "]";
	}
	 
	
	
	 

	 

	 

	 
	 
	 
}
