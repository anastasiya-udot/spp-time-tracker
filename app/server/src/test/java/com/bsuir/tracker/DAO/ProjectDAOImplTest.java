package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.ProjectEntity;
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
 * Created by Pavel on 26.04.2017.
 */
public class ProjectDAOImplTest {
    ProjectDAOImpl projectDAO;
    Session session;

    @Before
    public void setUp() throws Exception {
        projectDAO = new ProjectDAOImpl();
        SessionFactory sessionFactory = null;
        sessionFactory = new Configuration().configure().buildSessionFactory();

        session = sessionFactory.openSession();
        projectDAO.sessionFactory = sessionFactory;
    }

    @After
    public void tearDown() throws Exception {
        session.clear();
    }

    @Test
    public void addProject() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName("abc");
        projectEntity.setDescription("abc");
        projectEntity.setCompanyIdcompany(1);

        Transaction trans=projectDAO.sessionFactory.getCurrentSession().beginTransaction();
        projectDAO.addProject(projectEntity);
        projectDAO.deleteProject(projectEntity.getIdproject());
        trans.commit();
    }

    @Test(expected = PropertyValueException.class)
    public void addProject_throwsPropertyExeption_Name() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setDescription("abc");
        projectEntity.setCompanyIdcompany(1);

        Transaction trans=projectDAO.sessionFactory.getCurrentSession().beginTransaction();
        projectDAO.addProject(projectEntity);
        projectDAO.deleteProject(projectEntity.getIdproject());
        trans.commit();
    }

    @Test
    public void addProject_Description() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName("abc");
        projectEntity.setCompanyIdcompany(1);

        Transaction trans=projectDAO.sessionFactory.getCurrentSession().beginTransaction();
        projectDAO.addProject(projectEntity);
        projectDAO.deleteProject(projectEntity.getIdproject());
        trans.commit();
    }

    @Test(expected = ConstraintViolationException.class)
    public void addProject_throwsPropertyExeption_CompanyID() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName("abc");
        projectEntity.setDescription("abc");

        Transaction trans=projectDAO.sessionFactory.getCurrentSession().beginTransaction();
        projectDAO.addProject(projectEntity);
        projectDAO.deleteProject(projectEntity.getIdproject());
        trans.commit();
    }

    @Test(expected = PropertyValueException.class)
    public void addProject_throwsPropertyExeption() throws Exception{
        ProjectEntity projectEntity = new ProjectEntity();

        Transaction trans = projectDAO.sessionFactory.getCurrentSession().beginTransaction();
        projectDAO.addProject(projectEntity);
        projectDAO.deleteProject(projectEntity.getIdproject());
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addProject_throwsIllegalArgumentException() throws Exception{
        Transaction trans=projectDAO.sessionFactory.getCurrentSession().beginTransaction();
        projectDAO.addProject(null);
        trans.commit();
    }

    @Test
    public void getProject() throws Exception {
        Transaction trans=projectDAO.sessionFactory.getCurrentSession().beginTransaction();
        projectDAO.getProject(1);
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void getProject_throwsArgumentException() throws Exception{
        Transaction trans=projectDAO.sessionFactory.getCurrentSession().beginTransaction();
        projectDAO.getProject(-1);
        trans.commit();
    }

    @Test
    public void getAllProjects() throws Exception {
        Transaction trans=projectDAO.sessionFactory.getCurrentSession().beginTransaction();
        projectDAO.getAllProjects();
    }

    @Test(expected = PersistenceException.class)
    public void updateProject_ThrowsPersistenceException() throws Exception {
        Transaction trans=projectDAO.sessionFactory.getCurrentSession().beginTransaction();
        projectDAO.updateProject(new ProjectEntity());
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateProject_ThrowsIllegalArgumentException() throws Exception {
        Transaction trans=projectDAO.sessionFactory.getCurrentSession().beginTransaction();
        projectDAO.updateProject(null);
        trans.commit();
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteProject_throwsEntityNotFoundException() throws Exception{
        Transaction trans=projectDAO.sessionFactory.getCurrentSession().beginTransaction();
        projectDAO.deleteProject(99999);
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteProject_throwsSQLException() throws Exception{
        Transaction trans=projectDAO.sessionFactory.getCurrentSession().beginTransaction();
        projectDAO.deleteProject(-1);
        trans.commit();
    }
}