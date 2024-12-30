package com.revature.Roomy_Roomates.Services;

import com.revature.Roomy_Roomates.DAOs.OwnerDAO;
import com.revature.Roomy_Roomates.Exceptions.MissingInformation;
import com.revature.Roomy_Roomates.Exceptions.NotInDatabase;
import com.revature.Roomy_Roomates.Models.Owner;
import com.revature.Roomy_Roomates.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerService {

    public OwnerDAO ownerDAO;

    public OwnerService() {
    }
    @Autowired
    public OwnerService(OwnerDAO ownerDAO) {
        this.ownerDAO = ownerDAO;
    }

    public Owner getOwnerById(Owner givenOwner) throws NotInDatabase {
        Optional<Owner> user = ownerDAO.findById(givenOwner.getOwnerId());
        if(user.isEmpty()) throw new NotInDatabase();
        return user.get();
    }

    public Owner getOwnerById(Integer id) throws NotInDatabase {
        Optional<Owner> user = ownerDAO.findById(id);
        if(user.isEmpty()) throw new NotInDatabase();
        return user.get();
    }

    public Owner postOwner(Integer id,Owner givenOwner) throws MissingInformation {
        if(givenOwner.getBuisnessEmail().trim().isEmpty()) throw new MissingInformation();
        if(givenOwner.getBuisnessPhoneNumber() < 0) throw new MissingInformation();
        if(givenOwner.getBuisnessName().trim().isEmpty()) throw new MissingInformation();
        givenOwner.setOwnerId(id);
        return ownerDAO.save(givenOwner);
    }

    public Owner postOwner(Integer id,String buisnessEmail,String buisnessName, Integer buisnessPhoneNumber) throws MissingInformation {
        if(buisnessEmail.trim().isEmpty()) throw new MissingInformation();
        if(buisnessPhoneNumber < 0) throw new MissingInformation();
        if(buisnessName.trim().isEmpty()) throw new MissingInformation();
        return ownerDAO.save(new Owner(id,buisnessName,buisnessEmail,buisnessPhoneNumber));
    }

    public Owner updateOwner(Owner givenOwner) throws MissingInformation,NotInDatabase{
        if(givenOwner.getBuisnessEmail().trim().isEmpty()) throw new MissingInformation();
        if(givenOwner.getBuisnessPhoneNumber() < 0) throw new MissingInformation();
        if(givenOwner.getBuisnessName().trim().isEmpty()) throw new MissingInformation();
        Optional<Owner> user = ownerDAO.findById(givenOwner.getOwnerId());
        if(user.isEmpty()) throw new NotInDatabase();
        return  ownerDAO.save(givenOwner);
    }

    public Owner deleteOwner(Owner givenOwner) throws NotInDatabase{
        Optional<Owner> user = ownerDAO.findById(givenOwner.getOwnerId());
        if(user.isEmpty()) throw new NotInDatabase();
        ownerDAO.deleteById(givenOwner.getOwnerId());
        return user.get();
    }

    public Owner deleteOwner(Integer id) throws NotInDatabase{
        Optional<Owner> user = ownerDAO.findById(id);
        if(user.isEmpty()) throw new NotInDatabase();
        ownerDAO.deleteById(id);
        return user.get();
    }


}
