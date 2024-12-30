package com.revature.Roomy_Roomates.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repostiory
public interface RoomDAO extends JpaRepository<Room, Integer> {

}