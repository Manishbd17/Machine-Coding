package com.design.ecommerce.repository;

import java.util.HashMap;

import com.design.ecommerce.exception.EcommerceException;
import com.design.ecommerce.model.Buyer;
import com.design.ecommerce.model.ErrorCode;
import com.design.ecommerce.utils.ErrorCodeMap;

public class BuyerRepository {
	
	HashMap<String,Buyer> buyers; 
	
	public BuyerRepository() {
		buyers = new HashMap<>(); 
	}

	public Buyer createBuyer(Buyer buyer) {
		if(buyers.containsKey(buyer.getBuyerId())) {
			throw new EcommerceException(ErrorCode.BUYER_CREATION_FAILED,ErrorCodeMap.errorCodeStringHashMap.get(ErrorCode.BUYER_CREATION_FAILED));
		}
		buyers.put(buyer.getBuyerId(), buyer); 
		return buyer;
	}

	
	public Buyer getBuyer(String buyerId) {
		return buyers.get(buyerId); 
	}
}
