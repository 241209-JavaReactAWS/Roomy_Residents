package com.revature.Roomy_Roomates.DAOs;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.Roomy_Roomates.Models.*;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDAO extends JpaRepository<Room, Integer> {
    List<Room> findByStatus(Status status);


}