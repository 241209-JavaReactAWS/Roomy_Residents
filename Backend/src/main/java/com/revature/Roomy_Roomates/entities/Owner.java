package com.revature.Roomy_Roomates.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "owners")
public class Owner {

    @Id
    @Column(name = "ownerId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ownerId;
    private String buisnessName;
    private String buisnessEmail;
    private Integer buisnessPhoneNumber;

    public Owner() {
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


