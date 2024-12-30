package com.revature.Roomy_Roomates.Services;

import com.revature.Roomy_Roomates.DAOs.BookingDAO;
import com.revature.Roomy_Roomates.Models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingDAO bookingDAO;

    public List<Booking> findByUserId(Integer userId){
        List<Booking> bookings = bookingDAO.findByUserId(userId);
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return bookingDAO.findByUserId(userId);
    }
}
