package com.smerp.model.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_sac_codes_master")
public class SACCode extends AuditModel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sac_id", nullable = false, unique=true)
	private Integer id;
	
	@Column(name="sac_code")
	private String sacCoe;
	
	@Column(name="description")
	private String description;
	
	@Column(name="rate")
	private String rate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSacCoe() {
		return sacCoe;
	}

	public void setSacCoe(String sacCoe) {
		this.sacCoe = sacCoe;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "SACCode [id=" + id + ", sacCoe=" + sacCoe + ", description=" + description + ", rate=" + rate + "]";
	}
	
	
	
	

}
