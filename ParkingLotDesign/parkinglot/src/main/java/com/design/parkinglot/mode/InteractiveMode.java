package com.design.parkinglot.mode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.design.parkinglot.OutputPrinter;
import com.design.parkinglot.commands.CommandExecutorFactory;
import com.design.parkinglot.commands.ExitCommandExecutor;
import com.design.parkinglot.model.Command;

public class InteractiveMode extends Mode {
	
	public InteractiveMode(final CommandExecutorFactory commandExecutorFactory, final OutputPrinter outputPrinter) {
		super(commandExecutorFactory,outputPrinter);
		
	}

	@Override
	public void process() throws IOException {
		outputPrinter.welcome(); 
		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			final String input = reader.readLine(); 
			final Command command = new Command(input); 
			processCommand(command); 
			if(command.getCommandName().equals(ExitCommandExecutor.COMMAND_NAME)) {
				break;
			}
		}	
	}
}
