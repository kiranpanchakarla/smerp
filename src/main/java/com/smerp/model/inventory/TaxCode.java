package com.smerp.model.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "tbl_tax_code")
public class TaxCode {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "tax_code")
	private Double taxCode;

	@Column(name = "description")
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(Double taxCode) {
		this.taxCode = taxCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "TaxCode [id=" + id + ", taxCode=" + taxCode + ", description=" + description + "]";
	}
	
	
}
