package com.design.ecommerce.service.impl;

import com.design.ecommerce.exception.EcommerceException;
import com.design.ecommerce.model.ErrorCode;
import com.design.ecommerce.model.Product;
import com.design.ecommerce.repository.ProductRepository;
import com.design.ecommerce.service.ProductService;
import com.design.ecommerce.utils.ErrorCodeMap;

public class ProductServiceImpl implements ProductService {

	ProductRepository productRepository; 
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository; 
	}
	
	@Override
	public String addProduct(Product product) throws EcommerceException {
		Product createdProduct = productRepository.createProduct(product); 
		return createdProduct.getProductId(); 
	}

	@Override
	public Product getProduct(String productId) throws EcommerceException {
		return productRepository.getProduct(productId); 
	}

	@Override
	public boolean checkInventory(int orderedQuantity, String productId) throws EcommerceException {
		synchronized(this) {
			Product product = productRepository.getProduct(productId); 
			if(orderedQuantity <= product.getProductQuantity()) {
				product.setProductQuantity(product.getProductQuantity()-orderedQuantity);
				return true; 
			} else {
				throw new EcommerceException(ErrorCode.IN_SUFFICIENT_INVENTORY,ErrorCodeMap.errorCodeStringHashMap.get(ErrorCode.IN_SUFFICIENT_INVENTORY));
			}
		}
	}

}
