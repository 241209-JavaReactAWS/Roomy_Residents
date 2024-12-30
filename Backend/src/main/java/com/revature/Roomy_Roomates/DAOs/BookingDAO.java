package com.revature.Roomy_Roomates.DAOs;

import com.revature.Roomy_Roomates.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingDAO extends JpaRepository <Booking, Integer>{

    @Query("SELECT b FROM Booking b WHERE b.user.userId = :userId")
    List<Booking> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.room.roomId = :roomId " +
            "AND b.dateCheckOut > :dateCheckIn " +
            "AND b.dateCheckIn < :dateCheckOut")
    Integer checkAvailability(
            @Param("roomId")Integer roomId,
            @Param("dateCheckIn") LocalDate dateCheckIn,
            @Param("dateCheckOut") LocalDate dateCheckOut);
}
