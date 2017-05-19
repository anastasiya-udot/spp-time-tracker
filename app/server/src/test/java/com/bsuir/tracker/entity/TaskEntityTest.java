package com.bsuir.tracker.entity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pavel on 01.05.2017.
 */
public class TaskEntityTest {
    @Test(expected = IllegalArgumentException.class)
    public void setIdtask() throws Exception {
        TaskEntity task = new TaskEntity();
        task.setIdtask(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setProjectIdproject() throws Exception {
        TaskEntity task = new TaskEntity();
        task.setProjectIdproject(-1);
    }

}