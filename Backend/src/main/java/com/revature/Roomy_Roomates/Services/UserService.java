package com.revature.Roomy_Roomates.Services;

import com.revature.Roomy_Roomates.DAOs.UserDAO;
import com.revature.Roomy_Roomates.Exceptions.*;
import com.revature.Roomy_Roomates.Models.Roles;
import com.revature.Roomy_Roomates.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class UserService {

    private Integer passwordMinLength = 4;
    private Integer usernameMinLength = 4;
    private UserDAO userDAO;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10,new SecureRandom());

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUserById(Integer id) throws NotInDatabase {
        Optional<User> user = userDAO.findById(id);
        if(user.isEmpty()) throw new NotInDatabase();
        return user.get();
    }

    public User getUserById(User givenUser) throws NotInDatabase {
        Optional<User> user = userDAO.findById(givenUser.getUserId());
        if(user.isEmpty()) throw new NotInDatabase();
        return user.get();
    }

    public User getUserByUsername(String username) throws NotInDatabase{
        Optional<User> user = userDAO.findUserByUsername(username.trim());
        if(user.isEmpty()) throw new NotInDatabase();
        return user.get();
    }

    public User getUserByUsername(User givenUser) throws NotInDatabase{
        Optional<User> user = userDAO.findUserByUsername(givenUser.getUsername().trim());
        if(user.isEmpty()) throw new NotInDatabase();
        return user.get();
    }

    public User postUser(User user) throws ImproperFormat, AlreadyExists, MissingInformation {
        if(user.getPassword().trim().length() < passwordMinLength) throw new ImproperFormat();
        if(user.getUsername().trim().length() < usernameMinLength) throw new ImproperFormat();
        if(user.getFirstName().isEmpty()) throw new MissingInformation();
        if(user.getLastName().isEmpty()) throw new MissingInformation();
        if(user.getEmail().isEmpty()) throw new MissingInformation();
        if(userDAO.findUserByUsername(user.getUsername().trim()).isEmpty()) throw new AlreadyExists();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDAO.save(user);
    }

    public Boolean PasswordEquals(String username, String password) throws NotInDatabase{
        try{
            User user = getUserByUsername(username);
            String newPassword = bCryptPasswordEncoder.encode(password);
            if(user.getPassword().equals(newPassword)){
                return true;
            }
        } catch (NotInDatabase e){
            throw new NotInDatabase();
        }
        return false;
    }

    public Boolean PasswordEquals(User givenUser) throws NotInDatabase{
        try{
            User user = getUserByUsername(givenUser.getUsername());
            String newPassword = bCryptPasswordEncoder.encode(givenUser.getPassword());
            if(user.getPassword().equals(newPassword)){
                return true;
            }
        } catch (NotInDatabase e){
            throw new NotInDatabase();
        }
        return false;
    }

    public User updateUserInformationWithPassword(User givenUser) throws AlreadyExists,NotInDatabase, Unauthorized,ImproperFormat{
        if(!PasswordEquals(givenUser) && givenUser.getRole() != Roles.ADMIN) throw new Unauthorized();
        if(givenUser.getPassword().trim().length() < passwordMinLength) throw new ImproperFormat();
        if(givenUser.getUsername().trim().length() < usernameMinLength) throw new ImproperFormat();
        if(userDAO.findUserByUsername(givenUser.getUsername().trim()).isPresent()) throw new AlreadyExists();
        Optional<User> userSearch = userDAO.findById(givenUser.getUserId());
        if(userSearch.isEmpty()) throw new NotInDatabase();

        User user = userSearch.get();
        if(givenUser.getRole() == Roles.ADMIN){
            user.setRole(givenUser.getRole());;
        }

        if(!user.getFirstName().isEmpty()) user.setFirstName(givenUser.getFirstName());
        if(!user.getLastName().isEmpty()) user.setLastName(givenUser.getLastName());
        if(!user.getEmail().isEmpty()) user.setEmail(givenUser.getEmail());
        if(!user.getDate().toString().isEmpty()) user.setEmail(givenUser.getEmail());

        if(givenUser.getDate() != null) user.setDate(givenUser.getDate());
        return userDAO.save(user);
    }

    public User updateUserInformationWithoutPassword(User givenUser) throws AlreadyExists,NotInDatabase, Unauthorized,ImproperFormat{
        if(givenUser.getPassword().trim().length() < passwordMinLength) throw new ImproperFormat();
        if(givenUser.getUsername().trim().length() < usernameMinLength) throw new ImproperFormat();
        if(userDAO.findUserByUsername(givenUser.getUsername().trim()).isPresent()) throw new AlreadyExists();
        Optional<User> userSearch = userDAO.findById(givenUser.getUserId());
        if(userSearch.isEmpty()) throw new NotInDatabase();

        User user = userSearch.get();
        if(givenUser.getRole() == Roles.ADMIN){
            user.setRole(givenUser.getRole());;
        }

        if(!user.getFirstName().isEmpty()) user.setFirstName(givenUser.getFirstName());
        if(!user.getLastName().isEmpty()) user.setLastName(givenUser.getLastName());
        if(!user.getEmail().isEmpty()) user.setEmail(givenUser.getEmail());
        if(!user.getDate().toString().isEmpty()) user.setEmail(givenUser.getEmail());

        if(givenUser.getDate() != null) user.setDate(givenUser.getDate());
        return userDAO.save(user);
    }

    public User deleteUserWithPassword(User givenUser) throws Unauthorized,NotInDatabase{
        Optional<User> userSearch = userDAO.findById(givenUser.getUserId());
        if(userSearch.isEmpty()) throw new NotInDatabase();
        if(!PasswordEquals(givenUser) && givenUser.getRole() != Roles.ADMIN) throw new Unauthorized();
        userDAO.deleteById(givenUser.getUserId());
        return userSearch.get();
    }

    public User deleteUserWithoutPassword(User givenUser) throws Unauthorized,NotInDatabase{
        Optional<User> userSearch = userDAO.findById(givenUser.getUserId());
        if(userSearch.isEmpty()) throw new NotInDatabase();
        userDAO.deleteById(givenUser.getUserId());
        return userSearch.get();
    }


}
