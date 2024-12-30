package com.revature.Roomy_Roomates.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.Roomy_Roomates.Models.Room;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDAO extends JpaRepository<Room, Integer> {

}