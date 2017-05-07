package com.bsuir.tracker.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

/**
 * Created by Pavel on 25.04.2017.
 */
@Entity
@Table(name = "task", schema = "spp-time-tracker", catalog = "")
public class TaskEntity {
    private int idtask;
    private String code;
    private String description;
    private int projectIdproject;
    private Set<PeriodEntity> periodEntities = new HashSet<PeriodEntity>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtask", nullable = false)
    public int getIdtask() {
        return idtask;
    }

    public void setIdtask(int idtask) {
        if (idtask < 0){
            throw new IllegalArgumentException();
        }
        this.idtask = idtask;
    }

    @Basic
    @Column(name = "code", nullable = false, length = 45)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    @Column(name = "project_idproject", nullable = false)
    public int getProjectIdproject() {
        return projectIdproject;
    }

    public void setProjectIdproject(int projectIdproject) {
        if (projectIdproject < 0){
            throw new IllegalArgumentException();
        }
        this.projectIdproject = projectIdproject;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "task_period", joinColumns = {
            @JoinColumn(name = "task_idtask", nullable = false, updatable = false) },
            inverseJoinColumns = {@JoinColumn(name = "period_idperiod", nullable = false, updatable = false) })
    public Set<PeriodEntity> getPeriodEntities() {
        return periodEntities;
    }

    public void setPeriodEntities(Set<PeriodEntity> periodEntities) {
        this.periodEntities = periodEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskEntity that = (TaskEntity) o;

        if (idtask != that.idtask) return false;
        if (projectIdproject != that.projectIdproject) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idtask;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + projectIdproject;
        return result;
    }
}
