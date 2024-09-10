package com.design.ecommerce.repository;

import java.util.HashMap;

import com.design.ecommerce.exception.EcommerceException;
import com.design.ecommerce.model.ErrorCode;
import com.design.ecommerce.model.Order;
import com.design.ecommerce.utils.ErrorCodeMap;

public class OrderRepository {
	HashMap<String,Order> orders; 
	
	public OrderRepository() {
		orders = new HashMap<>(); 
	}
	
	public Order createOrder(Order order) {
		if(orders.containsKey(order.getOrderId())) {
			throw new EcommerceException(ErrorCode.ORDER_CREATION_FAILED,ErrorCodeMap.errorCodeStringHashMap.get(ErrorCode.PRODUCT_ALREADY_CREATED));
		}
		orders.put(order.getOrderId(), order);
		return order; 
	}
	
	public Order getOrder(String orderId) {
		return orders.get(orderId); 
	}
}
