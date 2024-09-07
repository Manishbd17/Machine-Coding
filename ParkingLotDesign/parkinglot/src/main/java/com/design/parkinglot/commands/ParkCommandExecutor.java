package com.design.parkinglot.commands;

import com.design.parkinglot.OutputPrinter;
import com.design.parkinglot.exception.NoFreeSlotException;
import com.design.parkinglot.model.Car;
import com.design.parkinglot.model.Command;
import com.design.parkinglot.service.ParkingLotService;

public class ParkCommandExecutor extends CommandExecutor {

	public static String COMMAND_NAME="park"; 
	
	public ParkCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
		super(parkingLotService, outputPrinter);
	}

	@Override
	public boolean validate(Command command) {
		return command.getParams().size()==2;
	}

	@Override
	public void execute(Command command) {
		final Car car = new Car(command.getParams().get(1),command.getParams().get(0)); 
		try {
			final Integer slot = parkingLotService.park(car); 
			outputPrinter.printWithNewLine("Allocated slot number: " + slot);
		} catch(NoFreeSlotException e) {
			outputPrinter.parkingLotFull();
		}
	}
	
	
}
