package com.systemdesign.loggingframework.category;

import com.systemdesign.loggingframework.sink.LoggerSubject;

public class DebugLogger extends AbstractLogger {

	protected DebugLogger(int levels) {
		this.levels = levels;
	}
	
	@Override
	protected void display(String message, LoggerSubject loggerSubject) {
		loggerSubject.notifyAllObservers(3,"DEBUG: " + message); 
	}

}
