package com.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advice.RestaurantException;
import com.entity.Restaurant;
import com.serviceImpl.RestaurantServiceImpl;


@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantServiceImpl restaurantServiceImpl;

    @GetMapping
    public List<Restaurant> getAllRestaurants() throws Throwable {
        return restaurantServiceImpl.getAllRestaurants();
    }

    @GetMapping("/{restaurantId}")
    public Optional<Restaurant> getRestaurantById(@PathVariable int restaurantId) throws Throwable {
        return restaurantServiceImpl.getRestaurantById(restaurantId);
    }
    
    @GetMapping("/byName/{restaurantName}")
    public List<Restaurant> getRestaurantsByName(@PathVariable String restaurantName) throws RestaurantException {
        return restaurantServiceImpl.getRestaurantsByName(restaurantName);
    }

    
    @GetMapping("/byLocation/{location}")
    public List<Restaurant> getRestaurantsByLocation(@PathVariable String location) throws RestaurantException {
        return restaurantServiceImpl.getRestaurantsByLocation(location);
    }
    
    @PostMapping
    public Restaurant createRestaurant(@Valid @RequestBody Restaurant restaurant) {
        return restaurantServiceImpl.createRestaurant(restaurant);
    }
    

    @PutMapping("/{restaurantId}")
    public Restaurant updateRestaurant(@PathVariable int restaurantId,@Valid @RequestBody Restaurant updatedRestaurant) throws RestaurantException {
        return restaurantServiceImpl.updateRestaurant(restaurantId, updatedRestaurant);
    }

    @DeleteMapping("/{restaurantId}")
    public void deleteRestaurant(@PathVariable int restaurantId) throws RestaurantException {
        restaurantServiceImpl.deleteRestaurant(restaurantId);
    }
}

