package com.revature.Roomy_Roomates.Controllers;

import com.revature.Roomy_Roomates.Models.Hotel;
import com.revature.Roomy_Roomates.Models.Room;
import com.revature.Roomy_Roomates.Services.RoomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rooms")
public class RoomController {

    @Autowired
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping({"roomId"})
    public ResponseEntity<Room> getRoomById(@PathVariable int roomId) {
        Optional<Room> room = roomService.getRoomById(roomId);
        if (room.isPresent()) {
            return new ResponseEntity<>(room.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room, HttpSession session) {

        if (session.isNew() || session.getAttribute("username") == null){
            return ResponseEntity.status(401).build(); // Unauthorized
        }

        Room newRoom = roomService.createNewRoom(room);

        if (newRoom == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(newRoom);
    }

    @PutMapping({"roomId"})
    public ResponseEntity<Room> updateRoom(@RequestBody Room room, HttpSession session, @PathVariable int roomId) {

        if (session.isNew() || session.getAttribute("username") == null){
            return ResponseEntity.status(401).build();
        }

        Optional<Room> oldRoom = roomService.getRoomById(roomId);

        if (oldRoom.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Room newRoom = roomService.updateRoom(roomId, room);
        return ResponseEntity.ok(newRoom);

    }

    @DeleteMapping({"roomId"})
    public ResponseEntity<Void> deleteRoom(@PathVariable int roomId, HttpSession session) {

        if (session.isNew() || session.getAttribute("username") == null) {
            return ResponseEntity.status(401).build();
        }
        Optional<Room> room = roomService.getRoomById(roomId);
        if (room.isPresent()) {
            roomService.deleteRoom(roomId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
