package com.bsuir.tracker.Documentation.Factories.Entities;

import java.sql.Timestamp;

/**
 * Created by Pavel on 25.05.2017.
 */
public class EmployeeInfo {
    private String employeeName;
    //private String company;
    private String worked;
    private String mustWorked;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /*public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }*/

    public String getWorked() {
        return worked;
    }

    public void setWorked(String worked) {
        this.worked = worked;
    }

    public String getMustWorked() {
        return mustWorked;
    }

    public void setMustWorked(String mustWorked) {
        this.mustWorked = mustWorked;
    }
}
