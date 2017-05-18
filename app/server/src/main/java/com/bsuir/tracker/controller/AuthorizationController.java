package com.bsuir.tracker.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.bsuir.tracker.controller.security.GetTokenService;
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
public class AuthorizationController {

    private static  final Logger logger = Logger.getLogger(ImageController.class);

    public AuthorizationController(){
        System.out.println("RegistrationController Initializer");
    }

    @Autowired
    private  EmployeeService employeeService;
    @Autowired
    private GetTokenService getTokenService;

    @RequestMapping(value = "/Authorization")
    public ModelAndView Authorization(ModelAndView model) throws IOException {
        String email = new String();
        String password = new String();
        model.addObject("email", email);
        model.addObject("password", password);
        model.setViewName("Authorization");
        return model;
    }

    @RequestMapping(value = "/Authorize", method = RequestMethod.POST)
    public ModelAndView Authorize(HttpServletRequest request, @ModelAttribute("email") @Validated String email, @ModelAttribute("password") @Validated String password, BindingResult bindingResult) throws Exception
    {
        if(bindingResult.hasErrors()){
            return GetErrorView("Whoops, something gone wrong with your input!");
        }
        try {
            EmployeeEntity user = employeeService.getEmployeeByMail(email);
            if (user != null)
            {
                String token = null;
                token = getTokenService.getToken(email, password);
                if(token != null)
                {
                    request.getSession().setAttribute("token", token);
                }
                else
                {
                    return GetErrorView("Whoops, something gone wrong with your password!");
                }
            }
            else
            {
                return GetErrorView("Whoops, something gone wrong with your name!");
            }
        }
        catch (ConstraintViolationException e) {
            return GetErrorView("Whoops, something gone wrong with ID-s!");
        }
        catch (DataIntegrityViolationException e) {
            return GetErrorView("Whoops, something gone wrong with SQL data integrity!");
        }
        return new ModelAndView("redirect:/Backdoor/List");
    }

    @RequestMapping(value = "/LogOut")
    public ModelAndView LogOut(HttpServletRequest request, ModelAndView model) throws IOException {
        request.getSession().removeAttribute("token");
        String email = new String();
        String password = new String();
        model.addObject("email", email);
        model.addObject("password", password);
        model.setViewName("Authorization");
        return model;
    }

    private ModelAndView GetErrorView(String message)
    {
        ModelAndView modelAndView = new ModelAndView("redirect:/errorView");
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}