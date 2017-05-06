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
import com.bsuir.tracker.entity.RoleEntity;
import com.bsuir.tracker.Service.RoleService;

/**
 * Created by Pavel on 25.04.2017.
 */
@Controller
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
    public ModelAndView saveRole(@ModelAttribute RoleEntity role) {
        if (role.getIdrole() == 0) {
            roleService.addRole(role);
        } else {
            roleService.updateRole(role);
        }
        return new ModelAndView("redirect:/Roles");
    }

    @RequestMapping(value = "/deleteRole", method = RequestMethod.GET)
    public ModelAndView deleteRole(HttpServletRequest request) {
        int idRole = Integer.parseInt(request.getParameter("id"));
        roleService.deleteRole(idRole);
        return new ModelAndView("redirect:/Roles");
    }

    @RequestMapping(value = "/editRole", method = RequestMethod.GET)
    public ModelAndView editRole(HttpServletRequest request) {
        int idRole = Integer.parseInt(request.getParameter("id"));
        RoleEntity role = roleService.getRole(idRole);
        ModelAndView model = new ModelAndView("RoleForm");
        model.addObject("role", role);
        return model;
    }
}