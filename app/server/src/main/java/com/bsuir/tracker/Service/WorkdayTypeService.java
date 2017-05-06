package com.bsuir.tracker.Service;

import com.bsuir.tracker.entity.WorkdayTypeEntity;

import java.util.List;

/**
 * Created by Pavel on 01.05.2017.
 */
public interface WorkdayTypeService
{

    public void addWorkdayType(WorkdayTypeEntity workdayTypeEntity);

    public WorkdayTypeEntity getWorkdayType(int id);

    public List<WorkdayTypeEntity> getAllWorkdayTypes();

    public WorkdayTypeEntity updateWorkdayType(WorkdayTypeEntity workdayTypeEntity);

    public void deleteWorkdayType(int id);

}