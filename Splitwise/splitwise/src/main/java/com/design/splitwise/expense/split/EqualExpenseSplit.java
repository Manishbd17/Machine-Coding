package com.design.splitwise.expense.split;

import java.util.List;

import com.design.splitwise.model.Split;

public class EqualExpenseSplit implements ExpenseSplit {

	@Override
	public void validateSplitRequest(List<Split> splitList, double totalAmount) {
		double amountShouldBePresent = totalAmount/splitList.size(); 
		for(Split split: splitList) {
			if(split.getAmountOwe() != amountShouldBePresent) {
				System.out.print("Owed Amount is not equal to the present amount");
			}
		}
		
	}

}
