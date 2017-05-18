package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.TaskEntity;
import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import static org.junit.Assert.*;

/**
 * Created by Pavel on 01.05.2017.
 */
public class TaskDAOImplTest {
    TaskDAOImpl taskDAO;
    Session session;

    @Before
    public void setUp() throws Exception {
        taskDAO = new TaskDAOImpl();
        SessionFactory sessionFactory = null;
        sessionFactory = new Configuration().configure().buildSessionFactory();

        session = sessionFactory.openSession();
        taskDAO.sessionFactory = sessionFactory;
    }

    @After
    public void tearDown() throws Exception {
        session.clear();
    }

    @Test
    public void addTask() throws Exception {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setCode("abc");
        taskEntity.setDescription("abc");
        taskEntity.setProjectIdproject(1);

        Transaction trans=taskDAO.sessionFactory.getCurrentSession().beginTransaction();
        taskDAO.addTask(taskEntity);
        trans.rollback();
    }

    @Test(expected = PropertyValueException.class)
    public void addTask_Code() throws Exception {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setDescription("abc");
        taskEntity.setProjectIdproject(1);

        Transaction trans=taskDAO.sessionFactory.getCurrentSession().beginTransaction();
        taskDAO.addTask(taskEntity);
        trans.rollback();
    }

    @Test
    public void addTask_Description() throws Exception {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setCode("abc");
        taskEntity.setProjectIdproject(1);

        Transaction trans=taskDAO.sessionFactory.getCurrentSession().beginTransaction();
        taskDAO.addTask(taskEntity);
        trans.rollback();
    }

    @Test(expected = ConstraintViolationException.class)
    public void addTask_ProjectID() throws Exception{
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setCode("abc");
        taskEntity.setDescription("abc");

        Transaction trans=taskDAO.sessionFactory.getCurrentSession().beginTransaction();
        taskDAO.addTask(taskEntity);
        trans.rollback();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTask_throwsIllegalArgumentException() throws Exception{
        Transaction trans = taskDAO.sessionFactory.getCurrentSession().beginTransaction();
        taskDAO.addTask(null);
        trans.commit();
    }

    @Test
    public void getTask() throws Exception {
        Transaction trans=taskDAO.sessionFactory.getCurrentSession().beginTransaction();
        taskDAO.getTask(1);
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTask_throwsArgumentException() throws Exception{
        Transaction trans = taskDAO.sessionFactory.getCurrentSession().beginTransaction();
        taskDAO.getTask(-1);
        trans.commit();
    }

    @Test
    public void getAllTasks() throws Exception {
        Transaction trans = taskDAO.sessionFactory.getCurrentSession().beginTransaction();
        taskDAO.getAllTasks();
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTask_ThrowsIllegalArgumentException() throws Exception {
        Transaction trans = taskDAO.sessionFactory.getCurrentSession().beginTransaction();
        taskDAO.updateTask(null);
        trans.rollback();
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteTask_throwsEntityNotFoundException() throws Exception{
        Transaction trans = taskDAO.sessionFactory.getCurrentSession().beginTransaction();
        taskDAO.deleteTask(99999);
        trans.rollback();
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteTask_throwsSQLException() throws Exception{
        Transaction trans = taskDAO.sessionFactory.getCurrentSession().beginTransaction();
        taskDAO.deleteTask(-1);
        trans.rollback();
    }
}