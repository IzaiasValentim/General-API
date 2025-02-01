package com.izaiasvalentim.general.Domain;

import com.izaiasvalentim.general.Domain.Enums.TypeRoles;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ApiUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String CPF;
    @Column(unique = true)
    private String email;
    private String password;
    private String phone;
    private String address;
    @Column(name = "id_role")
    private int role;
    @Temporal(TemporalType.DATE)
    private LocalDate admissionDate;
    @Temporal(TemporalType.DATE)
    private LocalDate shutdowsDate;
    private Boolean isAdmin;
    private Boolean isActive;
    private Boolean isDeleted;


    public ApiUser(String email, String username, String CPF, String phone, String address,
                   LocalDate admissionDate, LocalDate shutdowsDate, Boolean isActive, Boolean isDeleted) {
        this.email = email;
        this.username = username;
        this.CPF = CPF;
        this.phone = phone;
        this.address = address;
        this.admissionDate = admissionDate;
        this.shutdowsDate = shutdowsDate;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
    }

    @Transient
    public int getRole() {
        return TypeRoles.getLevelById(this.role);
    }

    public void setRole(TypeRoles role) {
        if (role == null) {
            this.role = 0;
        } else {
            this.role = role.getId();
        }
    }

    public ApiUser() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalDate getShutdowsDate() {
        return shutdowsDate;
    }

    public void setShutdowsDate(LocalDate shutdowsDate) {
        this.shutdowsDate = shutdowsDate;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setIsAdmin() {
        this.isAdmin = this.role == 1;
    }

    public Boolean getIsAdmin() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
