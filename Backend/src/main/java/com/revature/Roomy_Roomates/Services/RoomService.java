package com.revature.Roomy_Roomates.Services;

import com.revature.Roomy_Roomates.DAOs.RoomDAO;
import com.revature.Roomy_Roomates.Models.Room;
import com.revature.Roomy_Roomates.Models.Room;
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

    public Room updateRoom(Room room){
        return roomDAO.save(room);
    }

    public void deleteRoom(int roomId)  {
        roomDAO.deleteById(roomId);
    }

    public Status getRoomByAvailability(Status status) {
        return roomDAO.getRoomByAvailability();
    }

}