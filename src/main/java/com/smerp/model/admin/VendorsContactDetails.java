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

import com.smerp.model.master.AuditModel;



@Entity
@Table(name="tbl_admin_vendor_contact_details")
public class VendorsContactDetails extends AuditModel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="vendor_contact_id", nullable = false, unique=true)
	private Integer id;
	
	@Column(name="contact_name")
	private String contactName;
	
	@Column(name="contact_id")
	private String contactId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="name")
	private String name;
	
	@Column(name="mobile_no")
	private String mobileNo;
	
	@Column(name="fax")
	private String fax;
	
	@Column(name="email")
	private String email;
	
	@Column(name="gender")
	private String gender;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = true)
	private Vendor vendor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	@Override
	public String toString() {
		return "VendorsAddress [id=" + id + ", contactName=" + contactName + ", contactId=" + contactId + ", title="
				+ title + ", name=" + name + ", mobileNo=" + mobileNo + ", fax=" + fax + ", email=" + email
				+ ", gender=" + gender + ", vendor=" + vendor + "]";
	}

	
	

}
