package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Person;

@Repository
public interface CustomerRepository extends JpaRepository<Person,Long> {

	Person getById(long id);
	

	
}
