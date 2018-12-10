package com.example.demo.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Person;
import com.example.demo.entities.Product;
import com.example.demo.entities.SortBySave;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
private ProductRepository productRepository;


public void saveProduct(Product product) {
	
	productRepository.save(product);
}

public List<Product> getAllProduct(){
	return productRepository.findAll();
	
}
public Product getProduct(long productId) {
	
	return productRepository.findOne(productId);
}

public List<Product> getAll(long categoryId){
	return productRepository.getAll(categoryId);
}

public List<Product> getBySave(){
	double number =0.0;
	return productRepository.getBySave(number);
}
public List<Product> sort() {
	List<Product> list = getBySave();
	for(int i=0;i<list.size();i++) {
		Collections.sort(list, new SortBySave());
		Collections.reverse(list);
	}
	return list;
}
public void delete(Product product) {
	
	productRepository.delete(product);
}
public void update(Product product) {
	productRepository.saveAndFlush(product);
}
public List<Product> getByRecipe(long recipeId){
	return productRepository.getByRecipe(recipeId);
}

public List<Product> search(String search)
{
return productRepository.search(search);	
}
public List<Product> getByStoreQuantity(){
	int number =20;
	return productRepository.getByStoreQuantity(number);
}
}
