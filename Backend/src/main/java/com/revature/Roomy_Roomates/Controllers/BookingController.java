package com.revature.Roomy_Roomates.Controllers;

import com.revature.Roomy_Roomates.Models.Booking;
import com.revature.Roomy_Roomates.Services.BookingService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")

public class BookingController {
    @Autowired
    private BookingService bookingService;;

    /******* GET ALL BOOKINGS BY USER ID  *********/
    @GetMapping("/{userId}/bookings")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable Integer userId) {
        List<Booking> bookings = bookingService.findByUserId(userId);
        if (bookings == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookings);
    }
    /******* CREATE  BOOKINGS BY USER ID  *********/
    @PostMapping
    public ResponseEntity<Booking> createBooking(@PathVariable("user_Id") Integer userId,
                                                 @RequestBody Booking booking) {
        if(userId == null){
            return ResponseEntity.badRequest().build();
        }
        booking.setUserId(userId);
        Booking newBooking = bookingService.createBooking(
                booking.getUserId(),
                booking.getRoom(),
                booking.getDateCheckIn(),
                booking.getDateCheckOut(),
                booking.getTotalCost(),
                booking.getBookingStatus());

        return ResponseEntity.status(HttpStatus.CREATED).body(newBooking);
    }
    /******* DELETE BOOKINGS BY BOOKING ID *********/
    @DeleteMapping ("/{bookingId}")
    public ResponseEntity<Booking> deleteBooking(@PathVariable Integer bookingId)
    {
        Booking booking = bookingService.deleteBooking(bookingId);
        if (booking == null)
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(booking);
    }
    /******* UPDATE BOOKINGS BY BOOKING ID *********/
    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(
            @PathVariable Integer bookingId,
            @RequestBody Booking booking)
        {
             Optional<Booking> updatedBooking = bookingService.updateBooking(bookingId, booking);
             if (updatedBooking.isEmpty()) {
                 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedBooking.get());
    }
}
