package com.smerp.model.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smerp.model.master.AuditModel;


@Entity
@Table(name="tbl_inventory_uom_Category")
public class UomCategory extends AuditModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="uom_category_id" , nullable=false, unique=true)
	private Integer id;
	
	@Column(name="uom_category_name")
	private String uomCategoryName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUomCategoryName() {
		return uomCategoryName;
	}

	public void setUomCategoryName(String uomCategoryName) {
		this.uomCategoryName = uomCategoryName;
	}

	@Override
	public String toString() {
		return "UomCategory [id=" + id + ", uomCategoryName=" + uomCategoryName + "]";
	}
	
	
	

}
