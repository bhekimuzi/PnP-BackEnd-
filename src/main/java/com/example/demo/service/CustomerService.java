package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.demo.entities.Customer;
import com.example.demo.entities.Login;
import com.example.demo.entities.Person;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository userRepository;
	
	
	
	public void createUser(Person person) {
		
		userRepository.save(person);
		
		
	}
	
	public void createAdmin(Customer customer) {
	
		userRepository.save(customer);
		
		
	}
	
	public Person validateUser(String email){
	
	List<Person> list = userRepository.findAll();
	Person person=null;
	for(int i=0;i<list.size();i++) {
		if(email.equals(list.get(i).getLogin().getEmail())) {
			
			person= list.get(i);
			break;
		}
		
	}
		return person;
		
	}
	
	public Person login(Login login) {
		Person person =null;
		person =login(login.getEmail(),login.getPassword());
		
		return person;
	}
	
	public Person login(String username,String password) {
		Person person =validateUser(username);
		if(person.getLogin().getPassword().equals(password)) {
			
			return person;
		}
		
		return null;
	}
	
	public void delete(Person person) {
		
		userRepository.delete(person);
	}

	public Person getById(long id) {
		
		return userRepository.getById(id);
	}
	
	public void update(Person person) {
		
		userRepository.saveAndFlush(person);
	}
	public List<Person> getAll(){
		
		return userRepository.findAll();
	}
}
