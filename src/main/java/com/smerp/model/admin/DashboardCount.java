package com.smerp.model.admin;

public class DashboardCount {

	
	private Integer total;
	private Integer open;
	private Integer cancelled;
	private Integer draft;
	private Integer completed;
	private Integer convertedToRFQ;
	private Integer approved;
	private Integer rejected;
	private Integer convertedToPO;
	
	public Integer getConvertedToPO() {
		return convertedToPO;
	}
	public void setConvertedToPO(Integer convertedToPO) {
		this.convertedToPO = convertedToPO;
	}
	public Integer getCancelled() {
		return cancelled;
	}
	public void setCancelled(Integer cancelled) {
		this.cancelled = cancelled;
	}
	public Integer getDraft() {
		return draft;
	}
	public void setDraft(Integer draft) {
		this.draft = draft;
	}
	public Integer getCompleted() {
		return completed;
	}
	public void setCompleted(Integer complete) {
		completed = complete;
	}
	public Integer getConvertedToRFQ() {
		return convertedToRFQ;
	}
	public void setConvertedToRFQ(Integer convertedToRfq) {
		convertedToRFQ = convertedToRfq;
	}
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getOpen() {
		return open;
	}
	public void setOpen(Integer open) {
		this.open = open;
	}
	public Integer getApproved() {
		return approved;
	}
	public void setApproved(Integer approve) {
		approved = approve;
	}
	public Integer getRejected() {
		return rejected;
	}
	public void setRejected(Integer rejecte) {
		rejected = rejecte;
	}
	
	@Override
	public String toString() {
		return "DashboardCount [total=" + total + ", open=" + open + ", cancelled=" + cancelled + ", draft=" + draft
				+ ", Completed=" + completed + ", ConvertedToRFQ=" + convertedToRFQ + ", Approved=" + approved
				+ ", Rejected=" + rejected + "]";
	}
	
}
