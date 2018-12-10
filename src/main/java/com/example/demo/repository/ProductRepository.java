package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Product;
import com.example.demo.entities.Category;


public interface ProductRepository extends JpaRepository<Product,Long>{
	 @Query("select p FROM Product p WHERE p.category.categoryId =:categoryId")
	 List<Product> getAll(long categoryId);
	 @Query("select p FROM Product p WHERE p.save !=:number")
	 List<Product>  getBySave(double number);
	 @Query("select p FROM Product p WHERE p.recipe.recipeId !=:recipeId")
	 List<Product>  getByRecipe(long recipeId);
	 
	 @Query("Select p from Product p where p.brandName like CONCAT('%',:search,'%') Or p.productName like CONCAT('%',:search,'%')")
	 List<Product> search(String search);
	 @Query("select p FROM Product p WHERE p.storeQuantity <=:number")
	 List<Product> getByStoreQuantity(int number);

}

