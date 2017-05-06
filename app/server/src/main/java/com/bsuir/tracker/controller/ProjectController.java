package com.bsuir.tracker.controller;

import com.bsuir.tracker.Service.ProjectService;
import com.bsuir.tracker.entity.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public ModelAndView saveProject(@ModelAttribute ProjectEntity project) {
        if (project.getIdproject() == 0) {
            projectService.addProject(project);
        } else {
            projectService.updateProject(project);
        }
        return new ModelAndView("redirect:/Projects");
    }

    @RequestMapping(value = "/deleteProject", method = RequestMethod.GET)
    public ModelAndView deleteProject(HttpServletRequest request) {
        int idProject = Integer.parseInt(request.getParameter("id"));
        projectService.deleteProject(idProject);
        return new ModelAndView("redirect:/Projects");
    }

    @RequestMapping(value = "/editProject", method = RequestMethod.GET)
    public ModelAndView editProject(HttpServletRequest request) {
        int idProject = Integer.parseInt(request.getParameter("id"));
        ProjectEntity project = projectService.getProject(idProject);
        ModelAndView model = new ModelAndView("ProjectForm");
        model.addObject("project", project);

        return model;
    }

}