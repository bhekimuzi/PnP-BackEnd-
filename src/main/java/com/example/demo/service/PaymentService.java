package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Payment;
import com.example.demo.repository.PaymentRepository;

@Service
public class PaymentService {
@Autowired
private PaymentRepository paymentRepository;

public void save(Payment payment) {
	paymentRepository.save(payment);
}
}
