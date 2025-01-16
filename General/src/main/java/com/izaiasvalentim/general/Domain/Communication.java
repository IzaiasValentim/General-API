package com.izaiasvalentim.general.Domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Communication {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String message;
    private int scope;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    private Boolean isDeleted;

    public Communication(String message, int scope, Date creationDate, Date endDate, Boolean isDeleted) {
        this.message = message;
        this.scope = scope;
        this.creationDate = creationDate;
        this.endDate = endDate;
        this.isDeleted = isDeleted;
    }

    public Communication() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
