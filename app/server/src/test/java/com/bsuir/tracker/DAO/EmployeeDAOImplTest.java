package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.EmployeeEntity;
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
public class EmployeeDAOImplTest {
    EmployeeDAOImpl employeeDAO;
    Session session;

    @Before
    public void setUp() throws Exception {
        employeeDAO = new EmployeeDAOImpl();
        SessionFactory sessionFactory = null;
        sessionFactory = new Configuration().configure().buildSessionFactory();

        session = sessionFactory.openSession();
        employeeDAO.sessionFactory = sessionFactory;
    }

    @After
    public void tearDown() throws Exception {
        session.clear();
    }

    @Test
    public void addEmployee() throws Exception {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("abc");
        employeeEntity.setSurname("abc");
        employeeEntity.setEmail("abc");
        employeeEntity.setPassword("abc");
        employeeEntity.setRoleIdrole(1);
        employeeEntity.setCompanyIdcompany(1);

        employeeEntity.setAvatarIdimage(1);
        employeeEntity.setResetPasswordToken("abc");
        employeeEntity.setChangeEmailToken("abc");
        employeeEntity.setConfirmRegisterToken("abc");
        employeeEntity.setTempEmail("abc");
        employeeEntity.setPatronymic("abc");
        employeeEntity.setWorkdayIdworkdayType(1);

        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.addEmployee(employeeEntity);
        employeeDAO.deleteEmployee(employeeEntity.getIdemployee());
        trans.commit();
    }

    @Test(expected = PersistenceException.class)
    public void addEmployee_idEmployee() throws Exception {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setIdemployee(1);
        employeeEntity.setName("abc");
        employeeEntity.setSurname("abc");
        employeeEntity.setEmail("abc");
        employeeEntity.setPassword("abc");
        employeeEntity.setRoleIdrole(1);
        employeeEntity.setCompanyIdcompany(1);

        employeeEntity.setAvatarIdimage(1);
        employeeEntity.setResetPasswordToken("abc");
        employeeEntity.setChangeEmailToken("abc");
        employeeEntity.setConfirmRegisterToken("abc");
        employeeEntity.setTempEmail("abc");
        employeeEntity.setPatronymic("abc");
        employeeEntity.setWorkdayIdworkdayType(1);

        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.addEmployee(employeeEntity);
        employeeDAO.deleteEmployee(employeeEntity.getIdemployee());
        trans.commit();
    }

    @Test
    public void addEmployee_AvatarIDImage() throws Exception {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("abc");
        employeeEntity.setSurname("abc");
        employeeEntity.setEmail("abc");
        employeeEntity.setPassword("abc");
        employeeEntity.setRoleIdrole(1);
        employeeEntity.setCompanyIdcompany(1);

        employeeEntity.setResetPasswordToken("abc");
        employeeEntity.setChangeEmailToken("abc");
        employeeEntity.setConfirmRegisterToken("abc");
        employeeEntity.setTempEmail("abc");
        employeeEntity.setPatronymic("abc");
        employeeEntity.setWorkdayIdworkdayType(1);

        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.addEmployee(employeeEntity);
        employeeDAO.deleteEmployee(employeeEntity.getIdemployee());
        trans.commit();
    }

    @Test
    public void addEmployee_ResetPasswordToken() throws Exception {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("abc");
        employeeEntity.setSurname("abc");
        employeeEntity.setEmail("abc");
        employeeEntity.setPassword("abc");
        employeeEntity.setRoleIdrole(1);
        employeeEntity.setCompanyIdcompany(1);

        employeeEntity.setAvatarIdimage(1);
        employeeEntity.setChangeEmailToken("abc");
        employeeEntity.setConfirmRegisterToken("abc");
        employeeEntity.setTempEmail("abc");
        employeeEntity.setPatronymic("abc");
        employeeEntity.setWorkdayIdworkdayType(1);

        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.addEmployee(employeeEntity);
        employeeDAO.deleteEmployee(employeeEntity.getIdemployee());
        trans.commit();
    }

    @Test
    public void addEmployee_ChangeEmailToken() throws Exception {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("abc");
        employeeEntity.setSurname("abc");
        employeeEntity.setEmail("abc");
        employeeEntity.setPassword("abc");
        employeeEntity.setRoleIdrole(1);
        employeeEntity.setCompanyIdcompany(1);

        employeeEntity.setAvatarIdimage(1);
        employeeEntity.setResetPasswordToken("abc");
        employeeEntity.setConfirmRegisterToken("abc");
        employeeEntity.setTempEmail("abc");
        employeeEntity.setPatronymic("abc");
        employeeEntity.setWorkdayIdworkdayType(1);

        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.addEmployee(employeeEntity);
        employeeDAO.deleteEmployee(employeeEntity.getIdemployee());
        trans.commit();
    }

