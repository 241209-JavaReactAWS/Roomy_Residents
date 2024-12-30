package com.revature.Roomy_Roomates.Controllers;

import com.revature.Roomy_Roomates.Models.Booking;
import com.revature.Roomy_Roomates.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
