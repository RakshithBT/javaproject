package com.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Customer extends AppUser {
	
	 @NotBlank(message = "First name is required")
	    @Size(max = 50, message = "First name must be less than or equal to 50 characters")
	 private String customerFirstName;
	 
	 @NotBlank(message = "Last name is required")
	    @Size(max = 50, message = "Last name must be less than or equal to 50 characters")
	    private String customerLastName;
	 
	 @NotBlank(message = "Address is required")
	    @Size(max = 255, message = "Address must be less than or equal to 255 characters")
	    private String address;
	 
	 @NotNull(message = "Mobile number is required")
	    private Long mobilenumber;
	 
	  @Email(message = "Invalid email format")
	    @NotBlank(message = "Email is required")
	    @Size(max = 100, message = "Email must be less than or equal to 100 characters")
	    private String emailId;

	    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	    @JsonBackReference // To avoid circular reference
	    private List<Orders> orders;
	    
	    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	    @JsonIgnore 
	    private List<Cart> carts;

}

