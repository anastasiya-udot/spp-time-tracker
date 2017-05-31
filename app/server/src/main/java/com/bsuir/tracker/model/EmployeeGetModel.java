package com.bsuir.tracker.model;

/**
 * Created by Pavel on 20.05.2017.
 */
public class EmployeeGetModel {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private int worktype;
    private String email;
    private String roleCode;
    private CompanyNameIdModel company;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCompany(CompanyNameIdModel company) {
        this.company = company;
    }

    public CompanyNameIdModel getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getWorktype() {
        return worktype;
    }

    public void setWorktype(int worktype) {
        this.worktype = worktype;
    }
}
