package com.smerp.model.admin;

import java.util.List;

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

import org.hibernate.annotations.Cascade;

import com.smerp.model.master.AuditModel;
import com.smerp.model.master.Country;

@Entity
@Table(name = "tbl_admin_vendor")
public class Vendor extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vendor_id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "vendor_code")
	private String vendorCode;

	@Column(name = "name")
	private String name;

	@Column(name = "group_name")
	private String groupName;

	@Column(name = "mobile_no")
	private String mobileNo;

	@Column(name = "fax")
	private String fax;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "business_partner_type")
	private String businessPartnerType;

	@Column(name = "payment_term")
	private String paymentTerms;

	@Column(name = "credit_limits")
	private String creditLimit;

	@Column(name = "commitment_limit")
	private String commitmentLimit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country bankCountry;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "bank_code")
	private String bankCode;

	@Column(name = "account_id")
	private String accountId;

	@Column(name = "bank_account_name")
	private String bankAccountName;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id")
	private List<VendorAddress> vendorAddress;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id")
	private List<VendorsContactDetails> vendorContactDetails;

	@Column(name = "branch")
	private String branch;
	
	@Column(name="isActive")
	private Boolean isActive = true;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getBusinessPartnerType() {
		return businessPartnerType;
	}

	public void setBusinessPartnerType(String businessPartnerType) {
		this.businessPartnerType = businessPartnerType;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getCommitmentLimit() {
		return commitmentLimit;
	}

	public void setCommitmentLimit(String commitmentLimit) {
		this.commitmentLimit = commitmentLimit;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Country getBankCountry() {
		return bankCountry;
	}

	public void setBankCountry(Country bankCountry) {
		this.bankCountry = bankCountry;
	}

	public List<VendorAddress> getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(List<VendorAddress> vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	public List<VendorsContactDetails> getVendorContactDetails() {
		return vendorContactDetails;
	}

	public void setVendorContactDetails(List<VendorsContactDetails> vendorContactDetails) {
		this.vendorContactDetails = vendorContactDetails;
	}
	
	
	

	

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Vendor [id=" + id + ", vendorCode=" + vendorCode + ", name=" + name + ", groupName=" + groupName
				+ ", mobileNo=" + mobileNo + ", fax=" + fax + ", emailId=" + emailId + ", businessPartnerType="
				+ businessPartnerType + ", paymentTerms=" + paymentTerms + ", creditLimit=" + creditLimit
				+ ", commitmentLimit=" + commitmentLimit + ", bankCountry=" + bankCountry + ", bankName=" + bankName
				+ ", bankCode=" + bankCode + ", accountId=" + accountId + ", bankAccountName=" + bankAccountName
				+ ", vendorAddress=" + vendorAddress + ", vendorContactDetails=" + vendorContactDetails + ", branch="
				+ branch + "]";
	}

	/*
	 * @Override public String toString() { return "Vendor [id=" + id +
	 * ", vendorCode=" + vendorCode + ", name=" + name + ", group=" + group +
	 * ", mobileNo=" + mobileNo + ", fax=" + fax + ", emailId=" + emailId +
	 * ", businessPartnerType=" + businessPartnerType + ", paymentTerms=" +
	 * paymentTerms + ", creditLimit=" + creditLimit + ", commitmentLimit=" +
	 * commitmentLimit + ", bankCountry=" + bankCountry + ", bankName=" + bankName +
	 * ", bankCode=" + bankCode + ", accountId=" + accountId + ", bankAccountName="
	 * + bankAccountName + ", branch=" + branch + "]"; }
	 */

	/*
	 * @JsonManagedReference
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendors")
	 * 
	 * @LazyCollection(LazyCollectionOption.FALSE) private List<VendorPayTo>
	 * vendorPayTos;
	 * 
	 * @JsonManagedReference
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendors")
	 * 
	 * @LazyCollection(LazyCollectionOption.FALSE) private List<VendorsAddress>
	 * vendorsAddresses;
	 * 
	 * @JsonManagedReference
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendors")
	 * 
	 * @LazyCollection(LazyCollectionOption.FALSE) private List<VendorShipFrom>
	 * vendorShipFroms;
	 */

}
