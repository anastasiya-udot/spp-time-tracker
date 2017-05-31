package com.bsuir.tracker.Documentation.Factories.Implementations;

import com.bsuir.tracker.Documentation.Factories.Entities.EmployeeTasksInfo;
import org.springframework.cglib.core.internal.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Pavel on 25.05.2017.
 */
public class EmployeeTasks_Blank implements IFactory<EmployeeTasksInfo> {
    private Function<EmployeeTasksInfo, List<String>> mapper;
    private List<String> headers;

    public EmployeeTasks_Blank(){
        headers = new ArrayList<String>();

        headers.add("Код");
        headers.add("Описание");

        mapper = (blank) -> {
            List<String> fields = new ArrayList<>();

            fields.add(blank.getCode());
            fields.add(blank.getDescription());
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
    public void setMapper(Function<EmployeeTasksInfo, List<String>> item) {
        mapper = item;
    }

    @Override
    public List<List<String>> map(List<EmployeeTasksInfo> in) {
        if (mapper != null) return in.stream().map((item) -> mapper.apply(item)).collect(Collectors.toList());
        return null;
    }
}