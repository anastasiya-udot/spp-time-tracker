package com.bsuir.tracker.Documentation.Factories.Implementations;

import com.bsuir.tracker.Documentation.Factories.Entities.RequestInfo;
import org.springframework.cglib.core.internal.Function;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Pavel on 24.05.2017.
 */
public class Request_Blank implements IFactory<RequestInfo> {

    private Function<RequestInfo, List<String>> mapper;
    private List<String> headers;

    public Request_Blank()
    {
        headers = new ArrayList<String>();
        headers.add("Имя (От)");
        headers.add("Имя (К)");
        headers.add("Компания");
        headers.add("Дата");
        headers.add("Содержание");
        headers.add("Дата (от)");
        headers.add("Дата (до)");
        mapper = (blank) -> {
            List<String> fields = new ArrayList<>();

            fields.add(blank.getSourceEmployeeName());
            fields.add(blank.getDestinationEmployeeCompany());
            fields.add(blank.getDestinationEmployeeCompany());

            Date date;

            date = new Date (blank.getDate().getTime()/* * 1000*/);
            fields.add(new SimpleDateFormat("yyyy-MM-dd").format(date));
            fields.add(blank.getContent());
            date = new Date (blank.getStartPeriod().getTime()/* * 1000*/);
            fields.add(new SimpleDateFormat("yyyy-MM-dd").format(date));
            date = new Date (blank.getEndPeriod().getTime()/* * 1000*/);
            fields.add(new SimpleDateFormat("yyyy-MM-dd").format(date));
            return fields;
        };
    }
    public List<String> getHeaders() {
            return headers;
        }

    public void setHeaders(List<String> item) {
            headers = item;
        }

    public void setMapper(Function<RequestInfo, List<String>> item) {
            mapper = item;
        }

    public List<List<String>> map(List<RequestInfo> in) {
        if (mapper != null) return in.stream().map((item) -> mapper.apply(item)).collect(Collectors.toList());
        return null;
    }
}
