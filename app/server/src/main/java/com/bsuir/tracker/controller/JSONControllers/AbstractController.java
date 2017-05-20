package com.bsuir.tracker.controller.JSONControllers;

import com.bsuir.tracker.Service.CompanyService;
import com.bsuir.tracker.Service.EmployeeService;
import com.bsuir.tracker.Service.ProjectService;
import com.bsuir.tracker.controller.security.GetTokenService;
import com.bsuir.tracker.entity.CompanyEntity;
import com.bsuir.tracker.entity.EmployeeEntity;
import com.bsuir.tracker.entity.ProjectEntity;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

/**
 * Created by Pavel on 20.05.2017.
 */
@Controller
public class AbstractController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CompanyService companyService;
    @Autowired
    ProjectService projectService;
    @Autowired
    GetTokenService getTokenService;

    private int FULL_WORDAY_ID = 3;

    protected void AddProject(String name, int idCompany)
    {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName(name);
        projectEntity.setCompanyIdcompany(idCompany);
        projectService.addProject(projectEntity);
    }

    protected void AddCompany(String companyName, String companyLegalNumber) throws ConstraintViolationException, DataIntegrityViolationException {
        CompanyEntity newCompany = new CompanyEntity();
        newCompany.setName(companyName);
        newCompany.setLegalNumber(companyLegalNumber);
        companyService.addCompany(newCompany);
    }

    protected void AddEmployee(String name, String surname, String patronymic, String email, String pass, int idCompany, int idRole) throws ConstraintViolationException, DataIntegrityViolationException {
        EmployeeEntity newEmployee = new EmployeeEntity();
        newEmployee.setName(name);
        newEmployee.setSurname(surname);
        newEmployee.setPatronymic(patronymic);
        newEmployee.setEmail(email);
        newEmployee.setPassword(pass);
        newEmployee.setRoleIdrole(idRole);
        newEmployee.setCompanyIdcompany(idCompany);
        newEmployee.setWorkdayIdworkdayType(FULL_WORDAY_ID);
        employeeService.addEmployee(newEmployee);
    }

    protected boolean CheckNoSuchUser(String email)
    {
        if (null == employeeService.getEmployeeByMail(email))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
