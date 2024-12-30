package com.revature.Roomy_Roomates.DAOs;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.Roomy_Roomates.Models.*;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDAO extends JpaRepository<Room, Integer> {
    List<Room> findByStatus(Status status);
    List<Room> findByHotelAndStatus(Hotel hotel, Status status);
    List<Room> findByHotel(Hotel hotel);
    List<Room> findByRoomType(String roomType);
    List<Room> findByRoomTypeAndStatus(String roomType, Status status);
    List<Room> findByRoomTypeAndHotel(String roomType, Hotel hotel);
    List<Room> findByRoomTypeAndStatusAndHotel(String roomType, Status status, Hotel hotel);
}