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
import com.bsuir.tracker.entity.WorkdayTypeEntity;
import com.bsuir.tracker.Service.WorkdayTypeService;

/**
 * Created by Pavel on 25.04.2017.
 */
@Controller
@RequestMapping(value = "/Backdoor")
public class WorkdayTypeController {
    private static  final Logger logger = Logger.getLogger(ImageController.class);

    public WorkdayTypeController(){
        System.out.println("WorkdayTypeController Initializer");
    }

    @Autowired
    private  WorkdayTypeService workdayTypeService;

    @RequestMapping(value = "/WorkdayTypes")
    public ModelAndView listWorkdayTypes(ModelAndView model) throws IOException {
        List<WorkdayTypeEntity> workdayTypeEntityList = workdayTypeService.getAllWorkdayTypes();
        model.addObject("listWorkdayType", workdayTypeEntityList);
        model.setViewName("WorkdayTypes");
        return model;
    }

    @RequestMapping(value = "/newWorkdayType", method = RequestMethod.GET)
    public ModelAndView newWorkdayType(ModelAndView model) {
        WorkdayTypeEntity workdayTypeEntity = new WorkdayTypeEntity();
        model.addObject("workdayType", workdayTypeEntity);
        model.setViewName("WorkdayTypeForm");
        return model;
    }

    @RequestMapping(value = "/saveWorkdayType", method = RequestMethod.POST)
    public ModelAndView saveWorkdayType(@ModelAttribute("workdayTypeEntity") @Validated WorkdayTypeEntity workdayTypeEntity, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("redirect:/errorView");
            modelAndView.addObject("message", "Whoops, something gone wrong with your input!");
            return modelAndView;
        }
        try {
            if (workdayTypeEntity.getIdworkdayType() == 0) {
                workdayTypeService.addWorkdayType(workdayTypeEntity);
            } else {
                workdayTypeService.updateWorkdayType(workdayTypeEntity);
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
        return new ModelAndView("redirect:/Backdoor/WorkdayTypes");
    }

    @RequestMapping(value = "/deleteWorkdayType", method = RequestMethod.GET)
    public ModelAndView deleteWorkdayType(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            workdayTypeService.deleteWorkdayType(id);
            return new ModelAndView("redirect:/Backdoor/WorkdayTypes");
        }
        catch (ConstraintViolationException e)
        {
            ModelAndView modelAndView = new ModelAndView("redirect:/errorView");
            modelAndView.addObject("message", "Whoops, you're trying to delete entity that is still in use!");
            return modelAndView;
        }
        catch (DataIntegrityViolationException e)
        {
            return GetErrorView("Whoops, something gone wrong with SQL data integrity!");
        }
    }

    @RequestMapping(value = "/editWorkdayType", method = RequestMethod.GET)
    public ModelAndView editWorkdayType(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        WorkdayTypeEntity workdayTypeEntity = workdayTypeService.getWorkdayType(id);
        ModelAndView model = new ModelAndView("WorkdayTypeForm");
        model.addObject("workday", workdayTypeEntity);
        return model;
    }

    private ModelAndView GetErrorView(String message)
    {
        ModelAndView modelAndView = new ModelAndView("redirect:/errorView?message=" + message);
        return modelAndView;
    }
}