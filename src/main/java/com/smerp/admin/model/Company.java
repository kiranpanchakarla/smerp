package com.smerp.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.smerp.master.model.AuditModel;
import com.smerp.master.model.Country;
import com.smerp.master.model.Currency;
import com.smerp.master.model.States;

@Entity
@Table(name="tbl_admin_company")
public class Company extends AuditModel {
	
	
	@Id
	@Column(name="company_id" , nullable = false, unique = true)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="company_name")
	private String name;
	
	@Column(name="company_tag_line")
	private String companyTagLine;
	
	@Column(name="gstin_vat")
	private Integer gstinVat;
	
	@Column(name="pan_num") 
	private Integer panNum;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="currencies_id")
	private Currency currency;
	
	@Column(name="street1")
	private String street1;
	
	@Column(name="street2")
	private String street2;
	
	@Column(name="phone_num")
	private Integer phoneNum;
	
	@Column(name="fax_num")
	private Integer faxNum;
	
	@Column(name="city")
	private String city;
	
	@Column(name="email_id")
	private String emailId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="states_id")
	private States states;
	
	@Column(name="zip_code")
	private Integer zipCode;
	
	@Column(name="web_site")
	private String webSite;
	
	@ManyToOne(fetch=FetchType.EAGER)
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

	public String getCompanyTagLine() {
		return companyTagLine;
	}

	public void setCompanyTagLine(String companyTagLine) {
		this.companyTagLine = companyTagLine;
	}

	public Integer getGstinVat() {
		return gstinVat;
	}

	public void setGstinVat(Integer gstinVat) {
		this.gstinVat = gstinVat;
	}

	public Integer getPanNum() {
		return panNum;
	}

	public void setPanNum(Integer panNum) {
		this.panNum = panNum;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public Integer getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(Integer phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Integer getFaxNum() {
		return faxNum;
	}

	public void setFaxNum(Integer faxNum) {
		this.faxNum = faxNum;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public States getStates() {
		return states;
	}

	public void setStates(States states) {
		this.states = states;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", companyTagLine=" + companyTagLine + ", gstinVat=" + gstinVat
				+ ", panNum=" + panNum + ", currency=" + currency + ", street1=" + street1 + ", street2=" + street2
				+ ", phoneNum=" + phoneNum + ", faxNum=" + faxNum + ", city=" + city + ", emailId=" + emailId
				+ ", states=" + states + ", zipCode=" + zipCode + ", webSite=" + webSite + ", country=" + country + "]";
	}
	
	
	
	
	

}
