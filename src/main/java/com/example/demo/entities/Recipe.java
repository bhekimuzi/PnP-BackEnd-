package com.example.demo.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Recipe {
	@Id
	@GeneratedValue
	private long recipeId;
	private String recipeName;
	private String ingredients;
	private String method;
	private String photo;
	private String banner;
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	private Set<Product> product;
    private String category;
	
	
	public Recipe() {
	}



	
	public Recipe(long recipeId, String recipeName, String ingredients, String method, String photo, String banner,
			Set<Product> product, String category) {
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.ingredients = ingredients;
		this.method = method;
		this.photo = photo;
		this.banner = banner;
		this.product = product;
		this.category = category;
	}




	public String getBanner() {
		return banner;
	}




	public void setBanner(String banner) {
		this.banner = banner;
	}




	public String getCategory() {
		return category;
	}





	public void setCategory(String category) {
		this.category = category;
	}





	public long getRecipeId() {
		return recipeId;
	}


	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
	}


	public String getRecipeName() {
		return recipeName;
	}


	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}


	public String getIngredients() {
		return ingredients;
	}


	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}


	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
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
