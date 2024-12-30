package com.revature.Roomy_Roomates.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Roomy_Roomates.Models.Hotel;
import com.revature.Roomy_Roomates.Services.HotelService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("hotel")
public class HotelController{

    private final HotelService hotelService;
    
    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("{hotelId}") 
    public ResponseEntity<Hotel> getHotelById(@PathVariable int hotelId){
        Optional<Hotel> hotel = hotelService.getHotelById(hotelId);

        if (hotel.isPresent()){
            return new ResponseEntity<>(hotel.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping
    public List<Hotel> getAllHotels(){
        return hotelService.getAllHotels();
    }

    @PostMapping
    public ResponseEntity<Hotel> createNewHotel(HttpSession session, @RequestBody Hotel hotel){
        // Check if user is logged in
        if (session.isNew() || session.getAttribute("username") == null){
            return ResponseEntity.status(401).build(); // Unauthorized
        }

        Hotel newHotel = hotelService.createNewHotel(hotel);

        if(newHotel == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(201).body(newHotel);
    }

    @PutMapping("{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable int hotelId, HttpSession session, @RequestBody Hotel hotel){
        if (session.isNew() || session.getAttribute("username") == null){
            return ResponseEntity.status(401).build(); // Unauthorized
        }

        if (hotel.getHotelId() != hotelId) {
            return ResponseEntity.badRequest().build(); // Bad Request
        }
    
        Optional<Hotel> existingHotel = hotelService.getHotelById(hotelId);
        if (existingHotel.isEmpty()) {
            return ResponseEntity.notFound().build(); // Hotel Not Found
        }
        
        Hotel updatedHotel = hotelService.updateHotel(hotel);
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping("{hotelId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable int hotelId, HttpSession session, @RequestBody Hotel hotel){
        
        if (session.isNew() || session.getAttribute("username") == null) {
            return ResponseEntity.status(401).build();
        }

        Optional<Hotel> existingHotel = hotelService.getHotelById(hotelId);
        if (existingHotel.isEmpty()) {
            return ResponseEntity.notFound().build(); // Hotel Not Found
        }

        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok().build();
    }

}