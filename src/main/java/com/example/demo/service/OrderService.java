package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.ProductOrder;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	
	public void save(ProductOrder productOrder) {
		orderRepository.save(productOrder);
	}
	
	public List<ProductOrder> getAllOrder(){
		
		return orderRepository.findAll();
	}
	public ProductOrder getProductOrder(long id) {
		return orderRepository.getAll(id);
	}
}
