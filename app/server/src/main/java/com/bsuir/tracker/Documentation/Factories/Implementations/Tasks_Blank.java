package com.bsuir.tracker.Documentation.Factories.Implementations;

import com.bsuir.tracker.Documentation.Factories.Entities.TasksInfo;
import org.springframework.cglib.core.internal.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Pavel on 25.05.2017.
 */
public class Tasks_Blank implements IFactory<TasksInfo> {
    private Function<TasksInfo, List<String>> mapper;
    private List<String> headers;

    public Tasks_Blank(){
        headers = new ArrayList<String>();

        headers.add("Имя");
        headers.add("Кол-во заданий выполнено");

        mapper = (blank) -> {
            List<String> fields = new ArrayList<>();

            fields.add(blank.getName());
            fields.add("" + blank.getCount());
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
    public void setMapper(Function<TasksInfo, List<String>> item) {
        mapper = item;
    }

    @Override
    public List<List<String>> map(List<TasksInfo> in) {
        if (mapper != null) return in.stream().map((item) -> mapper.apply(item)).collect(Collectors.toList());
        return null;
    }
}