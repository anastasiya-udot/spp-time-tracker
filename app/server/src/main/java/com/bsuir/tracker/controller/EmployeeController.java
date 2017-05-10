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

@Controller
public class EmployeeController {

    private static  final Logger logger = Logger.getLogger(ImageController.class);

    public EmployeeController(){
        System.out.println("EmployeeController Initializer");
    }

    @Autowired
    private  EmployeeService employeeService;

    @RequestMapping(value = "/Employees")
    public ModelAndView listEmployee(ModelAndView model) throws IOException {
        List<EmployeeEntity> listEmployee = employeeService.getAllEmployees();
        model.addObject("listEmployee", listEmployee);
        model.setViewName("Employees");
        return model;
    }

    @RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
    public ModelAndView newEmployee(ModelAndView model) {
        EmployeeEntity employee = new EmployeeEntity();
        model.addObject("employee", employee);
        model.setViewName("EmployeeForm");
        return model;
    }

    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute("employee") @Validated EmployeeEntity employee, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("redirect:/errorView");
            modelAndView.addObject("message", "Whoops, something gone wrong with your input!");
            return modelAndView;
        }
        try {
            if (employee.getIdemployee() == 0) {
                employeeService.addEmployee(employee);
            } else {
                employeeService.updateEmployee(employee);
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
        return new ModelAndView("redirect:/Employees");
    }

    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(HttpServletRequest request) {
        int idEmployee = Integer.parseInt(request.getParameter("id"));
        employeeService.deleteEmployee(idEmployee);
        return new ModelAndView("redirect:/Employees");
    }

    @RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
    public ModelAndView editEmployee(HttpServletRequest request) {
        int idEmployee = Integer.parseInt(request.getParameter("id"));
        EmployeeEntity employee = employeeService.getEmployee(idEmployee);
        ModelAndView model = new ModelAndView("EmployeeForm");
        model.addObject("employee", employee);
        return model;
    }

}
