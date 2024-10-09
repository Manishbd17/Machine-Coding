package com.systemdesign.loggingframework.category;

import com.systemdesign.loggingframework.sink.LoggerSubject;

public class InfoLogger extends AbstractLogger {
	
	protected InfoLogger(int levels) {
		this.levels = levels; 
	}

	@Override
	protected void display(String message, LoggerSubject loggerSubject) {
		loggerSubject.notifyAllObservers(1,"INFO: "+message); 
	}
}
