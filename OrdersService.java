package com.service;

import java.util.List;


import com.advice.OrdersException;
import com.entity.FoodItems;
import com.entity.Orders;

public interface OrdersService {

	 List<FoodItems> getFoodItemsByCustomerOrderId(int orderId, int customerId) throws OrdersException;

	    Orders createOrder(Orders order);

	    Orders updateOrder(int orderId, Orders updatedOrder) throws OrdersException;

	    List<Orders> getAllOrders() throws OrdersException;

	    Orders getOrderById(int orderId) throws OrdersException;

	    void deleteOrder(int orderId) throws OrdersException;
	  
}
