package com.systemdesign.loggingframework.sink;

public class ConsoleLog implements LogObserver {
	
	@Override
	public void log(String message) {
		System.out.println("Console message: " + message);
	}
}
