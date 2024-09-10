package com.design.ecommerce.model;

public class PinCodeServiceability {
	private final String destPinCode; 
	private final PaymentMode paymentMode;
	
	public PinCodeServiceability(String destPinCode, PaymentMode paymentMode) {
        this.destPinCode = destPinCode;
        this.paymentMode=paymentMode;
    }
	
	public String getDestPinCode() {
		return destPinCode;
	}
	public PaymentMode getPaymentMode() {
		return paymentMode;
	} 
	

}
