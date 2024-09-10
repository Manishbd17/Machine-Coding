package com.design.ecommerce.repository;

import java.util.HashMap;

import com.design.ecommerce.model.PaymentMode;
import com.design.ecommerce.model.PinCodeServiceability;

public class PinCodeServiceabilityRepository {
	HashMap<String,HashMap<String,PaymentMode>> pinCodes; 
	
	public PinCodeServiceabilityRepository() { 
		pinCodes = new HashMap<>(); 
	}
	
	public Boolean createPinCodeServiceability(String sourcePinCode,PinCodeServiceability pinCodeServiceability) {
		if(pinCodes.get(sourcePinCode) == null) {
			HashMap<String,PaymentMode> destPinCodes = new HashMap<>(); 
			destPinCodes.put(pinCodeServiceability.getDestPinCode(), pinCodeServiceability.getPaymentMode()); 
			pinCodes.put(sourcePinCode, destPinCodes); 
		}
		pinCodes.get(sourcePinCode).put(pinCodeServiceability.getDestPinCode(), pinCodeServiceability.getPaymentMode()); 
		return true; 
	}
	
	
	public HashMap<String,PaymentMode> getAllDestPinCodes(String sourcePinCode) {
		return pinCodes.get(sourcePinCode);
	}
}
