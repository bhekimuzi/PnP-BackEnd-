package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Payment implements Serializable{
@Id
@GeneratedValue
private long paymentId;
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@OnDelete(action = OnDeleteAction.CASCADE)
@JsonIgnore
private ProductOrder productOrder;
private String cardHolderName;
private int cardNumber;
private int cvc;
private String validDates;
@Lob
private byte[] receipt;

public Payment() {
}



public Payment(long paymentId, ProductOrder productOrder, String cardHolderName, int cardNumber, int cvc,
		String validDates, byte[] receipt) {
	this.paymentId = paymentId;
	this.productOrder = productOrder;
	this.cardHolderName = cardHolderName;
	this.cardNumber = cardNumber;
	this.cvc = cvc;
	this.validDates = validDates;
	this.receipt = receipt;
}



public byte[] getReceipt() {
	return receipt;
}



public void setReceipt(byte[] receipt) {
	this.receipt = receipt;
}



public long getPaymentId() {
	return paymentId;
}

public void setPaymentId(long paymentId) {
	this.paymentId = paymentId;
}

public ProductOrder getProductOrder() {
	return productOrder;
}

public void setProductOrder(ProductOrder productOrder) {
	this.productOrder = productOrder;
}

public String getCardHolderName() {
	return cardHolderName;
}

public void setCardHolderName(String cardHolderName) {
	this.cardHolderName = cardHolderName;
}

public int getCardNumber() {
	return cardNumber;
}

public void setCardNumber(int cardNumber) {
	this.cardNumber = cardNumber;
}

public int getCvc() {
	return cvc;
}

public void setCvc(int cvc) {
	this.cvc = cvc;
}

public String getValidDates() {
	return validDates;
}

public void setValidDates(String validDates) {
	this.validDates = validDates;
}





}
