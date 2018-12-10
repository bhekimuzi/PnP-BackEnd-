package com.example.demo.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Recipe;
import com.example.demo.repository.RecipeRepository;

@Service
public class RecipeService {
@Autowired
private RecipeRepository recipeRepository;

public void save(HashSet<Recipe> hashSet) {
	recipeRepository.save(hashSet);
}

public List<Recipe> getByCategory(){
	return recipeRepository.findAll();
}

public Recipe getRecipe(long recipeId) {
	return recipeRepository.findOne(recipeId);
}
}
