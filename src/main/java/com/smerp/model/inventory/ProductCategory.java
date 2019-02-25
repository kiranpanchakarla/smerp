package com.smerp.model.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smerp.model.master.UserAuditModel;


@Entity
@Table(name="tbl_inventory_product_category")
public class ProductCategory extends UserAuditModel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_category_id", nullable= false, unique = true)
	private Integer id;
	
	@Column(name="category_name")
	private String name;
	
	@Column(name="category_type")
	private String categoryType;
	
	@Column(name="inventory_valuation")
	private String inventoryValuation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getInventoryValuation() {
		return inventoryValuation;
	}

	public void setInventoryValuation(String inventoryValuation) {
		this.inventoryValuation = inventoryValuation;
	}

	@Override
	public String toString() {
		return "ProductCategory [id=" + id + ", name=" + name + ", categoryType=" + categoryType
				+ ", inventoryValuation=" + inventoryValuation + "]";
	}
	
	
	
	 

}
