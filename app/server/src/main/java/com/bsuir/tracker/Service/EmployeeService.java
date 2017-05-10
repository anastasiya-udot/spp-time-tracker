package com.bsuir.tracker.Service;

import java.util.List;
import com.bsuir.tracker.entity.EmployeeEntity;

/**
 * Created by Pavel on 25.04.2017.
 */
public interface EmployeeService {

    public void addEmployee(EmployeeEntity employee);

    public EmployeeEntity getEmployee(int idEmployee);

    public EmployeeEntity getEmployeeByMail(String name);

    public List<EmployeeEntity> getAllEmployees();

    public EmployeeEntity updateEmployee(EmployeeEntity employee);

    public void deleteEmployee(int idEmployee);

}
