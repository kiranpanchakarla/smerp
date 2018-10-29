package com.smerp.model.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smerp.model.master.AuditModel;


@Entity
@Table(name="tbl_inventory_product_attribute_values")
public class ProductAttributeValues extends AuditModel {
	
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
	
	
	@Column(name="create_varient")
	private Boolean createVarient;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Boolean getCreateVarient() {
		return createVarient;
	}


	public void setCreateVarient(Boolean createVarient) {
		this.createVarient = createVarient;
	}


	@Override
	public String toString() {
		return "ProductAttributeValues [id=" + id + ", attributeValue=" + attributeValue + ", createVarient="
				+ createVarient + "]";
	}
	
	
	

}