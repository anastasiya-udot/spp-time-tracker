package com.bsuir.tracker.controller.JSONControllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bsuir.tracker.entity.ImageEntity;
import com.bsuir.tracker.Service.ImageService;
/**
 * Created by Pavel on 25.04.2017.
 */

@Controller
public class testController {
    @Autowired
    ImageService imageService;

    public testController(){
        System.out.println("testController Initializer");
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public ResponseEntity test(ModelAndView model) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("error", "error message");
        return ResponseEntity.status(HttpStatus.OK).body(/*imageService.getAllImages()*/ data);
    }
}