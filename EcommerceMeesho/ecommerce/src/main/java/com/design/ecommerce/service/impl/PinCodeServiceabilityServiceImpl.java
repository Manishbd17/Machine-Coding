package com.design.ecommerce.service.impl;

import java.util.HashMap;

import com.design.ecommerce.exception.EcommerceException;
import com.design.ecommerce.model.PaymentMode;
import com.design.ecommerce.model.PinCodeServiceability;
import com.design.ecommerce.repository.PinCodeServiceabilityRepository;
import com.design.ecommerce.service.PinCodeServiceabilityService;

public class PinCodeServiceabilityServiceImpl implements PinCodeServiceabilityService{

	PinCodeServiceabilityRepository pinCodeServiceabilityRepository;
	
	public PinCodeServiceabilityServiceImpl(PinCodeServiceabilityRepository pinCodeServiceabilityRepository) {
		this.pinCodeServiceabilityRepository = pinCodeServiceabilityRepository;
	}
	
	@Override
	public Boolean createPinCodeServiceability(String sourcePinCode, String destPinCode, PaymentMode paymentMode)
			throws EcommerceException {
		PinCodeServiceability pinCodeServiceability = new PinCodeServiceability(destPinCode, paymentMode);
		return pinCodeServiceabilityRepository.createPinCodeServiceability(sourcePinCode, pinCodeServiceability); 
	}

	@Override
	public Boolean checkIsSourceAndDestPinCodeMatchesForPaymentType(String sourcePinCode, String destPinCode,
			PaymentMode paymentMode) throws EcommerceException {
		HashMap<String,PaymentMode> allDestPinCodes = pinCodeServiceabilityRepository.getAllDestPinCodes(sourcePinCode);
		return allDestPinCodes.containsKey(destPinCode) && paymentMode.equals(allDestPinCodes.get(destPinCode)); 			
	}

}
