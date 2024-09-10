package com.design.ecommerce.service.impl;

import com.design.ecommerce.exception.EcommerceException;
import com.design.ecommerce.model.Buyer;
import com.design.ecommerce.repository.BuyerRepository;
import com.design.ecommerce.service.BuyerService;

public class BuyerServiceImpl implements BuyerService {

	BuyerRepository buyerRepository;  
	
	public BuyerServiceImpl(BuyerRepository buyerRepository) {
		this.buyerRepository = buyerRepository; 
	}
	
	@Override
	public String addBuyer(Buyer buyer) throws EcommerceException {
		Buyer createdBuyer = buyerRepository.createBuyer(buyer); 
		return createdBuyer.getBuyerId(); 
	}

	@Override
	public Buyer getBuyer(String buyerId) throws EcommerceException {
		return buyerRepository.getBuyer(buyerId); 
	}

}
