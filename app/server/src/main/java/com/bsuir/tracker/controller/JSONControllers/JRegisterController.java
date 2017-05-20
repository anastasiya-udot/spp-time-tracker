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
import com.bsuir.tracker.model.CompanyRegisterModel;
import com.bsuir.tracker.model.EmployeeAuthorizationResModel;
import com.bsuir.tracker.model.EmployeeRegisterModel;
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
public class JRegisterController extends AbstractController {
    private static  final Logger logger = Logger.getLogger(ImageController.class);
    public JRegisterController(){
        System.out.println("JRegisterController Initializer");
    }

    //@Autowired
    //private EmployeeService employeeService;
    @Autowired
    private ImageService imageService;
    //@Autowired
    //private CompanyService companyService;
    @Autowired
    private RoleService roleService;
    //@Autowired
    //private GetTokenService getTokenService;

    private int EMPLOYEE_ROLE_ID = 2;

    @RequestMapping(value = "/authorization/new-user/post", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity authorization_newUser_post(@RequestBody @Validated EmployeeRegisterModel employeeRegisterModel, BindingResult bindingResult) throws Exception {
        Map<String, Object> response = new HashMap<>();
        if(bindingResult.hasErrors()){
            response.put("error", "Data Binding Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try {
            if (CheckNoSuchUser(employeeRegisterModel.getEmail()))
            {
                System.out.println(employeeRegisterModel.getName());
                System.out.println(employeeRegisterModel.getSurname());
                System.out.println(employeeRegisterModel.getPatronymic());
                System.out.println(employeeRegisterModel.getEmail());
                System.out.println(employeeRegisterModel.getPassword());
                System.out.println(employeeRegisterModel.getCompany());
                AddEmployee(employeeRegisterModel.getName(),
                                    employeeRegisterModel.getSurname(),
                                    employeeRegisterModel.getPatronymic(),
                                    employeeRegisterModel.getEmail(),
                                    employeeRegisterModel.getPassword(),
                                    employeeRegisterModel.getCompany(),
                                    EMPLOYEE_ROLE_ID);
                return ResponseEntity.status(HttpStatus.OK).body(null);
            } else {
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

    @RequestMapping(value = "/authorization/sign-in/post", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity authorization_signIn_post(@RequestBody @Validated UserAuthorizationModel userData, BindingResult bindingResult) throws Exception {
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
                token = getTokenService.getToken(userData.getEmail(), userData.getPassword());
                if(token != null)
                {
                    response.put("token", token);

                    EmployeeAuthorizationResModel employeeAuthorizationResModel = new EmployeeAuthorizationResModel();
                    employeeAuthorizationResModel.setName(user.getName());
                    employeeAuthorizationResModel.setSurname(user.getSurname());
                    employeeAuthorizationResModel.setPatronymic(user.getPatronymic());
                    employeeAuthorizationResModel.setEmail(user.getEmail());

                    response.put("user", employeeAuthorizationResModel);

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
}
