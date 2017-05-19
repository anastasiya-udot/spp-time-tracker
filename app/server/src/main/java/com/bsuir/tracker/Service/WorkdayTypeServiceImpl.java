package com.bsuir.tracker.Service;

import com.bsuir.tracker.DAO.WorkdayTypeDAO;
import com.bsuir.tracker.entity.WorkdayTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pavel on 26.04.2017.
 */

@Service
@Transactional
public class WorkdayTypeServiceImpl implements WorkdayTypeService {
    @Autowired
    private WorkdayTypeDAO workdayTypeDAO ;

    @Transactional
    public void addWorkdayType(WorkdayTypeEntity workdayTypeEntity) {
        workdayTypeDAO.addWorkdayType(workdayTypeEntity);
    }

    @Override
    public WorkdayTypeEntity getWorkdayType(int id) {
        return workdayTypeDAO.getWorkdayType(id);
    }

    @Transactional
    public List<WorkdayTypeEntity> getAllWorkdayTypes() {
        return workdayTypeDAO.getAllWorkdayTypes();
    }

    @Override
    public WorkdayTypeEntity updateWorkdayType(WorkdayTypeEntity workdayTypeEntity) {
        return workdayTypeDAO.updateWorkdayType(workdayTypeEntity);
    }

    @Transactional
    public void deleteWorkdayType(int id) {
        workdayTypeDAO.deleteWorkdayType(id);
    }

    public void setWorkdayTypeDAO(WorkdayTypeDAO workdayTypeDAO){
        this.workdayTypeDAO = workdayTypeDAO;
    }
}