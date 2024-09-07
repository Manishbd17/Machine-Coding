package com.design.parkinglot.mode;

import java.io.IOException;

import com.design.parkinglot.OutputPrinter;
import com.design.parkinglot.commands.CommandExecutor;
import com.design.parkinglot.commands.CommandExecutorFactory;
import com.design.parkinglot.exception.InvalidCommandException;
import com.design.parkinglot.model.Command;

public abstract class Mode {
	
	private CommandExecutorFactory commandExecutorFactory; 
	protected OutputPrinter outputPrinter; 
	
	public Mode(final CommandExecutorFactory commandExecutorFactory, final OutputPrinter outputPrinter) {
		this.commandExecutorFactory = commandExecutorFactory; 
		this.outputPrinter = outputPrinter; 
	}
	
	protected void processCommand(final Command command) {
		final CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command); 
		if(commandExecutor.validate(command)) {
			commandExecutor.execute(command); 
		} else {
			throw new InvalidCommandException(); 
		}
	}
	
	public abstract void process() throws IOException; 
	
}
