package com.smerp.model.inventory;

import java.util.Date;
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
import com.smerp.model.admin.Vendor;
import com.smerp.model.admin.VendorAddress;
import com.smerp.model.admin.VendorsContactDetails;

@Entity
@Table(name="tbl_admin_rfq")
public class RequestForQuotation {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="rfq_id" , nullable = false, unique = true)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="vendor_id")
	private Vendor vendor;
	
	private transient String name;
	private transient String emailId;
	
	private transient String vendorId;
	
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "rfq_id", referencedColumnName = "rfq_id")
	private List<LineItems> lineItems;;
	
	@Column(name="docNumber")
	private String docNumber;
	@Column(name="status")
	private String status;
	@Column(name="referenceDocNumber")
	private String referenceDocNumber;
	@Column(name="postingDate")
	private Date postingDate;
	@Column(name="documentDate")
	private Date documentDate;
	@Column(name="requiredDate")
	private Date requiredDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vendor_address_id")
	private VendorAddress vendorAddress;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vendor_contact_id")
	private VendorsContactDetails vendorContactDetails;
	
	
	
	
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getDocNumber() {
		return docNumber;
	}
	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReferenceDocNumber() {
		return referenceDocNumber;
	}
	public void setReferenceDocNumber(String referenceDocNumber) {
		this.referenceDocNumber = referenceDocNumber;
	}
	public Date getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}
	public Date getDocumentDate() {
		return documentDate;
	}
	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}
	public Date getRequiredDate() {
		return requiredDate;
	}
	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}
	
	public List<LineItems> getLineItems() {
		return lineItems;
	}
	public void setLineItems(List<LineItems> lineItems) {
		this.lineItems = lineItems;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public VendorAddress getVendorAddress() {
		return vendorAddress;
	}
	public void setVendorAddress(VendorAddress vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	public VendorsContactDetails getVendorContactDetails() {
		return vendorContactDetails;
	}
	public void setVendorContactDetails(VendorsContactDetails vendorContactDetails) {
		this.vendorContactDetails = vendorContactDetails;
	}
	@Override
	public String toString() {
		return "RequestForQuotation [id=" + id + ", vendor=" + vendor + ", lineItems=" + lineItems + ", docNumber="
				+ docNumber + ", status=" + status + ", referenceDocNumber=" + referenceDocNumber + ", postingDate="
				+ postingDate + ", documentDate=" + documentDate + ", requiredDate=" + requiredDate + ", vendorAddress="
				+ vendorAddress + ", vendorContactDetails=" + vendorContactDetails + "]";
	}
	
	
	
	
	

}
