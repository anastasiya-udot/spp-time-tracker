package com.bsuir.tracker.Service;

import com.bsuir.tracker.DAO.EmployeeDAO;
import com.bsuir.tracker.DAO.RoleDAO;
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
    @Autowired
    private RoleDAO roleDAO;

    private  String ADMINISTRATOR_ROLE_IDENTIFIER = "26";
    private  String DIRECTOR_ROLE_IDENTIFIER = "31";

    @Transactional
    public void addEmployee(EmployeeEntity employee) {
        employeeDAO.addEmployee(employee);
    }

    public EmployeeEntity getEmployee(int idEmployee) {
        return employeeDAO.getEmployee(idEmployee);
    }

    public EmployeeEntity getEmployeeByMail(String mail) {return employeeDAO.getEmployeeByMail(mail); }

    @Transactional
    public List<EmployeeEntity> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public EmployeeEntity updateEmployee(EmployeeEntity employee) {
        return employeeDAO.updateEmployee(employee);
    }

    @Transactional
    public void deleteEmployee(int idEmployee) {
        if (countAdministrators() > 1)
        {
            employeeDAO.deleteEmployee(idEmployee);
        }
    }

    public int countAdministrators()
    {
        int counter = 0;
        List<EmployeeEntity> employeeEntityList = employeeDAO.getAllEmployees();
        if (employeeEntityList != null)
            for(EmployeeEntity employeeEntity : employeeEntityList)
            {
                String code = roleDAO.getRole(employeeEntity.getRoleIdrole()).getCode();
                if (code.equals(ADMINISTRATOR_ROLE_IDENTIFIER))
                {
                    counter++;
                }
            }
        return counter;
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }
}
