package com.controller;

import java.util.List;
import java.util.Optional;

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

import com.advice.FoodItemsException;
import com.entity.Category;
import com.entity.FoodItems;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.repository.OrdersRepository;
import com.serviceImpl.CategoryServiceImpl;
import com.serviceImpl.FoodItemsServiceImpl;

@RestController
@RequestMapping("/api/fooditems")

public class FoodItemsController {
	
	@JsonIgnore
    @Autowired
    private FoodItemsServiceImpl foodItemsServiceImpl;

	 @Autowired
	    private CategoryServiceImpl categoryServiceImpl;
	
	 @Autowired
	    private OrdersRepository ordersRepository;
	 
    @GetMapping("/getfood")
    public List<FoodItems> getAllFoodItems() throws Throwable {
        return foodItemsServiceImpl.getAllFoodItems();
    }
    

    @GetMapping("/{foodItemId}")
    public Optional<FoodItems> getFoodItemById(@PathVariable int foodItemId) throws Throwable {
        return foodItemsServiceImpl.getFoodItemById(foodItemId);
    }
    
    
    @GetMapping("/byCategory/{categoryId}")
    public List<FoodItems> getFoodItemsByCategory(@PathVariable Long categoryId) throws FoodItemsException {
        // You need to fetch the category by its ID here or handle not found case
        // For simplicity, assuming you have a CategoryService to fetch category by ID
        Category category = categoryServiceImpl.getCategoryById(categoryId);

        if (category == null) {
            // Handle category not found
            // You can throw an exception or return an appropriate response
        }

        // Get food items by category
        return foodItemsServiceImpl.getFoodItemsByCategory(category);
    }
    
    @GetMapping("/byName/{itemName}") // New endpoint to get food items by name
    public List<FoodItems> getFoodItemsByName(@PathVariable String itemName) throws FoodItemsException {
        return foodItemsServiceImpl.getFoodItemsByName(itemName);
    }

    
    
    //
    
    @GetMapping("/past-fooditems/{customerId}")
    public ResponseEntity<List<FoodItems>> getPastFoodItemsByCustomerId(@PathVariable int customerId) throws Exception {
        List<FoodItems> pastFoodItems = foodItemsServiceImpl.getPastFoodItemsByCustomerId(customerId);
        return ResponseEntity.ok(pastFoodItems);
    }
    
    //
    
    // // 
    
    
    @GetMapping("/ordered")
    public ResponseEntity<List<FoodItems>> getOrderedFoodItemsByCustomerIdAndRestaurantId(
            @RequestParam("customerId") int customerId,
            @RequestParam("restaurantId") int restaurantId) {
        List<FoodItems> orderedFoodItems = foodItemsServiceImpl.getOrderedFoodItemsByCustomerIdAndRestaurantId(customerId, restaurantId);
        return ResponseEntity.ok(orderedFoodItems);
    }
    
    // // 
    @PostMapping
    public FoodItems createFoodItem(@RequestBody FoodItems foodItem) {
        return foodItemsServiceImpl.createFoodItem(foodItem);
    }

    @PutMapping("/{foodItemId}")
    public FoodItems updateFoodItem(@PathVariable int foodItemId, @RequestBody FoodItems updatedFoodItem) throws FoodItemsException {
        return foodItemsServiceImpl.updateFoodItem(foodItemId, updatedFoodItem);
    }

    @DeleteMapping("/{foodItemId}")
    public void deleteFoodItem(@PathVariable int foodItemId) throws Throwable {
        foodItemsServiceImpl.deleteFoodItem(foodItemId);
    }
}
