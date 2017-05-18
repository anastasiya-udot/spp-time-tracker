package com.bsuir.tracker.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Pavel on 25.04.2017.
 */

@Controller
public class indexController {
    public indexController(){
        System.out.println("ImageController Initializer");
    }

    @RequestMapping(value = "/Backdoor/List")
    public ModelAndView listCompany(ModelAndView model) throws IOException {
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = "/errorView")
    public ModelAndView errorView(ModelAndView model) throws IOException {
        model.setViewName("ErrorView");
        return model;
    }

    @RequestMapping(value = "/noPermission")
    public ModelAndView noPermission(ModelAndView model) throws IOException {
        model.setViewName("Unpermissioned");
        return model;
    }
}
