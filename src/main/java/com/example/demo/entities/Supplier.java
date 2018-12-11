package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Supplier extends Person implements Serializable{

	private String companyName;
	private int productId;
	
	public Supplier(String companyName,int productId,long id,String email, String name,String surname,Address address,Login login) {
		super(id,name,surname,address,login);
		this.companyName = companyName;
		this.productId = productId;
	}

	public Supplier() {
	
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	
}
