package com.bsuir.tracker.model;

/**
 * Created by Pavel on 21.05.2017.
 */
public class PeriodGetModel {
    private int id;
    private long startPeriod;
    private long endPeriod;

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

    public long getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(long endPeriod) {
        this.endPeriod = endPeriod;
    }
}
