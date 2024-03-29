package com.smerp.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.smerp.model.master.Country;
import com.smerp.model.master.Currency;
import com.smerp.model.master.States;
import com.smerp.model.master.UserAuditModel;

@Entity
@Table(name = "tbl_admin_company")
public class Company extends UserAuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "company_name")
	private String name;

	@Column(name = "company_tag_line")
	private String companyTagLine;

	@Column(name = "gstin_vat")
	private String gstinVat;

	@Column(name = "pan_num")
	private String panNum;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "currencies_id")
	private Currency currency;

	@Column(name = "street1")
	private String street1;

	@Column(name = "street2")
	private String street2;

	@Column(name = "phone_num")
	private String phoneNum;

	@Column(name = "fax_num")
	private String faxNum;

	@Column(name = "city")
	private String city;

	@Column(name = "email_id")
	private String emailId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "states_id")
	private States states;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "states_code")
	private States statesCode;

	@Column(name = "zip_code")
	private String zipCode;

	@Column(name = "web_site")
	private String webSite;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id")
	private Country country;
	
	@Column(name="logi_file_path")
    private String logo;
	
	@Column(name="isActive")
	private Boolean isActive = true;
	
   private transient String path;
   
  
	
	public String getPath() {
	return path;
}

public void setPath(String path) {
	this.path = path;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	
	

	public String getFaxNum() {
		return faxNum;
	}

	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

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

	public String getGstinVat() {
		return gstinVat;
	}

	public void setGstinVat(String gstinVat) {
		this.gstinVat = gstinVat;
	}

	

	public String getPanNum() {
		return panNum;
	}

	public void setPanNum(String panNum) {
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public States getStatesCode() {
		return statesCode;
	}

	public void setStatesCode(States statesCode) {
		this.statesCode = statesCode;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", companyTagLine=" + companyTagLine + ", gstinVat=" + gstinVat
				+ ", panNum=" + panNum + ", currency=" + currency + ", street1=" + street1 + ", street2=" + street2
				+ ", phoneNum=" + phoneNum + ", faxNum=" + faxNum + ", city=" + city + ", emailId=" + emailId
				+ ", states=" + states + ", statesCode=" + statesCode + ", zipCode=" + zipCode + ", webSite=" + webSite
				+ ", country=" + country + ", logo=" + logo + ", isActive=" + isActive + "]";
	}

	

	

	
}
