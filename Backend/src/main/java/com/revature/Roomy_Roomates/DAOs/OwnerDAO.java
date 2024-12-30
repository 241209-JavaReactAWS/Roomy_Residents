package com.revature.Roomy_Roomates.DAOs;

import com.revature.Roomy_Roomates.Models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerDAO extends JpaRepository<Owner,Integer> {
}
