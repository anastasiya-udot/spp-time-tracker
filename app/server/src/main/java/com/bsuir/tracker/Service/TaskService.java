package com.bsuir.tracker.Service;

import com.bsuir.tracker.entity.TaskEntity;

import java.util.List;

/**
 * Created by Pavel on 01.05.2017.
 */
public interface TaskService
{

        public void addTask(TaskEntity task);

        public TaskEntity getTask(int idTask);

        public List<TaskEntity> getAllTasks();

        public TaskEntity updateTask(TaskEntity task);

        public void deleteTask(int idTask);

}
