package com.design.bookmyshow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Seat {
	private final Integer seatId; 
	private final int rowNo; 
	private final int seatNo; 
}
