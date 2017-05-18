package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.ProjectEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Pavel on 26.04.2017.
 */
@Repository
public class ProjectDAOImpl implements ProjectDAO {
    @Autowired
    public SessionFactory sessionFactory;

    @Override
    public void addProject(ProjectEntity project) {
        if (project == null){
            throw new IllegalArgumentException();
        }
        sessionFactory.getCurrentSession().saveOrUpdate(project);
    }

    @Override
    public ProjectEntity getProject(int idProject) {
        if (idProject < 1){
            throw new IllegalArgumentException();
        }
        ProjectEntity result;
        try {
            result = (ProjectEntity) sessionFactory.getCurrentSession().get(ProjectEntity.class, idProject);
        }
        catch (Exception e){
            System.out.println(e);
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public List<ProjectEntity> getAllProjects() {
        List<ProjectEntity> result = null;
        try {
            result = sessionFactory.getCurrentSession().createQuery("from ProjectEntity ").list();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public ProjectEntity updateProject(ProjectEntity project) {
        if (project == null){
            throw new IllegalArgumentException();
        }
        sessionFactory.getCurrentSession().update(project);
        return project;
    }

    @Override
    public void deleteProject(int idProject) {
        if (idProject < 1){
            throw new IllegalArgumentException();
        }
        ProjectEntity project = (ProjectEntity) sessionFactory.getCurrentSession().load(ProjectEntity.class, idProject);
        if (null != project){
            this.sessionFactory.getCurrentSession().delete(project);
        }
    }
}
