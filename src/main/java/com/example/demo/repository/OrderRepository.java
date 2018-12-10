package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Category;
import com.example.demo.entities.ProductOrder;



public interface OrderRepository extends JpaRepository<ProductOrder,Long>{
	 @Query("select c FROM ProductOrder c WHERE c.customer.id =:id")
	 ProductOrder getAll(long id);
}
