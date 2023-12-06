package com.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.advice.CustomerException;
import com.entity.Customer;
import com.entity.FoodItems;

public interface CustomerService {

	
	 public List<FoodItems> getPreviousOrderedFoodItems(int customerId, int orderId, int cartId) throws CustomerException;
	 
	 public Customer updateCustomer(int customerId, Customer updatedCustomer) throws CustomerException;
	 
	 public List<Customer> getAllCustomers() throws CustomerException;
	 
	 public Customer createCustomer(@Valid Customer customer) throws CustomerException;

	 public Optional<Customer> getCustomerById(int customerId) throws CustomerException;
	 
	 public void deleteCustomer(int customerId) throws CustomerException;
}
