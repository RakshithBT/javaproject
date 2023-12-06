package com.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advice.RestaurantException;
import com.entity.Restaurant;
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

	List<Restaurant> findByRestaurantName(String restaurantName) throws RestaurantException;
	  List<Restaurant> findByLocation(String location);
	  
	  Optional<Restaurant> findByUsernameAndPasswordAndRole(String username, String password, String role);
}
