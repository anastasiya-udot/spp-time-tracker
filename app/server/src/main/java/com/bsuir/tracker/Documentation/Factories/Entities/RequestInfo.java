package com.bsuir.tracker.Documentation.Factories.Entities;

import java.sql.Timestamp;

/**
 * Created by Pavel on 24.05.2017.
 */
public class RequestInfo {
    private String sourceEmployeeName;
    private String destinationEmployeeName;
    private String destinationEmployeeCompany;
    private Timestamp date;
    private String content;
    private Timestamp startPeriod;
    private Timestamp endPeriod;

    public String getDestinationEmployeeName() {
        return destinationEmployeeName;
    }

    public void setDestinationEmployeeName(String destinationEmployeeName) {
        this.destinationEmployeeName = destinationEmployeeName;
    }

    public String getSourceEmployeeName() {
        return sourceEmployeeName;
    }

    public void setSourceEmployeeName(String sourceEmployeeName) {
        this.sourceEmployeeName = sourceEmployeeName;
    }

    public String getDestinationEmployeeCompany() {
        return destinationEmployeeCompany;
    }

    public void setDestinationEmployeeCompany(String destinationEmployeeCompany) {
        this.destinationEmployeeCompany = destinationEmployeeCompany;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Timestamp startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Timestamp getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Timestamp endPeriod) {
        this.endPeriod = endPeriod;
    }
}
