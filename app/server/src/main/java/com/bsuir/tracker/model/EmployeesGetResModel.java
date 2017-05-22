package com.bsuir.tracker.model;

/**
 * Created by Pavel on 22.05.2017.
 */
public class EmployeesGetResModel {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private int worktype;
    private long inFact;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getWorktype() {
        return worktype;
    }

    public void setWorktype(int worktype) {
        this.worktype = worktype;
    }

    public long getInFact() {
        return inFact;
    }

    public void setInFact(long inFact) {
        this.inFact = inFact;
    }
}