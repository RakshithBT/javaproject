package com.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.repository.AppUserRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if the admin user exists, if not, create it
        if (!appUserRepository.existsByUsername("admin")) {
            AppUser appUser = new AppUser();
            appUser.setUsername("admin");
            appUser.setPassword("adminpassword"); // Use plain text password
            appUser.setRole("ADMIN");
            appUserRepository.save(appUser);
        }
    }
}
