package com.revature.Roomy_Roomates.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.Roomy_Roomates.Models.Rooms;

@Repostiory
public interface RoomDAO extends JpaRepository<Room, Integer> {

}