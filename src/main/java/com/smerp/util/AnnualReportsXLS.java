package com.smerp.util;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smerp.model.reports.ProductWiseReport;
import com.smerp.model.reports.VendorWiseReport;
import com.smerp.model.reports.WarehouseWiseReport;
import com.smerp.service.reports.VendorWiseReportService;

@Component
public class AnnualReportsXLS {

	private static final Logger logger = LogManager.getLogger(AnnualReportsXLS.class);

	@Autowired
	private XLSXDownload xLSXDownload;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	VendorWiseReportService vendorWiseReportService;
	
	public static final String SNO = "S.No.";
	public static final String VENDORCODE = "Vendor Code";
	public static final String VENDOR = "Vendor Name";
	public static final String PRODUCTNO = "Product Number";
	public static final String DESCRIPTION = "Description";
	public static final String WAREHOUSE = "Warehouse Name";
	public static final String MONTH = "Month";
	public static final String AMOUNT = "Amount";
	public static final String QTY = "Quantity";
	public static final String ANNUAL = "Annual Amount";
	public static final String NEWLINE = "\n";
	public static final String SEMICOLUMN = ";";
	public static final String BLANK = "";
	public static final String ZERO = "0";
	public static final String DASH = "-";
	public static final String DOCDATE = "Document Date";
	public static final String DEPARTMENT = "Department";
	public static final String PO = "Purchase Order";
	 

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private String getVendorHeader() {
		String heading = SNO + SEMICOLUMN + VENDORCODE + SEMICOLUMN + VENDOR + SEMICOLUMN  + ANNUAL + NEWLINE;
		return heading;
	}
	
	private String getWarehouseHeader() {
		String heading = SNO + SEMICOLUMN + WAREHOUSE  +  SEMICOLUMN + ANNUAL + NEWLINE;
		return heading;
	}
	
	private String getProductHeader() {
		String heading = SNO + SEMICOLUMN + PRODUCTNO + SEMICOLUMN + DESCRIPTION + SEMICOLUMN  +  QTY + 
				 SEMICOLUMN + ANNUAL + NEWLINE;
		return heading;
	}
	
	public ByteArrayOutputStream VendorReport(List<VendorWiseReport> vendorList) throws Exception {
		String excelReport = "";
		String excelData = "";
		String concat = "";

		int index = 1;
		if (!vendorList.isEmpty() && vendorList != null) {
			for (VendorWiseReport list : vendorList) {

				excelData = index + SEMICOLUMN
						+ (StringUtil.isEmptyTrim(list.getVendorCode()) ? BLANK : list.getVendorCode()) + SEMICOLUMN
						+ (StringUtil.isEmptyTrim(list.getVendorName()) ? BLANK : list.getVendorName()) + SEMICOLUMN
						+ (list.getTotalAmount() == null ? BLANK : list.getTotalAmount()) + SEMICOLUMN
						 + NEWLINE;

				concat = concat + excelData;
				index = index + 1;

			}

		}

		excelReport = getVendorHeader() + concat;
		ByteArrayOutputStream byteArrayOutputStream = xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}
	
	public ByteArrayOutputStream warehouseReport(List<WarehouseWiseReport> warehouseList) throws Exception {
		String excelReport = "";
		String excelData = "";
		String concat = "";

		int index = 1;
		if (!warehouseList.isEmpty() && warehouseList != null) {
			for (WarehouseWiseReport list : warehouseList) {

				excelData = index + SEMICOLUMN
						+ (StringUtil.isEmptyTrim(list.getWarehouseName()) ? BLANK : list.getWarehouseName()) + SEMICOLUMN
						+ (list.getTotalAmount() == null ? BLANK : list.getTotalAmount()) + SEMICOLUMN
					    + NEWLINE;

				concat = concat + excelData;
				index = index + 1;

			}

		}

		excelReport = getWarehouseHeader() + concat;
		ByteArrayOutputStream byteArrayOutputStream = xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}

	public ByteArrayOutputStream productReport(List<ProductWiseReport> productList) throws Exception {
		String excelReport = "";
		String excelData = "";
		String concat = "";

		int index = 1;
		if (!productList.isEmpty() && productList != null) {
			for (ProductWiseReport list : productList) {

				excelData = index + SEMICOLUMN
						+ (StringUtil.isEmptyTrim(list.getProductName()) ? BLANK : list.getProductName()) + SEMICOLUMN
						+ (StringUtil.isEmptyTrim(list.getDescription()) ? BLANK : list.getDescription()) + SEMICOLUMN
						+ (list.getTotalQty() == null ? BLANK : list.getTotalQty()) + SEMICOLUMN
						+ (list.getTotalAmount() == null ? BLANK : list.getTotalAmount()) + SEMICOLUMN
						+ NEWLINE;

				concat = concat + excelData;
				index = index + 1;

			}
		}

		excelReport = getProductHeader() + concat;
		ByteArrayOutputStream byteArrayOutputStream = xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}
	
}
