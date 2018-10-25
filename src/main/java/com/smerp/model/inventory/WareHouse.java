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

import com.smerp.model.master.AuditModel;
import com.smerp.model.master.States;


@Entity
@Table(name="tbl_inventory_ware_house")
public class WareHouse extends AuditModel {
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="warehouse_id", nullable = false, unique=true)
	private Integer id;
	
	@Column(name="warehouse_name")
	private String wareHouseName;
	
	@Column(name="address1")
	private String addrees1;
	
	@Column(name="address2")
	private String address2;
	
	@Column(name="short_code")
	private String shortCode;
	
	@Column(name="city")
	private String city;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="states_id")
	private States states;
	
	@Column(name="pin_code")
	private Integer pinCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}

	public String getAddrees1() {
		return addrees1;
	}

	public void setAddrees1(String addrees1) {
		this.addrees1 = addrees1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
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

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	@Override
	public String toString() {
		return "WareHouse [id=" + id + ", wareHouseName=" + wareHouseName + ", addrees1=" + addrees1 + ", address2="
				+ address2 + ", shortCode=" + shortCode + ", city=" + city + ", states=" + states + ", pinCode="
				+ pinCode + "]";
	}
	
	
	
	
	

}
