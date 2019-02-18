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

import com.smerp.model.master.UserAuditModel;


@Entity
@Table(name="tbl_inventory_product_attribute_values")
public class ProductAttributeValues extends UserAuditModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_attribute_value_id", nullable=false, unique=true)
	private  Integer id;
	
	@Column(name="attribute_value")
	private String attributeValue;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_attributes_id")
	private ProductAttributes productAttributes;
	
	


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getAttributeValue() {
		return attributeValue;
	}


	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}


	public ProductAttributes getProductAttributes() {
		return productAttributes;
	}


	public void setProductAttributes(ProductAttributes productAttributes) {
		this.productAttributes = productAttributes;
	}


	@Override
	public String toString() {
		return "ProductAttributeValues [id=" + id + ", attributeValue=" + attributeValue + ", productAttributes="
				+ productAttributes + "]";
	}


	
	
	

}
