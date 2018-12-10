package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Login implements Serializable{
	
@Id
@GeneratedValue
private long loginId;
@NotEmpty
private String password;
@NotEmpty
@Column(unique=true)
private String email;
private String role;

public Login() {
}


public Login(String password, String email) {
	this.password = password;
	this.email = email;
}


public Login(long loginId, String password, String email, String role) {
	this.loginId = loginId;
	this.password = password;
	this.email = email;
	this.role = role;
}


public long getLoginId() {
	return loginId;
}


public void setLoginId(long loginId) {
	this.loginId = loginId;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String getRole() {
	return role;
}


public void setRole(String role) {
	this.role = role;
}


}
