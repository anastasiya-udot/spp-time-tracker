package com.bsuir.tracker.model;

/**
 * Created by Pavel on 21.05.2017.
 */
public class TasksGetModel {
    private int id;
    private long startPeriod;
    private long finishPeriod;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(long startPeriod) {
        this.startPeriod = startPeriod;
    }

    public long getFinishPeriod() {
        return finishPeriod;
    }

    public void setFinishPeriod(long finishPeriod) {
        this.finishPeriod = finishPeriod;
    }
}
