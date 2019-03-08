package com.smerp.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class XLSXDownload {

    @SuppressWarnings("deprecation")
    public ByteArrayOutputStream preparedDownloadXLS(String usersDataFile) throws Exception {
        ArrayList<ArrayList<String>> allRowAndColData = new ArrayList<ArrayList<String>>();
        ArrayList<String> oneRowData = null;
        String currentLine;
        InputStream fileInputStream = null;
        DataInputStream dataInputStream = null;
        ByteArrayOutputStream byt = new ByteArrayOutputStream();
        try {
            fileInputStream = new ByteArrayInputStream(usersDataFile.getBytes("UTF-8"));
            dataInputStream = new DataInputStream(fileInputStream);
            while ((currentLine = dataInputStream.readLine()) != null) {
                oneRowData = new ArrayList<String>();
                String oneRowArray[] = currentLine.split(";");
                for (int j = 0; j < oneRowArray.length; j++) {
                    oneRowData.add(oneRowArray[j].replace("\"", "").trim());
                }
                allRowAndColData.add(oneRowData);
            }

            XSSFWorkbook workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet();
            CellStyle style = workBook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.getIndex());
            //style.setFillPattern(CellStyle.MAROON);
            //style.setWrapText(true);
            // style.setVerticalAlignment(CellStyle.ALIGN_GENERAL);
            Font font = workBook.createFont();
            font.setColor(IndexedColors.BLACK.getIndex());
            font.setBold(true);
            style.setFont(font);

            CellStyle format = workBook.createCellStyle();
            //format.setWrapText(true);
            //style.setVerticalAlignment(CellStyle.ALIGN_LEFT);
            for (int eachRow = 0; eachRow < allRowAndColData.size(); eachRow++) {
                ArrayList<?> rowData = (ArrayList<?>) allRowAndColData.get(eachRow);
                XSSFRow row = sheet.createRow((short) 0 + eachRow);
                for (int eachCell = 0; eachCell < rowData.size(); eachCell++) {
                    XSSFCell cell = row.createCell((short) eachCell);
                    if (NumberUtils.isNumber(rowData.get(eachCell).toString())) {
                        double d = Double.parseDouble(rowData.get(eachCell).toString());
                        cell.setCellValue(d);
                        if (eachRow == 0) {
                            cell.setCellStyle(style);
                        } else {
                            cell.setCellStyle(format);
                        }

                        if (rowData.get(eachCell) == null) {
                            cell.setCellValue(0.0);
                        }

                    } else {
                        cell.setCellValue(rowData.get(eachCell).toString());
                        if (eachRow == 0) {
                            cell.setCellStyle(style);
                        } else {
                            cell.setCellStyle(format);
                        }
                    }
                    sheet.setColumnWidth(eachCell, 5000);
                }
            }
            workBook.write(byt);
        } catch (Exception exception) {
            throw new Exception("Exception while generating excel file from CSV file in " + this.getClass()
                    + ". Exception : " + exception.getMessage());
        } finally {
            if (fileInputStream != null)
                fileInputStream.close();
            if (dataInputStream != null)
                dataInputStream.close();
        }
        return byt;
    }
    
    

}
