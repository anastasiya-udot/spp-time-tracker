package com.bsuir.tracker.controller.JSONControllers;

import com.bsuir.tracker.Service.CompanyService;
import com.bsuir.tracker.Service.EmployeeService;
import com.bsuir.tracker.Service.RoleService;
import com.bsuir.tracker.Service.WorkdayTypeService;
import com.bsuir.tracker.entity.CompanyEntity;
import com.bsuir.tracker.entity.EmployeeEntity;
import com.bsuir.tracker.model.CompanyNameIdModel;
import com.bsuir.tracker.model.EmployeeGetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pavel on 20.05.2017.
 */
@Controller
public class JEmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    WorkdayTypeService workdayTypeService;
    @Autowired
    RoleService roleService;
    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "/employee/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity employee_get_id(@PathVariable int id) throws Exception{
        try {
            EmployeeEntity employeeEntity = employeeService.getEmployee(id);
            if(null != employeeEntity)
            {
                EmployeeGetModel employeeGetModel = new EmployeeGetModel();

                employeeGetModel.setId(employeeEntity.getIdemployee());
                employeeGetModel.setName(employeeEntity.getName());
                employeeGetModel.setSurname(employeeEntity.getSurname());
                employeeGetModel.setPatronymic(employeeEntity.getPatronymic());
                employeeGetModel.setEmail(employeeEntity.getEmail());

                employeeGetModel.setWorktype(workdayTypeService.getWorkdayType(employeeEntity.getWorkdayIdworkdayType()).getTime());
                employeeGetModel.setRoleCode(roleService.getRole(employeeEntity.getRoleIdrole()).getCode());

                CompanyEntity companyEntity = companyService.getCompany(employeeEntity.getCompanyIdcompany());
                CompanyNameIdModel companyNameIdModel = new CompanyNameIdModel();
                companyNameIdModel.setId(companyEntity.getIdcompany());
                companyNameIdModel.setName(companyEntity.getName());

                employeeGetModel.setCompany(companyNameIdModel);

                return ResponseEntity.status(HttpStatus.OK).body(employeeGetModel);
            }
            else {
                Map<String, String> response = new HashMap<>();

                response.put("error", "No such user");

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
