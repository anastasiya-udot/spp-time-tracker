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
        if (checkCanDeleteUpdate(employee.getIdemployee()))
            return employeeDAO.updateEmployee(employee);
        else
            return null;
    }

    @Transactional
    public void deleteEmployee(int idEmployee) {
        if(employeeDAO.getEmployee(idEmployee) == null)
        {
            return;
        }
        if (!checkCanDeleteUpdate(idEmployee) /*getRoleCode(idEmployee).equals(ADMINISTRATOR_ROLE_IDENTIFIER) && (countAdministrators() <= 1)*/)
        {
            return;
        }
        employeeDAO.deleteEmployee(idEmployee);
    }

    public boolean checkCanDeleteUpdate(int id)
    {
        int counter = 0;
        boolean isAdmin = false;
        boolean result = true;
        List<EmployeeEntity> employeeEntityList = employeeDAO.getAllEmployees();
        if (employeeEntityList != null)
            for(EmployeeEntity employeeEntity : employeeEntityList)
            {
                String code = roleDAO.getRole(employeeEntity.getRoleIdrole()).getCode();
                if (code.equals(ADMINISTRATOR_ROLE_IDENTIFIER))
                {
                    counter++;
                    if(employeeEntity.getIdemployee() == id)
                    {
                        isAdmin = true;
                    }
                }
            }
        if (isAdmin && (counter <= 1))
        {
            result = false;
        }
        else
        {
            result = true;
        }
        return result;
    }

    /*public int countAdministrators()
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

    public String getRoleCode(int id)
    {
        return (roleDAO.getRole(employeeDAO.getEmployee(id).getRoleIdrole())).getCode();
    }*/

    public void setEmployeeDAO(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }
}
