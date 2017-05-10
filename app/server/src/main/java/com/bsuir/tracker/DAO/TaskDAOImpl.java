package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.TaskEntity;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Pavel on 25.04.2017.
 */

@Repository
public class TaskDAOImpl implements TaskDAO {
    @Autowired
    public SessionFactory sessionFactory;

    @Override
    public void addTask(TaskEntity task) {
        if (task == null){
            throw new IllegalArgumentException();
        }
        sessionFactory.getCurrentSession().saveOrUpdate(task);
    }

    @Override
    public TaskEntity getTask(int idTask) {
        if (idTask < 1){
            throw new IllegalArgumentException();
        }
        TaskEntity result;
        try {
            result = (TaskEntity) sessionFactory.getCurrentSession().get(TaskEntity.class, idTask);
        }
        catch (Exception e){
            System.out.println(e);
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public List<TaskEntity> getAllTasks() {
        List<TaskEntity> result = null;
        try {
            result = sessionFactory.getCurrentSession().createQuery("from TaskEntity").list();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public TaskEntity updateTask(TaskEntity task) {
        if (task == null){
            throw new IllegalArgumentException();
        }
        sessionFactory.getCurrentSession().update(task);
        return task;
    }

    @Override
    public void deleteTask(int idTask) {
        if (idTask < 1){
            throw new IllegalArgumentException();
        }
        TaskEntity company = (TaskEntity) sessionFactory.getCurrentSession().load(TaskEntity.class, idTask);
        if (null != company){
            this.sessionFactory.getCurrentSession().delete(company);
        }
    }
}