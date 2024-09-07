package com.design.parkinglot.commands;

import java.util.List;

import com.design.parkinglot.OutputPrinter;
import com.design.parkinglot.model.Command;
import com.design.parkinglot.service.ParkingLotService;
import com.design.parkinglot.validator.IntegerValidator;

public class LeaveCommandExecutor extends CommandExecutor {
	
	public static String COMMAND_NAME = "leave"; 
	
	public LeaveCommandExecutor(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
		super(parkingLotService, outputPrinter);
	}

	@Override
	public boolean validate(Command command) {
		List<String> params = command.getParams(); 
		if(params.size()!=1) {
			return false; 
		}
		return IntegerValidator.isInteger(params.get(0)); 
	}

	@Override
	public void execute(Command command) {
		final int slotNumber = Integer.parseInt(command.getParams().get(0)); 
		parkingLotService.makeSlotFree(slotNumber);
		outputPrinter.printWithNewLine("Slot number " + slotNumber + " is free");
	}

	
}
