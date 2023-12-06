package com.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
	 boolean existsByUsername(String username);
	    Optional<AppUser> findByUsernameAndPasswordAndRole(String username, String password, String role);
	
	 
}
