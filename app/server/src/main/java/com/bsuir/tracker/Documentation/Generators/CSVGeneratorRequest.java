package com.bsuir.tracker.Documentation.Generators;

import com.bsuir.tracker.Documentation.Factories.Entities.RequestInfo;
import com.bsuir.tracker.Documentation.Factories.Implementations.IFactory;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_CREATED;

/**
 * Created by angre on 12.05.2017.
 */
public class CSVGeneratorRequest implements IGenerator<RequestInfo> {
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
        return ".csv";
    }

    public String getContentType() {
        return "text/csv; charset=utf-8";
    }

    public void writeToResponse(List<RequestInfo> list, HttpServletResponse response) {
        response.setHeader("Content-disposition", "attachment;filename=" + getDocumentName());
        response.setHeader("Content-type", getContentType());
        response.setStatus(SC_CREATED);

        try {
            ICsvListWriter listWriter = new CsvListWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
            addContent(list, listWriter);
            listWriter.flush();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addContent(List<RequestInfo> model, ICsvListWriter listWriter) throws IOException {
        RequestInfo item = (RequestInfo) model.toArray()[0];
        //------------------------------------------------------------------------------
        Date tempDate = new Date (item.getDate().getTime());
        String date =  new SimpleDateFormat("yyyy-MM-dd").format(tempDate);

        tempDate = new Date (item.getStartPeriod().getTime());
        String startDate = new SimpleDateFormat("yyyy-MM-dd").format(tempDate);

        tempDate = new Date (item.getEndPeriod().getTime());
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(tempDate);
        //------------------------------------------------------------------------------
        String[] headers = new String[]{"Тип документа", "Компания", "Имя (От)", "Имя (К)", "Дата", "Содержание", "Дата (от)", "Дата (до)"};
        String[] items = new String[]{"Ообъяснительная", item.getDestinationEmployeeCompany(), item.getSourceEmployeeName(), item.getDestinationEmployeeName(), date , item.getContent(), startDate, endDate};
        listWriter.writeHeader(headers);
        List<List<String>> modelList =  modelViewer.map(model);

        listWriter.write(items);
    }
}
