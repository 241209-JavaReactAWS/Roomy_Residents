package com.revature.Roomy_Roomates.Controllers;

import com.revature.Roomy_Roomates.Models.Booking;
import com.revature.Roomy_Roomates.Models.Room;
import com.revature.Roomy_Roomates.Models.User;
import com.revature.Roomy_Roomates.Services.BookingService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        if (userId == null) {
            return ResponseEntity.badRequest().body(null);
        }
        List<Booking> bookings = bookingService.findByUserId(userId);
        if (bookings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookings);
    }

    /******* CREATE  BOOKINGS BY USER ID  *********/
    @PostMapping
    public ResponseEntity<Booking> createBooking(@PathVariable("user_Id") Integer userId,
                                                 @RequestParam("roomId") Integer roomId,
                                                 @RequestParam("dateCheckIn") LocalDate dateCheckIn,
                                                 @RequestParam("dateCheckOut") LocalDate dateCheckOut,
                                                 @RequestParam("totalCost") BigDecimal totalCost,
                                                 @RequestParam("bookingStatus") String bookingStatus) {
      try
      { User user =new User();
        user.setUserId(userId);

        Room room = new Room();
        room.setRoomId(roomId);

        Booking newBooking = bookingService.createBooking(
               user, room, dateCheckIn, dateCheckOut, totalCost, bookingStatus);

            return ResponseEntity.status(HttpStatus.CREATED).body(newBooking);
      }
      catch (IllegalArgumentException e)
      {
            return ResponseEntity.badRequest().build();
      }
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
