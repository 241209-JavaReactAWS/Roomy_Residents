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
    private Integer room_id;

    @Column(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Column(name = "room_type", nullable = false)
    private String room_type;

    @Column(name = "status", nullable = false)
    private com.revature.Roomy_Roomates.entities.Status status;

    @Column(name = "image", nullable = false)
    private String image;

    public Room() {
    }

    public Room(int room_id, Hotel hotel, String room_type, com.revature.Roomy_Roomates.entities.Status status, String image) {
        this.room_id = room_id;
        this.hotel = hotel;
        this.room_type = room_type;
        this.status = status;
        this.image = image;
    }
}