package com.revature.Roomy_Roomates.DAOs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.Roomy_Roomates.Models.Hotel;

@Repository
public interface HotelDAO extends JpaRepository<Hotel, Integer> {

    List<Hotel> findAllByNameIgnoreCase(String hotelName);

    List<Hotel> findAllByRating(int rating);

    List<Hotel> findAllByHotelCityAndHotelState(String hotelCity, String hotelState);

    List<Hotel> findAllByHotelZipcode(int hotelZipcode);

    List<Hotel> findAllByHotelState(String hotelState);
    
}
