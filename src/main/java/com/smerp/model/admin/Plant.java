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
import com.smerp.model.master.States;
import com.smerp.model.master.UserAuditModel;



@Entity
@Table(name="tbl_admin_plant")
public class Plant extends UserAuditModel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="plant_id" , nullable = false, unique = true)
	private Integer id;
	
	
	@Column(name="plant_name")
	private String  plantName;
	
	@Column(name="address_1")
	private String  address1;
	
	@Column(name="address_2")
	private String  address2;

	@Column(name = "city")
	private String city;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "states_id")
	private States states;
	
	@Column(name = "zip_code")
	private String zipCode;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id")
	private Country country;
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getPlantName() {
		return plantName;
	}


	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}


	public String getAddress1() {
		return address1;
	}


	public void setAddress1(String address1) {
		this.address1 = address1;
	}


	public String getAddress2() {
		return address2;
	}


	public void setAddress2(String address2) {
		this.address2 = address2;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public States getStates() {
		return states;
	}


	public void setStates(States states) {
		this.states = states;
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


	@Override
	public String toString() {
		return "Plant [id=" + id + ", plantName=" + plantName + ", address1=" + address1 + ", address2=" + address2
				+ ", city=" + city + ", states=" + states + ", zipCode=" + zipCode + ", country=" + country + "]";
	}

    
	 
	
	
	
	
	
	
	
	
}
