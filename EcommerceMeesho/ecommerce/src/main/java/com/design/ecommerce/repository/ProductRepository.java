package com.design.ecommerce.repository;

import java.util.HashMap;

import com.design.ecommerce.exception.EcommerceException;
import com.design.ecommerce.model.ErrorCode;
import com.design.ecommerce.model.Product;
import com.design.ecommerce.utils.ErrorCodeMap;

public class ProductRepository {
	HashMap<String,Product> products;
	
	public ProductRepository() {
		products = new HashMap<>(); 
	}
	
	public Product createProduct(Product product) {
		if(products.containsKey(product.getProductId())) {
			throw new EcommerceException(ErrorCode.PRODUCT_ALREADY_CREATED,ErrorCodeMap.errorCodeStringHashMap.get(ErrorCode.PRODUCT_ALREADY_CREATED));
		}
		products.put(product.getProductId(), product); 
		return product; 
	}
	
	public Product getProduct(String productId) {
		return products.get(productId); 
	}
	
	
	
	
}
