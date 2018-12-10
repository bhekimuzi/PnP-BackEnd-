package com.example.demo.service;


import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Competition;
import com.example.demo.entities.Recipe;
import com.example.demo.repository.CompetitionRepository;

@Service
public class CompetitionService {
	
	@Autowired
	private CompetitionRepository competitionRepository;
public void save(HashSet<Competition> hashSet) {
	competitionRepository.save(hashSet);
}
public List<Competition> getAllFind(){
	return competitionRepository.findAll();
}

public Competition getById(long competitionId) {
	return competitionRepository.findOne(competitionId);
}
}
