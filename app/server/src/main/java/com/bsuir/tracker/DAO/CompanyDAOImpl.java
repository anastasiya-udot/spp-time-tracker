package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.CompanyEntity;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import java.util.List;

/**
 * Created by Pavel on 25.04.2017.
 */

@Repository
public class CompanyDAOImpl implements CompanyDAO {
    @Autowired
    public SessionFactory sessionFactory;

    @Override
    public void addCompany(CompanyEntity company) {
        if (company == null){
            throw new IllegalArgumentException();
        }
        sessionFactory.getCurrentSession().saveOrUpdate(company);
    }

    @Override
    public CompanyEntity getCompany(int idCompany) {
        if (idCompany < 1){
            throw new IllegalArgumentException();
        }
        CompanyEntity result;
        try {
            result = (CompanyEntity) sessionFactory.getCurrentSession().get(CompanyEntity.class, idCompany);
        }
        catch (Exception e){
            System.out.println(e);
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public List<CompanyEntity> getAllCompanies() {
        List<CompanyEntity> result = null;
        try {
            result = sessionFactory.getCurrentSession().createQuery("from CompanyEntity").list();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public List<Object> getAllCompaniesNamesId()
    {
        List<Object> result = null;
        try {
            result = sessionFactory.getCurrentSession().createQuery("select CompanyEntity .id, CompanyEntity.name from CompanyEntity").list();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public CompanyEntity updateCompany(CompanyEntity company) {
        if (company == null){
            throw new IllegalArgumentException();
        }
        sessionFactory.getCurrentSession().update(company);
        return company;
    }

    @Override
    public void deleteCompany(int idCompany) {
        if (idCompany < 1){
            throw new IllegalArgumentException();
        }
        CompanyEntity company = (CompanyEntity) sessionFactory.getCurrentSession().load(CompanyEntity.class, idCompany);
        if (null != company){
            this.sessionFactory.getCurrentSession().delete(company);
        }
    }
}
