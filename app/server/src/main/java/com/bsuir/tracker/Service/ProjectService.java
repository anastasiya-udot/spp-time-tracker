package com.bsuir.tracker.Service;

import com.bsuir.tracker.entity.ProjectEntity;

import java.util.List;

/**
 * Created by Pavel on 26.04.2017.
 */
public interface ProjectService {

    public void addProject(ProjectEntity project);

    public ProjectEntity getProject(int idProject);

    public List<ProjectEntity> getAllProjects();

    public ProjectEntity updateProject(ProjectEntity project);

    public void deleteProject(int idProject);

}
