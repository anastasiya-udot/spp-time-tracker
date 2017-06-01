package com.bsuir.tracker.controller.JSONControllers;

import com.bsuir.tracker.Service.EmployeeService;
import com.bsuir.tracker.Service.RequestService;
import com.bsuir.tracker.Service.WorkdayTypeService;
import com.bsuir.tracker.entity.EmployeeEntity;
import com.bsuir.tracker.entity.RequestEntity;
import com.bsuir.tracker.entity.WorkdayTypeEntity;
import com.bsuir.tracker.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pavel on 20.05.2017.
 */
@Controller
public class JRequestsController {
    @Autowired
    RequestService requestService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    WorkdayTypeService workdayTypeService;

    @RequestMapping(value = "/requests/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity requests_get_id(@PathVariable int id) throws Exception{
        Map<String, List<RequestSenderModel>> response = new HashMap<>();
        List<RequestSenderModel> requestSenderModels = new ArrayList<>();

        try {
            List<RequestEntity> requestEntities = null;
            requestEntities = requestService.getAllRequestsByDestEmployeeId(id);

            for (RequestEntity requestEntity: requestEntities) {
                RequestSenderModel requestSenderModel = new RequestSenderModel();

                requestSenderModel.setDate(requestEntity.getDate().getTime());
                requestSenderModel.setStartPeriod(requestEntity.getStartPeriod().getTime());
                requestSenderModel.setEndPeriod(requestEntity.getEndPeriod().getTime());
                requestSenderModel.setContent(requestEntity.getContent());
                requestSenderModel.setId(requestEntity.getIdrequest());

                requestSenderModel.setWorktype(workdayTypeService.getWorkdayType(employeeService.getEmployee(requestEntity.getDestinationIdemployee()).getWorkdayIdworkdayType()).getTime());

                SenderModel senderModel = new SenderModel();
                EmployeeEntity senderEmployeeEntity = employeeService.getEmployee(requestEntity.getSourceIdemployee());
                senderModel.setName(senderEmployeeEntity.getName());
                senderModel.setSurname(senderEmployeeEntity.getSurname());

                requestSenderModel.setSender(senderModel);

                requestSenderModels.add(requestSenderModel);
            }

            response.put("requests", requestSenderModels);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "/request/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity request_delete_id(@PathVariable int id) throws Exception{
        try {
            requestService.deleteRequest(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("error", "invalid input");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @RequestMapping(value = "/add-new-request/post", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addNewRequest_post(@RequestBody @Validated RequestPostModel requestPostModel, BindingResult bindingResult) throws Exception{
        Map<String, Object> response = new HashMap<>();
        if(bindingResult.hasErrors()){
            response.put("error", "Data Binding Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            if((employeeService.getEmployee(requestPostModel.getIdSource()) == null) || (employeeService.getEmployee(requestPostModel.getIdDestination()) == null)){
                response.put("error", "No such users!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            RequestEntity requestEntity = new RequestEntity();
            requestEntity.setSourceIdemployee(requestPostModel.getIdSource());
            requestEntity.setDestinationIdemployee(requestPostModel.getIdDestination());
            requestEntity.setStartPeriod(new Timestamp(requestPostModel.getStartPeriod()));
            requestEntity.setEndPeriod(new Timestamp(requestPostModel.getEndPeriod()));
            requestEntity.setDate(new Timestamp(requestPostModel.getDate()));
            requestEntity.setContent("");
            requestEntity.setContent(""); //To make content NOT NULL;

            requestService.addRequest(requestEntity);

            RequestSenderModel requestSenderModel = new RequestSenderModel();
            requestSenderModel.setDate(requestEntity.getDate().getTime());
            requestSenderModel.setStartPeriod(requestEntity.getStartPeriod().getTime());
            requestSenderModel.setEndPeriod(requestEntity.getEndPeriod().getTime());
            requestSenderModel.setContent(requestEntity.getContent());
            requestSenderModel.setId(requestEntity.getIdrequest());

            requestSenderModel.setWorktype(workdayTypeService.getWorkdayType(employeeService.getEmployee(requestEntity.getDestinationIdemployee()).getWorkdayIdworkdayType()).getTime());

            SenderModel senderModel = new SenderModel();
            EmployeeEntity senderEmployeeEntity = employeeService.getEmployee(requestEntity.getSourceIdemployee());
            senderModel.setName(senderEmployeeEntity.getName());
            senderModel.setSurname(senderEmployeeEntity.getSurname());

            requestSenderModel.setSender(senderModel);

            return ResponseEntity.status(HttpStatus.OK).body(requestSenderModel);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "/update-request/post", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updateRequest_post(@RequestBody @Validated RequestUpdateModel requestUpdateModel, BindingResult bindingResult) throws Exception{
        Map<String, Object> response = new HashMap<>();
        if(bindingResult.hasErrors()){
            response.put("error", "Data Binding Error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try {
            RequestEntity requestEntity =  requestService.getRequest(requestUpdateModel.getId());
            if(null != requestEntity){
                requestEntity.setContent(requestUpdateModel.getContent());
                requestService.updateRequest(requestEntity);
                return ResponseEntity.status(HttpStatus.OK).body(null);
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
