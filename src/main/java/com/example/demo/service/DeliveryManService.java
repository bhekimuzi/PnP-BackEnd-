package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.DeliveryMan;
import com.example.demo.repository.DeliveryManRepository;

@Service
public class DeliveryManService {
	@Autowired
private DeliveryManRepository deliveryManRepository;

public void saveDeliveryMan(DeliveryMan deliveryMan) {
	deliveryManRepository.save(deliveryMan);
}
}
