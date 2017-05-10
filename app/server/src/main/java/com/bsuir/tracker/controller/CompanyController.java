package com.bsuir.tracker.controller;

import java.io.IOException;
import java.sql.SQLException;
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
import com.bsuir.tracker.entity.CompanyEntity;
import com.bsuir.tracker.Service.CompanyService;

/**
 * Created by Pavel on 25.04.2017.
 */
@Controller
public class CompanyController {
    private static  final Logger logger = Logger.getLogger(ImageController.class);

    public CompanyController(){
        System.out.println("CompanyController Initializer");
    }

    @Autowired
    private  CompanyService companyService;

    @RequestMapping(value = "/Companies")
    public ModelAndView listCompany(ModelAndView model) throws IOException {
        List<CompanyEntity> listCompany = companyService.getAllCompanies();
        model.addObject("listCompany", listCompany);
        model.setViewName("Companies");
        return model;
    }

    @RequestMapping(value = "/newCompany", method = RequestMethod.GET)
    public ModelAndView newCompany(ModelAndView model) {
        CompanyEntity company = new CompanyEntity();
        model.addObject("company", company);
        model.setViewName("CompanyForm");
        return model;
    }

    @RequestMapping(value = "/saveCompany", method = RequestMethod.POST)
    public ModelAndView saveCompany(@ModelAttribute("company") @Validated CompanyEntity company, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("redirect:/errorView");
            modelAndView.addObject("message", "Whoops, something gone wrong with your input!");
            return modelAndView;
        }
        try {
            if (company.getIdcompany() == 0) {
                companyService.addCompany(company);
            } else {
                companyService.updateCompany(company);
            }
        }
        catch (ConstraintViolationException e) {
            ModelAndView modelAndView = new ModelAndView("redirect:/errorView");
            modelAndView.addObject("message", "Whoops, something gone wrong with ID-s!");
            return modelAndView;
        }
        catch (DataIntegrityViolationException e) {
            ModelAndView modelAndView = new ModelAndView("redirect:/errorView");
            modelAndView.addObject("message", "Whoops, something gone wrong with SQL data integrity!");
            return modelAndView;
        }
        return new ModelAndView("redirect:/Companies");
    }

    @RequestMapping(value = "/deleteCompany", method = RequestMethod.GET)
    public ModelAndView deleteCompany(HttpServletRequest request) {
        int idCompany = Integer.parseInt(request.getParameter("id"));
        companyService.deleteCompany(idCompany);
        return new ModelAndView("redirect:/Companies");
    }

    @RequestMapping(value = "/editCompany", method = RequestMethod.GET)
    public ModelAndView editCompany(HttpServletRequest request) {
        int idCompany = Integer.parseInt(request.getParameter("id"));
        CompanyEntity company = companyService.getCompany(idCompany);
        ModelAndView model = new ModelAndView("CompanyForm");
        model.addObject("company", company);
        return model;
    }
}