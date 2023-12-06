package com.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.advice.RestaurantException;
import com.entity.Restaurant;
import com.repository.RestaurantRepository;
import com.service.RestaurantService;

@Service
@Validated
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

//    public List<Restaurant> getAllRestaurants() {
//        return restaurantRepository.findAll();
//    }

//    public Optional<Restaurant> getRestaurantById(int restaurantId) {
//        return restaurantRepository.findById(restaurantId);
//    }
    
    public List<Restaurant>getRestaurantsByName(String restaurantName) throws RestaurantException {
        List<Restaurant> restaurants = restaurantRepository.findByRestaurantName(restaurantName);
        if (restaurants.isEmpty()) {
            throw new RestaurantException("No restaurants found with the given name");
        }
        return restaurants;
    }
    
//    public List<Restaurant> getRestaurantsByLocation(String location) {
//        return restaurantRepository.findByLocation(location);
//    }
    
    public List<Restaurant> getRestaurantsByLocation(String location) throws RestaurantException {
        List<Restaurant> restaurants = restaurantRepository.findByLocation(location);
        if (restaurants.isEmpty()) {
            throw new RestaurantException("No restaurants found in the given location");
        }
        return restaurants;
    }

    
    public Restaurant createRestaurant(@Valid Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(int restaurantId,@Valid Restaurant updatedRestaurant) throws RestaurantException {
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new RestaurantException("Restaurant not found");
        }
        updatedRestaurant.setUserId(restaurantId);
        return restaurantRepository.save(updatedRestaurant);
    }

//    public void deleteRestaurant(int restaurantId) {
//        restaurantRepository.deleteById(restaurantId);
//    }

    public List<Restaurant> getAllRestaurants()throws RestaurantException{
		List<Restaurant> restaurants = restaurantRepository.findAll();  
	    if (restaurants.isEmpty()) {
	        throw new RestaurantException("No restaurants found");
	    }
	    else {
	    return restaurants;
	}
	}
    
    
    public Optional<Restaurant> getRestaurantById(int restaurantId) throws RestaurantException{
    	Optional<Restaurant> restaurants = restaurantRepository.findById(restaurantId);
		if(!restaurants.isEmpty()) {
		return restaurants;
	}
		else {
			throw new RestaurantException("restaurant with "+ restaurantId +" Not Found");
		}
	}
    
    @Override
    @Transactional // Add this annotation to enable transaction management
    public void deleteRestaurant(int restaurantId) throws RestaurantException {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if (restaurantOptional.isPresent()) {
            restaurantRepository.delete(restaurantOptional.get()); // Delete the restaurant
        } else {
            throw new RestaurantException("Restaurant with ID " + restaurantId + " not found");
        }
    }
    

}
