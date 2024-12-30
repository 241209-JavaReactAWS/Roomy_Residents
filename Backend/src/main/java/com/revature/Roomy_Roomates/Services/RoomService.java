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

    public List<Room> getRoomByAvailability(boolean status) {
        return roomDAO.findByStatus(status);
    }

    public List<Room> getRoomByHotel(Hotel hotel) {
        return roomDAO.findByHotel(hotel);
    }

    public List<Room> getRoomByRoomType(String roomType) {
        return roomDAO.findByRoomType(roomType);
    }

    public List<Room> getRoomByHotelAndStatus(Hotel hotel, boolean status) {
        return roomDAO.findByHotelAndStatus(hotel, status);
    }

    public List<Room> getRoomByRoomTypeAndStatus(String roomType, boolean status) {
        return roomDAO.findByRoomTypeAndStatus(roomType, status);
    }

    public List<Room> getRoomByRoomTypeAndHotel(String roomType, Hotel hotel) {
        return roomDAO.findByRoomTypeAndHotel(roomType, hotel);
    }

    public List<Room> getRoomByRoomTypeAndStatusAndHotel(String roomType, boolean status, Hotel hotel) {
        return roomDAO.findByRoomTypeAndStatusAndHotel(roomType, status, hotel);
    }
}