package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Payment;
import com.example.demo.entities.Product;
import com.example.demo.entities.ProductOrder;

public interface PaymentRepository extends JpaRepository<Payment,Long>{
	
}
