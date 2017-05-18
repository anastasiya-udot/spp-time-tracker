package com.bsuir.tracker.entity;

import javax.persistence.*;

/**
 * Created by Pavel on 25.04.2017.
 */
@Entity
@Table(name = "project", schema = "spp-time-tracker", catalog = "")
public class ProjectEntity {
    private int idproject;
    private String name;
    private String description;
    private int companyIdcompany;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproject", nullable = false)
    public int getIdproject() {
        return idproject;
    }

    public void setIdproject(int idproject) {
        if (idproject < 0){
            throw new IllegalArgumentException();
        }
        this.idproject = idproject;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "company_idcompany", nullable = false)
    public int getCompanyIdcompany() {
        return companyIdcompany;
    }

    public void setCompanyIdcompany(int companyIdcompany) {
        if (companyIdcompany < 0){
            throw new IllegalArgumentException();
        }
        this.companyIdcompany = companyIdcompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectEntity that = (ProjectEntity) o;

        if (idproject != that.idproject) return false;
        if (companyIdcompany != that.companyIdcompany) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idproject;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + companyIdcompany;
        return result;
    }
}
