package com.design.splitwise.controller;

import java.util.List;
import java.util.Map;

import com.design.splitwise.model.Balance;
import com.design.splitwise.model.Split;
import com.design.splitwise.model.User;
import com.design.splitwise.model.UserExpenseBalanceSheet;

public class BalanceSheetController {
	
	public void updateUserExpenseBalanceSheet(User expensePaidBy,List<Split> splits,double totalexpenseAmount) {
		UserExpenseBalanceSheet paidByUserExpenseSheet = expensePaidBy.getUserExpenseBalanceSheet(); 
		paidByUserExpenseSheet.setTotalPayment(paidByUserExpenseSheet.getTotalPayment() + totalexpenseAmount);
		
		for(Split split : splits) {
			User userOwe = split.getUser(); 
			UserExpenseBalanceSheet oweUserExpenseBalanceSheet = userOwe.getUserExpenseBalanceSheet(); 
			double amountOwe = split.getAmountOwe(); 
			
			if(expensePaidBy.getUserId().equals(userOwe.getUserId())) {
				paidByUserExpenseSheet.setTotalYourExpense(paidByUserExpenseSheet.getTotalYourExpense() + amountOwe);
			}
			else {
				//update balance of paid user
				paidByUserExpenseSheet.setTotalYouGetBack(paidByUserExpenseSheet.getTotalYouGetBack() + amountOwe);
				
				Balance userOweBalance; 
				if(paidByUserExpenseSheet.getUserVsBalance().containsKey(userOwe.getUserId())) {
					userOweBalance = paidByUserExpenseSheet.getUserVsBalance().get(userOwe.getUserId());
				} else {
					userOweBalance = new Balance(); 
					paidByUserExpenseSheet.getUserVsBalance().put(userOwe.getUserId(), userOweBalance); 
				}
				userOweBalance.setAmountGetBack(userOweBalance.getAmountGetBack() + amountOwe);
				
				
				//update balance of owe user
				oweUserExpenseBalanceSheet.setTotalYouOwe(oweUserExpenseBalanceSheet.getTotalYouOwe() + amountOwe);
				oweUserExpenseBalanceSheet.setTotalYourExpense(oweUserExpenseBalanceSheet.getTotalYourExpense() + amountOwe);
				
				Balance userPaidBalance; 
				if(oweUserExpenseBalanceSheet.getUserVsBalance().containsKey(expensePaidBy.getUserId())) {
					userPaidBalance = oweUserExpenseBalanceSheet.getUserVsBalance().get(expensePaidBy.getUserId()); 
				} else {
					userPaidBalance = new Balance(); 
					oweUserExpenseBalanceSheet.getUserVsBalance().put(expensePaidBy.getUserId(),userPaidBalance); 
				}
				userPaidBalance.setAmountOwe(userPaidBalance.getAmountOwe() + amountOwe);
				
			}
		}
	}
	
	
	
	public void showBalanceSheetOfUser(User user) {
		System.out.println("-----------------------------------------------------------");
		
		System.out.println("Balance sheet of the user: " + user.getUserId()); 
		
		UserExpenseBalanceSheet userExpenseBalanceSheet = user.getUserExpenseBalanceSheet(); 
		System.out.println("TotalYourExpense: " + userExpenseBalanceSheet.getTotalYourExpense());
	    System.out.println("TotalGetBack: " + userExpenseBalanceSheet.getTotalYouGetBack());
	    System.out.println("TotalYourOwe: " + userExpenseBalanceSheet.getTotalYouOwe());
	    System.out.println("TotalPaymentMade: " + userExpenseBalanceSheet.getTotalPayment());
	    
	    for(Map.Entry<String, Balance> entry : userExpenseBalanceSheet.getUserVsBalance().entrySet()) {
	    	String userId = entry.getKey(); 
	    	Balance balance = entry.getValue(); 
	    	System.out.println("userID:" + userId + " YouGetBack:" + balance.getAmountGetBack() + " YouOwe:" + balance.getAmountOwe());
	    }

	    System.out.println("---------------------------------------");
		
	}
	
	
	
}
