package com.design.splitwise.expense.split;

import java.util.List;

import com.design.splitwise.model.Split;

public interface ExpenseSplit {
	public void validateSplitRequest(List<Split> splitList, double totalAmount);
}
