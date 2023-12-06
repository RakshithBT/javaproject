package com.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Orders {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int orderId;
	  @NotNull(message = "Date is required")
	    private LocalDateTime date;

	    @NotBlank(message = "Status is required")
	    @Size(max = 255, message = "Status must be less than or equal to 255 characters")
	    private String status;

	    @ManyToOne
	    @JsonIgnore // Add this annotation to break the loop
	    @JoinColumn(name = "customer_id")
	    //@JsonBackReference // To avoid circular reference
	    private Customer customer;
	    
	    @ManyToOne
	    @JsonIgnore // Add this annotation to break the loop
	    @JoinColumn(name = "cart_id")
	    private Cart cart;
	  
	    @ManyToMany
	    @JsonIgnore // Add this annotation to break the loop
	    @JoinTable(
	        name = "order_fooditems",
	        joinColumns = @JoinColumn(name = "order_id"),
	        inverseJoinColumns = @JoinColumn(name = "fooditem_id")
	    )
	    private List<FoodItems> foodItems;
	}

	    


