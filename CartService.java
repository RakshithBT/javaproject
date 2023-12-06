package com.service;

import java.util.List;
import java.util.Optional;

import com.advice.CartException;
import com.entity.Cart;

public interface CartService {

	
    public Cart updateCart(int cartId, Cart updatedCart) throws CartException;
    
    public List<Cart> getAllCarts()throws CartException;
    
    public Optional<Cart> getCartById(int cartId)throws CartException;
    
    public Optional<Cart> deleteCart(int cartId)throws CartException;
    
    public Cart createCart(Cart cart)throws CartException;
}
