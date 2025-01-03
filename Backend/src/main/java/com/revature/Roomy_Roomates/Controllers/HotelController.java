package com.revature.Roomy_Roomates.Controllers;

import java.util.List;
import java.util.Optional;

import com.revature.Roomy_Roomates.Models.Room;
import com.revature.Roomy_Roomates.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revature.Roomy_Roomates.Models.Hotel;
import com.revature.Roomy_Roomates.Services.HotelService;
import com.revature.Roomy_Roomates.Services.RoomService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("hotels")
public class HotelController{

    private final HotelService hotelService;
    private final RoomService roomService;

    @Autowired
    public HotelController(HotelService hotelService, RoomService roomService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
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

    @GetMapping("{hotelId}/rooms")
    public ResponseEntity<List<Room>> getRoomsForHotel(@PathVariable int hotelId,
                                                       @RequestParam(required = false) boolean status,
                                                       @RequestParam(required = false) String roomType,
                                                       @RequestParam(required = false) int capacity) {

        // Fetch the hotel to make sure it exists
        Optional<Hotel> hotel = hotelService.getHotelById(hotelId);
        if (hotel.isEmpty()) {
            return ResponseEntity.notFound().build(); // Hotel not found
        }

        // Call the roomService with optional filters for room status, type, capacity
        List<Room> rooms = roomService.getRoomsForHotel(hotelId, status, roomType, capacity);

        return ResponseEntity.ok(rooms); // Return the list of rooms
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
        
        Hotel updatedHotel = hotelService.updateHotel(hotel,hotelId);
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

        hotelService.deleteHotel(hotel, hotelId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/filter")
    public List<Hotel> getHotelByFiltering( @RequestParam(name = "hotelName", required = false) String hotelName,
                                            @RequestParam(name = "rating", required = false) Integer rating,
                                            @RequestParam(name = "hotelCity", required = false) String hotelCity,
                                            @RequestParam(name = "hotelState", required = false) String hotelState,
                                            @RequestParam(name = "hotelZipcode", required = false) Integer hotelZipcode){
        if (hotelName != null) {
            return hotelService.searchByHotelName(hotelName);
        } else if (rating != null) {
            return hotelService.searchByHotelRating(rating);
        } else if (hotelCity != null && hotelState != null) {
            return hotelService.searchByCityAndState(hotelCity, hotelState);
        } else if (hotelState != null) {
            return hotelService.searchByState(hotelState);
        } else if (hotelZipcode != null) {
            return hotelService.searchByZipcode(hotelZipcode);
        } else {
            return hotelService.getAllHotels(); 
        }
    }


}