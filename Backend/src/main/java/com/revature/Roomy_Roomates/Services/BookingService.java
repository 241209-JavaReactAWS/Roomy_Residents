package com.revature.Roomy_Roomates.Services;

import com.revature.Roomy_Roomates.DAOs.BookingDAO;
import com.revature.Roomy_Roomates.Models.Booking;
import com.revature.Roomy_Roomates.Models.Room;
import com.revature.Roomy_Roomates.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingDAO bookingDAO;

    /*********************** GET ALL BOOKINGS BY USER ID ********************/
    public List<Booking> findByUserId(Integer userId){

        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        List<Booking> bookings = bookingDAO.findByUserId(userId);
        if (bookings.isEmpty()) {
            throw new IllegalArgumentException("No bookings found for user ID: " + userId);
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
    public boolean isRoomAvailable (Integer roomId, LocalDate dateCheckIn, LocalDate dateCheckOut){
        Integer count = bookingDAO.checkAvailability(roomId, dateCheckIn, dateCheckOut);
        return count == 0;
    }
     /************************ CREATE BOOKING BY USER ID ******************/
    public Booking createBooking(   User user,
                                    Room room,
                                    LocalDate dateCheckIn,
                                    LocalDate dateCheckOut,
                                    BigDecimal totalCost,
                                    String bookingStatus){
        if (!isRoomAvailable(room.getRoomId(),dateCheckIn, dateCheckOut)) {
            throw new IllegalArgumentException("Room is not available");
        }
        if (user == null || room == null || dateCheckIn == null || dateCheckOut == null ||
                totalCost == null || bookingStatus == null) {
            throw new IllegalArgumentException("Booking details cannot be null");
        }
        Booking booking = new Booking(user, room, dateCheckIn, dateCheckOut, totalCost, bookingStatus);
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
           booking.setUser(bookingUpdate.getUser());
           booking.setRoom(bookingUpdate.getRoom());
           booking.setDateCheckIn(bookingUpdate.getDateCheckIn());
           booking.setDateCheckOut(bookingUpdate.getDateCheckOut());
           booking.setTotalCost(bookingUpdate.getTotalCost());
           booking.setBookingStatus(bookingUpdate.getBookingStatus());

        return Optional.of(bookingDAO.save(booking));

    }
}
