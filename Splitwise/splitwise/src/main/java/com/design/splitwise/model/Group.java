package com.design.splitwise.model;

import java.util.ArrayList;
import java.util.List;

import com.design.splitwise.controller.ExpenseController;

public class Group {
	private final String groupId; 
	private final String groupName;
	List<User> groupMembers;
	List<Expense> expenseList;
	ExpenseController expenseController ;
	
	public void addMember(User member) {
		groupMembers.add(member); 
	}

	public String getGroupId() {
		return groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public Group(String groupId,String groupName) {
		this.groupId = groupId; 
		this.groupName = groupName; 
		groupMembers = new ArrayList<>(); 
		expenseList = new ArrayList<>();
		expenseController = new ExpenseController();
	}
	
	public Expense createExpense(String expenseId,String desc,double expenseAmount,List<Split> splitList,SplitType splitType,User paidByUser) {
		Expense expense =expenseController.createExpense(expenseId,desc,expenseAmount,paidByUser,splitType,splitList);
		expenseList.add(expense);
		return expense; 
	}
	
}
