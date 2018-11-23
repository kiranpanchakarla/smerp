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
import com.smerp.model.master.AuditModel;

@Entity
@Table(name="tbl_admin_rfq")
public class RequestForQuotation extends AuditModel {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="rfq_id" , nullable = false, unique = true)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="vendor_id")
	private Vendor vendor;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "rfq_id", referencedColumnName = "rfq_id")
	private List<LineItems> lineItems;;
	
	@Column(name="doc_number")
	private String docNumber;
	@Column(name="status")
	private String status;
	@Column(name="reference_doc_number")
	private String referenceDocNumber;
	@Column(name="posting_date")
	private Date postingDate;
	@Column(name="document_date")
	private Date documentDate;
	@Column(name="required_date")
	private Date requiredDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vendor_shipping_address_id")
	private VendorAddress vendorShippingAddress;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vendor_paytype_address_id")
	private VendorAddress vendorPayTypeAddress;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vendor_contact_id")
	private VendorsContactDetails vendorContactDetails;
	
	@Column(name="category")
	private String category;
	
	@Column(name="is_active")
	private Boolean isActive = true;
	
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
	
	public VendorAddress getVendorShippingAddress() {
		return vendorShippingAddress;
	}
	public void setVendorShippingAddress(VendorAddress vendorShippingAddress) {
		this.vendorShippingAddress = vendorShippingAddress;
	}
	public VendorAddress getVendorPayTypeAddress() {
		return vendorPayTypeAddress;
	}
	public void setVendorPayTypeAddress(VendorAddress vendorPayTypeAddress) {
		this.vendorPayTypeAddress = vendorPayTypeAddress;
	}
	public VendorsContactDetails getVendorContactDetails() {
		return vendorContactDetails;
	}
	public void setVendorContactDetails(VendorsContactDetails vendorContactDetails) {
		this.vendorContactDetails = vendorContactDetails;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "RequestForQuotation [id=" + id + ", vendor=" + vendor + ", lineItems=" + lineItems + ", docNumber="
				+ docNumber + ", status=" + status + ", referenceDocNumber=" + referenceDocNumber + ", postingDate="
				+ postingDate + ", documentDate=" + documentDate + ", requiredDate=" + requiredDate
				+ ", vendorShippingAddress=" + vendorShippingAddress + ", vendorPayTypeAddress=" + vendorPayTypeAddress
				+ ", vendorContactDetails=" + vendorContactDetails + ", category=" + category + ", isActive=" + isActive
				+ "]";
	}
	
	
	
	
	/*
	changes
	*/
	

	
	
	
	
	
	

}
