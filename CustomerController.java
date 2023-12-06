package com.controller;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advice.CustomerException;
import com.entity.Customer;
import com.entity.FoodItems;
import com.serviceImpl.CustomerServiceImpl;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() throws Throwable {
        List<Customer> customers = customerServiceImpl.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) throws Throwable {
        Optional<Customer> customer = customerServiceImpl.getCustomerById(customerId);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    
    
    @GetMapping("/{customerId}/orders/{orderId}/carts/{cartId}/fooditems")
    public ResponseEntity<List<FoodItems>> getPreviousOrderedFoodItems(
            @PathVariable("customerId") int customerId,
            @PathVariable("orderId") int orderId,
            @PathVariable("cartId") int cartId) {

        List<FoodItems> foodItems = customerServiceImpl.getPreviousOrderedFoodItems(customerId, orderId, cartId);
        return ResponseEntity.ok(foodItems);
    }
    

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid Customer customer) throws CustomerException {
        Customer createdCustomer = customerServiceImpl.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable int customerId,
            @RequestBody @Valid Customer updatedCustomer
    ) throws CustomerException {
        Customer customer = customerServiceImpl.updateCustomer(customerId, updatedCustomer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId) throws Throwable {
        customerServiceImpl.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}