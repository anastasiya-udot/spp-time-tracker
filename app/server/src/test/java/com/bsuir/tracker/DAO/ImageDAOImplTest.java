package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.ImageEntity;
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

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by Pavel on 26.04.2017.
 */
public class ImageDAOImplTest {
    ImageDAOImpl imageDAO;
    Session session;

    @Before
    public void setUp() throws Exception {
        imageDAO = new ImageDAOImpl();
        SessionFactory sessionFactory = null;
        sessionFactory = new Configuration().configure().buildSessionFactory();

        session = sessionFactory.openSession();
        imageDAO.sessionFactory = sessionFactory;
    }

    @After
    public void tearDown() throws Exception {
        session.clear();
    }

    @Test
    public void addImage() throws Exception{
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setUrl("abc");
        imageEntity.setPublicId("abc");

        Transaction trans=imageDAO.sessionFactory.getCurrentSession().beginTransaction();
        imageDAO.addImage(imageEntity);
        trans.commit();
    }

    /*@Test(expected = PersistenceException.class)
    public void addImage_idImage() throws Exception{
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setIdimage(1);
        imageEntity.setUrl("abc");
        imageEntity.setPublicId("abc");

        Transaction trans=imageDAO.sessionFactory.getCurrentSession().beginTransaction();
        imageDAO.addImage(imageEntity);
        trans.commit();
        imageDAO.deleteImage(imageEntity.getIdimage());
        trans.commit();
    }*/

    @Test(expected = PropertyValueException.class)
    public void addImage_throwsPropertyExeption_PublicID() throws Exception {

        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setUrl("abc");

        Transaction trans=imageDAO.sessionFactory.getCurrentSession().beginTransaction();
        imageDAO.addImage(imageEntity);
        trans.commit();
        imageDAO.deleteImage(imageEntity.getIdimage());
        trans.commit();
    }

    @Test(expected = PropertyValueException.class)
    public void addImage_throwsPropertyExeption_URL() throws Exception {

        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setPublicId("abc");

        Transaction trans=imageDAO.sessionFactory.getCurrentSession().beginTransaction();
        imageDAO.addImage(imageEntity);
        imageDAO.deleteImage(imageEntity.getIdimage());
        trans.commit();
    }

    @Test(expected = PropertyValueException.class)
    public void addImage_throwsPropertyExeption() throws Exception{
        ImageEntity imageEntity = new ImageEntity();

        Transaction trans = imageDAO.sessionFactory.getCurrentSession().beginTransaction();
        imageDAO.addImage(imageEntity);
        imageDAO.deleteImage(imageEntity.getIdimage());
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addImage_throwsIllegalArgumentException() throws Exception{
        Transaction trans=imageDAO.sessionFactory.getCurrentSession().beginTransaction();
        imageDAO.addImage(null);
        trans.commit();
    }

    @Test
    public void getImage() throws Exception {
        Transaction trans=imageDAO.sessionFactory.getCurrentSession().beginTransaction();
        imageDAO.getImage(1);
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void getImage_throwsArgumentException() throws Exception{
        Transaction trans=imageDAO.sessionFactory.getCurrentSession().beginTransaction();
        imageDAO.getImage(-1);
        trans.commit();
    }

    @Test
    public void getAllImages() throws Exception {
        Transaction trans=imageDAO.sessionFactory.getCurrentSession().beginTransaction();
        imageDAO.getAllImages();
    }

    @Test(expected = PersistenceException.class)
    public void updateImage_ThrowsPersistenceException() throws Exception {
        Transaction trans=imageDAO.sessionFactory.getCurrentSession().beginTransaction();
        imageDAO.updateImage(new ImageEntity());
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateImage_ThrowsIllegalArgumentException() throws Exception {
        Transaction trans=imageDAO.sessionFactory.getCurrentSession().beginTransaction();
        imageDAO.updateImage(null);
        trans.commit();
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteImage_throwsEntityNotFoundException() throws Exception{
        Transaction trans=imageDAO.sessionFactory.getCurrentSession().beginTransaction();
        imageDAO.deleteImage(99999);
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteImage_throwsSQLException() throws Exception{
        Transaction trans=imageDAO.sessionFactory.getCurrentSession().beginTransaction();
        imageDAO.deleteImage(-1);
        trans.commit();
    }

}