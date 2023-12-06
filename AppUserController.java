package com.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.advice.UserNotAvailableException;
import com.entity.AppUser;
import com.serviceImpl.AppUserServiceImpl;

@RestController
@RequestMapping("/api/appusers")
public class AppUserController {

    @Autowired
    private AppUserServiceImpl appUserServiceImpl;
    
    
    @PostMapping("/customer")
    public ResponseEntity<String> registerCustomer(@RequestParam String userName, @RequestParam String password,@RequestParam String role) throws UserNotAvailableException {

    	appUserServiceImpl.logInCustomer(userName, password, role);

		return ResponseEntity.ok("Logged in successfully");
	}
    
    @PostMapping("/restaurant")
    public ResponseEntity<String> registerRestaurant(@RequestParam String userName, @RequestParam String password, @RequestParam String role) throws UserNotAvailableException {

    	appUserServiceImpl.logInRestaurant(userName, password, role);

		return ResponseEntity.ok("Logged in successfully");
	}
    
    @PostMapping("/admin/login")
    public String adminLogin(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        if (appUserServiceImpl.validateAdminLogin(username, password, role)) {
            return "Admin login successful";
        } else {
            return "Admin login failed";
        }
    }
    
    
    
    @GetMapping
    public List<AppUser> getAllAppUsers() {
        return appUserServiceImpl.getAllAppUsers();
    }

    @GetMapping("/{userId}")
    public Optional<AppUser> getAppUserById(@PathVariable int userId) {
        return appUserServiceImpl.getAppUserById(userId);
    }

    @PostMapping
    public AppUser createAppUser(@Valid @RequestBody AppUser appUser) {
        return appUserServiceImpl.createAppUser(appUser);
    }

    @PutMapping("/{userId}")
    public AppUser updateAppUser(@PathVariable int userId, @Valid @RequestBody AppUser updatedAppUser) {
        return appUserServiceImpl.updateAppUser(userId, updatedAppUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteAppUser(@PathVariable int userId) {
        appUserServiceImpl.deleteAppUser(userId);
    }

}
