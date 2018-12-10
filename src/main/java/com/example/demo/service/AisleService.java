package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Aisle;
import com.example.demo.repository.AisleRepository;

@Service
public class AisleService {
@Autowired
private AisleRepository aisleRepository;

public void save(Aisle aisle) {
	aisleRepository.save(aisle);
}
public List<Aisle> getAll(){
	return aisleRepository.findAll();
}
public Aisle getById(long aisleId) {
	return aisleRepository.findOne(aisleId);
}
}
