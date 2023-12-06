package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
// // // 
	Optional<Customer> findByUsername(String username);
// // // 
	
	Optional<Customer> findByUsernameAndPasswordAndRole(String username, String password, String role);
}
