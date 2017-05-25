package com.bsuir.tracker.Documentation.Generators;

/**
 * Created by Pavel on 24.05.2017.
 */
import com.bsuir.tracker.Documentation.Factories.Entities.RequestInfo;
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
 * Created by angre on 12.05.2017.
 */
public class XLSGeneratorRequest implements IGenerator<RequestInfo> {
    private boolean isProtected = false;
    private IFactory<RequestInfo> modelViewer = null;

    public boolean getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(boolean value) {
        isProtected = value;
    }

    public IFactory<RequestInfo> getModelViewer() {
        return modelViewer;
    }

    public void setModelViewer(IFactory<RequestInfo> item) {
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

    public void writeToResponse(List<RequestInfo> list, HttpServletResponse response) {
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

    private void addContent(List<RequestInfo> model, Sheet sheet, CellStyle styleHeader, CellStyle styleData) {
        RequestInfo item = (RequestInfo) model.toArray()[0];

        Row header = sheet.createRow(0);
        Row company = sheet.createRow(1);
        Row nameFrom = sheet.createRow(2);
        Row nameTo = sheet.createRow(3);
        Row date = sheet.createRow(4);
        Row content = sheet.createRow(5);
        Row dateFrom = sheet.createRow(6);
        Row dateTo = sheet.createRow(7);

        header.createCell(0).setCellValue("Ообъяснительная");
        header.getCell(0).setCellStyle(styleHeader);
        header.createCell(1);
        header.getCell(1).setCellStyle(styleHeader);

        company.createCell(0).setCellValue("Компания");
        company.getCell(0).setCellStyle(styleHeader);
        company.createCell(1).setCellValue(item.getDestinationEmployeeCompany());
        company.getCell(1).setCellStyle(styleData);

        nameFrom.createCell(0).setCellValue("Имя (От)");
        nameFrom.getCell(0).setCellStyle(styleHeader);
        nameFrom.createCell(1).setCellValue(item.getSourceEmployeeName());
        nameFrom.getCell(1).setCellStyle(styleData);

        nameTo.createCell(0).setCellValue("Имя (К)");
        nameTo.getCell(0).setCellStyle(styleHeader);
        nameTo.createCell(1).setCellValue(item.getDestinationEmployeeName());
        nameTo.getCell(1).setCellStyle(styleData);

        Date tempDate = new Date (item.getDate().getTime());
        date.createCell(0).setCellValue("Дата");
        date.getCell(0).setCellStyle(styleHeader);
        date.createCell(1).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(tempDate));
        date.getCell(1).setCellStyle(styleData);

        content.createCell(0).setCellValue("Содержание");
        content.getCell(0).setCellStyle(styleHeader);
        content.createCell(1).setCellValue(item.getContent());
        content.getCell(1).setCellStyle(styleData);

        tempDate = new Date (item.getStartPeriod().getTime());
        dateFrom.createCell(0).setCellValue("Дата (от)");
        dateFrom.getCell(0).setCellStyle(styleHeader);
        dateFrom.createCell(1).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(tempDate));
        dateFrom.getCell(1).setCellStyle(styleData);

        tempDate = new Date (item.getEndPeriod().getTime());
        dateTo.createCell(0).setCellValue("Дата (до)");
        dateTo.getCell(0).setCellStyle(styleHeader);
        dateTo.createCell(1).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(tempDate));
        dateTo.getCell(1).setCellStyle(styleData);

        //for (int i=0; i<8; i++)
        //    for(int j=0; j<2; j++) sheet.getRow(i).getCell(j).setCellStyle(style);

        sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));
        //sheet.addMergedRegion(new CellRangeAddress(1,1,0,1));

        for (int i = 0; i < 2; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private XSSFWorkbook getWorkBook(List<RequestInfo> list)
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