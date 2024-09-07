package com.design.parkinglot;

import java.io.IOException;

import com.design.parkinglot.commands.CommandExecutorFactory;
import com.design.parkinglot.exception.InvalidModeException;
import com.design.parkinglot.mode.FileInputMode;
import com.design.parkinglot.mode.InteractiveMode;
import com.design.parkinglot.service.ParkingLotService;

public class Main {

	public static void main(String[] args) throws IOException {
		final OutputPrinter outputPrinter = new OutputPrinter(); 
		final ParkingLotService parkingLotService = new ParkingLotService(); 
		final CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(parkingLotService); 
		
		if(isInteractiveMode(args)) {
			new InteractiveMode(commandExecutorFactory,outputPrinter).process(); 
		} else if (isFileInputMode(args)) {
			new FileInputMode(commandExecutorFactory,outputPrinter,args[0]).process(); 
		} else {
			throw new InvalidModeException(); 
		}
	}
	
	private static boolean isInteractiveMode(final String[] args) {
		return args.length == 0; 
	}

	private static boolean isFileInputMode(final String[] args) {
		return args.length == 1; 
	}

}
