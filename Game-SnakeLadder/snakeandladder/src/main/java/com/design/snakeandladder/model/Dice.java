package com.design.snakeandladder.model;

import org.apache.commons.lang3.RandomUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Dice {
	private int minValue; 
	private int maxValue; 
	private int currValue; 
	
	public int roll() {
		return RandomUtils.nextInt(minValue,maxValue+1);
	}
}
