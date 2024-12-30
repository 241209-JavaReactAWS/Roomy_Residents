package com.revature.Roomy_Roomates.Services;

import com.revature.Roomy_Roomates.DAOs.BookingDAO;
import com.revature.Roomy_Roomates.Models.Booking;
import com.revature.Roomy_Roomates.Models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingDAO bookingDAO;

    /*********************** GET ALL BOOKINGS BY USER ID ********************/
    public List<Booking> findByUserId(Integer userId){
        List<Booking> bookings = bookingDAO.findByUserId(userId);
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return bookingDAO.findByUserId(userId);
    }

    /******************* DELETE BOOKING BY BOOKING ID ******************/
    public Booking deleteBooking(Integer bookingId){
        Booking booking = bookingDAO.findById(bookingId).orElse(null);
        if (booking == null) {
            throw new IllegalArgumentException("Booking ID cannot be null");
        }
        bookingDAO.deleteById(bookingId);
        return booking;
    }
    /**************** CHECK ROOM IS AVAILABLE  ****************/
    public boolean isRoomAvailable (Integer roomId, LocalDateTime dateCheckIn, LocalDateTime dateCheckOut){
        Integer count = bookingDAO.checkAvailability(roomId, dateCheckIn, dateCheckOut);
        return count == 0;
    }
     /************************ CREATE BOOKING BY USER ID ******************/
    public Booking createBooking(   Integer user_id,
                                    Room room,
                                    String dateCheckIn,
                                    String datCheckOut,
                                    BigDecimal totalCost,
                                    String bookingStatus){
        if (!isRoomAvailable(room.getRoom_Id(), LocalDateTime.parse(dateCheckIn), LocalDateTime.parse(datCheckOut))) {
            throw new IllegalArgumentException("Room is not available");
        }

        Booking booking = new Booking(userId, room, dateCheckIn, datCheckOut, totalCost, bookingStatus);
        booking.setAtTime(LocalDateTime.now());
        return bookingDAO.save(booking);
    }

    /**************** UPDATE BOOKING BY BOOKING ID ****************/
    public Optional<Booking> updateBooking (Integer bookingId, Booking bookingUpdate){
        Optional<Booking> existingBooking = bookingDAO.findById(bookingId);
        if (existingBooking.isEmpty()) {
            throw new IllegalArgumentException("Booking ID not found");
        }
        Booking booking = existingBooking.get();
           booking.setUserId(bookingUpdate.getUserId());
           booking.setRoom(bookingUpdate.getRoom());
           booking.setDateCheckIn(bookingUpdate.getDateCheckIn());
           booking.setDateCheckOut(bookingUpdate.getDateCheckOut());
           booking.setTotalCost(bookingUpdate.getTotalCost());
           booking.setBookingStatus(bookingUpdate.getBookingStatus());

        return Optional.of(bookingDAO.save(booking));

    }
}