    @Test
    public void addEmployee_ConfirmRegisterToken() throws Exception {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("abc");
        employeeEntity.setSurname("abc");
        employeeEntity.setEmail("abc");
        employeeEntity.setPassword("abc");
        employeeEntity.setRoleIdrole(1);
        employeeEntity.setCompanyIdcompany(1);

        employeeEntity.setAvatarIdimage(1);
        employeeEntity.setResetPasswordToken("abc");
        employeeEntity.setChangeEmailToken("abc");
        employeeEntity.setTempEmail("abc");
        employeeEntity.setPatronymic("abc");
        employeeEntity.setWorkdayIdworkdayType(1);

        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.addEmployee(employeeEntity);
        employeeDAO.deleteEmployee(employeeEntity.getIdemployee());
        trans.commit();
    }

    @Test
    public void addEmployee_TempEmail() throws Exception {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("abc");
        employeeEntity.setSurname("abc");
        employeeEntity.setEmail("abc");
        employeeEntity.setPassword("abc");
        employeeEntity.setRoleIdrole(1);
        employeeEntity.setCompanyIdcompany(1);

        employeeEntity.setAvatarIdimage(1);
        employeeEntity.setResetPasswordToken("abc");
        employeeEntity.setChangeEmailToken("abc");
        employeeEntity.setConfirmRegisterToken("abc");
        employeeEntity.setPatronymic("abc");
        employeeEntity.setWorkdayIdworkdayType(1);

        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.addEmployee(employeeEntity);
        employeeDAO.deleteEmployee(employeeEntity.getIdemployee());
        trans.commit();
    }

    @Test
    public void addEmployee_Patronymic() throws Exception {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("abc");
        employeeEntity.setSurname("abc");
        employeeEntity.setEmail("abc");
        employeeEntity.setPassword("abc");
        employeeEntity.setRoleIdrole(1);
        employeeEntity.setCompanyIdcompany(1);

        employeeEntity.setAvatarIdimage(1);
        employeeEntity.setResetPasswordToken("abc");
        employeeEntity.setChangeEmailToken("abc");
        employeeEntity.setConfirmRegisterToken("abc");
        employeeEntity.setTempEmail("abc");
        employeeEntity.setWorkdayIdworkdayType(1);

        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.addEmployee(employeeEntity);
        employeeDAO.deleteEmployee(employeeEntity.getIdemployee());
        trans.commit();
    }

    @Test
    public void addEmployee_WorkDayType() throws Exception {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("abc");
        employeeEntity.setSurname("abc");
        employeeEntity.setEmail("abc");
        employeeEntity.setPassword("abc");
        employeeEntity.setRoleIdrole(1);
        employeeEntity.setCompanyIdcompany(1);

        employeeEntity.setAvatarIdimage(1);
        employeeEntity.setResetPasswordToken("abc");
        employeeEntity.setChangeEmailToken("abc");
        employeeEntity.setConfirmRegisterToken("abc");
        employeeEntity.setTempEmail("abc");
        employeeEntity.setPatronymic("abc");

        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.addEmployee(employeeEntity);
        employeeDAO.deleteEmployee(employeeEntity.getIdemployee());
        trans.commit();
    }

    @Test(expected = PropertyValueException.class)
    public void addEmployee_throwsPropertyExeption_Name() throws Exception {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setSurname("abc");
        employeeEntity.setEmail("abc");
        employeeEntity.setPassword("abc");
        employeeEntity.setRoleIdrole(1);
        employeeEntity.setCompanyIdcompany(1);

        employeeEntity.setAvatarIdimage(1);
        employeeEntity.setResetPasswordToken("abc");
        employeeEntity.setChangeEmailToken("abc");
        employeeEntity.setConfirmRegisterToken("abc");
        employeeEntity.setTempEmail("abc");
        employeeEntity.setPatronymic("abc");
        employeeEntity.setWorkdayIdworkdayType(1);

        Transaction trans = employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.addEmployee(employeeEntity);
        employeeDAO.deleteEmployee(employeeEntity.getIdemployee());
        trans.commit();
    }

    @Test(expected = PropertyValueException.class)
    public void addEmployee_throwsPropertyExeption_Surname() throws Exception {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("abc");
        employeeEntity.setEmail("abc");
        employeeEntity.setPassword("abc");
        employeeEntity.setRoleIdrole(1);
        employeeEntity.setCompanyIdcompany(1);

        employeeEntity.setAvatarIdimage(1);
        employeeEntity.setResetPasswordToken("abc");
        employeeEntity.setChangeEmailToken("abc");
        employeeEntity.setConfirmRegisterToken("abc");
        employeeEntity.setTempEmail("abc");
        employeeEntity.setPatronymic("abc");
        employeeEntity.setWorkdayIdworkdayType(1);

        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.addEmployee(employeeEntity);
        employeeDAO.deleteEmployee(employeeEntity.getIdemployee());
        trans.commit();
    }

