package com.revature.Roomy_Roomates.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.revature.Roomy_Roomates.DAOs.HotelDAO;
import com.revature.Roomy_Roomates.Models.Hotel;

@Service
public class HotelService {
    
    private final HotelDAO hotelDAO;

    @Autowired
    public HotelService(HotelDAO hotelDAO){
        this.hotelDAO=hotelDAO;
    }

    public Optional<Hotel> getHotelById(int hotelId){
        return hotelDAO.findById(hotelId);
    }

    public List<Hotel> getAllHotels(){
        return hotelDAO.findAll();
    }

    public Hotel createNewHotel(Hotel hotel){
        return hotelDAO.save(hotel);
    }

    public Hotel updateNewHotel(Hotel hotel){
        return hotelDAO.save(hotel);
    }

    public void deleteBook(int hotelId)  {

        hotelDAO.deleteById(hotelId);
    }
    
}
