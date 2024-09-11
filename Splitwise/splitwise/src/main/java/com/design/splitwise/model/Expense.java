package com.design.splitwise.model;

import java.util.ArrayList;
import java.util.List;

public class Expense {
	private final String expenseId;
	private final String description;
	private final double expenseAmount; 
	private User paidByUser;
	private SplitType splitType;
	private List<Split> splitList = new ArrayList<>();
	
	public Expense(String expenseId,String description,double expenseAmount,User paidByUser,SplitType splitType,List<Split> splitList) {
		this.expenseId = expenseId;
		this.description = description; 
		this.expenseAmount = expenseAmount; 
		this.paidByUser = paidByUser; 
		this.splitType = splitType;
		this.splitList.addAll(splitList); 
	}
	
}
