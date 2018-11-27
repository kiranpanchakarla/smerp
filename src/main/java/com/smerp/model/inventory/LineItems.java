package com.smerp.model.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smerp.model.master.AuditModel;

@Entity
@Table(name = "tbl_admin_rfq_lineitems")
public class LineItems extends AuditModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lineitem_id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "product_id")
	private Integer productId;

	/*
	 * @Column(name="product_name") private String productName;
	 */

	@Column(name = "product_number")
	private String prodouctNumber;

	@Column(name = "description")
	private String description;

	@Column(name = "uom")
	private String uom;

	/*
	 * @Column(name="quantity") private Integer quantity;
	 */

	@Column(name = "required_quantity")
	private Integer requiredQuantity;

	@Column(name = "product_group")
	private String productGroup;

	@Column(name = "warehouse")
	private String warehouse;

	
	
	@Column(name="hsn")
    private String hsn;
    
    @Column(name="sac_code")
    private String sacCode;

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	

	public Integer getId() {
		return id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getRequiredQuantity() {
		return requiredQuantity;
	}

	public void setRequiredQuantity(Integer requiredQuantity) {
		this.requiredQuantity = requiredQuantity;
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

	@Override
	public String toString() {
		return "LineItems [id=" + id + ", productId=" + productId + ", prodouctNumber=" + prodouctNumber
				+ ", description=" + description + ", uom=" + uom + ", requiredQuantity=" + requiredQuantity
				+ ", productGroup=" + productGroup + ", warehouse=" + warehouse + ", hsn=" + hsn + ", sacCode="
				+ sacCode + "]";
	}

	

	

}
