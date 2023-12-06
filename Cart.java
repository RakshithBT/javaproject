package com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;


import lombok.Data;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    private int cartQuantity;
    private double totalprice;

    @ManyToOne
    //@JsonBackReference // To avoid circular reference
    @JoinColumn(name = "customer_id")
   // @JsonIgnore // Add this annotation to break the loop
    private Customer customer;

    //
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();
    //
    
    @ManyToMany
    @JsonBackReference // To avoid circular reference
    @JoinTable(name = "cart_fooditems",
        joinColumns = @JoinColumn(name = "cart_id"),
        inverseJoinColumns = @JoinColumn(name = "fooditem_id"))
    private List<FoodItems> fooditems;
    
    
    // // // // 
   
    // // // //
    
    
}
