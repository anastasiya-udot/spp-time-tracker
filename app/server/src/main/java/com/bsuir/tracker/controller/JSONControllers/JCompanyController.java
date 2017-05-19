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
}