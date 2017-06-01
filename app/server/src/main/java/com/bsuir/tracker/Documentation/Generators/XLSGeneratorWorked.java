package com.bsuir.tracker.Documentation.Generators;

import com.bsuir.tracker.Documentation.Factories.Entities.WorkedInfo;
import com.bsuir.tracker.Documentation.Factories.Implementations.IFactory;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_CREATED;

/**
 * Created by Pavel on 24.05.2017.
 */

public class XLSGeneratorWorked implements IGenerator<WorkedInfo> {
    private boolean isProtected = false;
    private IFactory<WorkedInfo> modelViewer = null;

    public boolean getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(boolean value) {
        isProtected = value;
    }

    public IFactory<WorkedInfo> getModelViewer() {
        return modelViewer;
    }

    public void setModelViewer(IFactory<WorkedInfo> item) {
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

    public void writeToResponse(List<WorkedInfo> list, HttpServletResponse response) {
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

    private void addContent(List<WorkedInfo> model, Sheet sheet, CellStyle styleHeader, CellStyle styleData) {
        WorkedInfo item = (WorkedInfo) model.toArray()[0];

        Row header = sheet.createRow(0);
        Row company = sheet.createRow(1);
        Row name = sheet.createRow(2);
        Row worked = sheet.createRow(3);
        Row completed = sheet.createRow(4);

        header.createCell(0).setCellValue("Сводка");
        header.getCell(0).setCellStyle(styleHeader);
        header.createCell(1);
        header.getCell(1).setCellStyle(styleHeader);

        company.createCell(0).setCellValue("Компания");
        company.getCell(0).setCellStyle(styleHeader);
        company.createCell(1).setCellValue(item.getCompany());
        company.getCell(1).setCellStyle(styleData);

        name.createCell(0).setCellValue("Имя");
        name.getCell(0).setCellStyle(styleHeader);
        name.createCell(1).setCellValue(item.getEmployeeName());
        name.getCell(1).setCellStyle(styleData);

        worked.createCell(0).setCellValue("Проработано");
        worked.getCell(0).setCellStyle(styleHeader);
        worked.createCell(1).setCellValue(item.getWorked());
        worked.getCell(1).setCellStyle(styleData);

        completed.createCell(0).setCellValue("Выполнено");
        completed.getCell(0).setCellStyle(styleHeader);
        completed.createCell(1).setCellValue(item.getTasks());
        completed.getCell(1).setCellStyle(styleData);

        sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));
        //sheet.addMergedRegion(new CellRangeAddress(1,1,0,1));

        for (int i = 0; i < 2; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private XSSFWorkbook getWorkBook(List<WorkedInfo> list)
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