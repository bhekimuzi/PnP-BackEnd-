package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class DeliveryMan extends Person implements Serializable{

	public DeliveryMan() 
	{
		
	}
	
	public DeliveryMan(long id, String name,String surname,Address address,Login login) {
		super(id,name,surname,address,login);
		
	}
}
