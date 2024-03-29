package com.smerp.model.inventorytransactions;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.smerp.model.admin.Plant;
import com.smerp.model.master.AuditModel;


@Entity
@Table(name="tbl_inventory_goods_receipt")
public class InventoryGoodsReceipt extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id" , nullable = false , unique = true)
	private Integer id;
	
	@Column(name="doc_number")
    private String docNumber;
	
	@Column(name="document_date")
    private Date documentDate;
	
	@Column(name="status")
    private String status;
	
	private transient String statusType;
	
	@Column(name="posting_date")
	private Date postingDate;
	
	@Column(name="required_date")
	private Date requiredDate;
	
	@Column(name = "reference_doc_number")
	private String referenceDocNumber;
	
	@Column(name="remarks")
	private String remarks;
	
	 @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "inv_goods_receipt_id", referencedColumnName = "id" )
	private List<InventoryGoodsReceiptList> inventoryGoodsReceiptList;
	 
	@Column(name="is_active")
	private Boolean isActive = true;
	
	@Column(name = "total_discount")
	private Double totalDiscount;

	@Column(name = "total_payment")
	private Double totalPayment ;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="plant_id", referencedColumnName = "plant_id")
	private Plant  plant;
	
	@Column(name="first_Approve_id")
	private Integer  firstApproveId;
	
	@Column(name="second_Approve_id")
	private Integer  secondApproveId;
	
	
	@Column(name="second_Level_enable")
	private Boolean  secondLevelEnable = false;

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	@Column(name = "freight")
	private Double freight;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public String getReferenceDocNumber() {
		return referenceDocNumber;
	}

	public void setReferenceDocNumber(String referenceDocNumber) {
		this.referenceDocNumber = referenceDocNumber;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<InventoryGoodsReceiptList> getInventoryGoodsReceiptList() {
		return inventoryGoodsReceiptList;
	}

	public void setInventoryGoodsReceiptList(List<InventoryGoodsReceiptList> inventoryGoodsReceiptList) {
		this.inventoryGoodsReceiptList = inventoryGoodsReceiptList;
	} 

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public Double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(Double totalPayment) {
		this.totalPayment = totalPayment;
	}

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}
	
    private transient String amtRounding;
    
    private transient String taxAmt;
	
	private transient Double totalBeforeDisAmt ;

	public String getAmtRounding() {
		return amtRounding;
	}

	public void setAmtRounding(String amtRounding) {
		this.amtRounding = amtRounding;
	}

	public String getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(String taxAmt) {
		this.taxAmt = taxAmt;
	}

	public Double getTotalBeforeDisAmt() {
		return totalBeforeDisAmt;
	}

	public void setTotalBeforeDisAmt(Double totalBeforeDisAmt) {
		this.totalBeforeDisAmt = totalBeforeDisAmt;
	}

	@Column(name = "deliver_to")
	private String deliverTo;
	
	private transient String roundedOff;

	public String getDeliverTo() {
		return deliverTo;
	}

	public void setDeliverTo(String deliverTo) {
		this.deliverTo = deliverTo;
	}

	public String getRoundedOff() {
		return roundedOff;
	}

	public void setRoundedOff(String roundedOff) {
		this.roundedOff = roundedOff;
	}

	public Integer getFirstApproveId() {
		return firstApproveId;
	}

	public void setFirstApproveId(Integer firstApproveId) {
		this.firstApproveId = firstApproveId;
	}

	public Integer getSecondApproveId() {
		return secondApproveId;
	}

	public void setSecondApproveId(Integer secondApproveId) {
		this.secondApproveId = secondApproveId;
	}

	public Boolean getSecondLevelEnable() {
		return secondLevelEnable;
	}

	public void setSecondLevelEnable(Boolean secondLevelEnable) {
		this.secondLevelEnable = secondLevelEnable;
	}
	
	
	
	
}
