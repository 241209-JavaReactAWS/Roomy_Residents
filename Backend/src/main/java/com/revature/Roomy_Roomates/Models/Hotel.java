package com.revature.Roomy_Roomates.Models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="hotels")
public class Hotel{

    @Id
    @Column(name = "hotel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hotelID;
    
    @Column(name="hotel_name")
    private String hotelName;
    
    @Column(name="hotel_address")
    private String hotelAddress;
    
    @Column(name="hotel_zipcode")
    private Integer hotelZipcode;
    
    @Column(name="hotel_phone_number")
    private String hotelPhoneNumber;

    @Column(name="hotel_email")
    private String hotelEmail;

    @Column(name="hotel_rating")
    private int hotelRating;

    @Column(name = "hotel_image")
    private String hotelImage;

    @ManyToOne(fetch = FetchType.LAZY)
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
       this.hotelRating=hotelRating;
       this.hotelImage = hotelImage;
       this.owner = null;
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

    public int getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(int hotelRating) {
        this.hotelRating = hotelRating;
    }

    public String getHotelImage() {
        return hotelImage;
    }

    public void setHotelImage(String hotelImage) {
        this.hotelImage = hotelImage;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    

}