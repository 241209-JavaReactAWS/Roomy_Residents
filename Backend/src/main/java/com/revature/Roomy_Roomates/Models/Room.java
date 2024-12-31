package com.revature.Roomy_Roomates.Models;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomId;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Column(name = "room_type", nullable = false)
    private String roomType;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "image", nullable = false)
    private String image;

    public Room() {
    }

    public Room(int room_id, int capacity, Hotel hotel, String room_type, boolean status, String image) {
        this.roomId = room_id;
        this.capacity = capacity;
        this.hotel = hotel;
        this.roomType = room_type;
        this.status = status;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public int getCapacity() { return capacity; }

    public void setCapacity(int capacity) { this.capacity = capacity; }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
