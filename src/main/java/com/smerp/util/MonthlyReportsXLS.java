package com.smerp.util;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smerp.model.reports.ProductWiseReport;
import com.smerp.model.reports.VendorWiseReport;
import com.smerp.model.reports.WarehouseWiseReport;
import com.smerp.model.search.SearchFilter;
import com.smerp.service.reports.VendorWiseReportService;

@Component
public class MonthlyReportsXLS {

	private static final Logger logger = LogManager.getLogger(MonthlyReportsXLS.class);

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
	public static final String JAN = "JAN";
	public static final String FEB = "FEB";
	public static final String MAR = "MAR";
	public static final String APR = "APR";
	public static final String MAY = "MAY";
	public static final String JUN = "JUN";
	public static final String JUL = "JUL";
	public static final String AUG = "AUG";
	public static final String SEP = "SEP";
	public static final String OCT = "OCT";
	public static final String NOV = "NOV";
	public static final String DEC = "DEC";

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private String getVendorHeader() {
		String heading = SNO + SEMICOLUMN + VENDORCODE + SEMICOLUMN + VENDOR + SEMICOLUMN  + SEMICOLUMN + APR
				+ SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + MAY + SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + JUN + SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + JUL + SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + AUG + SEMICOLUMN + SEP
				+ SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + OCT + SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + NOV + SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + DEC+ SEMICOLUMN + AMOUNT + DASH + JAN + SEMICOLUMN + FEB
				+ SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + MAR + SEMICOLUMN + NEWLINE;
		return heading;
	}
	
	private String getWarehouseHeader() {
		String heading = SNO + SEMICOLUMN + WAREHOUSE + SEMICOLUMN  + APR
				+ SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + MAY + SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + JUN + SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + JUL + SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + AUG + SEMICOLUMN + SEP
				+ SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + OCT + SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + NOV + SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + DEC+ SEMICOLUMN + AMOUNT + DASH + JAN + SEMICOLUMN + FEB
				+ SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + MAR + SEMICOLUMN + NEWLINE;
		return heading;
	}
	
