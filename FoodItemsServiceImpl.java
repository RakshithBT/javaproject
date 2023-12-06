package com.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advice.FoodItemsException;
import com.entity.Category;
import com.entity.Customer;
import com.entity.FoodItems;
import com.entity.Orders;
import com.repository.CustomerRepository;
import com.repository.FoodItemsRepository;
import com.repository.OrdersRepository;
import com.service.FoodItemsService;

@Service
public class FoodItemsServiceImpl implements FoodItemsService{

    @Autowired
    private FoodItemsRepository foodItemsRepository;

    //
    @Autowired
    private CustomerRepository customerRepository;
    //
    
    // // 
    @Autowired
    private OrdersRepository ordersRepository;
    // // 
    
//    public List<FoodItems> getAllFoodItems() {
//        return foodItemsRepository.findAll();
//    }

//    public Optional<FoodItems> getFoodItemById(int foodItemId) {
//        return foodItemsRepository.findById(foodItemId);
//    }

//    
//    public List<FoodItems> getFoodItemsByCategory(Category category) {
//        return foodItemsRepository.findByCategory(category);
//    }
    
    public List<FoodItems> getFoodItemsByCategory(Category category) throws FoodItemsException {
        List<FoodItems> foodItems = foodItemsRepository.findByCategory(category);
        if (foodItems.isEmpty()) {
            throw new FoodItemsException("No food items found for the given category");
        }
        return foodItems;
    }

    
    
//    public List<FoodItems> getFoodItemsByName(String itemName) {
//        return foodItemsRepository.findByItemName(itemName);
//    }
    
    public List<FoodItems> getFoodItemsByName(String itemName) throws FoodItemsException {
        List<FoodItems> foodItems = foodItemsRepository.findByItemName(itemName);
        if (foodItems.isEmpty()) {
            throw new FoodItemsException("No food items found with the given name");
        }
        return foodItems;
    }

        
    
    //
    
    public List<FoodItems> getPastFoodItemsByCustomerId(int customerId) throws FoodItemsException  {
        // Retrieve the customer by customer ID
        Customer customer = customerRepository.findById(customerId).orElse(null);

        if (customer != null) {
            List<FoodItems> pastFoodItems = new ArrayList<>();

            // Iterate through the customer's orders
            for (Orders order : customer.getOrders()) {
                // Retrieve the food items associated with each order
                List<FoodItems> foodItemsInOrder = order.getFoodItems();
                pastFoodItems.addAll(foodItemsInOrder);
            }

            // At this point, pastFoodItems will contain all food items
            // that the customer has ordered in the past.
            return pastFoodItems;
        } else {
            // Handle the case when the customer is not found
            throw new FoodItemsException("Customer not found with ID: " + customerId);
        }
    }
    
    //
    
    
    
    // // 
    
    public List<FoodItems> getOrderedFoodItemsByCustomerIdAndRestaurantId(int customerId, int restaurantId) {
        List<FoodItems> orderedFoodItems = new ArrayList<>();

        // Retrieve the orders for the customer
        List<Orders> customerOrders = ordersRepository.findByCustomerUserId(customerId);

        // Iterate through the orders and filter food items by restaurantId
        for (Orders order : customerOrders) {
            for (FoodItems foodItem : order.getFoodItems()) {
                if (foodItem.getRestaurant().getUserId() == restaurantId) {
                    orderedFoodItems.add(foodItem);
                }
            }
        }

        return orderedFoodItems;
    }
    // //
    
    
    public FoodItems createFoodItem(FoodItems foodItem) {
        return foodItemsRepository.save(foodItem);
    }




    public FoodItems updateFoodItem(int foodItemId, FoodItems updatedFoodItem) throws FoodItemsException {
        if (!foodItemsRepository.existsById(foodItemId)) {
            throw new FoodItemsException("Food Item not found");
        }
        updatedFoodItem.setId(foodItemId);
        return foodItemsRepository.save(updatedFoodItem);
    }

//    public void deleteFoodItem(int foodItemId) {
//        foodItemsRepository.deleteById(foodItemId);
//    }
    
    
    public List<FoodItems> getAllFoodItems() throws FoodItemsException{
 		List<FoodItems> foodItems = foodItemsRepository.findAll();  
 	    if (foodItems.isEmpty()) {
 	        throw new FoodItemsException("No fooditems found");
 	    }
 	    else {
 	    return foodItems;
 	}
 	}
     
     public Optional<FoodItems> getFoodItemById(int foodItemId)throws FoodItemsException{
     	Optional<FoodItems> foodItems = foodItemsRepository.findById(foodItemId);
 		if(!foodItems.isEmpty()) {
 		return foodItems;
 	}
 		else {
 			throw new FoodItemsException("fooditem with "+ foodItemId +" Not Found");
 		}
 	}
     
     public Optional<FoodItems> deleteFoodItem(int foodItemId)throws FoodItemsException{
     	Optional<FoodItems> foodItems = foodItemsRepository.findById(foodItemId);
 		if(!foodItems.isEmpty()) {
 		return foodItems;
 	}
 		else {
 			throw new FoodItemsException("fooditem with "+ foodItemId +" Not Found");
 		}
 	}
     
 }
