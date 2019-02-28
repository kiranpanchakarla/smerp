package com.smerp.model.inventorytransactions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smerp.model.master.AuditModel;

@Entity
@Table(name="tbl_inventory_goods_transfer_list")
public class InventoryGoodsTransferList extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name="product_number")
    private String productNumber;
	
	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name="description")
	private String description;
	
	@Column(name = "from_warehouse")
	private Integer fromWarehouse;
	
	@Column(name = "to_warehouse")
	private Integer toWarehouse;
	
	@Column(name="uom")
	private String uom;
	
	@Column(name="required_quantity")
	private Integer requiredQuantity;
	
	@Column(name="product_group")
	private String productGroup;
	
	
	@Column(name="hsn")
	private String hsn;
	
	@Column(name = "sku_quantity")
	private String sku;
	
	@Column(name = "unit_price")
	private Double unitPrice;
	
	
	@Column(name = "tax_code")
	private Double taxCode;
	
	@Column(name = "tax_description")
	private String taxDescription;
	
	
	private transient Integer tempRequiredQuantity;

	public Integer getFromWarehouse() {
		return fromWarehouse;
	}

	public void setFromWarehouse(Integer fromWarehouse) {
		this.fromWarehouse = fromWarehouse;
	}

	public Integer getToWarehouse() {
		return toWarehouse;
	}

	public void setToWarehouse(Integer toWarehouse) {
		this.toWarehouse = toWarehouse;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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


	public String getHsn() {
		return hsn;
	}

	public void setHsn(String hsn) {
		this.hsn = hsn;
	}

	 

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}


	public Double getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(Double taxCode) {
		this.taxCode = taxCode;
	}

	private transient String taxTotal;

	private transient String total;

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

	public String getTaxDescription() {
		return taxDescription;
	}

	public void setTaxDescription(String taxDescription) {
		this.taxDescription = taxDescription;
	}

	
	
	public Integer getTempRequiredQuantity() {
		return tempRequiredQuantity;
	}

	public void setTempRequiredQuantity(Integer tempRequiredQuantity) {
		this.tempRequiredQuantity = tempRequiredQuantity;
	}

	@Override
	public String toString() {
		return "InventoryGoodsTransferList [id=" + id + ", productNumber=" + productNumber + ", productId=" + productId
				+ ", description=" + description + ", fromWarehouse=" + fromWarehouse + ", toWarehouse=" + toWarehouse
				+ ", uom=" + uom + ", requiredQuantity=" + requiredQuantity + ", productGroup=" + productGroup
				+ ", hsn=" + hsn + ", sku=" + sku + ", unitPrice=" + unitPrice + ", taxCode=" + taxCode
				+ ", taxDescription=" + taxDescription + "]";
	}
	
	
	
}
