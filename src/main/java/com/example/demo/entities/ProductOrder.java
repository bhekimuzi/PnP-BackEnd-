package com.example.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProductOrder implements Serializable
{
@Id
@GeneratedValue
	private long orderId;
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "id", nullable = false)
@OnDelete(action = OnDeleteAction.CASCADE)
@JsonIgnore
private Customer customer;

private String address;
private String apartment;
private String addressNickName;
private String date;
private boolean mydefault;






public ProductOrder() {
}





public ProductOrder(long orderId, Customer customer, String address, String apartment,
		String addressNickName, String date, boolean mydefault) {
	this.orderId = orderId;
	this.customer = customer;

	this.address = address;
	this.apartment = apartment;
	this.addressNickName = addressNickName;
	this.date = date;
	this.mydefault = mydefault;
}





public boolean isMydefault() {
	return mydefault;
}



public void setMydefault(boolean mydefault) {
	this.mydefault = mydefault;
}


public long getOrderId() {
	return orderId;
}



public void setOrderId(long orderId) {
	this.orderId = orderId;
}



public Customer getCustomer() {
	return customer;
}



public void setCustomer(Customer customer) {
	this.customer = customer;
}







public String getAddress() {
	return address;
}



public void setAddress(String address) {
	this.address = address;
}



public String getApartment() {
	return apartment;
}



public void setApartment(String apartment) {
	this.apartment = apartment;
}



public String getAddressNickName() {
	return addressNickName;
}



public void setAddressNickName(String addressNickName) {
	this.addressNickName = addressNickName;
}



public String getDate() {
	return date;
}



public void setDate(String date) {
	this.date = date;
}





}
