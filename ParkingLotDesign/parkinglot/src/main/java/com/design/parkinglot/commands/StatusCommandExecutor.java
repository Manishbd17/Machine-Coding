package com.design.parkinglot.commands;

import java.util.List;

import com.design.parkinglot.OutputPrinter;
import com.design.parkinglot.model.Car;
import com.design.parkinglot.model.Command;
import com.design.parkinglot.model.Slot;
import com.design.parkinglot.service.ParkingLotService;

public class StatusCommandExecutor extends CommandExecutor {
	
	public static String COMMAND_NAME="status"; 

	public StatusCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
		super(parkingLotService, outputPrinter);
	}

	@Override
	public boolean validate(Command command) {
		return command.getParams().isEmpty();
	}

	@Override
	public void execute(Command command) {
		final List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots(); 
		if(occupiedSlots.isEmpty()) {
			outputPrinter.parkingLotEmpty();
			return ; 
		}
		outputPrinter.statusHeader(); 
		for(Slot slot : occupiedSlots) {
			final Car parkedCar = slot.getParkedCar(); 
			final String slotNumber = slot.getSlotNumber().toString(); 
			
			outputPrinter.printWithNewLine(padString(slotNumber,19) + padString(parkedCar.getRegistrationNumber(),12) 
									+ parkedCar.getColor());
		}
	}
	
	public static String padString(final String word, final int length) {
		String newWord = word; 
		for(int count=word.length();count<length;count++) {
			newWord = newWord + " "; 
		}
		return newWord; 
	}
	

}
