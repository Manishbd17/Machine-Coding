package com.design.ecommerce.model;

public class Address {
	private final String city; 
	private final String address1; 
	private final String pinCode; 
	
	public Address(String address1, String city, String pinCode) {
        this.address1 = address1;
        this.city = city;
        this.pinCode = pinCode;
	 }

    public String getAddress1() {
        return address1;
    }

    public String getCity() {
        return city;
    }

    public String getPinCode() {
        return pinCode;
    }
}
