package com.revature.Roomy_Roomates.DAOs;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.Roomy_Roomates.Models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDAO extends JpaRepository<Room, Integer> {
    // Custom query with JPQL (Java Persistence Query Language)
    @Query("SELECT r FROM Room r WHERE r.hotel.hotelId = :hotelId" +
            " AND (:availability IS NULL OR r.status = :status)" +
            " AND (:roomType IS NULL OR r.roomType = :roomType)" +
            " AND (:capacity IS NULL OR r.capacity = :capacity)")
    List<Room> findRoomsByHotelIdAndFilters(
            @Param("hotelId") int hotelId,
            @Param("availability") boolean status,
            @Param("roomType") String roomType,
            @Param("capacity") int capacity
    );


}