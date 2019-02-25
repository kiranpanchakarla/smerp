package com.smerp.model.master;

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
@Table(name="tbl_timezones_master")
public class TimeZone extends UserAuditModel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="timezone_id", nullable= false, unique= true)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="diff_from_utc")
	private String diffFromUtc;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="country_id")
	private Country country;

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

	public String getDiffFromUtc() {
		return diffFromUtc;
	}

	public void setDiffFromUtc(String diffFromUtc) {
		this.diffFromUtc = diffFromUtc;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "TimeZone [id=" + id + ", name=" + name + ", diffFromUtc=" + diffFromUtc + ", country=" + country + "]";
	}
	
	
	

}
