package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address implements Serializable{
	@Id
	@GeneratedValue
	private long addressId;
	private String cellNumber;
	private String city;
	private String streetAddress;
	private String suburb;
	private String postalCode;
	private String province;
	
	
	public Address() {
	}


	public Address(long addressId, String cellNumber, String city, String streetAddress, String suburb,
			String postalCode, String province) {
		this.addressId = addressId;
		this.cellNumber = cellNumber;
		this.city = city;
		this.streetAddress = streetAddress;
		this.suburb = suburb;
		this.postalCode = postalCode;
		this.province = province;
	}


	public long getAddressId() {
		return addressId;
	}


	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}


	public String getCellNumber() {
		return cellNumber;
	}


	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getStreetAddress() {
		return streetAddress;
	}


	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}


	public String getSuburb() {
		return suburb;
	}


	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}


	public String getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}
	
	
	
}
