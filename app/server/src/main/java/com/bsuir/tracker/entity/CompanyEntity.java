package com.bsuir.tracker.entity;

import javax.persistence.*;

/**
 * Created by Pavel on 25.04.2017.
 */
@Entity
@Table(name = "company", schema = "spp-time-tracker", catalog = "")
public class CompanyEntity {
    private int idcompany;
    private String name;
    private Integer logoIdimage;
    private String description;
    private String legalNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcompany", nullable = false)
    public int getIdcompany() {
        return idcompany;
    }

    public void setIdcompany(int idcompany) {
        if (idcompany < 0) {
            throw new IllegalArgumentException();
        }
        this.idcompany = idcompany;
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
    @Column(name = "logo_idimage", nullable = true)
    public Integer getLogoIdimage() {
        return logoIdimage;
    }

    public void setLogoIdimage(Integer logoIdimage) {
        if (null != logoIdimage)
        {
            if (logoIdimage < 0) {
                throw new IllegalArgumentException();
            }
        }
        this.logoIdimage = logoIdimage;
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
    @Column(name = "legal_number", nullable = false, length = 45)
    public String getLegalNumber() {
        return legalNumber;
    }

    public void setLegalNumber(String legalNumber) {
        this.legalNumber = legalNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyEntity that = (CompanyEntity) o;

        if (idcompany != that.idcompany) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (logoIdimage != null ? !logoIdimage.equals(that.logoIdimage) : that.logoIdimage != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (legalNumber != null ? !legalNumber.equals(that.legalNumber) : that.legalNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcompany;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (logoIdimage != null ? logoIdimage.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (legalNumber != null ? legalNumber.hashCode() : 0);
        return result;
    }
}
