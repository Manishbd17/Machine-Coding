package com.design.ecommerce.service;

import com.design.ecommerce.exception.EcommerceException;
import com.design.ecommerce.model.PaymentMode;

public interface PinCodeServiceabilityService {
	Boolean createPinCodeServiceability(String sourcePinCode,
            String destPinCode,
            PaymentMode paymentMode) throws EcommerceException; 
	Boolean checkIsSourceAndDestPinCodeMatchesForPaymentType(String sourcePinCode,
            String destPinCode,
            PaymentMode paymentMode) throws EcommerceException; 
}
