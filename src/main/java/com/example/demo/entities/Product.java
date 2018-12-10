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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import com.fasterxml.jackson.annotation.JsonIgnore;





@Entity

public class Product implements Serializable{

	@Id
	@GeneratedValue
	private long productId;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoryId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

	private String productName;
	private double originPrice =0.0;
	private double save = 0.0;
	private double newPrice=0.0;
	private int quantity =0;
	private int storeQuantity;
	private String image;

	private String brandName;
	 @ManyToOne
	    @JoinColumn(name = "recipeId")
	private Recipe recipe;
	 @ManyToOne
	    @JoinColumn(name = "competitionId")
	private Competition competition;
	
	





	public Product(String productName, Recipe recipe,Category category) {
		this.productName = productName;
		this.recipe = recipe;
		this.category =category;
	}


	public Product() {
	}


public Product(long productId, String productName, double originPrice, double save,
			double newPrice, int quantity, String image, String brandName,Category category,Recipe recipe,Competition competition,int storeQuantity) {
		this.productId = productId;
		
		this.productName = productName;
		this.originPrice = originPrice;
		this.save = save;
		this.newPrice = newPrice;
		this.quantity = quantity;
		this.image = image;
		this.brandName = brandName;
		this.category =category;
		this.recipe = recipe;
		this.competition = competition;
		this.storeQuantity = storeQuantity;
	}





public int getStoreQuantity() {
	return storeQuantity;
}


public void setStoreQuantity(int storeQuantity) {
	this.storeQuantity = storeQuantity;
}


public void setCompetition(Competition competition) {
	this.competition = competition;
}


public void setRecipe(Recipe recipe) {
	this.recipe = recipe;
}


public String getBrandName() {
	return brandName;
}


public void setBrandName(String brandName) {
	this.brandName = brandName;
}


public double calculateNewPrice(double originPrice,double save) {
	return originPrice-save;
}

	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	

	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getOriginPrice() {
		return originPrice;
	}

	public void setOriginPrice(double originPrice) {
		this.originPrice = originPrice;
	}

	public double getSave() {
		return save;
	}

	public void setSave(double save) {
		this.save = save;
	}

	public double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
}
