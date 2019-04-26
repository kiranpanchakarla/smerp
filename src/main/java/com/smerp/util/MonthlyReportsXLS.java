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
	public static final String DOCDATE = "Document Date";
	public static final String DEPARTMENT = "Department";
	public static final String PO = "Purchase Order";
	public static final String JAN = "JAN";
	public static final String FEB = "FEB";
	public static final String MAR = "MAR";
	public static final String APR = "APR";
	public static final String MAY = "May";
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
				+ SEMICOLUMN + MAY + SEMICOLUMN + JUN + SEMICOLUMN + JUL + SEMICOLUMN + AUG + SEMICOLUMN + SEP
				+ SEMICOLUMN + OCT + SEMICOLUMN + NOV + SEMICOLUMN + DEC + SEMICOLUMN + JAN + SEMICOLUMN + FEB
				+ SEMICOLUMN + MAR + SEMICOLUMN + NEWLINE;
		return heading;
	}
	
	private String getWarehouseHeader() {
		String heading = SNO + SEMICOLUMN + WAREHOUSE + SEMICOLUMN  + APR
				+ SEMICOLUMN + MAY + SEMICOLUMN + JUN + SEMICOLUMN + JUL + SEMICOLUMN + AUG + SEMICOLUMN + SEP
				+ SEMICOLUMN + OCT + SEMICOLUMN + NOV + SEMICOLUMN + DEC + SEMICOLUMN + JAN + SEMICOLUMN + FEB
				+ SEMICOLUMN + MAR + SEMICOLUMN + NEWLINE;
		return heading;
	}
	
	private String getProductHeader() {
		String heading = SNO + SEMICOLUMN + PRODUCTNO + SEMICOLUMN + DESCRIPTION + SEMICOLUMN  + QTY + SEMICOLUMN + APR
				+ SEMICOLUMN + QTY + SEMICOLUMN + MAY + SEMICOLUMN + QTY + SEMICOLUMN + JUN + SEMICOLUMN + QTY + SEMICOLUMN 
				+ JUL + SEMICOLUMN + QTY + SEMICOLUMN + AUG + SEMICOLUMN + QTY + SEMICOLUMN + SEP + SEMICOLUMN   
				+ QTY + SEMICOLUMN +  OCT + SEMICOLUMN + QTY + SEMICOLUMN + NOV + SEMICOLUMN +QTY + SEMICOLUMN +  DEC + SEMICOLUMN 
				+ QTY + SEMICOLUMN + JAN + SEMICOLUMN + QTY + SEMICOLUMN + FEB + QTY + SEMICOLUMN  + MAR + SEMICOLUMN + NEWLINE;
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
				heading += JAN + SEMICOLUMN;
			}
			if (months.get(i).equals(FEB)) {
				heading += FEB + SEMICOLUMN;
			}

			if (months.get(i).equals(MAR)) {
				heading += MAR + SEMICOLUMN;
			}

			if (months.get(i).equals(APR)) {
				heading += APR + SEMICOLUMN;
			}

			if (months.get(i).equals(MAY)) {
				heading += MAY + SEMICOLUMN;
			}

			if (months.get(i).equals(JUN)) {
				heading += JUN + SEMICOLUMN;
			}

			if (months.get(i).equals(JUL)) {
				heading += JUL + SEMICOLUMN;
			}

			if (months.get(i).equals(AUG)) {
				heading += AUG + SEMICOLUMN;
			}

			if (months.get(i).equals(SEP)) {
				heading += SEP + SEMICOLUMN;
			}

			if (months.get(i).equals(OCT)) {
				heading += OCT + SEMICOLUMN;
			}

			if (months.get(i).equals(NOV)) {
				heading += NOV + SEMICOLUMN;
			}

			if (months.get(i).equals(DEC)) {
				heading += DEC + SEMICOLUMN;
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
				heading += JAN + SEMICOLUMN;
			}
			if (months.get(i).equals(FEB)) {
				heading += FEB + SEMICOLUMN;
			}

			if (months.get(i).equals(MAR)) {
				heading += MAR + SEMICOLUMN;
			}

			if (months.get(i).equals(APR)) {
				heading += APR + SEMICOLUMN;
			}

			if (months.get(i).equals(MAY)) {
				heading += MAY + SEMICOLUMN;
			}

			if (months.get(i).equals(JUN)) {
				heading += JUN + SEMICOLUMN;
			}

			if (months.get(i).equals(JUL)) {
				heading += JUL + SEMICOLUMN;
			}

			if (months.get(i).equals(AUG)) {
				heading += AUG + SEMICOLUMN;
			}

			if (months.get(i).equals(SEP)) {
				heading += SEP + SEMICOLUMN;
			}

			if (months.get(i).equals(OCT)) {
				heading += OCT + SEMICOLUMN;
			}

			if (months.get(i).equals(NOV)) {
				heading += NOV + SEMICOLUMN;
			}

			if (months.get(i).equals(DEC)) {
				heading += DEC + SEMICOLUMN;
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

}
