package com.design.ecommerce.service.impl;

import com.design.ecommerce.exception.EcommerceException;
import com.design.ecommerce.model.ErrorCode;
import com.design.ecommerce.model.Order;
import com.design.ecommerce.repository.OrderRepository;
import com.design.ecommerce.service.BuyerService;
import com.design.ecommerce.service.OrderService;
import com.design.ecommerce.service.PinCodeServiceabilityService;
import com.design.ecommerce.service.ProductService;
import com.design.ecommerce.utils.ErrorCodeMap;

public class OrderServiceImpl implements OrderService {

	OrderRepository orderRepository; 
	ProductService productService; 
	BuyerService buyerService;
	PinCodeServiceabilityService pinCodeServiceabilityService;
	
	public OrderServiceImpl(OrderRepository orderRepository,ProductService productService,BuyerService buyerService,
	PinCodeServiceabilityService pinCodeServiceabilityService) {
		this.orderRepository = orderRepository; 
		this.productService = productService; 
		this.buyerService = buyerService;
		this.pinCodeServiceabilityService = pinCodeServiceabilityService; 
	}
	
	
	@Override
	public String addOrder(Order order) throws EcommerceException {
		final String sourcePincode = productService.getProduct(order.getProductId()).getAddress().getPinCode(); 
		final String destPincode = buyerService.getBuyer(order.getBuyerId()).getAddress().getPinCode(); 
		if(!pinCodeServiceabilityService.checkIsSourceAndDestPinCodeMatchesForPaymentType(sourcePincode, destPincode, order.getPaymentMode())) {
			throw new EcommerceException(ErrorCode.PIN_CODE_UNSERVICEABLE,ErrorCodeMap.errorCodeStringHashMap.get(ErrorCode.PIN_CODE_UNSERVICEABLE));
		}
		if(productService.checkInventory(order.getQuantity(), order.getProductId())) {
			Order createdOrder = orderRepository.createOrder(order); 
			return createdOrder.getOrderId(); 
		}
		throw new EcommerceException(ErrorCode.ORDER_CREATION_FAILED,ErrorCodeMap.errorCodeStringHashMap.get(ErrorCode.ORDER_CREATION_FAILED));
	}

	@Override
	public Order getOrder(String orderId) throws EcommerceException {
		return orderRepository.getOrder(orderId); 
	}

}
