package com.bsuir.tracker.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Pavel on 25.04.2017.
 */
@Entity
@Table(name = "request", schema = "spp-time-tracker", catalog = "")
public class RequestEntity {
    private int idrequest;
    private int sourceIdemployee;
    private int destinationIdemployee;
    private Timestamp date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrequest", nullable = false)
    public int getIdrequest() {
        return idrequest;
    }

    public void setIdrequest(int idrequest) {
        if (idrequest < 0){
            throw new IllegalArgumentException();
        }
        this.idrequest = idrequest;
    }

    @Basic
    @Column(name = "source_idemployee", nullable = false)
    public int getSourceIdemployee() {
        return sourceIdemployee;
    }

    public void setSourceIdemployee(int sourceIdemployee) {
        if (sourceIdemployee < 0){
            throw new IllegalArgumentException();
        }
        this.sourceIdemployee = sourceIdemployee;
    }

    @Basic
    @Column(name = "destination_idemployee", nullable = false)
    public int getDestinationIdemployee() {
        return destinationIdemployee;
    }

    public void setDestinationIdemployee(int destinationIdemployee) {
        if (destinationIdemployee < 0){
            throw new IllegalArgumentException();
        }
        this.destinationIdemployee = destinationIdemployee;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestEntity that = (RequestEntity) o;

        if (idrequest != that.idrequest) return false;
        if (sourceIdemployee != that.sourceIdemployee) return false;
        if (destinationIdemployee != that.destinationIdemployee) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idrequest;
        result = 31 * result + sourceIdemployee;
        result = 31 * result + destinationIdemployee;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
