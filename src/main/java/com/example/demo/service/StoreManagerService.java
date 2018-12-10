package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Customer;
import com.example.demo.entities.StoreManager;
import com.example.demo.repository.StoreManagerRepository;

@Service
public class StoreManagerService {
@Autowired
	private StoreManagerRepository storeManagerRepository;
	
	public void saveStoreManager(StoreManager storeManager) {
		
		storeManagerRepository.save(storeManager);
	}
	public StoreManager validateStoreManager(String email){
		
	List<StoreManager> list = storeManagerRepository.findAll();
	StoreManager storeManager=null;
	for(int i=0;i<list.size();i++) {
		if(email.equals(list.get(i).getLogin().getEmail())) {
			
			storeManager= list.get(i);
		}
		
	}
		return storeManager;
		
	}
}