    @Test(expected = PropertyValueException.class)
    public void addEmployee_throwsPropertyExeption_Email() throws Exception {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("abc");
        employeeEntity.setSurname("abc");
        employeeEntity.setPassword("abc");
        employeeEntity.setRoleIdrole(1);
        employeeEntity.setCompanyIdcompany(1);

        employeeEntity.setAvatarIdimage(1);
        employeeEntity.setResetPasswordToken("abc");
        employeeEntity.setChangeEmailToken("abc");
        employeeEntity.setConfirmRegisterToken("abc");
        employeeEntity.setTempEmail("abc");
        employeeEntity.setPatronymic("abc");
        employeeEntity.setWorkdayIdworkdayType(1);

        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.addEmployee(employeeEntity);
        employeeDAO.deleteEmployee(employeeEntity.getIdemployee());
        trans.commit();
    }

    @Test(expected = PropertyValueException.class)
    public void addEmployee_throwsPropertyExeption_Password() throws Exception {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("abc");
        employeeEntity.setSurname("abc");
        employeeEntity.setEmail("abc");
        employeeEntity.setRoleIdrole(1);
        employeeEntity.setCompanyIdcompany(1);

        employeeEntity.setAvatarIdimage(1);
        employeeEntity.setResetPasswordToken("abc");
        employeeEntity.setChangeEmailToken("abc");
        employeeEntity.setConfirmRegisterToken("abc");
        employeeEntity.setTempEmail("abc");
        employeeEntity.setPatronymic("abc");
        employeeEntity.setWorkdayIdworkdayType(1);

        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.addEmployee(employeeEntity);
        employeeDAO.deleteEmployee(employeeEntity.getIdemployee());
        trans.commit();
    }

    @Test(expected = ConstraintViolationException.class)
    public void addEmployee_throwsConstraintViolationException_RoleID() throws Exception {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("abc");
        employeeEntity.setSurname("abc");
        employeeEntity.setEmail("abc");
        employeeEntity.setPassword("abc");
        employeeEntity.setCompanyIdcompany(1);

        employeeEntity.setAvatarIdimage(1);
        employeeEntity.setResetPasswordToken("abc");
        employeeEntity.setChangeEmailToken("abc");
        employeeEntity.setConfirmRegisterToken("abc");
        employeeEntity.setTempEmail("abc");
        employeeEntity.setPatronymic("abc");
        employeeEntity.setWorkdayIdworkdayType(1);

        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.addEmployee(employeeEntity);
        employeeDAO.deleteEmployee(employeeEntity.getIdemployee());
        trans.commit();
    }

    @Test(expected = ConstraintViolationException.class)
    public void addEmployee_throwsConstraintViolationException_CompanyID() throws Exception {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("abc");
        employeeEntity.setSurname("abc");
        employeeEntity.setEmail("abc");
        employeeEntity.setPassword("abc");
        employeeEntity.setRoleIdrole(1);

        employeeEntity.setAvatarIdimage(1);
        employeeEntity.setResetPasswordToken("abc");
        employeeEntity.setChangeEmailToken("abc");
        employeeEntity.setConfirmRegisterToken("abc");
        employeeEntity.setTempEmail("abc");
        employeeEntity.setPatronymic("abc");
        employeeEntity.setWorkdayIdworkdayType(1);

        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.addEmployee(employeeEntity);
        employeeDAO.deleteEmployee(employeeEntity.getIdemployee());
        trans.commit();
    }

    @Test(expected = PropertyValueException.class)
    public void addEmployee_throwsPropertyExeption() throws Exception{
        EmployeeEntity employeeEntity = new EmployeeEntity();

        Transaction trans = employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.addEmployee(employeeEntity);
        employeeDAO.deleteEmployee(employeeEntity.getIdemployee());
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEmployee_throwsIllegalArgumentException() throws Exception{
        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.addEmployee(null);
        trans.commit();
    }

    @Test
    public void getEmployee() throws Exception {
        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.getEmployee(1);
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void getEmployee_throwsArgumentException() throws Exception{
        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.getEmployee(-1);
        trans.commit();
    }

    @Test
    public void getAllEmployees() throws Exception {
        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.getAllEmployees();
    }

    @Test(expected = PersistenceException.class)
    public void updateEmployee_ThrowsPersistenceException() throws Exception {
        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.updateEmployee(new EmployeeEntity());
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateEmployee_ThrowsIllegalArgumentException() throws Exception {
        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.updateEmployee(null);
        trans.commit();
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteEmployee_throwsEntityNotFoundException() throws Exception{
        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.deleteEmployee(99999);
        trans.commit();
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteEmployee_throwsSQLException() throws Exception{
        Transaction trans=employeeDAO.sessionFactory.getCurrentSession().beginTransaction();
        employeeDAO.deleteEmployee(-1);
        trans.commit();
    }
}