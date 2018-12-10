package com.example.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



import com.example.demo.entities.Product;


public class Basket implements Serializable{
	 private List<Product> list = new ArrayList<Product>();

	    public Basket() {
	    }

	    public Basket(List<Product> list) {
	        this.list = list;
	    }
	        
	    
	    public void addProduct(Product product)
	    {
	        list.add(product);
	    
	    }
	    public List<Product> getAll()
	    {
	    return list;
	    }
	    
	    public void removeProduct(int productID)
	    {
	    
	        for(int i = 0; i < list.size();++i)
	        {
	            if (productID == list.get(i).getProductId())
	            {
	                list.remove(i);
	                break;
	            }
	        
	        }
	    }
	    
	     public double total()
	    {
	        double totals = 0;
	        for(int i = 0; i < list.size();++i)
	        {
	            totals+=list.get(i).getNewPrice();
	        }
	        return totals;
	    }
	     public int countQty() {
	    	 int count=0;
	    	 count =list.size();
	    	 return count;
	     }
}
