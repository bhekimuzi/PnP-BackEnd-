package com.example.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Person implements Serializable {
	@Id
	@GeneratedValue
	private long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String surname;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "addressId")
	private Address address;
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "loginId")
	private Login login;
	


	


	public Person() {
	}

	

	public Person(long id, String name, String surname, Address address, Login login) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.login = login;
	}



	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}


}
