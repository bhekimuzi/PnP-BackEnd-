package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class StoreManager extends Person implements Serializable{

	
	public StoreManager() {
	
}
	
	public StoreManager(long id, String name,String surname,Login login,Address address) {
		super(id,name,surname,address,login);
		
	}

	
}
