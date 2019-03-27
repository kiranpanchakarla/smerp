package com.smerp.model.search;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

public class SearchFilter {

	private String searchBy;
	private String fieldName;
	private String sortBy;
	private String dateSelect;
	private Date fromDate;
	private Date toDate;
	private String typeOf;
	private String isConvertedDoc;
	private List<String> statusList;

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getDateSelect() {
		return dateSelect;
	}

	public void setDateSelect(String dateSelect) {
		this.dateSelect = dateSelect;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getTypeOf() {
		return typeOf;
	}

	public void setTypeOf(String typeOf) {
		this.typeOf = typeOf;
	}

	public String getIsConvertedDoc() {
		return isConvertedDoc;
	}

	public void setIsConvertedDoc(String isConvertedDoc) {
		this.isConvertedDoc = isConvertedDoc;
	}

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}
	
}
