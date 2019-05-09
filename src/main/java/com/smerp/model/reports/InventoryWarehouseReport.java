package com.smerp.model.reports;

public class InventoryWarehouseReport {

	private  Integer productId;
	private  String productName;
	private  String description;
	private  String productGroup;
	private  String uomName;
	private Double inStockQty;
	private Double madurawada;
	private Double yelamanchili;
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
	public Double getInStockQty() {
		return inStockQty;
	}
	public void setInStockQty(Double inStockQty) {
		this.inStockQty = inStockQty;
	}
	public Double getMadurawada() {
		return madurawada;
	}
	public void setMadurawada(Double madurawada) {
		this.madurawada = madurawada;
	}
	public Double getYelamanchili() {
		return yelamanchili;
	}
	public void setYelamanchili(Double yelamanchili) {
		this.yelamanchili = yelamanchili;
	}
	
}
