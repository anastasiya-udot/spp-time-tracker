package com.bsuir.tracker.entity;

import javax.persistence.*;

/**
 * Created by Pavel on 25.04.2017.
 */
@Entity
@Table(name = "role", schema = "spp-time-tracker", catalog = "")
public class RoleEntity {
    private int idrole;
    private String name;
    private String code;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrole", nullable = false)
    public int getIdrole() {
        return idrole;
    }

    public void setIdrole(int idrole) {
        if (idrole < 0){
            throw new IllegalArgumentException();
        }
        this.idrole = idrole;
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
    @Column(name = "code", nullable = false, length = 45)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (idrole != that.idrole) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idrole;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
