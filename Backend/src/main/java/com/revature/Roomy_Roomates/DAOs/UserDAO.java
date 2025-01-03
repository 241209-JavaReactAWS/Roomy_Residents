package com.revature.Roomy_Roomates.DAOs;

import com.revature.Roomy_Roomates.Models.Hotel;
import com.revature.Roomy_Roomates.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User,Integer> {
    public Optional<User> findUserByUsername(String username);

    public List<Hotel> findFavoritesByUsername(String username);
}
