package com.revature.Roomy_Roomates.Models;

import jakarta.persistence.*;

@Entity
@Table(name="hotels")
public class Hotel{

    @Id
    @Column(name = "hotel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hotelId;
    
    @Column(name="hotel_name")
    private String hotelName;
    
    @Column(name="hotel_address")
    private String hotelStreet;

    @Column(name="hotel_city")
    private String hotelCity;

    @Column(name="hotel_state")
    private String hotelState;
    
    @Column(name="hotel_zipcode")
    private Integer hotelZipcode;
    
    @Column(name="hotel_phone_number")
    private String hotelPhoneNumber;

    @Column(name="hotel_email")
    private String hotelEmail;

    @Column(name = "hotel_image")
    private String hotelImage;

    @Column(name= "rating")
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id")
    private Owner owner;

    public Hotel(){}

    public Hotel(int hotelId, String hotelName, String hotelAddress, int hotelZipcode, String hotelPhoneNumber,
            String hotelEmail, String hotelImage, int rating, Owner owner) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelStreet = hotelStreet;
        this.hotelZipcode = hotelZipcode;
        this.hotelPhoneNumber = hotelPhoneNumber;
        this.hotelEmail = hotelEmail;
        this.hotelImage = hotelImage;
        this.rating=rating;
        this.owner = owner;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelStreet() {
        return hotelStreet;
    }

    public void setHotelStreet(String hotelStreet) {
        this.hotelStreet = hotelStreet;
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
        return this.owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public String getHotelState() {
        return hotelState;
    }

    public void setHotelState(String hotelState) {
        this.hotelState = hotelState;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    

}