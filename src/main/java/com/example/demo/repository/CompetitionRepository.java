package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Category;
import com.example.demo.entities.Competition;

public interface CompetitionRepository extends JpaRepository<Competition,Long>{

}
