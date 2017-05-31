package com.bsuir.tracker.controller;

import com.bsuir.tracker.Documentation.Builder;
import com.bsuir.tracker.Documentation.BuilderForBlanks;
import com.bsuir.tracker.Documentation.BuilderForWorks;
import com.bsuir.tracker.Documentation.Enumerations.DocumentType;
import com.bsuir.tracker.Documentation.Factories.Entities.*;
import com.bsuir.tracker.Documentation.Factories.Implementations.*;
import com.bsuir.tracker.Service.*;
import com.bsuir.tracker.entity.EmployeeEntity;
import com.bsuir.tracker.entity.PeriodEntity;
import com.bsuir.tracker.entity.RequestEntity;
import com.bsuir.tracker.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by angre on 29.04.2017.
 */
//@RestController
@Controller
@RequestMapping("/documents")
public class DocumentationController/* implements ApplicationContextAware*/{
    //ApplicationContext context;
    @Autowired
    RequestService requestService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CompanyService companyService;
    @Autowired
    WorkdayTypeService workdayTypeService;
    @Autowired
    PeriodService periodService;
    @Autowired
    TaskService taskService;

    @RequestMapping(method = RequestMethod.GET, value = "/worked/{type}")
    public @ResponseBody
    void getWorked(@PathVariable String type, @RequestParam("employee_id") int employee_id, @RequestParam("isProtected") boolean isProtected, HttpServletResponse response){
        try {
            List<WorkedInfo> blanks = new ArrayList<>();

            EmployeeEntity employeeEntity = employeeService.getEmployee(employee_id);

            WorkedInfo workedInfo = new WorkedInfo();
            workedInfo.setEmployeeName(employeeEntity.getName() + " " + employeeEntity.getSurname());
            workedInfo.setCompany(companyService.getCompany(employeeEntity.getCompanyIdcompany()).getName());

            Calendar c = Calendar.getInstance();
            long today = c.getTimeInMillis();
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            long monday = c.getTimeInMillis();

            List<PeriodEntity> periodEntities = periodService.getAllPeriodsByEmployeeId(employeeEntity.getIdemployee());
            List<PeriodEntity> periodEntitiesInLimits = new ArrayList<>();
            for (PeriodEntity periodEntity : periodEntities) {
                if (periodEntity.getStart().getTime() >= monday) {
                    if ((periodEntity.getFinish() == null) || (periodEntity.getFinish().getTime() <= today)) {
                        periodEntitiesInLimits.add(periodEntity);
                    }
                }
            }

            long resultSumm = 0;
            Set<TaskEntity> taskEntityList = new HashSet<>();
            for (PeriodEntity periodEntity : periodEntitiesInLimits) {
                if(periodEntity.getFinish() != null){
                    if(periodEntity.getFinish().getTime() > periodEntity.getStart().getTime())
                        resultSumm += (periodEntity.getFinish().getTime() - periodEntity.getStart().getTime());
                }

                Set<TaskEntity> tempTaskEntityList = periodEntity.getTaskEntities();
                for (TaskEntity task : tempTaskEntityList) {
                    if (!(taskEntityList.contains(task))) {
                        taskEntityList.add(task);
                    }
                }
            }

            String worked =  String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(resultSumm),
                    TimeUnit.MILLISECONDS.toMinutes(resultSumm) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(resultSumm)),
                    TimeUnit.MILLISECONDS.toSeconds(resultSumm) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(resultSumm)));

            workedInfo.setWorked(worked);
            workedInfo.setTasks("" + taskEntityList.size());

            blanks.add(workedInfo);

            Worked_Blank factory = new Worked_Blank();
            createWorked(DocumentType.valueOf(type + 'd'), factory,  isProtected, response, blanks);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/request/{type}")
    public @ResponseBody
    void getRequest(@PathVariable String type, @RequestParam("request_id") int request_id, @RequestParam("isProtected") boolean isProtected, HttpServletResponse response){
        try {
            RequestEntity requestEntity = requestService.getRequest(request_id);

            RequestInfo info = new RequestInfo();
            info.setContent(requestEntity.getContent());
            info.setDate(requestEntity.getDate());
            EmployeeEntity from =  employeeService.getEmployee(requestEntity.getSourceIdemployee());
            EmployeeEntity to = employeeService.getEmployee(requestEntity.getDestinationIdemployee());
            info.setDestinationEmployeeCompany(companyService.getCompany(to.getCompanyIdcompany()).getName());
            info.setDestinationEmployeeName(to.getName() + " " + to.getSurname());
            info.setSourceEmployeeName(from.getName() + " " + from.getSurname());
            info.setStartPeriod(requestEntity.getStartPeriod());
            info.setEndPeriod(requestEntity.getEndPeriod());

            List<RequestInfo> requestInfos = new ArrayList<>();
            requestInfos.add(info);

            Request_Blank factory = new Request_Blank();
            createBlanks(DocumentType.valueOf(type + 'b'), factory,  isProtected, response, requestInfos);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/employee/{type}")
    public @ResponseBody
    void getEmployeeInCompany(@PathVariable String type, @RequestParam int company_id, @RequestParam boolean isProtected, HttpServletResponse response){
        try {
            List<EmployeeInfo> blanks = new ArrayList<EmployeeInfo>();
            List<EmployeeEntity> employeeEntityList = employeeService.getEmployeeByCompany(company_id);

            Calendar c = Calendar.getInstance();
            long today = c.getTimeInMillis();
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            long monday = c.getTimeInMillis();

            for (EmployeeEntity employee : employeeEntityList) {
                EmployeeInfo employeeInfo = new EmployeeInfo();

                employeeInfo.setEmployeeName(employee.getName() + " " + employee.getSurname());
                employeeInfo.setMustWorked(workdayTypeService.getWorkdayType(employee.getWorkdayIdworkdayType()).getTime() + "h.");

                List<PeriodEntity> periodEntities = periodService.getAllPeriodsByEmployeeId(employee.getIdemployee());
                List<PeriodEntity> periodEntitiesInLimits = new ArrayList<>();
                for (PeriodEntity periodEntity : periodEntities) {
                    if(periodEntity.getStart().getTime() >= monday)
                    {
                        if((periodEntity.getFinish() == null) || (periodEntity.getFinish().getTime() <= today)){
                            periodEntitiesInLimits.add(periodEntity);
                        }
                    }
                }

                long resultSumm = 0;
                for (PeriodEntity periodEntity : periodEntitiesInLimits) {
                    if(periodEntity.getFinish() != null){
                        resultSumm += (periodEntity.getFinish().getTime() - periodEntity.getStart().getTime());
                    }
                }

                String worked =  String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(resultSumm),
                        TimeUnit.MILLISECONDS.toMinutes(resultSumm) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(resultSumm)),
                        TimeUnit.MILLISECONDS.toSeconds(resultSumm) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(resultSumm)));

                employeeInfo.setWorked(worked);

                blanks.add(employeeInfo);
            }
            blanks.sort(Comparator.comparing(EmployeeInfo::getEmployeeName));
            Employee_Blank factory = new Employee_Blank();
            createDocument(DocumentType.valueOf(type), factory, isProtected, response, blanks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{type}")
    public @ResponseBody
    void getCompanyEmployeeTasks(@PathVariable String type, @RequestParam int company_id, @RequestParam boolean isProtected, HttpServletResponse response){
        try {
            List<TasksInfo> blanks = new ArrayList<TasksInfo>();
            List<EmployeeEntity> employeeEntityList = employeeService.getEmployeeByCompany(company_id);

            for (EmployeeEntity employeeEntity : employeeEntityList){
                Calendar c = Calendar.getInstance();
                long today = c.getTimeInMillis();
                c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                long monday = c.getTimeInMillis();

                List<PeriodEntity> periodEntities = periodService.getAllPeriodsByEmployeeId(employeeEntity.getIdemployee());
                List<PeriodEntity> periodEntitiesInLimits = new ArrayList<>();
                for (PeriodEntity periodEntity : periodEntities) {
                    if (periodEntity.getStart().getTime() >= monday) {
                        if ((periodEntity.getFinish() == null) || (periodEntity.getFinish().getTime() <= today)) {
                            periodEntitiesInLimits.add(periodEntity);
                        }
                    }
                }

                Set<TaskEntity> taskEntityList = new HashSet<>();
                for (PeriodEntity periodEntity : periodEntitiesInLimits) {
                    Set<TaskEntity> tempTaskEntityList = periodEntity.getTaskEntities();
                    for (TaskEntity task : tempTaskEntityList) {
                        if (!(taskEntityList.contains(task))) {
                            taskEntityList.add(task);
                        }
                    }
                }

                TasksInfo tasksInfo = new TasksInfo();
                tasksInfo.setName(employeeEntity.getName() + " " + employeeEntity.getSurname());
                tasksInfo.setCount("" + taskEntityList.size());
                blanks.add(tasksInfo);
            }

            blanks.sort(Comparator.comparing(TasksInfo::getName));
            Tasks_Blank factory = new Tasks_Blank();
            createDocument(DocumentType.valueOf(type), factory, isProtected, response, blanks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/employeeTasks/{type}")
    public @ResponseBody
    void getEmployeesTasks(@PathVariable String type, @RequestParam int employee_id, @RequestParam boolean isProtected, HttpServletResponse response){
        try {
            List<EmployeeTasksInfo> blanks = new ArrayList<EmployeeTasksInfo>();
            EmployeeEntity employeeEntity = employeeService.getEmployee(employee_id);
            if(employeeEntity == null) return;

            List<PeriodEntity> periodEntityList = periodService.getAllPeriodsByEmployeeId(employeeEntity.getIdemployee());

            Calendar c = Calendar.getInstance();
            long today = c.getTimeInMillis();
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            long monday = c.getTimeInMillis();

            List<PeriodEntity> periodEntities = periodService.getAllPeriodsByEmployeeId(employeeEntity.getIdemployee());
            List<PeriodEntity> periodEntitiesInLimits = new ArrayList<>();
            for (PeriodEntity periodEntity : periodEntities) {
                if(periodEntity.getStart().getTime() >= monday)
                {
                    if((periodEntity.getFinish() == null) || (periodEntity.getFinish().getTime() <= today)){
                        periodEntitiesInLimits.add(periodEntity);
                    }
                }
            }

            Set<TaskEntity> taskEntityList = new HashSet<>();
            for (PeriodEntity periodEntity : periodEntitiesInLimits) {
                Set<TaskEntity> tempTaskEntityList = periodEntity.getTaskEntities();
                for (TaskEntity task : tempTaskEntityList) {
                    if(!(taskEntityList.contains(task))) {
                        taskEntityList.add(task);
                    }
                }
            }

            for (TaskEntity taskEntity : taskEntityList) {
                EmployeeTasksInfo employeeTasksInfo = new EmployeeTasksInfo();
                employeeTasksInfo.setCode(taskEntity.getCode());
                employeeTasksInfo.setDescription(taskEntity.getDescription());
                blanks.add(employeeTasksInfo);
            }

            blanks.sort(Comparator.comparing(EmployeeTasksInfo::getCode));
            EmployeeTasks_Blank factory = new EmployeeTasks_Blank();
            createDocument(DocumentType.valueOf(type), factory, isProtected, response, blanks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <T> void createDocument(DocumentType type, IFactory<T> factory, boolean isProtected, HttpServletResponse response, List<T> entities)
    {
        Builder documentBuilder = new Builder<T>().setModelViewer(factory).setDocumentType(type).setProtectedFromCopy(isProtected);

        try {
            documentBuilder.writeToResponse(entities, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createBlanks(DocumentType type, IFactory<RequestInfo> factory, boolean isProtected, HttpServletResponse response, List<RequestInfo> entities)
    {
        BuilderForBlanks documentBuilder = new BuilderForBlanks().setModelViewer(factory).setDocumentType(type).setProtectedFromCopy(isProtected);

        try {
            documentBuilder.writeToResponse(entities, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createWorked(DocumentType type, IFactory<WorkedInfo> factory, boolean isProtected, HttpServletResponse response, List<WorkedInfo> entities)
    {
        BuilderForWorks documentBuilder = new BuilderForWorks().setModelViewer(factory).setDocumentType(type).setProtectedFromCopy(isProtected);

        try {
            documentBuilder.writeToResponse(entities, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
