package com.bsuir.tracker.Documentation.Generators;

import com.bsuir.tracker.Documentation.Factories.Implementations.IFactory;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_CREATED;

/**
 * Created by angre on 29.04.2017.
 */

public class XLSGenerator<T> implements IGenerator<T>{
    private boolean isProtected = false;
    private IFactory<T> modelViewer = null;

    public boolean getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(boolean value) {
        isProtected = value;
    }

    public IFactory<T> getModelViewer() {
        return modelViewer;
    }

    public void setModelViewer(IFactory<T> item) {
        modelViewer = item;
    }

    public String getDocumentName() {
        String filename = "Result";
        return String.format("%s%s", filename, getDocumentType());
    }

    public String getDocumentType() {
        return ".xls";
    }

    public String getContentType() {
        return "application/vnd.ms-excel";
    }

    public void writeToResponse(List<T> list, HttpServletResponse response) {
        response.setHeader("Content-type", getContentType());
        response.setHeader("Content-disposition", "attachment;filename=" + getDocumentName());
        response.setStatus(SC_CREATED);

        XSSFWorkbook workbook = getWorkBook(list);

        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addContent(List<T> model, Sheet sheet, CellStyle styleHeader, CellStyle styleData) {
        Row name = sheet.createRow(0);
        name.createCell(0).setCellValue("Перечень");
        name.getCell(0).setCellStyle(styleHeader);
        for (int i = 0; i < modelViewer.getHeaders().size() - 1; i++){
            name.createCell(i + 1);
            name.getCell(i + 1).setCellStyle(styleHeader);
        }
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,modelViewer.getHeaders().size() - 1));

        Row header = sheet.createRow(1);

        for (int i = 0; i < modelViewer.getHeaders().size(); i++) {
            String caption = modelViewer.getHeaders().get(i);
            header.createCell(i).setCellValue(caption);
            header.getCell(i).setCellStyle(styleHeader);
            header.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
        }

        int rowCount = 2;

        for (List<String> row : modelViewer.map(model)) {
            Row tableRow = sheet.createRow(rowCount++);

            for (int i = 0; i < row.size(); i++) {
                String item = row.get(i);
                tableRow.createCell(i).setCellValue(item);
                tableRow.getCell(i).setCellStyle(styleData);
                tableRow.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
            }
        }

        for (int i = 0; i < modelViewer.getHeaders().size(); i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private XSSFWorkbook getWorkBook(List<T> list)
    {
        XSSFWorkbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Document");
        sheet.setDefaultColumnWidth(30);
        PrintSetup ps = sheet.getPrintSetup();
        sheet.setAutobreaks(true);

        ps.setFitHeight((short)1);
        ps.setFitWidth((short)1);
        sheet.setFitToPage(true);

        CellStyle styleHeader = getCellStyleHeader(workbook);
        CellStyle styleDate = getCellStyleData(workbook);
        addContent(list, sheet, styleHeader, styleDate);

        return workbook;
    }

    private CellStyle getCellStyleHeader(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.index);
        Font font = workbook.createFont();
        font.setFontName("Arial");

        style.setWrapText(true);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.BLACK.index);
        style.setFont(font);

        return style;
    }

    private CellStyle getCellStyleData(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.index);
        Font font = workbook.createFont();
        font.setFontName("Arial");

        style.setWrapText(true);
        font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        font.setColor(HSSFColor.BLACK.index);
        style.setFont(font);

        return style;
    }
}
