package com.revature.Roomy_Roomates.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Roomy_Roomates.Models.User;
import com.revature.Roomy_Roomates.Services.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("User")
public class UserController{

    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/favorites/{hotelId}")
    public ResponseEntity<User> addHotelToFavorites(HttpSession session, @PathVariable int hotelId){
        if (session.isNew() || session.getAttribute("username") == null){
            return ResponseEntity.status(401).build();
        }
        User returnedUser = userService.addHotelToFavorites( (String) session.getAttribute("username"), hotelId);
        if (returnedUser == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(returnedUser);
    }

    @DeleteMapping("/favorites/{hotelId}")
    public ResponseEntity<User> removeHotelFromFavorites(HttpSession session, @PathVariable int hotelId){
        if (session.isNew() || session.getAttribute("username") == null){
            return ResponseEntity.status(401).build();
        }
        User returnedUser = userService.removeHotelFromFavorites((String) session.getAttribute("username"), hotelId);
        if (returnedUser == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(returnedUser);
    }

}
