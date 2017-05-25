package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.EmployeeEntity;
import java.util.List;

/**
 * Created by Pavel on 25.04.2017.
 */
public interface EmployeeDAO {

    public void addEmployee(EmployeeEntity employee);

    public EmployeeEntity getEmployee(int idEmployee);

    public EmployeeEntity getEmployeeByMail(String  name);

    public List<EmployeeEntity> getEmployeeByCompany(int  id);

    public List<EmployeeEntity> getAllEmployees();

    public EmployeeEntity updateEmployee(EmployeeEntity employee);

    public void deleteEmployee(int idEmployee);

}
