package com.bsuir.tracker.controller;

import com.bsuir.tracker.Service.TaskService;
import com.bsuir.tracker.entity.TaskEntity;
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
public class TaskController {

    public TaskController(){
        System.out.println("TaskController Initializer");
    }

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/Tasks")
    public ModelAndView listTask(ModelAndView model) throws IOException {
        List<TaskEntity> taskEntityList = taskService.getAllTasks();
        model.addObject("listTask", taskEntityList);
        model.setViewName("Tasks");
        return model;
    }

    @RequestMapping(value = "/newTask", method = RequestMethod.GET)
    public ModelAndView newTask(ModelAndView model) {
        TaskEntity taskEntity = new TaskEntity();
        model.addObject("task", taskEntity);
        model.setViewName("TaskForm");
        return model;
    }

    @RequestMapping(value = "/saveTask", method = RequestMethod.POST)
    public ModelAndView saveRequest(@ModelAttribute("task") @Validated TaskEntity taskEntity, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("redirect:/errorView");
            modelAndView.addObject("message", "Whoops, something gone wrong with your input!");
            return modelAndView;
        }
        try {
            if (taskEntity.getIdtask() == 0) {
                taskService.addTask(taskEntity);
            } else {
                taskService.updateTask(taskEntity);
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
        return new ModelAndView("redirect:/Backdoor/Tasks");
    }

    @RequestMapping(value = "/deleteTask", method = RequestMethod.GET)
    public ModelAndView deleteTask(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        taskService.deleteTask(id);
        return new ModelAndView("redirect:/Backdoor/Tasks");
    }

    @RequestMapping(value = "/editTask", method = RequestMethod.GET)
    public ModelAndView editTask(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        TaskEntity taskEntity = taskService.getTask(id);
        ModelAndView model = new ModelAndView("TaskForm");
        model.addObject("task", taskEntity);

        return model;
    }

}