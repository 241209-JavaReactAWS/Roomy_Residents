package com.revature.Roomy_Roomates.DAOs;

import com.revature.Roomy_Roomates.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDAO extends JpaRepository <Booking, Integer>{
    List<Booking> findByUserId(Integer userId);
}
