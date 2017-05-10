package com.bsuir.tracker.controller;

import com.bsuir.tracker.Service.PeriodService;
import com.bsuir.tracker.entity.PeriodEntity;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pavel on 26.04.2017.
 */

@Controller
@RequestMapping(value = "/Backdoor")
public class PeriodController {

    public PeriodController(){
        System.out.println("PeriodController Initializer");
    }

    @Autowired
    private PeriodService periodService;

    @RequestMapping(value = "/Periods")
    public ModelAndView listPeriod(ModelAndView model) throws IOException {
        List<PeriodEntity> periodEntityList = periodService.getAllPeriods();
        model.addObject("listPeriod", periodEntityList);
        model.setViewName("Periods");
        return model;
    }

    @RequestMapping(value = "/newPeriod", method = RequestMethod.GET)
    public ModelAndView newPeriod(ModelAndView model) {
        PeriodEntity periodEntity = new PeriodEntity();
        model.addObject("period", periodEntity);
        model.setViewName("PeriodForm");
        return model;
    }

    @RequestMapping(value = "/savePeriod", method = RequestMethod.POST)
    public ModelAndView savePeriod(@ModelAttribute("period") @Validated PeriodEntity periodEntity, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("redirect:/errorView");
            modelAndView.addObject("message", "Whoops, something gone wrong with your input!");
            return modelAndView;
        }
        try {
            if (periodEntity.getIdperiod() == 0) {
                periodService.addPeriod(periodEntity);
            } else {
                periodService.updatePeriod(periodEntity);
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
        return new ModelAndView("redirect:/Backdoor/Periods");

    }

    @RequestMapping(value = "/deletePeriod", method = RequestMethod.GET)
    public ModelAndView deletePeriod(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        periodService.deletePeriod(id);
        return new ModelAndView("redirect:/Backdoor/Periods");
    }

    @RequestMapping(value = "/editPeriod", method = RequestMethod.GET)
    public ModelAndView editPeriod(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        PeriodEntity periodEntity = periodService.getPeriod(id);
        ModelAndView model = new ModelAndView("PeriodForm");
        model.addObject("period", periodEntity);

        return model;
    }

}