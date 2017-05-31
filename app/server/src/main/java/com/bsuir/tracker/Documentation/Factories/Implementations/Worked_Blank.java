package com.bsuir.tracker.Documentation.Factories.Implementations;

import com.bsuir.tracker.Documentation.Factories.Entities.WorkedInfo;
import org.springframework.cglib.core.internal.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Pavel on 25.05.2017.
 */
public class Worked_Blank implements IFactory<WorkedInfo> {
    private Function<WorkedInfo, List<String>> mapper;
    private List<String> headers;

    public Worked_Blank(){
        headers = new ArrayList<String>();

        headers.add("Имя");
        headers.add("Компания");
        headers.add("Отработал");
        headers.add("Выполнил");

        mapper = (blank) -> {
            List<String> fields = new ArrayList<>();

            fields.add(blank.getEmployeeName());
            fields.add(blank.getCompany());
            fields.add(blank.getWorked());
            fields.add(blank.getTasks());
            return fields;
        };
    }

    @Override
    public List<String> getHeaders() {
        return headers;
    }

    @Override
    public void setHeaders(List<String> item) {
        headers = item;
    }

    @Override
    public void setMapper(Function<WorkedInfo, List<String>> item) {
        mapper = item;
    }

    @Override
    public List<List<String>> map(List<WorkedInfo> in) {
        if (mapper != null) return in.stream().map((item) -> mapper.apply(item)).collect(Collectors.toList());
        return null;
    }
}
