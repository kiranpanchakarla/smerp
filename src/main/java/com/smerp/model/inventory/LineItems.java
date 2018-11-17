package com.smerp.model.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_admin_rfq_lineitems")
public class LineItems {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="lineitem_id" , nullable = false, unique = true)
	private Integer id;

	@Column(name="productId")
	private Integer productId;
	
	@Column(name="productName")
	private String productName;
	
	@Column(name="uom")
	private String uom;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="productgroup")
	private String productGroup;
	
	@Column(name="warehouse")
	private String warehouse;
	
	@Column(name="hsnorsac")
	private String hsnOrSac;
	
	@Column(name="category")
	private String category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id", nullable = true)
	private RequestForQuotation requestForQuotation;
	
	




	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public String getHsnOrSac() {
		return hsnOrSac;
	}

	public void setHsnOrSac(String hsnOrSac) {
		this.hsnOrSac = hsnOrSac;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public RequestForQuotation getRequestForQuotation() {
		return requestForQuotation;
	}

	public void setRequestForQuotation(RequestForQuotation requestForQuotation) {
		this.requestForQuotation = requestForQuotation;
	}

	@Override
	public String toString() {
		return "LineItems [id=" + id + ", productId=" + productId + ", productName=" + productName + ", uom=" + uom
				+ ", quantity=" + quantity + ", productGroup=" + productGroup + ", warehouse=" + warehouse
				+ ", hsnOrSac=" + hsnOrSac + ", category=" + category + ", requestForQuotation=" + requestForQuotation
				+ "]";
	}

	
	
	

}
