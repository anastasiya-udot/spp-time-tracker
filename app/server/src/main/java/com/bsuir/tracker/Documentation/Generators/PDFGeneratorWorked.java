package com.bsuir.tracker.Documentation.Generators;

/**
 * Created by Pavel on 25.05.2017.
 */
import com.bsuir.tracker.Documentation.Factories.Entities.WorkedInfo;
import com.bsuir.tracker.Documentation.Factories.Implementations.IFactory;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_CREATED;

/**
 * Created by angre on 11.05.2017.
 */
public class PDFGeneratorWorked implements IGenerator<WorkedInfo>{
    private boolean isProtected = false;
    private IFactory<WorkedInfo> modelViewer = null;

    public boolean getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(boolean value) {
        isProtected = value;
    }

    public IFactory<WorkedInfo> getModelViewer()
    {
        return  modelViewer;
    }

    public void setModelViewer(IFactory<WorkedInfo> item) {
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
    public void writeToResponse(List<WorkedInfo> list, HttpServletResponse response) {
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

    private void addContent(List<WorkedInfo> model, Document document, Font generalFont, Font bold, Font middle) throws DocumentException {
        WorkedInfo item = (WorkedInfo) model.toArray()[0];

        Paragraph header = new Paragraph("Сводка", bold);
        Paragraph companyParagraph  = new Paragraph("По сотруднику компании " + item.getCompany(), generalFont);
        Paragraph name = new Paragraph(item.getEmployeeName(), generalFont);
        Paragraph paragraph =  new Paragraph(" ");

        Paragraph contentParagraph = new Paragraph("За подконтрольный период сотрудником "
                                                        + item.getEmployeeName()
                                                        + " ,было проведено "
                                                        + item.getWorked()
                                                        + " рабочего времени, в течение которого было выполнено "
                                                        + item.getTasks()
                                                        + " заданий.", generalFont);
        contentParagraph.setFont(generalFont);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        Paragraph signy = new Paragraph("Ответственный (Подпись): _____________   " + dtf.format(localDate), generalFont);

        header.setAlignment(Element.ALIGN_CENTER);
        companyParagraph.setAlignment(Element.ALIGN_CENTER);
        name.setAlignment(Element.ALIGN_CENTER);
        signy.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragraph);
        document.add(paragraph);
        document.add(header);
        document.add(companyParagraph);
        document.add(name);
        document.add(paragraph);
        document.add(contentParagraph);
        document.add(paragraph);
        document.add(paragraph);
        document.add(paragraph);
        document.add(paragraph);
        document.add(paragraph);
        document.add(signy);
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