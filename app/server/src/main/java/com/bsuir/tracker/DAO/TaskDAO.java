package com.bsuir.tracker.DAO;

import com.bsuir.tracker.entity.TaskEntity;
import java.util.List;

/**
 * Created by Pavel on 15.04.2017.
 */

public interface TaskDAO {

    public void addTask(TaskEntity task);                                                      //C

    public TaskEntity getTask(int idTask);                                                     //R

    public List<TaskEntity> getAllTasks();

    public TaskEntity updateTask(TaskEntity task);                                                  //U

    public void deleteTask(int idTask);                                                   //D

}