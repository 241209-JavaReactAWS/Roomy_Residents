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

    public Hotel updateHotel(Hotel updatedHotel, int hotelId){
        return hotelDAO.findById(hotelId)
            .map(existingHotel -> {
                existingHotel.setHotelName(updatedHotel.getHotelName());
                existingHotel.setHotelStreet(updatedHotel.getHotelStreet());
                existingHotel.setHotelCity(updatedHotel.getHotelCity());
                existingHotel.setHotelState(updatedHotel.getHotelState());
                existingHotel.setHotelZipcode(updatedHotel.getHotelZipcode());
                existingHotel.setHotelEmail(updatedHotel.getHotelEmail());
                existingHotel.setHotelPhoneNumber(updatedHotel.getHotelPhoneNumber());
                existingHotel.setHotelImage(updatedHotel.getHotelImage());
                return hotelDAO.save(existingHotel);
            })
            .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
    }

    public void deleteHotel(Hotel hotel, int hotelId)  {

        if(hotelDAO.existsById(hotel.getHotelId())){
            hotelDAO.deleteById(hotelId);
        }
        else {
            throw new IllegalArgumentException("Hotel not found");
        }        
    }

    public List<Hotel> searchByHotelName(String hotelName){
        return hotelDAO.findAllByHotelNameIgnoreCase(hotelName);
    }

    public List<Hotel> searchByHotelRating(int rating){
        return hotelDAO.findAllByRating(rating);
    }

    public List<Hotel> searchByCityAndState(String hotelCity, String hotelState) {
        return hotelDAO.findAllByHotelCityAndHotelState(hotelCity, hotelState);
    }

    public List<Hotel> searchByState(String hotelState) {
        return hotelDAO.findAllByHotelState(hotelState);
    }

    public List<Hotel> searchByZipcode(int hotelZipcode) {
        return hotelDAO.findAllByHotelZipcode(hotelZipcode);
    }
    
}
