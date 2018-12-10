package com.example.demo.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Competition {
@Id
@GeneratedValue
private long competitionId;
private String name;
private String description;
private String EndDate;
private String photo;
private String banner;
@OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
private Set<Product> product;





public Competition() {
}


public Competition(long competitionId, String name, String description, String endDate, String photo, String banner,
		Set<Product> product) {
	this.competitionId = competitionId;
	this.name = name;
	this.description = description;
	EndDate = endDate;
	this.photo = photo;
	this.banner = banner;
	this.product = product;
}


public String getBanner() {
	return banner;
}


public void setBanner(String banner) {
	this.banner = banner;
}


public long getCompetitionId() {
	return competitionId;
}
public void setCompetitionId(long competitionId) {
	this.competitionId = competitionId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getEndDate() {
	return EndDate;
}
public void setEndDate(String endDate) {
	EndDate = endDate;
}
public String getPhoto() {
	return photo;
}
public void setPhoto(String photo) {
	this.photo = photo;
}
public Set<Product> getProduct() {
	return product;
}
public void setProduct(Set<Product> product) {
	this.product = product;
}
	

	
}
