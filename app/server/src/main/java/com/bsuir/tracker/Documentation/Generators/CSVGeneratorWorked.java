package com.bsuir.tracker.Documentation.Generators;

import com.bsuir.tracker.Documentation.Factories.Entities.WorkedInfo;
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

public class CSVGeneratorWorked implements IGenerator<WorkedInfo> {
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
        return ".csv";
    }

    public String getContentType() {
        return "text/csv; charset=utf-8";
    }

    public void writeToResponse(List<WorkedInfo> list, HttpServletResponse response) {
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

    private void addContent(List<WorkedInfo> model, ICsvListWriter listWriter) throws IOException {
        WorkedInfo item = (WorkedInfo) model.toArray()[0];
        String[] headers = new String[]{"Тип", "Имя", "Компания", "Отработал", "Выполнил"};
        String[] items = new String[]{"Сводка", item.getEmployeeName(), item.getCompany(), item.getWorked(), item.getTasks()};
        listWriter.writeHeader(headers);
        List<List<String>> modelList =  modelViewer.map(model);

        listWriter.write(items);
    }
}