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

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by Pavel on 18.05.2017.
 */
@Controller
public class JCompanyController {
    private static  final Logger logger = Logger.getLogger(ImageController.class);
    public JCompanyController(){
        System.out.println("JCompanyController Initializer");
    }

    @Autowired
    private CompanyService companyService;
    @Autowired
    private GetTokenService getTokenService;

    @RequestMapping(value = "/companies/get", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity index_angular() throws Exception{
        Map<String, Map<String, List<Object>>> response = new HashMap<>();
        try {
            List<Object> listCompanyObjects = companyService.getAllCompaniesNameId();
            Map<String, List<Object>> resultMap = new HashMap<>();
            resultMap.put("companies", listCompanyObjects);
            response.put("data", resultMap);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /*
    @RequestMapping(value = "/companies/get", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity companies_get(@RequestBody @Validated EmployeeEntity employee, BindingResult bindingResult) throws IOException {
        Map<String, Object> response = new HashMap<>();
        if(bindingResult.hasErrors()){
            response.put("error", "Data Binding Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try {
            if ((employee.getIdemployee() == 0) && (CheckNoSuchUser(employee)))
            {
                employeeService.addEmployee(employee);
                return ResponseEntity.status(HttpStatus.OK).body(null);
            } else {
                response.put("error", "Email");
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

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity sign_in(@RequestBody @Validated UserAuthorizationModel userData, BindingResult bindingResult) throws IOException, Exception {
        Map<String, Object> response = new HashMap<>();
        if(bindingResult.hasErrors()){
            response.put("error", "Data Binding Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try {
            EmployeeEntity user = employeeService.getEmployeeByMail(userData.getEmail());
            if (user != null)
            {
                String token = null;
                token = getTokenService.getToken(user.getEmail(), user.getPassword());
                if(token != null)
                {
                    response.put("token", token);
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
                else
                {
                    response.put("error", "password");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }
            }
            else
            {
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

    private boolean CheckNoSuchUser(EmployeeEntity employeeEntity)
    {
        if (null == employeeService.getEmployeeByMail(employeeEntity.getEmail()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }*/
}