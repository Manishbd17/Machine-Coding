package com.design.ecommerce.utils;

import java.util.HashMap;

import com.design.ecommerce.model.ErrorCode;

public class ErrorCodeMap {
	public static HashMap<ErrorCode,String> errorCodeStringHashMap = new HashMap<>(); 
	static {
		errorCodeStringHashMap.put(ErrorCode.BUYER_CREATION_FAILED, "Buyer Already Created"); 
		errorCodeStringHashMap.put(ErrorCode.ORDER_CREATION_FAILED, "Order Already Created");
        errorCodeStringHashMap.put(ErrorCode.PRODUCT_CREATION_FAILED, "Product Creation Failed");
        errorCodeStringHashMap.put(ErrorCode.PRODUCT_ALREADY_CREATED, "Product Duplicate Exception");
        errorCodeStringHashMap.put(ErrorCode.IN_SUFFICIENT_INVENTORY, "Order Failed because product stock is in-sufficient");
        errorCodeStringHashMap.put(ErrorCode.PIN_CODE_UNSERVICEABLE, "Order failed because pin-code is unserviceable");
	}
}
