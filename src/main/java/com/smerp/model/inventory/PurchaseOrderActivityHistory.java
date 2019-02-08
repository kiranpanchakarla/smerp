package com.smerp.model.inventory;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="tbl_po_activity_history")
public class PurchaseOrderActivityHistory {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="activity_id" , nullable = false , unique = true)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="po_id")
	private PurchaseOrder purchaseOrder;
	
	@Column(name="log")
	private String log;
	
	@Column(name="activity")
	private String activity;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false, updatable = false)
    private Date createdDate;
	
	@Column(name="created_by")
	private String createdBy;

	public PurchaseOrderActivityHistory(Integer id, PurchaseOrder purchaseOrder, String log, String activity,
			Date createdDate, String createdBy) {
		super();
		this.id = id;
		this.purchaseOrder = purchaseOrder;
		this.log = log;
		this.activity = activity;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}

	public PurchaseOrderActivityHistory() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "PurchaseOrderUserActivityHistory [id=" + id + ", purchaseOrder=" + purchaseOrder + ", log=" + log
				+ ", activity=" + activity + ", createdDate=" + createdDate + ", createdBy=" + createdBy + "]";
	}
	
}
