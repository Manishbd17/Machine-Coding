package com.design.parkinglot.commands;

import java.util.List;
import java.util.Optional;

import com.design.parkinglot.OutputPrinter;
import com.design.parkinglot.model.Command;
import com.design.parkinglot.model.Slot;
import com.design.parkinglot.service.ParkingLotService;

public class SlotForRegNumberCommandExecutor extends CommandExecutor {

	public static String COMMAND_NAME="slot_number_for_registration_number"; 
	
	public SlotForRegNumberCommandExecutor(final ParkingLotService parkingLotService, final OutputPrinter outputPrinter) {
		super(parkingLotService, outputPrinter);
	}

	@Override
	public boolean validate(Command command) {
		return command.getParams().size()==1;
	}

	@Override
	public void execute(Command command) {
		final List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots(); 
		final String regNumber = command.getParams().get(0); 
		Optional<Slot> foundSlot = occupiedSlots.stream()
					.filter((slot) -> slot.getParkedCar().getRegistrationNumber().equals(regNumber))
					.findFirst(); 
		if(foundSlot.isPresent()) {
			outputPrinter.printWithNewLine(foundSlot.get().getSlotNumber().toString());
		} else {
			outputPrinter.notFound();
		}
		
	}
	
	
	
}
