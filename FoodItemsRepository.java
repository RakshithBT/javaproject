package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Category;
import com.entity.FoodItems;

@Repository
public interface FoodItemsRepository extends JpaRepository<FoodItems, Integer> {

	 List<FoodItems> findByCategory(Category category);

	 List<FoodItems> findByItemName(String itemName); 
	 
}
