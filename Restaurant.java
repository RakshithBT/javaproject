package com.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class Restaurant extends AppUser{

	 @NotBlank(message = "Restaurant name is required")
	    @Size(max = 100, message = "Restaurant name must be less than or equal to 100 characters")
	private String restaurantName;
	 
	 @NotBlank(message = "Location is required")
	    @Size(max = 255, message = "Location must be less than or equal to 255 characters")
    private String location;
	 
	 @NotNull(message = "Contact number is required")
    private Long contactNo;
	 
	  @Email(message = "Invalid email format")
	    @NotBlank(message = "Email is required")
	    @Size(max = 100, message = "Email must be less than or equal to 100 characters")
    private String emailId;

    @OneToMany(mappedBy = "restaurant")
    @JsonBackReference // To avoid circular reference
    private List<FoodItems> foodItems;
  

}
