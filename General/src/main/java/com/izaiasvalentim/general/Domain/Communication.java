package com.izaiasvalentim.general.Domain;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Communication {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String message;
    private int scope;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate endDate;
    private Boolean isDeleted;

    public Communication(String message, int scope, LocalDate creationDate, LocalDate endDate, Boolean isDeleted) {
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
