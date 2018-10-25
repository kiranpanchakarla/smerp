package com.smerp.model.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_hsn_codes_master")
public class HSNCode extends AuditModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="hsn_id" , nullable=false, unique= true)
	private Integer id;
	
	@Column(name="hsn_code")
	private String hsnCode;
	
	@Column(name="description")
	private String description;
	
	@Column(name="rate")
	private Integer rate;

	
	 @Column(name="is_active")
	 private Boolean isActive;
	 
	 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "HSNCode [id=" + id + ", hsnCode=" + hsnCode + ", description=" + description + ", rate=" + rate
				+ ", isActive=" + isActive + "]";
	}

	
	
	
	
	
	

}
