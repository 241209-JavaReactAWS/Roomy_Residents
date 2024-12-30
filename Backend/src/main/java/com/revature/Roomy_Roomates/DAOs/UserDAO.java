package com.revature.Roomy_Roomates.DAOs;

import com.revature.Roomy_Roomates.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User,Integer> {
}
