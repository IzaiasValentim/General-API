package com.izaiasvalentim.general.Domain.DTO.Client;

import com.izaiasvalentim.general.Domain.Client;

public class ClientDTO {
    private String name;
    private String email;
    private String identificationNumber;
    private String address;
    private String phoneNumber;
    private String phoneNumberReserve;
    private String status;
    private Boolean isDeleted;

    public ClientDTO() {
    }

    public ClientDTO(String name, String email, String identificationNumber, String address, String phoneNumber,
                     String phoneNumberReserve, String status, Boolean isDeleted) {
        this.name = name;
        this.email = email;
        this.identificationNumber = identificationNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.phoneNumberReserve = phoneNumberReserve;
        this.status = status;
        this.isDeleted = false;
    }

    public ClientDTO(Client client) {
        this.name = client.getName();
        this.email = client.getEmail();
        this.identificationNumber = client.getIdentificationNumber();
        this.address = client.getAddress();
        this.phoneNumber = client.getPhoneNumber();
        this.phoneNumberReserve = client.getPhoneNumberReserve();
        if (client.getActive()) {
            status = "Active";
        } else {
            status = "Inactive";
        }
        this.isDeleted = client.getDeleted();
    }

    public Client registerDTOToClient() {
        return new Client(name, email, identificationNumber, address, phoneNumber, phoneNumberReserve,
                "", isDeleted);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberReserve() {
        return phoneNumberReserve;
    }

    public void setPhoneNumberReserve(String phoneNumberReserve) {
        this.phoneNumberReserve = phoneNumberReserve;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
