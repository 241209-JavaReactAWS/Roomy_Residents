package com.revature.Roomy_Roomates.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "owners")
public class Owner {

    @Id
    @Column(name = "owner_id")
    private Integer ownerId;

    @Column(name = "buisness_name")
    private String buisnessName;

    @Column(name = "buisness_email")
    private String buisnessEmail;

    @Column(name = "buisness_phone_number")
    private Integer buisnessPhoneNumber;

    public Owner() {
    }

    public Owner(Integer ownerId, String buisnessName, String buisnessEmail, Integer buisnessPhoneNumber) {
        this.ownerId = ownerId;
        this.buisnessName = buisnessName;
        this.buisnessEmail = buisnessEmail;
        this.buisnessPhoneNumber = buisnessPhoneNumber;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getBuisnessName() {
        return buisnessName;
    }

    public void setBuisnessName(String buisnessName) {
        this.buisnessName = buisnessName;
    }

    public String getBuisnessEmail() {
        return buisnessEmail;
    }

    public void setBuisnessEmail(String buisnessEmail) {
        this.buisnessEmail = buisnessEmail;
    }

    public Integer getBuisnessPhoneNumber() {
        return buisnessPhoneNumber;
    }

    public void setBuisnessPhoneNumber(Integer buisnessPhoneNumber) {
        this.buisnessPhoneNumber = buisnessPhoneNumber;
    }
}


