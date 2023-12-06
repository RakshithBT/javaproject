package com.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advice.OrdersException;
import com.entity.FoodItems;
import com.entity.Orders;
import com.repository.OrdersRepository;
import com.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService{

    @Autowired
    private OrdersRepository ordersRepository;


    
//    public List<Orders> getAllOrders() {
//        return ordersRepository.findAll();
//    }

    
//    public Optional<Orders> getOrderById(int orderId) {
//        return ordersRepository.findById(orderId);
//    }
    
    @Override
    public List<FoodItems> getFoodItemsByCustomerOrderId(int orderId, int customerId) throws OrdersException {
        Optional<Orders> orderOptional = ordersRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Orders order = orderOptional.get();
            if (order.getCustomer().getUserId() == customerId) {
                return order.getFoodItems();
            }
        }
        throw new OrdersException("Order not found");
    }
    @Override
    public Orders createOrder(Orders order) {
        return ordersRepository.save(order);
    }
    
    @Override
    public Orders updateOrder(int orderId, Orders updatedOrder) throws OrdersException {
        if (!ordersRepository.existsById(orderId)) {
            throw new OrdersException("Order not found");
        }
        updatedOrder.setOrderId(orderId);
        return ordersRepository.save(updatedOrder);
    }

//    public void deleteOrder(int orderId) {
//        ordersRepository.deleteById(orderId);
//    }
    @Override
    public List<Orders> getAllOrders() throws OrdersException{
		List<Orders> orders = ordersRepository.findAll();  
	    if (orders.isEmpty()) {
	        throw new OrdersException("No orders found");
	    }
	    else {
	    return orders;
	}
	}
    
    
//    public Optional<Orders> getOrderById(int orderId) throws OrdersException{
//    	Optional<Orders> orders = ordersRepository.findById(orderId);
//		if(!orders.isEmpty()) {
//		return orders;
//	}
//		else {
//			throw new OrdersException("customer with "+ orderId +" Not Found");
//		}
//	}
    
    @Override
    public Orders getOrderById(int orderId) throws OrdersException {
        Optional<Orders> orders = ordersRepository.findById(orderId);
        if (orders.isPresent()) {
            return orders.get();
        } else {
            throw new OrdersException("Order with ID " + orderId + " Not Found");
        }
    }
    
    @Override
    public void deleteOrder(int orderId) throws OrdersException {
        if (!ordersRepository.existsById(orderId)) {
            throw new OrdersException("Order with ID " + orderId + " Not Found");
        }
        ordersRepository.deleteById(orderId);
    }
}
