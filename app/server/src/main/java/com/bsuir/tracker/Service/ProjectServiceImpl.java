package com.bsuir.tracker.Service;

import com.bsuir.tracker.DAO.ProjectDAO;
import com.bsuir.tracker.entity.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pavel on 26.04.2017.
 */

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectDAO projectDAO;

    @Transactional
    public void addProject(ProjectEntity project) {
        projectDAO.addProject(project);
    }

    @Override
    public ProjectEntity getProject(int idProject) {
        return projectDAO.getProject(idProject);
    }

    @Transactional
    public List<ProjectEntity> getAllProjects() {
        return projectDAO.getAllProjects();
    }

    @Override
    public ProjectEntity updateProject(ProjectEntity project) {
        return projectDAO.updateProject(project);
    }

    @Transactional
    public void deleteProject(int idProject) {
        projectDAO.deleteProject(idProject);
    }

    public void setProjectDAO(ProjectDAO projectDAO){
        this.projectDAO = projectDAO;
    }
}
