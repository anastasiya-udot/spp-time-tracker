package com.bsuir.tracker.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Pavel on 18.05.2017.
 */
@Entity
@Table(name = "request", schema = "spp-time-tracker", catalog = "")
public class RequestEntity {
    private int idrequest;
    private int sourceIdemployee;
    private int destinationIdemployee;
    private Timestamp date;
    private String content;
    private Timestamp startPeriod;
    private Timestamp endPeriod;

    @Id
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

    @Basic
    @Column(name = "content", nullable = false, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "start_period", nullable = false)
    public Timestamp getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Timestamp startPeriod) {
        this.startPeriod = startPeriod;
    }

    @Basic
    @Column(name = "end_period", nullable = false)
    public Timestamp getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Timestamp endPeriod) {
        this.endPeriod = endPeriod;
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
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (startPeriod != null ? !startPeriod.equals(that.startPeriod) : that.startPeriod != null) return false;
        if (endPeriod != null ? !endPeriod.equals(that.endPeriod) : that.endPeriod != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idrequest;
        result = 31 * result + sourceIdemployee;
        result = 31 * result + destinationIdemployee;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (startPeriod != null ? startPeriod.hashCode() : 0);
        result = 31 * result + (endPeriod != null ? endPeriod.hashCode() : 0);
        return result;
    }
}
