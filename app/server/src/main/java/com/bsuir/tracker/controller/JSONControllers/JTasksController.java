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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
