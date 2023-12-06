package com.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advice.CartException;
import com.entity.Cart;
import com.repository.CartRepository;
import com.service.CartService;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;
//
//    public List<Cart> getAllCarts() {
//        return cartRepository.findAll();
//    }
//
//    public Optional<Cart> getCartById(int cartId) {
//        return cartRepository.findById(cartId);
//    }

//    public Cart createCart(Cart cart) {
//        return cartRepository.save(cart);
//    }

//    public void deleteCart(int cartId) {
//        cartRepository.deleteById(cartId);
//    }
//    
    
    public List<Cart> getAllCarts() throws CartException{
		List<Cart> carts = cartRepository.findAll();  
	    if (carts.isEmpty()) {
	        throw new CartException("No Carts found");
	    }
	    else {
	    return carts;
	}
	}
    
    
    public Optional<Cart> getCartById(int cartId) throws CartException{
    	Optional<Cart> carts = cartRepository.findById(cartId);
		if(!carts.isEmpty()) {
		return carts;
	}
		else {
			throw new CartException("Cart with "+ cartId +" Not Found");
		}
	}
    
    public Optional<Cart> deleteCart(int cartId) throws CartException{
    	Optional<Cart> carts = cartRepository.findById(cartId);
		if(!carts.isEmpty()) {
		return carts;
	}
		else {
			throw new CartException("Cart with "+ cartId +" Not Found");
		}
	}
    
    
    public Cart createCart(Cart cart) throws CartException {
        try {
            return cartRepository.save(cart);
        } catch (Exception e) {
            throw new CartException("Failed to create cart: " + e.getMessage());
        }
    }
    public Cart updateCart(int cartId, Cart updatedCart) throws CartException {
        if (!cartRepository.existsById(cartId)) {
            throw new CartException("Cart not found");
        }
        updatedCart.setCartId(cartId);
        return cartRepository.save(updatedCart);
    }
    
  

}
