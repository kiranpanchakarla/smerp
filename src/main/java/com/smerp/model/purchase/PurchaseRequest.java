package com.smerp.model.purchase;

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
import javax.persistence.Transient;

import com.smerp.model.admin.User;
import com.smerp.model.inventory.Uom;
import com.smerp.model.master.AuditModel;

@Entity
@Table(name="tbl_purchase_purchase_req")
public class PurchaseRequest extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="purchase_req_id" , nullable = false , unique = true)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id", referencedColumnName = "user_id")
	private User  user;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="reference_user_id", referencedColumnName = "user_id")
	private User  referenceUser;
	
	@Column(name="doc_number")
    private String docNumber;

	@Column(name="document_date")
    private Date documentDate;
	
	@Column(name="status")
    private String status;


	@Column(name="posting_date")
	private Date postingDate;
	
	
	@Column(name="required_date")
	private Date requiredDate;
	
	@Column(name="type")
	private String type;
	

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true )
	@JoinColumn(name = "purchase_req_id", referencedColumnName = "purchase_req_id" )
	private List<PurchaseRequestList> purchaseRequestLists;
	
	@Column(name="is_active")
	private Boolean isActive = true;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	

	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<PurchaseRequestList> getPurchaseRequestLists() {
		return purchaseRequestLists;
	}

	public void setPurchaseRequestLists(List<PurchaseRequestList> purchaseRequestLists) {
		this.purchaseRequestLists = purchaseRequestLists;
	}

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public Date getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	
	
	
	
	public User getReferenceUser() {
		return referenceUser;
	}

	public void setReferenceUser(User referenceUser) {
		this.referenceUser = referenceUser;
	}

	@Override
	public String toString() {
		return "PurchaseRequest [id=" + id + ", user=" + user + ", referenceUser=" + referenceUser + ", docNumber="
				+ docNumber + ", documentDate=" + documentDate + ", status=" + status + ", postingDate=" + postingDate
				+ ", requiredDate=" + requiredDate + ", type=" + type + ", purchaseRequestLists=" + purchaseRequestLists
				+ ", isActive=" + isActive + "]";
	}

	

	
	
	
	
	
	
	
	
	

}
