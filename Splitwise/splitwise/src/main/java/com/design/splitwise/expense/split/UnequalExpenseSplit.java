package com.design.splitwise.expense.split;

import java.util.List;

import com.design.splitwise.model.Split;

public class UnequalExpenseSplit implements ExpenseSplit {

	@Override
	public void validateSplitRequest(List<Split> splitList, double totalAmount) {
		double amountShouldBePresent = totalAmount/splitList.size(); 
		for(Split split: splitList) {
			if(split.getAmountOwe()==amountShouldBePresent) {
				System.out.println("Owed amount is equal to present amount"); 
			}
		}
		
	}

}
