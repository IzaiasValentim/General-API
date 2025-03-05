package com.izaiasvalentim.general.Domain.DTO.Client;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.izaiasvalentim.general.Domain.Client;
import com.izaiasvalentim.general.Domain.Purchase;
import jakarta.persistence.*;

import java.util.List;

public class ClientRegisterDTO {
    private String name;
    private String email;
    private String identificationNumber;
    private String address;
    private String phoneNumber;
    private String phoneNumberReserve;
    private String payment;
    private String status;

    public ClientRegisterDTO() {
    }

    public ClientRegisterDTO(String name, String email, String identificationNumber, String address, String phoneNumber, String phoneNumberReserve, String payment, String status) {
        this.name = name;
        this.email = email;
        this.identificationNumber = identificationNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.phoneNumberReserve = phoneNumberReserve;
        this.payment = payment;
        this.status = status;
    }

    public ClientRegisterDTO(Client client) {
        this.name = client.getName();
        this.email = client.getEmail();
        this.identificationNumber = client.getIdentificationNumber();
        this.address = client.getAddress();
        this.phoneNumber = client.getPhoneNumber();
        this.phoneNumberReserve = client.getPhoneNumberReserve();
        this.payment = client.getPayment();
        if(client.getActive()){
            status = "Active";
        }else{
            status = "Inactive";
        }
    }

    public Client registerDTOToClient() {
        return new Client(name, email, identificationNumber, address, phoneNumber, phoneNumberReserve, payment);
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
