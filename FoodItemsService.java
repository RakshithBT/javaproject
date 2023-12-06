package com.service;

import java.util.List;
import java.util.Optional;

import com.advice.FoodItemsException; // Import FoodItemsException
import com.entity.Category;
import com.entity.FoodItems;

public interface FoodItemsService {

    public List<FoodItems> getFoodItemsByCategory(Category category) throws FoodItemsException; // Add 'throws FoodItemsException'

    public List<FoodItems> getFoodItemsByName(String itemName) throws FoodItemsException; // Add 'throws FoodItemsException'

    public List<FoodItems> getPastFoodItemsByCustomerId(int customerId) throws FoodItemsException; // Add 'throws FoodItemsException'

    List<FoodItems> getOrderedFoodItemsByCustomerIdAndRestaurantId(int customerId, int restaurantId) throws FoodItemsException; // Add 'throws FoodItemsException'

    public FoodItems createFoodItem(FoodItems foodItem);

    public FoodItems updateFoodItem(int foodItemId, FoodItems updatedFoodItem) throws FoodItemsException; // Add 'throws FoodItemsException'

    public List<FoodItems> getAllFoodItems() throws FoodItemsException; // Add 'throws FoodItemsException'

    public Optional<FoodItems> getFoodItemById(int foodItemId) throws FoodItemsException; // Add 'throws FoodItemsException'

    public Optional<FoodItems> deleteFoodItem(int foodItemId) throws FoodItemsException; // Add 'throws FoodItemsException'
}
