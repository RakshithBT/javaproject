package com.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.advice.CustomerException;
import com.entity.Cart;
import com.entity.Customer;
import com.entity.FoodItems;
import com.entity.Orders;
import com.repository.CustomerRepository;
import com.service.CustomerService;

@Service
@Validated
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

//    public List<Customer> getAllCustomers() {
//        return customerRepository.findAll();
//    }
    
//    public Optional<Customer> getCustomerById(int customerId) {
//        return customerRepository.findById(customerId);
//    }
    
    
    
    public List<FoodItems> getPreviousOrderedFoodItems(int customerId, int orderId, int cartId) {
        // Find the customer by ID
        Customer customer = customerRepository.findById(customerId).orElse(null);

        if (customer != null) {
            // Iterate through the customer's carts
            for (Cart cart : customer.getCarts()) {
                if (cart.getCartId() == cartId) {
                    // Iterate through the cart's orders
                    for (Orders order : cart.getOrders()) {
                        if (order.getOrderId() == orderId) {
                            // Return the food items associated with this order
                            return order.getFoodItems();
                        }
                    }
                }
            }
        }

        // Return an empty list if no matching order is found
        return Collections.emptyList();
    }
    
    
//
//    public Customer createCustomer(@Valid Customer customer) {
//        return customerRepository.save(customer);
//    }

    
    public Customer updateCustomer(int customerId, Customer updatedCustomer) throws CustomerException {
        // Check if the customer exists
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerException("Customer not found");
        }
        
        // Set the customerId of the updatedCustomer to match the one you want to update
        updatedCustomer.setUserId(customerId);
        
        // Update the customer
        return customerRepository.save(updatedCustomer);
    }
    
    

//    public void deleteCustomer(int customerId) {
//        customerRepository.deleteById(customerId);
//    }




public List<Customer> getAllCustomers()throws CustomerException{
	List<Customer> customers = customerRepository.findAll();  
    if (customers.isEmpty()) {
        throw new CustomerException("No Customers found");
    }
    else {
    return customers;
}
}
public Customer createCustomer(@Valid Customer customer) throws CustomerException {
    Optional<Customer> existingCustomer = customerRepository.findByUsername(customer.getUsername());
    if (existingCustomer.isPresent()) {
        throw new CustomerException("Username already exists");
    }
    return customerRepository.save(customer);
}


public Optional<Customer> getCustomerById(int customerId) throws CustomerException{
	Optional<Customer> customers = customerRepository.findById(customerId);
	if(!customers.isEmpty()) {
	return customers;
}
	else {
		throw new CustomerException("customer with "+ customerId +" Not Found");
	}
}
@Override
@Transactional // Add this annotation to enable transaction management
public void deleteCustomer(int customerId) throws CustomerException {
    Optional<Customer> customerOptional = customerRepository.findById(customerId);
    if (customerOptional.isPresent()) {
        customerRepository.delete(customerOptional.get()); // Delete the customer
    } else {
        throw new CustomerException("Customer with ID " + customerId + " not found");
    }
}
}

