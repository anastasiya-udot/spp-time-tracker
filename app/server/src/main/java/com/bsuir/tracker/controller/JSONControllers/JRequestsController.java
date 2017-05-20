package com.bsuir.tracker.controller.JSONControllers;

import com.bsuir.tracker.Service.EmployeeService;
import com.bsuir.tracker.Service.RequestService;
import com.bsuir.tracker.Service.WorkdayTypeService;
import com.bsuir.tracker.entity.EmployeeEntity;
import com.bsuir.tracker.entity.RequestEntity;
import com.bsuir.tracker.entity.WorkdayTypeEntity;
import com.bsuir.tracker.model.CompanyNameIdModel;
import com.bsuir.tracker.model.RequestSenderModel;
import com.bsuir.tracker.model.SenderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
            requestEntities = requestService.getAllRequestsByEmployeeId(id);

            for (RequestEntity requestEntity: requestEntities) {
                RequestSenderModel requestSenderModel = new RequestSenderModel();

                requestSenderModel.setDate(requestEntity.getDate().getTime());
                requestSenderModel.setStartPeriod(requestEntity.getStartPeriod().getTime());
                requestSenderModel.setEndPeriod(requestEntity.getEndPeriod().getTime());
                requestSenderModel.setContent(requestEntity.getContent());

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

        /*Map<String, Map<String, List<CompanyNameIdModel>>> response = new HashMap<>();
        try {
            List<CompanyNameIdModel> listCompanyObjects = companyService.getAllCompaniesNameId();
            Map<String, List<CompanyNameIdModel>> resultMap = new HashMap<>();
            resultMap.put("companies", listCompanyObjects);
            response.put("data", resultMap);
            System.out.println("" + resultMap);
            return ResponseEntity.status(HttpStatus.OK).body(resultMap);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }*/
    }
}
