package com.bsuir.tracker.model;

/**
 * Created by Pavel on 22.05.2017.
 */
public class EmployeesGetModel {
    private int companyId;
    private long startPeriod;
    private long finishPeriod;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
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
