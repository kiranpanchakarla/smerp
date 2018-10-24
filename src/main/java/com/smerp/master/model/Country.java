package com.smerp.master.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.smerp.admin.model.Company;

@Entity
@Table(name="tbl_country_master")
public class Country extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name="country_id" , nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="country_code")
	private String countryCode;
	
	@Column(name="name")
	private String name;
	
	@Column(name="phone_code")
	private String phoneCode;
	
	 @Column(name="is_active")
	 private Boolean isActive;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="currencies_id")
	private Currency currency;
	
	/*@OneToMany(cascade = CascadeType.ALL, mappedBy="country")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<TimeZone> timeZone;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="country")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<States> states;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="country")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Company> companies;*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*public Set<TimeZone> getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(Set<TimeZone> timeZone) {
		this.timeZone = timeZone;
	}

	public Set<States> getStates() {
		return states;
	}

	public void setStates(Set<States> states) {
		this.states = states;
	}*/

	/*@Override
	public String toString() {
		return "Country [id=" + id + ", countryCode=" + countryCode + ", name=" + name + ", phoneCode=" + phoneCode
				+ ", isActive=" + isActive + ", currency=" + currency + ", timeZone=" + timeZone + ", states=" + states
				+ "]";
	}
*/
	
	
	
	
	
	
	
	
	
	
	
	

}
