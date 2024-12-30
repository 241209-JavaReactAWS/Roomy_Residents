package com.revature.Roomy_Roomates.entities;


@Getter
@Setter
@Entity
@Table(name = 'rooms')
public class Room {

    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer room_id;

    @Column(name = "hotel_id", nullable = false)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "room_type", nullable = false)
    private String room_type;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "image", nullable = false)
    private String image;

    public Room() {}

    public Room(int room_id, int hotel_id, String room_type, Status status, String image) {
        this.room_id = room_id;
        this.hotel_id = hotel_id;
        this.room_type = room_type;
        this.status = status;
        this.image = image;
    }
