package com.smerp.util;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smerp.model.inventory.InventoryGoodsIssueList;
import com.smerp.model.inventory.InventoryProductsList;

@Component
public class DownloadProductXLS {

	@Autowired
	private XLSXDownload xLSXDownload;
	
	public static final String SNO = "S.No.";
	public static final String PRODUCTNUMBER = "Product#";
	public static final String PLANTNAME = "Warehouse";
	public static final String PRODUCTDESCRIPTION = "Product Description";
	public static final String UOM = "UOM";
	public static final String PRODUCTGROUP = "Group";
    public static final String QUANTITY = "Quantity";
	public static final String NEWLINE = "\n";
	public static final String SEMICOLUMN = ";";
	public static final String BLANK ="";
	public static final String DOCNUMBER = "Doc#";
	public static final String DOCDATE = "DocDate";
	public static final String DEPARTMENT = "Department";
	public static final String REMARKS = "Remarks";
	
	public ByteArrayOutputStream InventoryProductsReport(List<InventoryProductsList> productList) throws Exception {
		String excelReport = "";
		String excelData = "";
		String concat = "";
		String heading = SNO + SEMICOLUMN + PRODUCTNUMBER + SEMICOLUMN  + PLANTNAME + SEMICOLUMN  + PRODUCTDESCRIPTION + SEMICOLUMN + PRODUCTGROUP
				+ SEMICOLUMN  + UOM + SEMICOLUMN  + QUANTITY + SEMICOLUMN  +  NEWLINE;

		int index = 1;
		if (!productList.isEmpty() && productList != null) {
			for (InventoryProductsList list : productList) {
				excelData = index + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getProductName()) ? BLANK : list.getProductName()) + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getPlantName())? BLANK : list.getPlantName()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getProductDescription())? BLANK : list.getProductDescription()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getProductGroupDescription())? BLANK : list.getProductGroupDescription()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getUomName())? BLANK : list.getUomName()) + SEMICOLUMN +
						(list.getInstockQty()==null? BLANK : list.getInstockQty()) + NEWLINE ;
						
				concat = concat + excelData;
				index = index + 1;
			}
		}
		excelReport = heading + concat;
		ByteArrayOutputStream byteArrayOutputStream =xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}
	
	
	public ByteArrayOutputStream InventoryGoodsIssueReport(List<InventoryGoodsIssueList> productList) throws Exception {
		String excelReport = "";
		String excelData = "";
		String concat = "";
		String heading = SNO + SEMICOLUMN + DOCNUMBER + SEMICOLUMN  + DOCDATE + SEMICOLUMN + PRODUCTNUMBER + SEMICOLUMN + PRODUCTDESCRIPTION + SEMICOLUMN + PRODUCTGROUP
				+ SEMICOLUMN  + UOM + SEMICOLUMN  + QUANTITY +  SEMICOLUMN + DEPARTMENT + SEMICOLUMN + REMARKS + SEMICOLUMN +  NEWLINE;

		int index = 1;
		if (!productList.isEmpty() && productList != null) {
			for (InventoryGoodsIssueList list : productList) {
				excelData = index + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getDocNumber()) ? BLANK : list.getDocNumber()) + SEMICOLUMN + 
						(StringUtil.isEmptyTrim(list.getDocDate())? BLANK : list.getDocDate()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getProductNumber())? BLANK : list.getProductNumber()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getProductDescription())? BLANK : list.getProductDescription()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getProductGroup())? BLANK : list.getProductGroup()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getUomName())? BLANK : list.getUomName()) + SEMICOLUMN +
						(list.getRequiredQty()==null? BLANK : list.getRequiredQty()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getDepartmentName())? BLANK : list.getDepartmentName()) + SEMICOLUMN +
						(StringUtil.isEmptyTrim(list.getRemarks())? BLANK : list.getRemarks()) + NEWLINE ;
						
				concat = concat + excelData;
				index = index + 1;
			}
		}
		excelReport = heading + concat;
		ByteArrayOutputStream byteArrayOutputStream =xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}
}
