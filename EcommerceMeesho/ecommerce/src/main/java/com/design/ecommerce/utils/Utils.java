package com.design.ecommerce.utils;

import java.util.UUID;

public class Utils {
	public static String generateId() {
		return UUID.randomUUID().toString(); 
	}
}
