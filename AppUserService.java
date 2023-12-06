package com.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.advice.UserNotAvailableException;
import com.entity.AppUser;
import com.entity.Customer;
import com.entity.Restaurant;

public interface AppUserService {

	
	public List<AppUser> getAllAppUsers();
	
	public Optional<AppUser> getAppUserById(int userId);
	
	public AppUser createAppUser(@Valid AppUser appUser);
	
	 public AppUser updateAppUser(int userId,@Valid AppUser updatedAppUser);
	 
	 public void deleteAppUser(int userId);

	 public Customer logInCustomer(String userName, String password, String role) throws UserNotAvailableException;
	 
	 public Restaurant logInRestaurant(String userName, String password, String role) throws UserNotAvailableException;
	 
	 public boolean validateAdminLogin(String username, String password, String role);
	
}
