package com.bsuir.tracker.controller.JSONControllers;

import com.bsuir.tracker.Service.CompanyService;
import com.bsuir.tracker.Service.EmployeeService;
import com.bsuir.tracker.Service.ImageService;
import com.bsuir.tracker.Service.RoleService;
import com.bsuir.tracker.controller.ImageController;
import com.bsuir.tracker.controller.security.GetTokenService;
import com.bsuir.tracker.entity.CompanyEntity;
import com.bsuir.tracker.entity.EmployeeEntity;
import com.bsuir.tracker.entity.ImageEntity;
import com.bsuir.tracker.entity.RoleEntity;
import com.bsuir.tracker.model.CompanyNameIdModel;
import com.bsuir.tracker.model.CompanyRegisterModel;
import com.bsuir.tracker.model.UserAuthorizationModel;
import org.hibernate.exception.ConstraintViolationException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.MapKeyColumn;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by Pavel on 18.05.2017.
 */
@Controller
public class JCompanyController extends AbstractController {
    private static  final Logger logger = Logger.getLogger(ImageController.class);
    public JCompanyController(){
        System.out.println("JCompanyController Initializer");
    }

    private int DIRECTOR_ROLE_ID = 5;

    @RequestMapping(value = "/companies/get", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity companies_get() throws Exception{
        Map<String, Map<String, List<CompanyNameIdModel>>> response = new HashMap<>();
        try {
            List<CompanyNameIdModel> listCompanyObjects = companyService.getAllCompaniesNameId();
            Map<String, List<CompanyNameIdModel>> resultMap = new HashMap<>();
            resultMap.put("companies", listCompanyObjects);
            response.put("data", resultMap);
            System.out.println("" + resultMap);
            return ResponseEntity.status(HttpStatus.OK).body(/*response*/ resultMap);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "/authorization/new-company/post", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity authorization_newCompany_post(@RequestBody @Validated CompanyRegisterModel companyRegisterModel, BindingResult bindingResult) throws Exception{
        Map<String, Object> response = new HashMap<>();
        if(bindingResult.hasErrors()){
            response.put("error", "Data Binding Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try {
            if(null == employeeService.getEmployeeByMail(companyRegisterModel.getEmail())){
                if(null == companyService.getCompanyByName(companyRegisterModel.getCompany())){
                    AddCompany(companyRegisterModel.getName(),
                                companyRegisterModel.getLegalNumber());

                    CompanyEntity createdCompany = companyService.getCompanyByName(companyRegisterModel.getName());

                    if(null != createdCompany)
                    {
                        AddEmployee(companyRegisterModel.getName(),
                                companyRegisterModel.getSurname(),
                                companyRegisterModel.getPatronymic(),
                                companyRegisterModel.getEmail(),
                                companyRegisterModel.getPassword(),
                                createdCompany.getIdcompany(),
                                DIRECTOR_ROLE_ID
                                );

                        if(null != employeeService.getEmployeeByMail(companyRegisterModel.getEmail())) {
                            response.put("id", createdCompany.getIdcompany());
                            response.put("name", createdCompany.getName());
                            return ResponseEntity.status(HttpStatus.OK).body(response);
                        }
                        else {
                            companyService.deleteCompany(createdCompany.getIdcompany());
                            response.put("error", "Employee Creation Error");
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                        }
                    }
                    else
                    {
                        response.put("error", "Company Creation Error");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                    }
                }
                else {
                    response.put("error", "company");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }
            }
            else{
                response.put("error", "email");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }
        catch (ConstraintViolationException e) {
            response.put("error", "Constraint Violation Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        catch (DataIntegrityViolationException e) {
            response.put("error", "SQL Data Integrity Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}