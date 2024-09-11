package com.design.splitwise.model;

public class User {
	private final String userId; 
	private final String userName;
	private UserExpenseBalanceSheet userExpenseBalanceSheet;
	
	public User(String userId,String userName) {
		this.userId = userId; 
		this.userName = userName; 
		userExpenseBalanceSheet = new UserExpenseBalanceSheet();
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}
	
	public UserExpenseBalanceSheet getUserExpenseBalanceSheet() {
		return userExpenseBalanceSheet;
	}

	
}
