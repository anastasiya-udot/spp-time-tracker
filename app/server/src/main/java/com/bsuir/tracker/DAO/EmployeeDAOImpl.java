package com.bsuir.tracker.DAO;

import com.bsuir.tracker.Service.EmployeeService;
import com.bsuir.tracker.entity.EmployeeEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bsuir.tracker.entity.CompanyEntity;

import org.hibernate.query.Query;
import java.util.List;

/**
 * Created by Pavel on 25.04.2017.
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    @Autowired
    public SessionFactory sessionFactory;

    @Override
    public void addEmployee(EmployeeEntity employee) {
        if (employee == null){
            throw new IllegalArgumentException();
        }
        sessionFactory.getCurrentSession().saveOrUpdate(employee);
    }

    @Override
    public EmployeeEntity getEmployee(int idEmployee) {
        if (idEmployee < 1){
            throw new IllegalArgumentException();
        }
        EmployeeEntity result;
        try {
            result = (EmployeeEntity) sessionFactory.getCurrentSession().get(EmployeeEntity.class, idEmployee);
        }
        catch (Exception e){
            System.out.println(e);
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public EmployeeEntity getEmployeeByMail(String  email) {
        EmployeeEntity result = null;
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from EmployeeEntity where email=:email");
            query.setParameter("email", email);
            result = (EmployeeEntity) query.uniqueResult();
        }
        catch (Exception e){
            System.out.println(e);
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public List<EmployeeEntity> getEmployeeByCompany(int  id) {
        List<EmployeeEntity> result = null;
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from EmployeeEntity where companyIdcompany=:companyIdcompany");
            query.setParameter("companyIdcompany", id);
            result = query.list();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        List<EmployeeEntity> result = null;
        try {
            result = sessionFactory.getCurrentSession().createQuery("from EmployeeEntity").list();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public EmployeeEntity updateEmployee(EmployeeEntity employee) {
        if (employee == null){
            throw new IllegalArgumentException();
        }
        sessionFactory.getCurrentSession().merge(employee); //Update
        return employee;
    }

    @Override
    public void deleteEmployee(int idEmployee) {
        if (idEmployee < 1){
            throw new IllegalArgumentException();
        }
        EmployeeEntity employee = (EmployeeEntity) sessionFactory.getCurrentSession().load(EmployeeEntity.class, idEmployee);
        if (null != employee){
            this.sessionFactory.getCurrentSession().delete(employee);
        }
    }
}
