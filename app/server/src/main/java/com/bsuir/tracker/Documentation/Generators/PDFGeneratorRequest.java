package com.bsuir.tracker.Documentation.Generators;

import com.bsuir.tracker.Documentation.Factories.Entities.RequestInfo;
import com.bsuir.tracker.Documentation.Factories.Implementations.IFactory;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_CREATED;

/**
 * Created by angre on 11.05.2017.
 */
public class PDFGeneratorRequest implements IGenerator<RequestInfo>{
    private boolean isProtected = false;
    private IFactory<RequestInfo> modelViewer = null;

    public boolean getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(boolean value) {
        isProtected = value;
    }

    public IFactory<RequestInfo> getModelViewer()
    {
        return  modelViewer;
    }

    public void setModelViewer(IFactory<RequestInfo> item) {
        modelViewer = item;
    }

    public String getDocumentName() {
        //String filename = java.util.UUID.randomUUID().toString();
        String filename = "Result";
        return String.format("%s%s", filename, getDocumentType());
    }

    public String getDocumentType() {
        return ".pdf";
    }

    public String getContentType() {
        return "application/pdf";
    }

    @Override
    public void writeToResponse(List<RequestInfo> list, HttpServletResponse response) {
        response.setHeader("Content-disposition", "attachment;filename=" + getDocumentName());
        response.setHeader("Content-type", getContentType());
        response.setStatus(SC_CREATED);

        try {
            String fontFile = "./arial.ttf";
            String boldFontFile = "./arial_bold.ttf";
            String templateUrl = "./template.pdf";

            BaseFont bf = BaseFont.createFont(fontFile, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font bold = FontFactory.getFont(boldFontFile, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 25f);
            Font font = new Font(bf, 17f);
            Font middle = FontFactory.getFont(boldFontFile, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 17f);
            OutputStream outputStream = response.getOutputStream();
            PdfReader letterhead = new PdfReader(templateUrl);
            Rectangle pageSize = letterhead.getPageSizeWithRotation(1);
            Document document = new Document(pageSize);
            document.setMargins(25, 25, 150, 25);
            document.setMarginMirroringTopBottom(true);
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            writer.setPageEvent(new PdfNewPageEventHandler());
            setEncryption(writer);
            document.open();
            addHeader(letterhead, writer);
            addMetadata(document);
            addContent(list, document, font, bold, middle);
            document.close();
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEncryption(PdfWriter writer) throws DocumentException {
        if(isProtected) writer.setEncryption(null, null, ~(PdfWriter.ALLOW_COPY), PdfWriter.STANDARD_ENCRYPTION_128);
    }

    private void addContent(List<RequestInfo> model, Document document, Font generalFont, Font bold, Font middle) throws DocumentException {
        RequestInfo item = (RequestInfo) model.toArray()[0];

        Paragraph header = new Paragraph("ОБЪЯСНИТЕЛЬНАЯ", bold);
        Paragraph companyParagraph  = new Paragraph("Сотрудника компании " + item.getDestinationEmployeeCompany(), generalFont);
        Chunk fromAnnotation = new Chunk("От: ", generalFont);
        Chunk toAnnotation = new Chunk("К: ", generalFont);
        Chunk from = new Chunk("" + item.getSourceEmployeeName(), middle);
        Chunk to = new Chunk("" + item.getDestinationEmployeeName(), middle);

        Paragraph fromParagraph = new Paragraph();
        Paragraph toParagraph = new Paragraph();
        fromParagraph.add(fromAnnotation);
        fromParagraph.add(from);
        toParagraph.add(toAnnotation);
        toParagraph.add(to);

        Paragraph paragraph =  new Paragraph(" ");

        Paragraph contentParagraph = new Paragraph();
        contentParagraph.setFont(generalFont);
        if((item.getContent() == null) || (item.getContent().trim().equals(""))){
            Date dateEnd = new Date (item.getStartPeriod().getTime()/* * 1000*/);
            Date date = new Date (item.getDate().getTime()/* * 1000*/);
            contentParagraph.add("Я, " + item.getDestinationEmployeeName() + ", не выполнил рабочий план " + new SimpleDateFormat("yyyy-MM-dd").format(date) + " числа, до " + new SimpleDateFormat("yyyy-MM-dd").format(dateEnd) + " в связи с ____________________________________________________________________.");
        }
        else
        {
            Date dateEnd = new Date (item.getStartPeriod().getTime()/* * 1000*/);
            Date date = new Date (item.getDate().getTime()/* * 1000*/);
            contentParagraph.add("Я, " + item.getDestinationEmployeeName() + ", не выполнил рабочий план " + new SimpleDateFormat("yyyy-MM-dd").format(date) + " числа, до " + new SimpleDateFormat("yyyy-MM-dd").format(dateEnd) + " в связи с " + item.getContent());
        }

        Date dateStart = new Date (item.getStartPeriod().getTime()/* * 1000*/);
        Paragraph signy1 = new Paragraph("Сотрудник (Подпись): _____________   " + new SimpleDateFormat("yyyy-MM-dd").format(dateStart), generalFont);
        Paragraph signy2 = new Paragraph("Ответственный (Подпись): _____________   " + new SimpleDateFormat("yyyy-MM-dd").format(dateStart), generalFont);

        header.setAlignment(Element.ALIGN_CENTER);
        companyParagraph.setAlignment(Element.ALIGN_CENTER);
        fromParagraph.setAlignment(Element.ALIGN_RIGHT);
        toParagraph.setAlignment(Element.ALIGN_RIGHT);
        signy1.setAlignment(Element.ALIGN_RIGHT);
        signy2.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragraph);
        document.add(paragraph);
        document.add(header);
        document.add(companyParagraph);
        document.add(paragraph);
        document.add(fromParagraph);
        document.add(toParagraph);
        document.add(paragraph);
        document.add(contentParagraph);
        document.add(paragraph);
        document.add(paragraph);
        document.add(paragraph);
        document.add(paragraph);
        document.add(paragraph);
        document.add(signy1);
        document.add(signy2);
    }

    private void addMetadata(Document document) {
        document.addAuthor("uss-project");
        document.addCreationDate();
        document.addTitle("U.S.S Company Document");
    }

    private void addHeader(PdfReader letterhead, PdfWriter writer) {
        PdfContentByte content = writer.getDirectContent();
        PdfImportedPage page = writer.getImportedPage(letterhead, 1);
        content.addTemplate(page, 0, 0);
    }
}