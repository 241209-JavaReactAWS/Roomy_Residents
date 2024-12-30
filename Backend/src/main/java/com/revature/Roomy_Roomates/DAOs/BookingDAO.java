package com.revature.Roomy_Roomates.DAOs;

import com.revature.Roomy_Roomates.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingDAO extends JpaRepository <Booking, Integer>{
    List<Booking> findByUserId(Integer userId);
    @Query("SELECT COUNT(b) FROM Bookings b WHERE Room_id = :roomId " +
            "AND dateCheckOut >= :dateCheckIn " +
            "AND dateCheckIn < :dateCheckOut")
    Integer checkAvailability(
            @Param("roomId")Integer roomId,
            @Param("dateCheckIn") LocalDateTime dateCheckIn,
            @Param("dateCheckOut") LocalDateTime dateCheckOut);
}
