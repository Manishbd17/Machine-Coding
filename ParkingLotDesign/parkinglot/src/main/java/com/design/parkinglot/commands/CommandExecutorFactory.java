package com.design.parkinglot.commands;

import java.util.HashMap;
import java.util.Map;

import com.design.parkinglot.OutputPrinter;
import com.design.parkinglot.exception.InvalidCommandException;
import com.design.parkinglot.model.Command;
import com.design.parkinglot.service.ParkingLotService;

public class CommandExecutorFactory {

	private Map<String,CommandExecutor> commands = new HashMap<>();
	
	public CommandExecutorFactory(final ParkingLotService parkingLotService) {
		 final OutputPrinter outputPrinter = new OutputPrinter(); 
		 commands.put(CreateParkingLotCommandExecutor.COMMAND_NAME, new CreateParkingLotCommandExecutor(parkingLotService,outputPrinter));
		 commands.put(ColorToSlotNumberCommandExecutor.COMMAND_NAME, new ColorToSlotNumberCommandExecutor(parkingLotService,outputPrinter));
		 commands.put(ColorToRegNumberCommandExecutor.COMMAND_NAME, new ColorToRegNumberCommandExecutor(parkingLotService,outputPrinter));
		 commands.put(ExitCommandExecutor.COMMAND_NAME, new ExitCommandExecutor(parkingLotService,outputPrinter));
		 commands.put(ParkCommandExecutor.COMMAND_NAME, new ParkCommandExecutor(parkingLotService,outputPrinter));
		 commands.put(LeaveCommandExecutor.COMMAND_NAME, new LeaveCommandExecutor(parkingLotService,outputPrinter));
		 commands.put(SlotForRegNumberCommandExecutor.COMMAND_NAME, new SlotForRegNumberCommandExecutor(parkingLotService,outputPrinter));
		 commands.put(StatusCommandExecutor.COMMAND_NAME, new StatusCommandExecutor(parkingLotService,outputPrinter));

	}
	
	public CommandExecutor getCommandExecutor(final Command command) {
		final CommandExecutor commandExecutor = commands.get(command.getCommandName()); 
		if(commandExecutor==null) {
			throw new InvalidCommandException(); 
		}
		return commandExecutor; 
	}

}
