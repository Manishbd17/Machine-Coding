package com.design.ecommerce.service;

import com.design.ecommerce.exception.EcommerceException;
import com.design.ecommerce.model.Order;

public interface OrderService {
	String addOrder(Order order) throws EcommerceException; 
	Order getOrder(String orderId) throws EcommerceException; 
}
