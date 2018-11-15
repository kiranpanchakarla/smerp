package com.smerp.model.admin;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.smerp.model.master.AuditModel;
import com.smerp.model.master.Country;


@Entity
@Table(name="tbl_admin_vendor_address")
public class VendorAddress  extends AuditModel{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="vendor_address_id" , nullable = false, unique = true)
	private Integer id;
	
	
	
	@Column(name="address_id")
	private String addressId;
	
	@Column(name="address_name")
	private String addressName;
	
	@Column(name="street")
	private String street;
	
	@Column(name="city")
	private String city;
	
	@Column(name="zip_code")
	private String zipCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="country_id")
	private Country country;
	
	@Column(name="street_no")
	private String streetNo;
	
	@Column(name="gstin")
	private String gstin;
	
	@Column(name="gstin_type")
	private String gstinType;
	
	
	@Column(name="pay_type")
	private String payType;
	
	@Column(name="ship_from")
	private String shipFrom;
	
	@Column(name="isActive")
	private Boolean isActive = true;

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	


	public String getAddressId() {
		return addressId;
	}


	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}


	public String getAddressName() {
		return addressName;
	}


	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}


	public String getStreetNo() {
		return streetNo;
	}


	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}


	public String getGstin() {
		return gstin;
	}


	public void setGstin(String gstin) {
		this.gstin = gstin;
	}


	public String getGstinType() {
		return gstinType;
	}


	public void setGstinType(String gstinType) {
		this.gstinType = gstinType;
	}


	public String getPayType() {
		return payType;
	}


	public void setPayType(String payType) {
		this.payType = payType;
	}


	public String getShipFrom() {
		return shipFrom;
	}


	public void setShipFrom(String shipFrom) {
		this.shipFrom = shipFrom;
	}



	public Boolean getIsActive() {
		return isActive;
	}


	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}


	@Override
	public String toString() {
		return "VendorAddress [id=" + id + ", addressId=" + addressId + ", addressName=" + addressName + ", street="
				+ street + ", city=" + city + ", zipCode=" + zipCode + ", country=" + country + ", streetNo=" + streetNo
				+ ", gstin=" + gstin + ", gstinType=" + gstinType + ", payType=" + payType + ", shipFrom=" + shipFrom
				+ "]";
	}


}
