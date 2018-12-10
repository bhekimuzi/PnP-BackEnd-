package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Aisle implements Serializable{
@Id
@GeneratedValue
private long aisleId;
private String aisleName;
private String image;
private String banner;



public Aisle(long aisleId, String aisleName, String image, String banner) {
	this.aisleId = aisleId;
	this.aisleName = aisleName;
	this.image = image;
	this.banner = banner;
}

public Aisle() {
}


public String getBanner() {
	return banner;
}

public void setBanner(String banner) {
	this.banner = banner;
}

public long getAisleId() {
	return aisleId;
}

public void setAisleId(long aisleId) {
	this.aisleId = aisleId;
}

public String getAisleName() {
	return aisleName;
}

public void setAisleName(String aisleName) {
	this.aisleName = aisleName;
}

public String getImage() {
	return image;
}

public void setImage(String image) {
	this.image = image;
}




}
