package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Category;
import com.example.demo.entities.Product;

public interface CategoryRepository extends JpaRepository<Category,Long>{
	 @Query("select c FROM Category c WHERE c.aisle.aisleId =:aisleId")
	 List<Category> getAll(long aisleId);
}
