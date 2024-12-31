package com.revature.Roomy_Roomates.Controllers;

import com.revature.Roomy_Roomates.Exceptions.AlreadyExists;
import com.revature.Roomy_Roomates.Exceptions.ImproperFormat;
import com.revature.Roomy_Roomates.Exceptions.MissingInformation;
import com.revature.Roomy_Roomates.Exceptions.NotInDatabase;
import com.revature.Roomy_Roomates.Models.User;
import com.revature.Roomy_Roomates.Services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("userauth")
public class UserRegistrationLoginController {

    private final UserService userService;

    @Autowired
    public UserRegistrationLoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value="login")
    public ResponseEntity loginAccount(@RequestBody User givenUser, HttpServletResponse servlet){
        try{
            User resultUser = userService.getUserById(givenUser);
            if(userService.PasswordEquals(givenUser)){
                Cookie cookie = new Cookie("Roomy_Residents_Id",Integer.toString(givenUser.getUserId()));
                cookie.setMaxAge(100000);
                servlet.addCookie(cookie);
                return ResponseEntity.status(HttpStatus.OK).body(resultUser);
            }
            else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
            }
        } catch (NotInDatabase e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Something Went Wrong");
        }
    }

    @PostMapping(value = "register")
    public ResponseEntity registerAccount(@RequestBody User givenUser){
        try{
            User resultUser = userService.postUser(givenUser);
            return ResponseEntity.status(HttpStatus.OK).body(resultUser);
        } catch (ImproperFormat e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Improper Inputs");
        } catch (AlreadyExists e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username Already Exists");
        } catch (MissingInformation e) {
            return ResponseEntity.status(HttpStatus.LENGTH_REQUIRED).body("Missing Fields");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Something Went Wrong");
        }
    }

    @PostMapping(value="")
    public ResponseEntity removeLoginCookie(HttpServletResponse servlet){
        Cookie cookie = new Cookie("Roomy_Residents_Id",null);
        cookie.setMaxAge(-1);
        servlet.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.OK).body("Logged Out");
    }

    @GetMapping(value = "")
    public ResponseEntity getLoginCookie(@CookieValue(value = "Roomy_Residents_Id", defaultValue = "none") String cookie){
        if(cookie.equals("none")) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Cookie Found");
        return ResponseEntity.status(HttpStatus.OK).body(cookie);
    }
}
