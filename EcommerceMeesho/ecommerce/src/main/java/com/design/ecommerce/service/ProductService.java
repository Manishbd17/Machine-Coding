package com.design.ecommerce.service;

import com.design.ecommerce.exception.EcommerceException;
import com.design.ecommerce.model.Product;

public interface ProductService {
	String addProduct(Product product) throws EcommerceException; 
	Product getProduct(String  productId) throws EcommerceException; 
	boolean checkInventory(int orderedQuantity,String productId) throws EcommerceException;
}
