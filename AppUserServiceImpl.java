package com.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import com.advice.UserNotAvailableException;
import com.entity.AppUser;
import com.entity.Customer;
import com.entity.Restaurant;
import com.repository.AppUserRepository;
import com.repository.CustomerRepository;
import com.repository.RestaurantRepository;
import com.service.AppUserService;


@Service
@Validated
public class AppUserServiceImpl implements AppUserService{

    @Autowired
    private AppUserRepository appUserRepository;

    
    @Autowired
	private CustomerRepository customerRepo;
    
    @Autowired
    private RestaurantRepository restaurantRepo;
    
    
    
    public Customer logInCustomer(String userName, String password, String role) throws UserNotAvailableException {
		return customerRepo.findByUsernameAndPasswordAndRole(userName, password,role)
				.orElseThrow(() -> new UserNotAvailableException("User not available"));
	}	
    
    public Restaurant logInRestaurant(String userName, String password, String role) throws UserNotAvailableException {
		return restaurantRepo.findByUsernameAndPasswordAndRole(userName, password, role)
				.orElseThrow(() -> new UserNotAvailableException("User not available"));
	}

    
    

    public boolean validateAdminLogin(String username, String password, String role) {
        Optional<AppUser> adminUserOptional = appUserRepository.findByUsernameAndPasswordAndRole(username, password, role);
        return adminUserOptional.isPresent();
    }
   
    
    
    
    
    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }	

    public Optional<AppUser> getAppUserById(int userId) {
        return appUserRepository.findById(userId);
    }

    public AppUser createAppUser(@Valid AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public AppUser updateAppUser(int userId,@Valid AppUser updatedAppUser) {
        if (!appUserRepository.existsById(userId)) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "AppUser not found");
        }
        updatedAppUser.setUserId(userId);
        return appUserRepository.save(updatedAppUser);
    }

    public void deleteAppUser(int userId) {
        appUserRepository.deleteById(userId);
    }
}