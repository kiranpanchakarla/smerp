package com.smerp.model.purchase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smerp.model.master.AuditModel;


@Entity
@Table(name="tbl_purchase_purchase_req_list")
public class PurchaseRequestList extends AuditModel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_req_list_id" , nullable = false , unique = true)
	private Integer id;

	
	@Column(name="product_number")
    private String prodouctNumber;
	
	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name="description")
	private String description;
	
	@Column(name="uom")
	private String uom;
	
	@Column(name="required_quantity")
	private Integer requiredQuantity;
	
	@Column(name="product_group")
	private String productGroup;
	
	@Column(name="ware_house")
	private Integer warehouse;
	
	@Column(name="hsn")
	private String hsn;
	
	@Column(name="sac_code")
	private String sacCode;

	@Column(name = "sku_quantity")
	private String sku;
	
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getSacCode() {
		return sacCode;
	}

	public void setSacCode(String sacCode) {
		this.sacCode = sacCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProdouctNumber() {
		return prodouctNumber;
	}

	public void setProdouctNumber(String prodouctNumber) {
		this.prodouctNumber = prodouctNumber;
	}

	public Integer getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Integer warehouse) {
		this.warehouse = warehouse;
	}

	
	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "PurchaseRequestList [id=" + id + ", prodouctNumber=" + prodouctNumber + ", productId=" + productId
				+ ", description=" + description + ", uom=" + uom + ", requiredQuantity=" + requiredQuantity
				+ ", productGroup=" + productGroup + ", warehouse=" + warehouse + ", hsn=" + hsn + ", sacCode="
				+ sacCode + ", sku=" + sku + "]";
	}

	 
	
	
	

}
