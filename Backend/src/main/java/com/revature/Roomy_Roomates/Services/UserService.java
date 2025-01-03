package com.revature.Roomy_Roomates.Services;

import com.revature.Roomy_Roomates.DAOs.HotelDAO;
import com.revature.Roomy_Roomates.DAOs.UserDAO;
import com.revature.Roomy_Roomates.Exceptions.*;
import com.revature.Roomy_Roomates.Models.Hotel;
import com.revature.Roomy_Roomates.Models.Roles;
import com.revature.Roomy_Roomates.Models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private Integer passwordMinLength = 4;
    private Integer usernameMinLength = 4;
    private UserDAO userDAO;
    private HotelDAO hotelDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
        this.hotelDAO=hotelDAO;
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
        if(userDAO.findUserByUsername(user.getUsername().trim()).isPresent()) throw new AlreadyExists();
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(12)));
        return userDAO.save(user);
    }

    public Boolean PasswordEquals(String username, String password) throws NotInDatabase{
        try{
            User user = getUserByUsername(username);
            boolean newPassword = BCrypt.checkpw(password,user.getPassword());
            return newPassword;
        } catch (NotInDatabase e){
            throw new NotInDatabase();
        }
    }

    public Boolean PasswordEquals(User givenUser) throws NotInDatabase{
        try{
            User user = getUserByUsername(givenUser.getUsername());
            boolean newPassword = BCrypt.checkpw(givenUser.getPassword(),user.getPassword());
            return newPassword;
        } catch (NotInDatabase e){
            throw new NotInDatabase();
        }
    }

    public User updateUserInformationWithAuthentication(User givenUser) throws AlreadyExists,NotInDatabase, Unauthorized,ImproperFormat{
        if(!PasswordEquals(givenUser) && givenUser.getRole() != Roles.ADMIN) throw new Unauthorized();
        Optional<User> userSearch = userDAO.findById(givenUser.getUserId());
        if(userSearch.isEmpty()) throw new NotInDatabase();

        User user = userSearch.get();
        if(givenUser.getRole() == Roles.ADMIN){
            user.setRole(givenUser.getRole());;
        }

        if(givenUser.getUsername().trim().length() >= usernameMinLength &&
                userDAO.findUserByUsername(givenUser.getUsername().trim()).isEmpty())
                user.setUsername(givenUser.getUsername());
        if(!user.getFirstName().isEmpty()) user.setFirstName(givenUser.getFirstName());
        if(!user.getLastName().isEmpty()) user.setLastName(givenUser.getLastName());
        if(!user.getEmail().isEmpty()) user.setEmail(givenUser.getEmail());
        if(!user.getDate().toString().isEmpty()) user.setEmail(givenUser.getEmail());

        if(givenUser.getDate() != null) user.setDate(givenUser.getDate());
        return userDAO.save(user);
    }

    public User updateUserInformationWithoutAuthentication(User givenUser) throws AlreadyExists,NotInDatabase, Unauthorized,ImproperFormat{
        if(userDAO.findUserByUsername(givenUser.getUsername().trim()).isPresent()) throw new AlreadyExists();
        Optional<User> userSearch = userDAO.findById(givenUser.getUserId());
        if(userSearch.isEmpty()) throw new NotInDatabase();

        User user = userSearch.get();
        if(givenUser.getRole() == Roles.ADMIN){
            user.setRole(givenUser.getRole());;
        }
        if(givenUser.getUsername().trim().length() >= usernameMinLength &&
                userDAO.findUserByUsername(givenUser.getUsername().trim()).isEmpty())
                user.setUsername(givenUser.getUsername());
        if(user.getPassword().trim().length() >= passwordMinLength) user.setPassword(givenUser.getPassword());

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

    public List<Hotel> getFavoritesForUser(String username) {
        return userDAO.findFavoritesByUsername(username);
    }
    

    public User addHotelToFavorites(String username, int hotelId){
        Optional<User> possibleUser = userDAO.findUserByUsername(username);
        Optional<Hotel> possibleHotel = hotelDAO.findById(hotelId);
        if (possibleUser.isEmpty() || possibleHotel.isEmpty()){
            return null;
        }
        User returnedUser = possibleUser.get();
        Hotel returnedHotel = possibleHotel.get();
        Set<Hotel> favorites = returnedUser.getFavorites();
        favorites.add(returnedHotel);
        returnedUser.setFavorites(favorites);
        return userDAO.save(returnedUser);
    }

    public User removeHotelFromFavorites(String username, int hotelId){
        Optional<User> possibleUser = userDAO.findUserByUsername(username);
        Optional<Hotel> possibleHotel = hotelDAO.findById(hotelId);
        if (possibleUser.isEmpty() || possibleHotel.isEmpty()){
            return null;
        }
        User returnedUser = possibleUser.get();
        Hotel returnedHotel = possibleHotel.get();
        Set<Hotel> favorites = returnedUser.getFavorites();
        favorites.remove(returnedHotel);
        returnedUser.setFavorites(favorites);
        return userDAO.save(returnedUser);
    }


}
