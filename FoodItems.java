package com.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class FoodItems {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private String itemName;
	    private int itemPrice;
	    private int itemQuantity;

	    @ManyToOne
	    //@JsonBackReference // To avoid circular reference
	    private Restaurant restaurant;

	    @ManyToOne
	    @JoinColumn(name = "category_id") // Map to the category_id column in the FoodItems table
	    private Category category;
	    
	    @ManyToMany(mappedBy = "foodItems")
	    @JsonBackReference // To avoid circular reference
	    private List<Orders> orders;

}

