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
import com.bsuir.tracker.entity.RoleEntity;
import com.bsuir.tracker.Service.RoleService;

/**
 * Created by Pavel on 25.04.2017.
 */
@Controller
@RequestMapping(value = "/Backdoor")
public class RoleController {
    private static  final Logger logger = Logger.getLogger(ImageController.class);

    public RoleController(){
        System.out.println("RoleController Initializer");
    }

    @Autowired
    private  RoleService roleService;

    @RequestMapping(value = "/Roles")
    public ModelAndView listRoles(ModelAndView model) throws IOException {
        List<RoleEntity> listRole = roleService.getAllRoles();
        model.addObject("listRole", listRole);
        model.setViewName("Roles");
        return model;
    }

    @RequestMapping(value = "/newRole", method = RequestMethod.GET)
    public ModelAndView newRole(ModelAndView model) {
        RoleEntity role = new RoleEntity();
        model.addObject("role", role);
        model.setViewName("RoleForm");
        return model;
    }

    @RequestMapping(value = "/saveRole", method = RequestMethod.POST)
    public ModelAndView saveRole(@ModelAttribute("role") @Validated RoleEntity role, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("redirect:/errorView");
            modelAndView.addObject("message", "Whoops, something gone wrong with your input!");
            return modelAndView;
        }
        try {
            if (role.getIdrole() == 0) {
                roleService.addRole(role);
            } else {
                roleService.updateRole(role);
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
        return new ModelAndView("redirect:/Backdoor/Roles");
    }

    @RequestMapping(value = "/deleteRole", method = RequestMethod.GET)
    public ModelAndView deleteRole(HttpServletRequest request) {
        int idRole = Integer.parseInt(request.getParameter("id"));
        try {
            roleService.deleteRole(idRole);
            return new ModelAndView("redirect:/Backdoor/Roles");
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

    @RequestMapping(value = "/editRole", method = RequestMethod.GET)
    public ModelAndView editRole(HttpServletRequest request) {
        int idRole = Integer.parseInt(request.getParameter("id"));
        RoleEntity role = roleService.getRole(idRole);
        ModelAndView model = new ModelAndView("RoleForm");
        model.addObject("role", role);
        return model;
    }

    private ModelAndView GetErrorView(String message)
    {
        ModelAndView modelAndView = new ModelAndView("redirect:/errorView?message=" + message);
        return modelAndView;
    }
}