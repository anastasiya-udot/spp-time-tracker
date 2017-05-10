package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.WorkdayTypeEntity;
import java.util.List;


public interface WorkdayTypeDAO {

    public void addWorkdayType(WorkdayTypeEntity workdayTypeEntity);                                                      //C

    public WorkdayTypeEntity getWorkdayType(int id);                                                     //R

    public List<WorkdayTypeEntity> getAllWorkdayTypes();

    public WorkdayTypeEntity updateWorkdayType(WorkdayTypeEntity workdayTypeEntity);                                                  //U

    public void deleteWorkdayType(int id);                                                   //D

}