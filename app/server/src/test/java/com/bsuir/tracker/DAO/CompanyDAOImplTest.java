package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.CompanyEntity;
import org.hibernate.PropertyValueException;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by Pavel on 26.04.2017.
 */
public class CompanyDAOImplTest {
    CompanyDAOImpl companyDAO;
    Session session;

    @Before
    public void setUp() throws Exception {
        companyDAO = new CompanyDAOImpl();
        SessionFactory sessionFactory = null;
        sessionFactory = new Configuration().configure().buildSessionFactory();

        session = sessionFactory.openSession();
        companyDAO.sessionFactory = sessionFactory;
    }

    @After
    public void tearDown() throws Exception {
        session.clear();
    }

    @Test
    public void addCompany() throws Exception {

        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setLegalNumber("abc");
        companyEntity.setName("abc");
        companyEntity.setLogoIdimage(1);
        companyEntity.setDescription("abc");

        Transaction trans=companyDAO.sessionFactory.getCurrentSession().beginTransaction();
        companyDAO.addCompany(companyEntity);
        companyDAO.deleteCompany(companyEntity.getIdcompany());
        trans.commit();
    }

    @Test(expected = PersistenceException.class)
    public void addCompany_PersistenceException_idCompany() throws Exception {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setIdcompany(1);
        companyEntity.setLegalNumber("abc");
        companyEntity.setName("abc");
        companyEntity.setLogoIdimage(1);
        companyEntity.setDescription("abc");

        Transaction trans=companyDAO.sessionFactory.getCurrentSession().beginTransaction();
        companyDAO.addCompany(companyEntity);
        companyDAO.deleteCompany(companyEntity.getIdcompany());
        trans.commit();
    }

    @Test(expected = PropertyValueException.class)
    public void addCompany_throwsPropertyExeption_LegalNumber() throws Exception {

        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName("abc");
        companyEntity.setLogoIdimage(1);
        companyEntity.setDescription("abc");

        Transaction trans=companyDAO.sessionFactory.getCurrentSession().beginTransaction();
        companyDAO.addCompany(companyEntity);
        companyDAO.deleteCompany(companyEntity.getIdcompany());
        trans.commit();
    }

    @Test(expected = PropertyValueException.class)
    public void addCompany_throwsPropertyExeption_Name() throws Exception {

        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setLegalNumber("abc");
        companyEntity.setLogoIdimage(1);
        companyEntity.setDescription("abc");

        Transaction trans=companyDAO.sessionFactory.getCurrentSession().beginTransaction();
        companyDAO.addCompany(companyEntity);
        companyDAO.deleteCompany(companyEntity.getIdcompany());
        trans.commit();
    }

    @Test
    public void addCompany_logoIDImage() throws Exception {

        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName("abc");
        companyEntity.setLegalNumber("abc");
        companyEntity.setDescription("abc");

        Transaction trans=companyDAO.sessionFactory.getCurrentSession().beginTransaction();
        companyDAO.addCompany(companyEntity);
        companyDAO.deleteCompany(companyEntity.getIdcompany());
        trans.commit();
    }

    @Test
    public void addCompany_description() throws Exception {

        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName("abc");
        companyEntity.setLegalNumber("abc");
        companyEntity.setLogoIdimage(1);

        Transaction trans=companyDAO.sessionFactory.getCurrentSession().beginTransaction();
        companyDAO.addCompany(companyEntity);
        companyDAO.deleteCompany(companyEntity.getIdcompany());
        trans.commit();
    }

    @Test(expected = PropertyValueException.class)
    public void addCompany_throwsPropertyExeption() throws Exception{
        CompanyEntity companyEntity = new CompanyEntity();

        Transaction trans=companyDAO.sessionFactory.getCurrentSession().beginTransaction();
        companyDAO.addCompany(companyEntity);
        companyDAO.deleteCompany(companyEntity.getIdcompany());
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addCompany_throwsIllegalArgumentException() throws Exception{
        Transaction trans=companyDAO.sessionFactory.getCurrentSession().beginTransaction();
        companyDAO.addCompany(null);
        trans.commit();
    }

    @Test
    public void getCompany() throws Exception {
        Transaction trans=companyDAO.sessionFactory.getCurrentSession().beginTransaction();
        companyDAO.getCompany(1);
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCompany_throwsArgumentException() throws Exception{
        Transaction trans=companyDAO.sessionFactory.getCurrentSession().beginTransaction();
        companyDAO.getCompany(-1);
        trans.commit();
    }

    @Test
    public void getAllCompanies() throws Exception {
        Transaction trans=companyDAO.sessionFactory.getCurrentSession().beginTransaction();
        companyDAO.getAllCompanies();
    }

    @Test(expected = PersistenceException.class)
    public void updateCompany_ThrowsPersistenceException() throws Exception {
        Transaction trans=companyDAO.sessionFactory.getCurrentSession().beginTransaction();
        companyDAO.updateCompany(new CompanyEntity());
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateCompany_ThrowsIllegalArgumentException() throws Exception {
        Transaction trans=companyDAO.sessionFactory.getCurrentSession().beginTransaction();
        companyDAO.updateCompany(null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteCompany_throwsEntityNotFoundException() throws Exception{
        Transaction trans=companyDAO.sessionFactory.getCurrentSession().beginTransaction();
        companyDAO.deleteCompany(99999);
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteCompany_throwsSQLException() throws Exception{
        Transaction trans=companyDAO.sessionFactory.getCurrentSession().beginTransaction();
        companyDAO.deleteCompany(-1);
        trans.commit();
    }
}