package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.RoleEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Pavel on 26.04.2017.
 */
@Repository
public class RoleDAOImpl implements RoleDAO {
    @Autowired
    public SessionFactory sessionFactory;

    @Override
    public void addRole(RoleEntity role) {
        if (role == null){
            throw new IllegalArgumentException();
        }
        sessionFactory.getCurrentSession().saveOrUpdate(role);
    }

    @Override
    public RoleEntity getRole(int idRole) {
        if (idRole < 1){
            throw new IllegalArgumentException();
        }
        RoleEntity result;
        try {
            result = (RoleEntity) sessionFactory.getCurrentSession().get(RoleEntity.class, idRole);
        }
        catch (Exception e){
            System.out.println(e);
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        List<RoleEntity> result = null;
        try {
            result = sessionFactory.getCurrentSession().createQuery("from RoleEntity ").list();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public RoleEntity updateRole(RoleEntity role) {
        if (role == null){
            throw new IllegalArgumentException();
        }
        sessionFactory.getCurrentSession().update(role);
        return role;
    }

    @Override
    public void deleteRole(int idRole) {
        if (idRole < 1){
            throw new IllegalArgumentException();
        }
        RoleEntity role = (RoleEntity) sessionFactory.getCurrentSession().load(RoleEntity.class, idRole);
        if (null != role){
            this.sessionFactory.getCurrentSession().delete(role);
        }
    }
}
