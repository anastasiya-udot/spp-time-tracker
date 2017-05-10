package com.bsuir.tracker.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Pavel on 25.04.2017.
 */
@Entity
@Table(name = "task_period", schema = "spp-time-tracker", catalog = "")
public class TaskPeriodEntity {
    private int taskIdtask;
    private int periodIdperiod;

    @Basic
    @Column(name = "task_idtask", nullable = false)
    public int getTaskIdtask() {
        return taskIdtask;
    }

    public void setTaskIdtask(int taskIdtask) {
        this.taskIdtask = taskIdtask;
    }

    @Basic
    @Column(name = "period_idperiod", nullable = false)
    public int getPeriodIdperiod() {
        return periodIdperiod;
    }

    public void setPeriodIdperiod(int periodIdperiod) {
        this.periodIdperiod = periodIdperiod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskPeriodEntity that = (TaskPeriodEntity) o;

        if (taskIdtask != that.taskIdtask) return false;
        if (periodIdperiod != that.periodIdperiod) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taskIdtask;
        result = 31 * result + periodIdperiod;
        return result;
    }
}
