package com.design.parkinglot;

public class OutputPrinter {
	
	public void welcome() {
		printWithNewLine("Welcome to the Bonsai Parking Lot"); 
	}
	
	public void end() {
		printWithNewLine("Thanks for using the Bonsai Parking Lot Service");
	}
	
	public void notFound() {
		printWithNewLine("Not found");
	}
	
	public void parkingLotFull() {
		printWithNewLine("Sorry, Parking Lot is Full");
	}
	
	public void parkingLotEmpty() {
		printWithNewLine("Parking Lot is Empty");
	}
	
	public void invalidFile() {
		printWithNewLine("Invalid File Present in Input");
	}
	
	public void statusHeader() {
		printWithNewLine("Slot No        Registration No        Color");
	}
	
	public void printWithNewLine(String msg) {
		System.out.println(msg); 
	}
}
