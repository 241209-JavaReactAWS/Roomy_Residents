package com.revature.Roomy_Roomates.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.Roomy_Roomates.Models.Hotel;

@Repository
public interface HotelDAO extends JpaRepository<Hotel, Integer> {
    
}
