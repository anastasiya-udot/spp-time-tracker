package com.bsuir.tracker.entity;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by Pavel on 25.04.2017.
 */
@Entity
@Table(name = "workday_type", schema = "spp-time-tracker", catalog = "")
public class WorkdayTypeEntity {
    private int idworkdayType;
    private String typename;
    private int time;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idworkday_type", nullable = false)
    public int getIdworkdayType() {
        return idworkdayType;
    }

    public void setIdworkdayType(int idworkdayType) {
        this.idworkdayType = idworkdayType;
    }

    @Basic
    @Column(name = "typename", nullable = false, length = 45)
    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @Basic
    @Column(name = "time", nullable = true)
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkdayTypeEntity that = (WorkdayTypeEntity) o;

        if (idworkdayType != that.idworkdayType) return false;
        if (typename != null ? !typename.equals(that.typename) : that.typename != null) return false;
        if (time != that.time) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idworkdayType;
        result = 31 * result + (typename != null ? typename.hashCode() : 0);
        result = 31 * result + time;
        return result;
    }
}
