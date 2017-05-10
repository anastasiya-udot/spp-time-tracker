package com.bsuir.tracker.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.bsuir.tracker.entity.EmployeeEntity;
import com.bsuir.tracker.Service.EmployeeService;
import com.bsuir.tracker.entity.ImageEntity;
import com.bsuir.tracker.Service.ImageService;
import com.bsuir.tracker.entity.CompanyEntity;
import com.bsuir.tracker.Service.CompanyService;

@Controller
public class RegistrationController {

    private static  final Logger logger = Logger.getLogger(ImageController.class);

    public RegistrationController(){
        System.out.println("RegistrationController Initializer");
    }

    @Autowired
    private  EmployeeService employeeService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/Registration")
    public ModelAndView Registration(ModelAndView model) throws IOException {
        EmployeeEntity employee = new EmployeeEntity();
        model.addObject("employee", employee);

        List<ImageEntity> imageEntityList = imageService.getAllImages();
        model.addObject("avatars", imageEntityList);

        List<CompanyEntity> companyEntityList = companyService.getAllCompanies();
        model.addObject("companies", companyEntityList);

        model.setViewName("Registration");
        return model;
    }

    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    public ModelAndView Register(@ModelAttribute("employee") @Validated EmployeeEntity employee, @ModelAttribute("repeat_password") @Validated String repeat_password, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return GetErrorView("Whoops, something gone wrong with your input!");
        }
        try {
            if ((employee.getIdemployee() == 0) && (repeat_password == employee.getPassword()) && (CheckNoSuchUser(employee)))
            {
                employeeService.addEmployee(employee);
            } else {
                return GetErrorView("Whoops, something gone wrong with your input!");
            }
        }
        catch (ConstraintViolationException e) {
            return GetErrorView("Whoops, something gone wrong with ID-s!");
        }
        catch (DataIntegrityViolationException e) {
            return GetErrorView("Whoops, something gone wrong with SQL data integrity!");
        }
        return new ModelAndView("redirect:/Authorization");
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
    }

    private ModelAndView GetErrorView(String message)
    {
        ModelAndView modelAndView = new ModelAndView("redirect:/errorView");
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}