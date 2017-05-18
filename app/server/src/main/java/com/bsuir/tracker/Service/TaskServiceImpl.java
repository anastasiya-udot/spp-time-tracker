package com.bsuir.tracker.Service;

import com.bsuir.tracker.DAO.TaskDAO;
import com.bsuir.tracker.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pavel on 26.04.2017.
 */

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDAO taskDAO;

    @Transactional
    public void addTask(TaskEntity task) {
        taskDAO.addTask(task);
    }

    @Override
    public TaskEntity getTask(int idTask) {
        return taskDAO.getTask(idTask);
    }

    @Transactional
    public List<TaskEntity> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    @Override
    public TaskEntity updateTask(TaskEntity task) {
        return taskDAO.updateTask(task);
    }

    @Transactional
    public void deleteTask(int idTask) {
        taskDAO.deleteTask(idTask);
    }

    public void setTaskDAO(TaskDAO taskDAO){
        this.taskDAO = taskDAO;
    }
}