package com.revature.Roomy_Roomates.Services;

import com.revature.Roomy_Roomates.DAOs.RoomDAO;
import com.revature.Roomy_Roomates.Models.Room;
import com.revature.Roomy_Roomates.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomDAO roomDAO;

    @Autowired
    public RoomService(RoomDAO roomDAO) {this.roomDAO = roomDAO;}

    public Optional<Room> getRoomById(int roomId){
        return roomDAO.findById(roomId);
    }

    public List<Room> getAllRooms(){
        return roomDAO.findAll();}

    public Room createNewRoom(Room room){
        return roomDAO.save(room);
    }

    public Room updateRoom(int roomId, Room newRoom) {
    return roomDAO.findById(roomId)
            .map(oldRoom -> {
        oldRoom.setHotel(newRoom.getHotel());
        oldRoom.setCapacity(newRoom.getCapacity());
        oldRoom.setDescription(newRoom.getDescription());
        oldRoom.setRoomType(newRoom.getRoomType());
        oldRoom.setStatus(newRoom.isStatus());
        oldRoom.setImage(newRoom.getImage());
        return roomDAO.save(newRoom);
    })
            .orElseThrow(() -> new IllegalArgumentException("Room not found"));
    }

    public void deleteRoom(int roomId)  {
        roomDAO.deleteById(roomId);
    }

    public List<Room> getRoomsForHotel(int hotelId, boolean status, String roomType, int capacity) {
        // Filter rooms by the given criteria for a specific hotel
        // You would use repository methods to query the database with filters like availability, roomType, capacity
        return roomDAO.findRoomsByHotelIdAndFilters(hotelId, status, roomType, capacity);
    }
}