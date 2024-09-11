package com.design.splitwise.factory;

import com.design.splitwise.expense.split.EqualExpenseSplit;
import com.design.splitwise.expense.split.ExpenseSplit;
import com.design.splitwise.expense.split.PercentageExpenseSplit;
import com.design.splitwise.expense.split.UnequalExpenseSplit;
import com.design.splitwise.model.SplitType;

public class SplitFactory {
	public static ExpenseSplit getSplitObject(SplitType splitType) {
		switch(splitType) {
		    case EQUAL : 
		    	return new EqualExpenseSplit();
		    case UNEQUAL : 
		    	return new UnequalExpenseSplit(); 
		    case PERCENTAGE : 
		    	return new PercentageExpenseSplit(); 
		    default: 
		    	return null; 
		}
	}
}
