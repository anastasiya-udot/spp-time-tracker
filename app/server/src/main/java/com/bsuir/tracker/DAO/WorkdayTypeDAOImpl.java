package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.WorkdayTypeEntity;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by Pavel on 25.04.2017.
 */

@Repository
public class WorkdayTypeDAOImpl implements WorkdayTypeDAO {
    @Autowired
    public SessionFactory sessionFactory;

    @Override
    public void addWorkdayType(WorkdayTypeEntity workdayTypeEntity) {
        if (workdayTypeEntity == null){
            throw new IllegalArgumentException();
        }
        sessionFactory.getCurrentSession().saveOrUpdate(workdayTypeEntity);
    }

    @Override
    public WorkdayTypeEntity getWorkdayType(int id) {
        if (id < 1){
            throw new IllegalArgumentException();
        }
        WorkdayTypeEntity result;
        try {
            result = (WorkdayTypeEntity) sessionFactory.getCurrentSession().get(WorkdayTypeEntity.class, id);
        }
        catch (Exception e){
            System.out.println(e);
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public List<WorkdayTypeEntity> getAllWorkdayTypes() {
        List<WorkdayTypeEntity> result = null;
        try {
            result = sessionFactory.getCurrentSession().createQuery("from WorkdayTypeEntity").list();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public WorkdayTypeEntity updateWorkdayType(WorkdayTypeEntity workdayTypeEntity) {
        if (workdayTypeEntity == null){
            throw new IllegalArgumentException();
        }
        sessionFactory.getCurrentSession().update(workdayTypeEntity);
        return workdayTypeEntity;
    }

    @Override
    public void deleteWorkdayType(int id) {
        if (id < 1){
            throw new IllegalArgumentException();
        }
        WorkdayTypeEntity workdayTypeEntity = (WorkdayTypeEntity) sessionFactory.getCurrentSession().load(WorkdayTypeEntity.class, id);
        if (null != workdayTypeEntity){
            this.sessionFactory.getCurrentSession().delete(workdayTypeEntity);
        }
    }
}