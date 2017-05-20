package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.PeriodEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Pavel on 26.04.2017.
 */
@Repository
public class PeriodDAOImpl implements PeriodDAO {
    @Autowired
    public SessionFactory sessionFactory;

    @Override
    public void addPeriod(PeriodEntity period) {
        if (period == null){
            throw new IllegalArgumentException();
        }
        sessionFactory.getCurrentSession().saveOrUpdate(period);
    }

    @Override
    public PeriodEntity getPeriod(int idPeriod) {
        if (idPeriod < 1){
            throw new IllegalArgumentException();
        }
        PeriodEntity result;
        try {
            result = (PeriodEntity) sessionFactory.getCurrentSession().get(PeriodEntity.class, idPeriod);
        }
        catch (Exception e){
            System.out.println(e);
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public List<PeriodEntity> getAllPeriods() {
        List<PeriodEntity> result = null;
        try {
            result = sessionFactory.getCurrentSession().createQuery("from PeriodEntity ").list();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public List<PeriodEntity> getAllPeriodsByEmployeeId(int id) {
        List<PeriodEntity> result = null;
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from PeriodEntity where employeeIdemployee=:id");
            query.setParameter("id", id);
            result = query.list();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public PeriodEntity updatePeriod(PeriodEntity period) {
        if (period == null){
            throw new IllegalArgumentException();
        }
        sessionFactory.getCurrentSession().update(period);
        return period;
    }

    @Override
    public void deletePeriod(int idPeriod) {
        if (idPeriod < 1){
            throw new IllegalArgumentException();
        }
        PeriodEntity period = (PeriodEntity) sessionFactory.getCurrentSession().load(PeriodEntity.class, idPeriod);
        if (null != period){
            this.sessionFactory.getCurrentSession().delete(period);
        }
    }
}
