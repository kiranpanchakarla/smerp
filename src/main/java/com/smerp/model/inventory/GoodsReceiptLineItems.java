package com.smerp.model.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smerp.model.master.UserAuditModel;

@Entity
@Table(name = "tbl_goods_receipt_lineitems")
public class GoodsReceiptLineItems extends UserAuditModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "product_id")
	private Integer productId;

	@Column(name = "product_number")
	private String prodouctNumber;

	@Column(name = "description")
	private String description;

	@Column(name = "uom")
	private String uom;

	@Column(name = "required_quantity")
	private Integer requiredQuantity;

	@Column(name = "product_group")
	private String productGroup;

	@Column(name = "warehouse")
	private Integer warehouse;

	@Column(name = "hsn")
	private String hsn;

	@Column(name = "sac_code")
	private String sacCode;

	@Column(name = "unit_price")
	private Double unitPrice;

	@Column(name = "tax_code")
	private Double taxCode;
	
	@Column(name = "sku_quantity")
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
		return "GoodsReceiptLineItems [id=" + id + ", productId=" + productId + ", prodouctNumber=" + prodouctNumber
				+ ", description=" + description + ", uom=" + uom + ", requiredQuantity=" + requiredQuantity
				+ ", productGroup=" + productGroup + ", warehouse=" + warehouse + ", hsn=" + hsn + ", sacCode="
				+ sacCode + ", unitPrice=" + unitPrice + ", taxCode=" + taxCode + ", sku=" + sku
				+ ", tempRequiredQuantity=" + tempRequiredQuantity + ", taxTotal=" + taxTotal + ", total=" + total
				+ "]";
	}

	

	 

}
