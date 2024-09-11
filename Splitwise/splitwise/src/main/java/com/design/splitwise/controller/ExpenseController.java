package com.design.splitwise.controller;

import java.util.List;

import com.design.splitwise.expense.split.ExpenseSplit;
import com.design.splitwise.factory.SplitFactory;
import com.design.splitwise.model.Expense;
import com.design.splitwise.model.Split;
import com.design.splitwise.model.SplitType;
import com.design.splitwise.model.User;

public class ExpenseController {
	BalanceSheetController balanceSheetController; 
	
	public ExpenseController() {
		balanceSheetController = new BalanceSheetController(); 
	}
	
	public Expense createExpense(String expenseId,String description,double expenseAmount,User paidByUser,SplitType splitType,List<Split> splitList) {
		ExpenseSplit expenseSplit = SplitFactory.getSplitObject(splitType);
		expenseSplit.validateSplitRequest(splitList,expenseAmount);
		Expense expense = new Expense(expenseId,description,expenseAmount,paidByUser,splitType,splitList);
		balanceSheetController.updateUserExpenseBalanceSheet(paidByUser,splitList,expenseAmount);
		return expense; 
	}
}
