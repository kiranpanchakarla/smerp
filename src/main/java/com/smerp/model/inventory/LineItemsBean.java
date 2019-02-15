package com.smerp.model.inventory;

public class LineItemsBean {

	private Integer id;

	private Integer productId;

	private String prodouctNumber;

	private String description;

	private String uom;

	private Integer requiredQuantity;

	private String productGroup;

	private Integer warehouse;

	private String hsn;

	private String sacCode;

	private Double unitPrice;

	private Double taxCode;

	private String sku;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	private transient Integer tempRequiredQuantity;

	private transient String taxTotal;

	private transient String total;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProdouctNumber() {
		return prodouctNumber;
	}

	public void setProdouctNumber(String prodouctNumber) {
		this.prodouctNumber = prodouctNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public Integer getRequiredQuantity() {
		return requiredQuantity;
	}

	public void setRequiredQuantity(Integer requiredQuantity) {
		this.requiredQuantity = requiredQuantity;
	}

	public String getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}

	public Integer getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Integer warehouse) {
		this.warehouse = warehouse;
	}

	public String getHsn() {
		return hsn;
	}

	public void setHsn(String hsn) {
		this.hsn = hsn;
	}

	public String getSacCode() {
		return sacCode;
	}

	public void setSacCode(String sacCode) {
		this.sacCode = sacCode;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(Double taxCode) {
		this.taxCode = taxCode;
	}

	public String getTaxTotal() {
		return taxTotal;
	}

	public void setTaxTotal(String taxTotal) {
		this.taxTotal = taxTotal;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Integer getTempRequiredQuantity() {
		return tempRequiredQuantity;
	}

	public void setTempRequiredQuantity(Integer tempRequiredQuantity) {
		this.tempRequiredQuantity = tempRequiredQuantity;
	}

	@Override
	public String toString() {
		return "InVoiceLineItems [id=" + id + ", productId=" + productId + ", prodouctNumber=" + prodouctNumber
				+ ", description=" + description + ", uom=" + uom + ", requiredQuantity=" + requiredQuantity
				+ ", productGroup=" + productGroup + ", warehouse=" + warehouse + ", hsn=" + hsn + ", sacCode="
				+ sacCode + ", unitPrice=" + unitPrice + ", taxCode=" + taxCode + ", sku=" + sku + "]";
	}
	
}
