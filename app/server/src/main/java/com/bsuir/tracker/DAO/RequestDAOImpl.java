package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.RequestEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Pavel on 26.04.2017.
 */
@Repository
public class RequestDAOImpl implements RequestDAO {
    @Autowired
    public SessionFactory sessionFactory;

    @Override
    public void addRequest(RequestEntity request) {
        if (request == null){
            throw new IllegalArgumentException();
        }
        sessionFactory.getCurrentSession().saveOrUpdate(request);
    }

    @Override
    public RequestEntity getRequest(int idRequest) {
        if (idRequest < 1){
            throw new IllegalArgumentException();
        }
        RequestEntity result;
        try {
            result = (RequestEntity) sessionFactory.getCurrentSession().get(RequestEntity.class, idRequest);
        }
        catch (NoResultException nre){
            result = null;
        }
        catch (Exception e){
            System.out.println(e);
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public List<RequestEntity> getAllRequests() {
        List<RequestEntity> result = null;
        try {
            result = sessionFactory.getCurrentSession().createQuery("from RequestEntity ").list();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    public List<RequestEntity> getAllRequestsByDestEmployeeId(int id) {
        List<RequestEntity> result = null;
        try {
            Query query = sessionFactory.getCurrentSession().createQuery("from RequestEntity where destinationIdemployee=:id");
            query.setParameter("id", id);
            result = query.list();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public RequestEntity updateRequest(RequestEntity request) {
        if (request == null){
            throw new IllegalArgumentException();
        }
        sessionFactory.getCurrentSession().merge(request); //update
        return request;
    }

    @Override
    public void deleteRequest(int idRequest) {
        if (idRequest < 1){
            throw new IllegalArgumentException();
        }
        RequestEntity request = (RequestEntity) sessionFactory.getCurrentSession().load(RequestEntity.class, idRequest);
        if (null != request){
            this.sessionFactory.getCurrentSession().delete(request);
        }
    }
}