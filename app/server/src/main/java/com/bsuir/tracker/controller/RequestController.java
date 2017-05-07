package com.bsuir.tracker.controller;

import com.bsuir.tracker.Service.RequestService;
import com.bsuir.tracker.entity.RequestEntity;
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
public class RequestController {

    public RequestController(){
        System.out.println("RequestController Initializer");
    }

    @Autowired
    private RequestService requestService;

    @RequestMapping(value = "/Requests")
    public ModelAndView listRequest(ModelAndView model) throws IOException {
        List<RequestEntity> requestEntityList = requestService.getAllRequests();
        model.addObject("listRequest", requestEntityList);
        model.setViewName("Requests");
        return model;
    }

    @RequestMapping(value = "/newRequest", method = RequestMethod.GET)
    public ModelAndView newRequest(ModelAndView model) {
        RequestEntity requestEntity = new RequestEntity();
        model.addObject("request", requestEntity);
        model.setViewName("RequestForm");
        return model;
    }

    @RequestMapping(value = "/saveRequest", method = RequestMethod.POST)
    public ModelAndView saveRequest(@ModelAttribute("request") @Validated RequestEntity requestEntity, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("redirect:/errorView");
            modelAndView.addObject("message", "Whoops, something gone wrong with your input!");
            return modelAndView;
        }
        try
        {
            if (requestEntity.getIdrequest() == 0) {
                requestService.addRequest(requestEntity);
            } else {
                requestService.updateRequest(requestEntity);
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
        return new ModelAndView("redirect:/Requests");

    }

    @RequestMapping(value = "/deleteRequest", method = RequestMethod.GET)
    public ModelAndView deleteRequest(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        requestService.deleteRequest(id);
        return new ModelAndView("redirect:/Requests");
    }

    @RequestMapping(value = "/editRequest", method = RequestMethod.GET)
    public ModelAndView editRequest(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        RequestEntity requestEntity = requestService.getRequest(id);
        ModelAndView model = new ModelAndView("RequestForm");
        model.addObject("request", requestEntity);

        return model;
    }

}