package com.design.parkinglot.commands;

import com.design.parkinglot.OutputPrinter;
import com.design.parkinglot.model.Command;
import com.design.parkinglot.service.ParkingLotService;

public abstract class CommandExecutor {
	final ParkingLotService parkingLotService;
	final OutputPrinter outputPrinter;
	
	public CommandExecutor(final ParkingLotService parkingLotService,final OutputPrinter outputPrinter) {
		this.parkingLotService = parkingLotService; 
		this.outputPrinter = outputPrinter;
	}
	
	public abstract boolean validate(Command command);
	
	public abstract void execute(Command command); 
}
