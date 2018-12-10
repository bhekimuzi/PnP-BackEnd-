package com.example.demo.entities;

import java.util.Comparator;



public class SortBySave implements Comparator<Product>{

	@Override
	public int compare(Product a, Product b) {
		// TODO Auto-generated method stub
		return (int)(a.getSave() -b.getSave());
	}

}
