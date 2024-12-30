package com.revature.Roomy_Roomates.Models;

import jakarta.persistence.*;

@Entity
@Table(name="hotels")
public class Hotel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelID;
    
    @Column(name="hotel_name")
    private String hotelName;
    
    @Column(name="hotel_address")
    private String hotelAddress;
    
    @Column(name="hotel_zipcode")
    private int hotelZipcode;
    
    @Column(name="hotel_phone_number")
    private String hotelPhoneNumber;

    @Column(name="hotel_email")
    private String hotelEmail;

    @Column(name = "hotel_image")
    private String hotelImage;

    @OneToMany
    @JoinColumn(name="owner_id")
    private Owner owner;

    public Hotel(){}

    public Hotel(int hotelID, String hotelName, String hotelAddress, int hotelZipcode, String hotelPhoneNumber,
            String hotelEmail, String hotelImage, Owner owner) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.hotelZipcode = hotelZipcode;
        this.hotelPhoneNumber = hotelPhoneNumber;
        this.hotelEmail = hotelEmail;
        this.hotelImage = hotelImage;
        this.owner = owner;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public int getHotelZipcode() {
        return hotelZipcode;
    }

    public void setHotelZipcode(int hotelZipcode) {
        this.hotelZipcode = hotelZipcode;
    }

    public String getHotelPhoneNumber() {
        return hotelPhoneNumber;
    }

    public void setHotelPhoneNumber(String hotelPhoneNumber) {
        this.hotelPhoneNumber = hotelPhoneNumber;
    }

    public String getHotelEmail() {
        return hotelEmail;
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public String getHotelImage() {
        return hotelImage;
    }

    public void setHotelImage(String hotelImage) {
        this.hotelImage = hotelImage;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    

}