package com.design.parkinglot.commands;

import java.util.List;

import com.design.parkinglot.OutputPrinter;
import com.design.parkinglot.model.Command;
import com.design.parkinglot.model.ParkingLot;
import com.design.parkinglot.parking.strategy.NaturalParkingOrderStrategy;
import com.design.parkinglot.service.ParkingLotService;
import com.design.parkinglot.validator.IntegerValidator;

public class CreateParkingLotCommandExecutor extends CommandExecutor {
	
	public static String COMMAND_NAME = "create_parking_lot";
	
	public CreateParkingLotCommandExecutor(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
		super(parkingLotService, outputPrinter);
	}

	@Override
	public boolean validate(final Command command) {
		List<String> params = command.getParams(); 
		if(params.size()!=1) {
			return false;
		}
		return IntegerValidator.isInteger(params.get(0)); 
	}
	
	@Override
	public void execute(Command command) {
		final int parkingLotCapacity = Integer.parseInt(command.getParams().get(0)); 
		final ParkingLot parkingLot = new ParkingLot(parkingLotCapacity);
		parkingLotService.createParkingLot(parkingLot, new NaturalParkingOrderStrategy());
		outputPrinter.printWithNewLine("Created a parking lot with " + parkingLot.getCapacity() + " slots");
	}
	
	
}
