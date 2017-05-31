package com.bsuir.tracker.controller.JSONControllers;

import com.bsuir.tracker.Service.PeriodService;
import com.bsuir.tracker.entity.PeriodEntity;
import com.bsuir.tracker.entity.TaskEntity;
import com.bsuir.tracker.model.*;
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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pavel on 21.05.2017.
 */
@Controller
public class JPeriodController {
    @Autowired
    PeriodService periodService;

    @RequestMapping(value = "/worktime-for-period/get", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity tasks_get(@RequestBody @Validated PeriodGetModel periodGetModel, BindingResult bindingResult) throws Exception{
        Map<String, Object> response = new HashMap<>();
        if(bindingResult.hasErrors()){
            response.put("error", "Data Binding Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try {
            List<PeriodEntity> periodEntities = periodService.getAllPeriodsByEmployeeId(periodGetModel.getId());
            List<PeriodEntity> periodEntitiesInLimits = new ArrayList<>();
            for (PeriodEntity periodEntity : periodEntities) {
                if(periodEntity.getStart().getTime() >= periodGetModel.getStartPeriod())
                {
                    if((periodEntity.getFinish() == null) || (periodEntity.getFinish().getTime() <= periodGetModel.getEndPeriod())){
                        periodEntitiesInLimits.add(periodEntity);
                    }
                }
            }

            long resultSumm = 0;
            long lastNullStartPeriod = 0;
            for (PeriodEntity periodEntity : periodEntitiesInLimits) {
                if(periodEntity.getFinish() != null){
                    resultSumm += (periodEntity.getFinish().getTime() - periodEntity.getStart().getTime());
                }
                else{
                    lastNullStartPeriod = periodEntity.getStart().getTime();
                }
            }

            response.put("sum", resultSumm);
            response.put("startPeriod", lastNullStartPeriod);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "/start-period/post", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity startPeriod_post(@RequestBody @Validated PeriodPostModel periodPostModel, BindingResult bindingResult) throws Exception{
        Map<String, Object> response = new HashMap<>();
        if(bindingResult.hasErrors()){
            response.put("error", "Data Binding Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try {
            List<PeriodEntity> periodEntities = periodService.getAllPeriodsByEmployeeId(periodPostModel.getId());
            for (PeriodEntity periodEntity : periodEntities) {
                if(periodEntity.getFinish() == null)
                {
                    periodEntity.setFinish(new Timestamp(periodPostModel.getStartPeriod()));
                    periodService.updatePeriod(periodEntity);
                }
            }

            PeriodEntity periodEntity = new PeriodEntity();
            periodEntity.setStart(new Timestamp(periodPostModel.getStartPeriod()));
            periodEntity.setFinish(null);
            periodEntity.setEmployeeIdemployee(periodPostModel.getId());

            periodService.addPeriod(periodEntity);

            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "/finish-preiod/post", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity startPeriod_post(@RequestBody @Validated PeriodFinishPostModel periodFinishPostModel, BindingResult bindingResult) throws Exception{
        Map<String, Object> response = new HashMap<>();
        if(bindingResult.hasErrors()){
            response.put("error", "Data Binding Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try {
            List<PeriodEntity> periodEntities = periodService.getAllPeriodsByEmployeeId(periodFinishPostModel.getId());
            PeriodEntity lastNullPeriod = null;
            for (PeriodEntity periodEntity : periodEntities) {
                if(periodEntity.getFinish() == null)
                {
                    periodEntity.setFinish(periodEntity.getStart());
                    periodService.updatePeriod(periodEntity);
                    lastNullPeriod = periodEntity;
                }
            }
            if(lastNullPeriod != null) {
                lastNullPeriod.setFinish(new Timestamp(periodFinishPostModel.getFinishPeriod()));
                periodService.updatePeriod(lastNullPeriod);
                return ResponseEntity.status(HttpStatus.OK).body(null);
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
