package com.controller;

import java.util.List;

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

import com.advice.OrdersException;
import com.entity.FoodItems;
import com.entity.Orders;
import com.serviceImpl.OrdersServiceImpl;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private OrdersServiceImpl ordersServiceImpl;

    @GetMapping
    public List<Orders> getAllOrders() throws Throwable {
        return ordersServiceImpl.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getOrderById(@PathVariable int orderId) throws OrdersException {
        Orders order = ordersServiceImpl.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
    
    
    @GetMapping("/{orderId}/fooditems")
    public ResponseEntity<List<FoodItems>> getFoodItemsByCustomerOrderId(
            @PathVariable("orderId") int orderId,
            @RequestParam("customerId") int customerId) throws OrdersException {
        List<FoodItems> foodItems = ordersServiceImpl.getFoodItemsByCustomerOrderId(orderId, customerId);
        return ResponseEntity.ok(foodItems);
    }
    
    
    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        Orders createdOrder = ordersServiceImpl.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Orders> updateOrder(@PathVariable int orderId, @RequestBody Orders updatedOrder) throws OrdersException {
        Orders order = ordersServiceImpl.updateOrder(orderId, updatedOrder);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int orderId) throws OrdersException {
        ordersServiceImpl.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
