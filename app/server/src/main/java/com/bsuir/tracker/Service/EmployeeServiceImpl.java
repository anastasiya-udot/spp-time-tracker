package com.bsuir.tracker.Service;

import com.bsuir.tracker.DAO.EmployeeDAO;
import com.bsuir.tracker.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pavel on 25.04.2017.
 */

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;

    @Transactional
    public void addEmployee(EmployeeEntity employee) {
        employeeDAO.addEmployee(employee);
    }

    public EmployeeEntity getEmployee(int idEmployee) {
        return employeeDAO.getEmployee(idEmployee);
    }

    @Transactional
    public List<EmployeeEntity> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public EmployeeEntity updateEmployee(EmployeeEntity employee) {
        return employeeDAO.updateEmployee(employee);
    }

    @Transactional
    public void deleteEmployee(int idEmployee) {
        employeeDAO.deleteEmployee(idEmployee);
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }
}
