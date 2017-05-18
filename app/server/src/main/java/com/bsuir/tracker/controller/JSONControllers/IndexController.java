package com.bsuir.tracker.controller.JSONControllers;

import com.bsuir.tracker.Service.CompanyService;
import com.bsuir.tracker.Service.EmployeeService;
import com.bsuir.tracker.Service.ImageService;
import com.bsuir.tracker.Service.RoleService;
import com.bsuir.tracker.controller.ImageController;
import com.bsuir.tracker.controller.security.GetTokenService;
import com.bsuir.tracker.entity.EmployeeEntity;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by Pavel on 18.05.2017.
 */
@Controller
public class IndexController {
    private static  final Logger logger = Logger.getLogger(ImageController.class);
    public IndexController(){
        System.out.println("IndexController Initializer");
    }

    public static final String indexViewPath = "/tracker";

    @RequestMapping(value = indexViewPath, method = RequestMethod.GET)
    public String index_angular(){
        return "index";
    }
}