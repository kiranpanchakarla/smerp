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
@Table(name = "tbl_inventory_uom")
public class Uom extends UserAuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uom_id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "uom_name")
	private String uomName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uom_category_id")
	private UomCategory uomCategory;


	
	@Column(name = "rounding_precision")
	private String roundingPrecision;

	@Column(name = "type")
	private String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUomName() {
		return uomName;
	}

	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

	

	public UomCategory getUomCategory() {
		return uomCategory;
	}

	public void setUomCategory(UomCategory uomCategory) {
		this.uomCategory = uomCategory;
	}

	public String getRoundingPrecision() {
		return roundingPrecision;
	}

	public void setRoundingPrecision(String roundingPrecision) {
		this.roundingPrecision = roundingPrecision;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	

	@Override
	public String toString() {
		return "Uom [id=" + id + ", uomName=" + uomName + ", uomCategory=" + uomCategory + ", roundingPrecision="
				+ roundingPrecision + ", type=" + type + "]";
	}

	

}