	private String getProductHeader() {
		String heading = SNO + SEMICOLUMN + PRODUCTNO + SEMICOLUMN + DESCRIPTION + SEMICOLUMN  + QTY+DASH+APR + SEMICOLUMN + APR
				+ SEMICOLUMN + QTY+DASH+MAY + SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + MAY + SEMICOLUMN + QTY+DASH+JUN + SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + JUN + SEMICOLUMN + QTY+DASH+JUL + SEMICOLUMN 
				+ AMOUNT + DASH + AMOUNT + DASH + JUL + SEMICOLUMN + QTY+DASH+AUG + SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + AUG + SEMICOLUMN + QTY+DASH+SEP + SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + SEP + SEMICOLUMN   
				+ QTY+DASH+OCT + SEMICOLUMN +  OCT + SEMICOLUMN + QTY+DASH+NOV + SEMICOLUMN + AMOUNT + DASH + AMOUNT + DASH + NOV + SEMICOLUMN +QTY+DASH+DEC + SEMICOLUMN +  DEC + SEMICOLUMN 
				+ QTY+DASH+JAN + SEMICOLUMN + AMOUNT + DASH + JAN + SEMICOLUMN + QTY+DASH+FEB + SEMICOLUMN + AMOUNT + DASH + FEB+ QTY+DASH+MAR + SEMICOLUMN  + AMOUNT + DASH + AMOUNT + DASH + MAR + SEMICOLUMN + NEWLINE;
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
						+ (list.getAPR() == null ? BLANK : list.getAPR()) + SEMICOLUMN
						+ (list.getMAY() == null ? BLANK : list.getMAY()) + SEMICOLUMN
						+ (list.getJUN() == null ? BLANK : list.getJUN()) + SEMICOLUMN
						+ (list.getJUL() == null ? BLANK : list.getJUL()) + SEMICOLUMN
						+ (list.getAUG() == null ? BLANK : list.getAUG()) + SEMICOLUMN
						+ (list.getSEP() == null ? BLANK : list.getSEP()) + SEMICOLUMN
						+ (list.getOCT() == null ? BLANK : list.getOCT()) + SEMICOLUMN
						+ (list.getNOV() == null ? BLANK : list.getNOV()) + SEMICOLUMN
						+ (list.getDEC() == null ? BLANK : list.getDEC()) + SEMICOLUMN
						+ (list.getJAN() == null ? BLANK : list.getJAN()) + SEMICOLUMN
						+ (list.getFEB() == null ? BLANK : list.getFEB()) + SEMICOLUMN
						+ (list.getMAR() == null ? BLANK : list.getMAR()) + SEMICOLUMN + NEWLINE;

				concat = concat + excelData;
				index = index + 1;

			}

		}

		excelReport = getVendorHeader() + concat;
		ByteArrayOutputStream byteArrayOutputStream = xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}

	public ByteArrayOutputStream vendorSearchReport(List<VendorWiseReport> vendorList, List<String> months)
			throws Exception {

		String excelReport = "";
		String excelData = "";
		String concat = "";

		String heading = SNO + SEMICOLUMN + VENDORCODE + SEMICOLUMN + VENDOR  + SEMICOLUMN;
		for (int i = 0; i < months.size(); i++) {
			if (months.get(i).equals(JAN)) {
				heading+=  AMOUNT + DASH + JAN + SEMICOLUMN;
			}
			if (months.get(i).equals(FEB)) {
				heading+=  AMOUNT + DASH + FEB + SEMICOLUMN;
			}

			if (months.get(i).equals(MAR)) {
				heading+=  AMOUNT + DASH + MAR + SEMICOLUMN;
			}

			if (months.get(i).equals(APR)) {
				heading+=  AMOUNT + DASH + APR + SEMICOLUMN;
			}

			if (months.get(i).equals(MAY)) {
				heading+=  AMOUNT + DASH + MAY + SEMICOLUMN;
			}

			if (months.get(i).equals(JUN)) {
				heading+=  AMOUNT + DASH + JUN + SEMICOLUMN;
			}

			if (months.get(i).equals(JUL)) {
				heading+=  AMOUNT + DASH + JUL + SEMICOLUMN;
			}

			if (months.get(i).equals(AUG)) {
				heading+=  AMOUNT + DASH + AUG + SEMICOLUMN;
			}

			if (months.get(i).equals(SEP)) {
				heading+=  AMOUNT + DASH + SEP + SEMICOLUMN;
			}

			if (months.get(i).equals(OCT)) {
				heading+=  AMOUNT + DASH + OCT + SEMICOLUMN;
			}

			if (months.get(i).equals(NOV)) {
				heading+=  AMOUNT + DASH + NOV + SEMICOLUMN;
			}

			if (months.get(i).equals(DEC)) {
				heading+=  AMOUNT + DASH + DEC + SEMICOLUMN;
			}
		}
		
		heading += NEWLINE;

		int index = 1;
		if (!vendorList.isEmpty() && vendorList != null) {
			for (VendorWiseReport list : vendorList) {

				excelData = index + SEMICOLUMN
						+ (StringUtil.isEmptyTrim(list.getVendorCode()) ? BLANK : list.getVendorCode()) + SEMICOLUMN
						+ (StringUtil.isEmptyTrim(list.getVendorName()) ? BLANK : list.getVendorName()) + SEMICOLUMN;
				for (int i = 0; i < months.size(); i++) {
					if (months.get(i).equals(JAN)) {
						excelData += (list.getJAN() == null ? BLANK : list.getJAN()) + SEMICOLUMN;
					}

					if (months.get(i).equals(FEB)) {
						excelData += (list.getFEB() == null ? BLANK : list.getFEB()) + SEMICOLUMN;
					}

					if (months.get(i).equals(MAR)) {
						excelData += (list.getMAR() == null ? BLANK : list.getMAR()) + SEMICOLUMN;
					}

					if (months.get(i).equals(APR)) {
						excelData += (list.getAPR() == null ? BLANK : list.getAPR()) + SEMICOLUMN;
					}

					if (months.get(i).equals(MAY)) {
						excelData += (list.getMAY() == null ? BLANK : list.getMAY()) + SEMICOLUMN;
					}

					if (months.get(i).equals(JUN)) {
						excelData += (list.getJUN() == null ? BLANK : list.getJUN()) + SEMICOLUMN;
					}

					if (months.get(i).equals(JUL)) {
						excelData += (list.getJUL() == null ? BLANK : list.getJUL()) + SEMICOLUMN;
					}

					if (months.get(i).equals(AUG)) {
						excelData += (list.getAUG() == null ? BLANK : list.getAUG()) + SEMICOLUMN;
					}

					if (months.get(i).equals(SEP)) {
						excelData += (list.getSEP() == null ? BLANK : list.getSEP()) + SEMICOLUMN;
					}

					if (months.get(i).equals(OCT)) {
						excelData += (list.getOCT() == null ? BLANK : list.getOCT()) + SEMICOLUMN;
					}

					if (months.get(i).equals(NOV)) {
						excelData += (list.getNOV() == null ? BLANK : list.getNOV()) + SEMICOLUMN;
					}

					if (months.get(i).equals(DEC)) {
						excelData += (list.getDEC() == null ? BLANK : list.getDEC()) + SEMICOLUMN;
					}

				}
				excelData += NEWLINE;

				concat = concat + excelData;
				index = index + 1;

			}

		}

		excelReport = heading + concat;
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
						+ (list.getAPR() == null ? BLANK : list.getAPR()) + SEMICOLUMN
						+ (list.getMAY() == null ? BLANK : list.getMAY()) + SEMICOLUMN
						+ (list.getJUN() == null ? BLANK : list.getJUN()) + SEMICOLUMN
						+ (list.getJUL() == null ? BLANK : list.getJUL()) + SEMICOLUMN
						+ (list.getAUG() == null ? BLANK : list.getAUG()) + SEMICOLUMN
						+ (list.getSEP() == null ? BLANK : list.getSEP()) + SEMICOLUMN
						+ (list.getOCT() == null ? BLANK : list.getOCT()) + SEMICOLUMN
						+ (list.getNOV() == null ? BLANK : list.getNOV()) + SEMICOLUMN
						+ (list.getDEC() == null ? BLANK : list.getDEC()) + SEMICOLUMN
						+ (list.getJAN() == null ? BLANK : list.getJAN()) + SEMICOLUMN
						+ (list.getFEB() == null ? BLANK : list.getFEB()) + SEMICOLUMN
						+ (list.getMAR() == null ? BLANK : list.getMAR()) + SEMICOLUMN + NEWLINE;

				concat = concat + excelData;
				index = index + 1;

			}

		}

		excelReport = getWarehouseHeader() + concat;
		ByteArrayOutputStream byteArrayOutputStream = xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}

	public ByteArrayOutputStream warehouseSearchReport(List<WarehouseWiseReport> warehouseList, List<String> months)
			throws Exception {

		String excelReport = "";
		String excelData = "";
		String concat = "";

		String heading = SNO + SEMICOLUMN + WAREHOUSE + SEMICOLUMN;
		for (int i = 0; i < months.size(); i++) {
			if (months.get(i).equals(JAN)) {
				heading+=  AMOUNT + DASH + JAN + SEMICOLUMN;
			}
			if (months.get(i).equals(FEB)) {
				heading+=  AMOUNT + DASH + FEB + SEMICOLUMN;
			}

			if (months.get(i).equals(MAR)) {
				heading+=  AMOUNT + DASH + MAR + SEMICOLUMN;
			}

			if (months.get(i).equals(APR)) {
				heading+=  AMOUNT + DASH + APR + SEMICOLUMN;
			}

			if (months.get(i).equals(MAY)) {
				heading+=  AMOUNT + DASH + MAY + SEMICOLUMN;
			}

			if (months.get(i).equals(JUN)) {
				heading+=  AMOUNT + DASH + JUN + SEMICOLUMN;
			}

			if (months.get(i).equals(JUL)) {
				heading+=  AMOUNT + DASH + JUL + SEMICOLUMN;
			}

			if (months.get(i).equals(AUG)) {
				heading+=  AMOUNT + DASH + AUG + SEMICOLUMN;
			}

			if (months.get(i).equals(SEP)) {
				heading+=  AMOUNT + DASH + SEP + SEMICOLUMN;
			}

			if (months.get(i).equals(OCT)) {
				heading+=  AMOUNT + DASH + OCT + SEMICOLUMN;
			}

			if (months.get(i).equals(NOV)) {
				heading+=  AMOUNT + DASH + NOV + SEMICOLUMN;
			}

			if (months.get(i).equals(DEC)) {
				heading+=  AMOUNT + DASH + DEC + SEMICOLUMN;
			}
		}
		
		heading += NEWLINE;

		int index = 1;
		if (!warehouseList.isEmpty() && warehouseList != null) {
			for (WarehouseWiseReport list : warehouseList) {

				excelData = index + SEMICOLUMN
						+ (StringUtil.isEmptyTrim(list.getWarehouseName()) ? BLANK : list.getWarehouseName()) + SEMICOLUMN ;
				for (int i = 0; i < months.size(); i++) {
					if (months.get(i).equals(JAN)) {
						excelData += (list.getJAN() == null ? BLANK : list.getJAN()) + SEMICOLUMN;
					}

					if (months.get(i).equals(FEB)) {
						excelData += (list.getFEB() == null ? BLANK : list.getFEB()) + SEMICOLUMN;
					}

					if (months.get(i).equals(MAR)) {
						excelData += (list.getMAR() == null ? BLANK : list.getMAR()) + SEMICOLUMN;
					}

					if (months.get(i).equals(APR)) {
						excelData += (list.getAPR() == null ? BLANK : list.getAPR()) + SEMICOLUMN;
					}

					if (months.get(i).equals(MAY)) {
						excelData += (list.getMAY() == null ? BLANK : list.getMAY()) + SEMICOLUMN;
					}

					if (months.get(i).equals(JUN)) {
						excelData += (list.getJUN() == null ? BLANK : list.getJUN()) + SEMICOLUMN;
					}

					if (months.get(i).equals(JUL)) {
						excelData += (list.getJUL() == null ? BLANK : list.getJUL()) + SEMICOLUMN;
					}

					if (months.get(i).equals(AUG)) {
						excelData += (list.getAUG() == null ? BLANK : list.getAUG()) + SEMICOLUMN;
					}

					if (months.get(i).equals(SEP)) {
						excelData += (list.getSEP() == null ? BLANK : list.getSEP()) + SEMICOLUMN;
					}

					if (months.get(i).equals(OCT)) {
						excelData += (list.getOCT() == null ? BLANK : list.getOCT()) + SEMICOLUMN;
					}

					if (months.get(i).equals(NOV)) {
						excelData += (list.getNOV() == null ? BLANK : list.getNOV()) + SEMICOLUMN;
					}

					if (months.get(i).equals(DEC)) {
						excelData += (list.getDEC() == null ? BLANK : list.getDEC()) + SEMICOLUMN;
					}

				}
				excelData += NEWLINE;

				concat = concat + excelData;
				index = index + 1;

			}

		}

		excelReport = heading + concat;
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
						+ (list.getAprQty() == null ? BLANK : list.getAprQty()) + SEMICOLUMN
						+ (list.getAprAmt() == null ? BLANK : list.getAprAmt()) + SEMICOLUMN
						+ (list.getMayQty() == null ? BLANK : list.getMayQty()) + SEMICOLUMN
						+ (list.getMayAmt() == null ? BLANK : list.getMayAmt()) + SEMICOLUMN
						+ (list.getJunQty() == null ? BLANK : list.getJunQty()) + SEMICOLUMN
						+ (list.getJunAmt() == null ? BLANK : list.getJunAmt()) + SEMICOLUMN
						+ (list.getJulQty() == null ? BLANK : list.getJulQty()) + SEMICOLUMN
						+ (list.getJulAmt() == null ? BLANK : list.getJulAmt()) + SEMICOLUMN
						+ (list.getAugQty() == null ? BLANK : list.getAugQty()) + SEMICOLUMN
						+ (list.getAugAmt() == null ? BLANK : list.getAugAmt()) + SEMICOLUMN
						+ (list.getSeptQty() == null ? BLANK : list.getSeptQty()) + SEMICOLUMN
						+ (list.getSeptAmt() == null ? BLANK : list.getSeptAmt()) + SEMICOLUMN
						+ (list.getOctQty() == null ? BLANK : list.getOctQty()) + SEMICOLUMN
						+ (list.getOctAmt() == null ? BLANK : list.getOctAmt()) + SEMICOLUMN
						+ (list.getNovQty() == null ? BLANK : list.getNovQty()) + SEMICOLUMN
						+ (list.getNovAmt() == null ? BLANK : list.getNovAmt()) + SEMICOLUMN
						+ (list.getDecQty() == null ? BLANK : list.getDecQty()) + SEMICOLUMN
						+ (list.getDecAmt() == null ? BLANK : list.getDecAmt()) + SEMICOLUMN
						+ (list.getJanQty() == null ? BLANK : list.getJanQty()) + SEMICOLUMN
						+ (list.getJanAmt() == null ? BLANK : list.getJanAmt()) + SEMICOLUMN
						+ (list.getFebQty() == null ? BLANK : list.getFebQty()) + SEMICOLUMN
						+ (list.getFebAmt() == null ? BLANK : list.getFebAmt()) + SEMICOLUMN
						+ (list.getMarQty() == null ? BLANK : list.getMarQty()) + SEMICOLUMN
						+ (list.getMarAmt() == null ? BLANK : list.getMarAmt()) + SEMICOLUMN
						+ NEWLINE;

				concat = concat + excelData;
				index = index + 1;

			}

		}

		excelReport = getWarehouseHeader() + concat;
		ByteArrayOutputStream byteArrayOutputStream = xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}

	public ByteArrayOutputStream productSearchReport(List<ProductWiseReport> productList, List<String> months)
			throws Exception {

		String excelReport = "";
		String excelData = "";
		String concat = "";

		String heading = SNO + SEMICOLUMN + PRODUCTNO + SEMICOLUMN + DESCRIPTION +SEMICOLUMN;
		for (int i = 0; i < months.size(); i++) {
			if (months.get(i).equals(JAN)) {
				heading += QTY+DASH+JAN + SEMICOLUMN + AMOUNT + DASH + JAN + SEMICOLUMN;
			}
			if (months.get(i).equals(FEB)) {
				heading += QTY+DASH+FEB + SEMICOLUMN + AMOUNT + DASH + FEB + SEMICOLUMN;
			}

			if (months.get(i).equals(MAR)) {
				heading += QTY+DASH+MAR + SEMICOLUMN + AMOUNT + DASH + MAR + SEMICOLUMN;
			}

			if (months.get(i).equals(APR)) {
				heading += QTY+DASH+APR + SEMICOLUMN + AMOUNT + DASH + APR + SEMICOLUMN;
			}

			if (months.get(i).equals(MAY)) {
				heading += QTY+DASH+MAY + SEMICOLUMN + AMOUNT + DASH + MAY + SEMICOLUMN;
			}

			if (months.get(i).equals(JUN)) {
				heading += QTY+DASH+JUN + SEMICOLUMN + AMOUNT + DASH + JUN + SEMICOLUMN;
			}

			if (months.get(i).equals(JUL)) {
				heading += QTY+DASH+JUL + SEMICOLUMN + AMOUNT + DASH + JUL + SEMICOLUMN;
			}

			if (months.get(i).equals(AUG)) {
				heading += QTY+DASH+AUG + SEMICOLUMN + AMOUNT + DASH + AUG + SEMICOLUMN;
			}

			if (months.get(i).equals(SEP)) {
				heading += QTY+DASH+SEP + SEMICOLUMN + AMOUNT + DASH + SEP + SEMICOLUMN;
			}

			if (months.get(i).equals(OCT)) {
				heading += QTY+DASH+OCT + SEMICOLUMN + AMOUNT + DASH + OCT + SEMICOLUMN;
			}

			if (months.get(i).equals(NOV)) {
				heading += QTY+DASH+NOV + SEMICOLUMN + AMOUNT + DASH + NOV + SEMICOLUMN;
			}

			if (months.get(i).equals(DEC)) {
				heading += QTY+DASH+DEC + SEMICOLUMN + AMOUNT + DASH + DEC+ SEMICOLUMN;
			}
		}
		
		heading += NEWLINE;

		int index = 1;
		if (!productList.isEmpty() && productList != null) {
			for (ProductWiseReport list : productList) {

				excelData = index + SEMICOLUMN
						+ (StringUtil.isEmptyTrim(list.getProductName()) ? BLANK : list.getProductName()) + SEMICOLUMN
						+ (StringUtil.isEmptyTrim(list.getDescription()) ? BLANK : list.getDescription()) + SEMICOLUMN;
				for (int i = 0; i < months.size(); i++) {
					if (months.get(i).equals(JAN)) {
						excelData +=    (list.getJanQty() == null ? BLANK : list.getJanQty()) + SEMICOLUMN
								      + (list.getJanAmt() == null ? BLANK : list.getJanAmt()) + SEMICOLUMN;
					}

					if (months.get(i).equals(FEB)) {
						excelData +=    (list.getFebQty() == null ? BLANK : list.getFebQty()) + SEMICOLUMN
							          + (list.getFebAmt() == null ? BLANK : list.getFebAmt()) + SEMICOLUMN;
					}

					if (months.get(i).equals(MAR)) {
						excelData +=    (list.getMarQty() == null ? BLANK : list.getMarQty()) + SEMICOLUMN
							          + (list.getMarAmt() == null ? BLANK : list.getMarAmt()) + SEMICOLUMN;
					}

					if (months.get(i).equals(APR)) {
						excelData +=    (list.getAprQty() == null ? BLANK : list.getAprQty()) + SEMICOLUMN
							          + (list.getAprAmt() == null ? BLANK : list.getAprAmt()) + SEMICOLUMN;
					}

					if (months.get(i).equals(MAY)) {
						excelData +=    (list.getMayQty() == null ? BLANK : list.getMayQty()) + SEMICOLUMN
							          + (list.getMayAmt() == null ? BLANK : list.getMayAmt()) + SEMICOLUMN;
					}

					if (months.get(i).equals(JUN)) {
						excelData +=    (list.getJunQty() == null ? BLANK : list.getJunQty()) + SEMICOLUMN
							          + (list.getJunAmt() == null ? BLANK : list.getJunAmt()) + SEMICOLUMN;
					}

					if (months.get(i).equals(JUL)) {
						excelData +=    (list.getJulQty() == null ? BLANK : list.getJulQty()) + SEMICOLUMN
							          + (list.getJulAmt() == null ? BLANK : list.getJulAmt()) + SEMICOLUMN;
					}

					if (months.get(i).equals(AUG)) {
						excelData +=    (list.getAugQty() == null ? BLANK : list.getAugQty()) + SEMICOLUMN
							          + (list.getAugAmt() == null ? BLANK : list.getAugAmt()) + SEMICOLUMN;
					}

					if (months.get(i).equals(SEP)) {
						excelData +=    (list.getSeptQty() == null ? BLANK : list.getSeptQty()) + SEMICOLUMN
							          + (list.getSeptAmt() == null ? BLANK : list.getSeptAmt()) + SEMICOLUMN;
					}

					if (months.get(i).equals(OCT)) {
						excelData +=    (list.getOctQty() == null ? BLANK : list.getOctQty()) + SEMICOLUMN
							          + (list.getOctAmt() == null ? BLANK : list.getOctAmt()) + SEMICOLUMN;
					}

					if (months.get(i).equals(NOV)) {
						excelData +=    (list.getNovQty() == null ? BLANK : list.getNovQty()) + SEMICOLUMN
							          + (list.getNovAmt() == null ? BLANK : list.getNovAmt()) + SEMICOLUMN;
					}

					if (months.get(i).equals(DEC)) {
						excelData +=    (list.getDecQty() == null ? BLANK : list.getDecQty()) + SEMICOLUMN
							          + (list.getDecAmt() == null ? BLANK : list.getDecAmt()) + SEMICOLUMN;
					}

				}
				excelData += NEWLINE;

				concat = concat + excelData;
				index = index + 1;

			}

		}

		excelReport = heading + concat;
		ByteArrayOutputStream byteArrayOutputStream = xLSXDownload.preparedDownloadXLS(excelReport);
		return byteArrayOutputStream;
	}
}
