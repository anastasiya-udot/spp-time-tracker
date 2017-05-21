package com.bsuir.tracker.controller.JSONControllers;

import com.bsuir.tracker.Service.PeriodService;
import com.bsuir.tracker.Service.TaskService;
import com.bsuir.tracker.entity.PeriodEntity;
import com.bsuir.tracker.entity.TaskEntity;
import com.bsuir.tracker.model.TaskGetResModel;
import com.bsuir.tracker.model.TasksGetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Pavel on 21.05.2017.
 */
@Controller
public class JTasksController {
    @Autowired
    TaskService taskService;
    @Autowired
    PeriodService periodService;

    @RequestMapping(value = "/tasks/get", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity tasks_get(@RequestBody @Validated TasksGetModel tasksGetModel, BindingResult bindingResult) throws Exception{
        Map<String, Object> response = new HashMap<>();
        if(bindingResult.hasErrors()){
            response.put("error", "Data Binding Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try {
            List<PeriodEntity> periodEntities = periodService.getAllPeriodsByEmployeeId(tasksGetModel.getId());
            List<PeriodEntity> periodEntitiesInLimits = new ArrayList<>();
            for (PeriodEntity periodEntity : periodEntities) {
                if(periodEntity.getStart().getTime() >= tasksGetModel.getStartPeriod())
                {
                    if((periodEntity.getFinish() == null) || (periodEntity.getFinish().getTime() <= tasksGetModel.getFinishPeriod())){
                        periodEntitiesInLimits.add(periodEntity);
                    }
                }
            }

            List<TaskGetResModel> taskGetResModels = new ArrayList<>();

            for (PeriodEntity periodEntity : periodEntitiesInLimits) {
                for (TaskEntity taskEntity: periodEntity.getTaskEntities()) {
                    TaskGetResModel taskGetResModel = new TaskGetResModel();

                    taskGetResModel.setId(taskEntity.getIdtask());
                    taskGetResModel.setCode(taskEntity.getCode());
                    taskGetResModel.setDescription(taskEntity.getDescription());

                    taskGetResModels.add(taskGetResModel);
                }
            }

            return ResponseEntity.status(HttpStatus.OK).body(taskGetResModels);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "/task/delete/{idtask}/{iduser}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity request_delete_id(@PathVariable int idtask, @PathVariable int iduser) throws Exception{
        try {
                TaskEntity taskEntity = taskService.getTask(idtask);
                if (null != taskEntity) {
                    Set<PeriodEntity> periodEntities = taskEntity.getPeriodEntities();
                    Set<PeriodEntity> periodEntitiesToRemove = new HashSet<>();
                    for (PeriodEntity periodEntity : periodEntities) {
                        if (periodEntity.getEmployeeIdemployee() == iduser) {
                            periodEntitiesToRemove.add(periodEntity);
                        }
                    }
                    if (periodEntitiesToRemove.size() > 0) {
                        periodEntities.removeAll(periodEntitiesToRemove);
                    }

                    taskEntity.setPeriodEntities(periodEntities);
                    taskService.updateTask(taskEntity);

                    if (taskEntity.getPeriodEntities().size() <= 0) {
                        taskService.deleteTask(taskEntity.getIdtask());
                    }

                    return ResponseEntity.status(HttpStatus.OK).body(null);
                }
                else{
                    Map<String, String> response = new HashMap<>();
                    response.put("error", "invalid input");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                }
        }
        catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("error", "invalid input");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
