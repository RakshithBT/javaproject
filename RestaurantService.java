package com.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.advice.RestaurantException;
import com.entity.Restaurant;

public interface RestaurantService {

	public List<Restaurant>getRestaurantsByName(String restaurantName) throws RestaurantException;
	
	 public List<Restaurant> getRestaurantsByLocation(String location) throws RestaurantException;
	 
	 public Restaurant createRestaurant(@Valid Restaurant restaurant);
	 
	 public Restaurant updateRestaurant(int restaurantId,@Valid Restaurant updatedRestaurant) throws RestaurantException;
	
	 public List<Restaurant> getAllRestaurants() throws RestaurantException;
	 
	 public Optional<Restaurant> getRestaurantById(int restaurantId) throws RestaurantException;
	 
	 public void deleteRestaurant(int restaurantId) throws RestaurantException;


}
