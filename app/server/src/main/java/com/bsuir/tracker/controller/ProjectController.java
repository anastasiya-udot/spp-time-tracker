package com.bsuir.tracker.controller;

import com.bsuir.tracker.Service.ProjectService;
import com.bsuir.tracker.entity.ProjectEntity;
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
public class ProjectController {

    public ProjectController(){
        System.out.println("ProjectController Initializer");
    }

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/Projects")
    public ModelAndView listProject(ModelAndView model) throws IOException {
        List<ProjectEntity> listProject = projectService.getAllProjects();
        model.addObject("listProject", listProject);
        model.setViewName("Projects");
        return model;
    }

    @RequestMapping(value = "/newProject", method = RequestMethod.GET)
    public ModelAndView newProject(ModelAndView model) {
        ProjectEntity project = new ProjectEntity();
        model.addObject("project", project);
        model.setViewName("ProjectForm");
        return model;
    }

    @RequestMapping(value = "/saveProject", method = RequestMethod.POST)
    public ModelAndView saveProject(@ModelAttribute("project") @Validated ProjectEntity project, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("redirect:/errorView");
            modelAndView.addObject("message", "Whoops, something gone wrong with your input!");
            return modelAndView;
        }
        try {
            if (project.getIdproject() == 0) {
                projectService.addProject(project);
            } else {
                projectService.updateProject(project);
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
        return new ModelAndView("redirect:/Backdoor/Projects");
    }

    @RequestMapping(value = "/deleteProject", method = RequestMethod.GET)
    public ModelAndView deleteProject(HttpServletRequest request) {
        int idProject = Integer.parseInt(request.getParameter("id"));
        try {
            projectService.deleteProject(idProject);
            return new ModelAndView("redirect:/Backdoor/Projects");
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

    @RequestMapping(value = "/editProject", method = RequestMethod.GET)
    public ModelAndView editProject(HttpServletRequest request) {
        int idProject = Integer.parseInt(request.getParameter("id"));
        ProjectEntity project = projectService.getProject(idProject);
        ModelAndView model = new ModelAndView("ProjectForm");
        model.addObject("project", project);

        return model;
    }

    private ModelAndView GetErrorView(String message)
    {
        ModelAndView modelAndView = new ModelAndView("redirect:/errorView?message=" + message);
        return modelAndView;
    }
}