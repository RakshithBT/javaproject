package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advice.CartException;
import com.entity.Cart;
import com.serviceImpl.CartServiceImpl;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @GetMapping
    public List<Cart> getAllCarts() throws Throwable {
        return cartServiceImpl.getAllCarts();
    }

    @GetMapping("/{cartId}")
    public Optional<Cart> getCartById(@PathVariable int cartId) throws Throwable {
        return cartServiceImpl.getCartById(cartId);
    }

    @PostMapping("/create")
    public Cart createCart(@RequestBody Cart cart) throws CartException {
        return cartServiceImpl.createCart(cart);
    }

    @PutMapping("/{cartId}")
    public Cart updateCart(@PathVariable int cartId, @RequestBody Cart updatedCart) throws CartException {
        return cartServiceImpl.updateCart(cartId, updatedCart);
    }


    
    @DeleteMapping("/{cartId}")
    public void deleteCart(@PathVariable int cartId) throws Throwable {
        cartServiceImpl.deleteCart(cartId);
    }
}
