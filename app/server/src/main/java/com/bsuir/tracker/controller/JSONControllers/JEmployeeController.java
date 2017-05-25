package com.bsuir.tracker.controller.JSONControllers;

import com.bsuir.tracker.Service.*;
import com.bsuir.tracker.entity.CompanyEntity;
import com.bsuir.tracker.entity.EmployeeEntity;
import com.bsuir.tracker.entity.PeriodEntity;
import com.bsuir.tracker.entity.RequestEntity;
import com.bsuir.tracker.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    PeriodService periodService;

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

    @RequestMapping(value = "/get-all-employees/get", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getAllEmployees_get(@RequestBody @Validated EmployeesGetModel employeesGetModel, BindingResult bindingResult) throws Exception{
        Map<String, Object> response = new HashMap<>();
        if(bindingResult.hasErrors()){
            response.put("error", "Data Binding Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            List<EmployeesGetResModel> employeesGetResModelList = new ArrayList<>();

            List<EmployeeEntity> employeeEntityList = employeeService.getEmployeeByCompany(employeesGetModel.getCompanyId());
            for (EmployeeEntity employeeEntity: employeeEntityList)
            {
                List<PeriodEntity> periodEntities = periodService.getAllPeriodsByEmployeeId(employeeEntity.getIdemployee());
                List<PeriodEntity> periodEntitiesInLimits = new ArrayList<>();
                for (PeriodEntity periodEntity : periodEntities) {
                    if (periodEntity.getStart().getTime() >= employeesGetModel.getStartPeriod()) {
                        if ((periodEntity.getFinish() == null) || (periodEntity.getFinish().getTime() <= employeesGetModel.getFinishPeriod())) {
                            periodEntitiesInLimits.add(periodEntity);
                        }
                    }
                }

                long resultSumm = 0;
                for (PeriodEntity periodEntity : periodEntitiesInLimits) {
                    if (periodEntity.getFinish() != null) {
                        resultSumm += (periodEntity.getFinish().getTime() - periodEntity.getStart().getTime());
                    }
                }

                EmployeesGetResModel employeesGetResModel = new EmployeesGetResModel();
                employeesGetResModel.setId(employeeEntity.getIdemployee());
                employeesGetResModel.setName(employeeEntity.getName());
                employeesGetResModel.setSurname(employeeEntity.getSurname());
                employeesGetResModel.setPatronymic(employeeEntity.getPatronymic());
                employeesGetResModel.setWorktype(workdayTypeService.getWorkdayType(employeeEntity.getWorkdayIdworkdayType()).getTime());
                employeesGetResModel.setInFact(resultSumm);

                employeesGetResModelList.add(employeesGetResModel);
            }

            response.put("employees", employeesGetResModelList);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
