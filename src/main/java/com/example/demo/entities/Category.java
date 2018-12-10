package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category implements Serializable {
	@Id
	@GeneratedValue
	private long categoryId;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "aisleId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Aisle aisle;
	private String categoryName;
	private String image;



	public Aisle getAisle() {
		return aisle;
	}

	public void setAisle(Aisle aisle) {
		this.aisle = aisle;
	}

	

	public Category(long categoryId, Aisle aisle, String categoryName, String image) {
		this.categoryId = categoryId;
		this.aisle = aisle;
		this.categoryName = categoryName;
		this.image = image;
	}

	public Category() {

	}
	

	

	public long getCategoryId() {
		return categoryId;
	}

	

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
