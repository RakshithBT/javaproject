package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import javax.persistence.Id;

import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "app_users")
@Data
public class AppUser {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	 
	    private int userId;
	
	 
	 @NotBlank(message = "Username is required")
	    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	    private String username;
	
	 @NotBlank(message = "Password is required")
	    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
	    private String password;
	
	 @NotBlank(message = "Role is required")
	    @Size(max = 50, message = "Role must be less than or equal to 50 characters")
	    private String role;
	   
}
