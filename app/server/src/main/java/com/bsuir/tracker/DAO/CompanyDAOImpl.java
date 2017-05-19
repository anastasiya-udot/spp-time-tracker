package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.CompanyEntity;
import com.bsuir.tracker.model.CompanyNameIdModel;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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
    public List<CompanyNameIdModel> getAllCompaniesNamesId()
    {
        List<CompanyNameIdModel> result = new ArrayList<CompanyNameIdModel>();
        try {
            List<Object[]> tempResult = sessionFactory.getCurrentSession().createQuery("select company.id, company.name from CompanyEntity company").list();
            if (tempResult != null)
            {
                for (Object[] tempObject : tempResult) {
                    CompanyNameIdModel tempCompany =  new CompanyNameIdModel();
                    tempCompany.setId((int)tempObject[0]);
                    tempCompany.setName((String)tempObject[1]);
                    result.add(tempCompany);
                }
            }
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
