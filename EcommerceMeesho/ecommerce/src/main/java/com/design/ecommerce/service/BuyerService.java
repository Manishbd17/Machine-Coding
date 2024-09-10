package com.design.ecommerce.service;

import com.design.ecommerce.exception.EcommerceException;
import com.design.ecommerce.model.Buyer;

public interface BuyerService {
	String addBuyer(Buyer buyer) throws EcommerceException; 
	Buyer getBuyer(String buyerId) throws EcommerceException;
}
