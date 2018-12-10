package com.example.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Customer extends Person implements Serializable{

	
	private boolean smartShopper;



	

	public Customer(boolean smartShopper,List<Product> list,long id, String name,String surname,Login login,Address address) {
		super(id,name,surname,address,login);
	
		this.smartShopper =smartShopper;
		
	}
	
	

	public Customer() {
		super();
	}

	public boolean isSmartShopper() {
		return smartShopper;
	}

	public void setSmartShopper(boolean smartShopper) {
		this.smartShopper = smartShopper;
	}
	
	
}
