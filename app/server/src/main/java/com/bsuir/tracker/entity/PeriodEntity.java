package com.bsuir.tracker.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
import java.util.HashSet;

/**
 * Created by Pavel on 25.04.2017.
 */
@Entity
@Table(name = "period", schema = "spp-time-tracker", catalog = "")
public class PeriodEntity {
    private int idperiod;
    private Timestamp start;
    private Timestamp finish;
    private int employeeIdemployee;
    private Set<TaskEntity> taskEntities = new HashSet<TaskEntity>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idperiod", nullable = false)
    public int getIdperiod() {
        return idperiod;
    }

    public void setIdperiod(int idperiod) {
        this.idperiod = idperiod;
    }

    @Basic
    @Column(name = "start", nullable = false)
    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    @Basic
    @Column(name = "finish", nullable = true)
    public Timestamp getFinish() {
        return finish;
    }

    public void setFinish(Timestamp finish) {
        this.finish = finish;
    }

    @Basic
    @Column(name = "employee_idemployee", nullable = false)
    public int getEmployeeIdemployee() {
        return employeeIdemployee;
    }

    public void setEmployeeIdemployee(int employeeIdemployee) {
        this.employeeIdemployee = employeeIdemployee;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "periodEntities")
    public Set<TaskEntity> getTaskEntities() {
        return taskEntities;
    }

    public void setTaskEntities(Set<TaskEntity> taskEntities) {
        this.taskEntities = taskEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeriodEntity that = (PeriodEntity) o;

        if (idperiod != that.idperiod) return false;
        if (employeeIdemployee != that.employeeIdemployee) return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (finish != null ? !finish.equals(that.finish) : that.finish != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idperiod;
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (finish != null ? finish.hashCode() : 0);
        result = 31 * result + employeeIdemployee;
        return result;
    }
}
