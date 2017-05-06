package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.RoleEntity;
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
public class RoleDAOImplTest {
    RoleDAOImpl roleDAO;
    Session session;

    @Before
    public void setUp() throws Exception {
        roleDAO = new RoleDAOImpl();
        SessionFactory sessionFactory = null;
        sessionFactory = new Configuration().configure().buildSessionFactory();

        session = sessionFactory.openSession();
        roleDAO.sessionFactory = sessionFactory;
    }

    @After
    public void tearDown() throws Exception {
        session.clear();
    }

    @Test
    public void addRole() throws Exception {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("abc");
        roleEntity.setCode("abc");

        Transaction trans=roleDAO.sessionFactory.getCurrentSession().beginTransaction();
        roleDAO.addRole(roleEntity);
        roleDAO.deleteRole(roleEntity.getIdrole());
        trans.commit();
    }

    @Test(expected = PropertyValueException.class)
    public void addRole_Name() throws Exception {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setCode("abc");

        Transaction trans=roleDAO.sessionFactory.getCurrentSession().beginTransaction();
        roleDAO.addRole(roleEntity);
        roleDAO.deleteRole(roleEntity.getIdrole());
        trans.commit();
    }

    @Test(expected = PropertyValueException.class)
    public void addRole_Code() throws Exception {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("abc");

        Transaction trans=roleDAO.sessionFactory.getCurrentSession().beginTransaction();
        roleDAO.addRole(roleEntity);
        roleDAO.deleteRole(roleEntity.getIdrole());
        trans.commit();
    }

    @Test(expected = PropertyValueException.class)
    public void addRole_throwsPropertyExeption() throws Exception{
        RoleEntity roleEntity = new RoleEntity();

        Transaction trans = roleDAO.sessionFactory.getCurrentSession().beginTransaction();
        roleDAO.addRole(roleEntity);
        roleDAO.deleteRole(roleEntity.getIdrole());
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addRole_throwsIllegalArgumentException() throws Exception{
        Transaction trans = roleDAO.sessionFactory.getCurrentSession().beginTransaction();
        roleDAO.addRole(null);
        trans.commit();
    }

    @Test
    public void getRole() throws Exception {
        Transaction trans=roleDAO.sessionFactory.getCurrentSession().beginTransaction();
        roleDAO.getRole(1);
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void getRole_throwsArgumentException() throws Exception{
        Transaction trans=roleDAO.sessionFactory.getCurrentSession().beginTransaction();
        roleDAO.getRole(-1);
        trans.commit();
    }

    @Test
    public void getAllRoles() throws Exception {
        Transaction trans=roleDAO.sessionFactory.getCurrentSession().beginTransaction();
        roleDAO.getAllRoles();
    }

    @Test(expected = PersistenceException.class)
    public void updateRole_ThrowsPersistenceException() throws Exception {
        Transaction trans=roleDAO.sessionFactory.getCurrentSession().beginTransaction();
        roleDAO.updateRole(new RoleEntity());
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateRole_ThrowsIllegalArgumentException() throws Exception {
        Transaction trans=roleDAO.sessionFactory.getCurrentSession().beginTransaction();
        roleDAO.updateRole(null);
        trans.commit();
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteRole_throwsEntityNotFoundException() throws Exception{
        Transaction trans = roleDAO.sessionFactory.getCurrentSession().beginTransaction();
        roleDAO.deleteRole(99999);
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteRole_throwsSQLException() throws Exception{
        Transaction trans=roleDAO.sessionFactory.getCurrentSession().beginTransaction();
        roleDAO.deleteRole(-1);
        trans.commit();
    }
}