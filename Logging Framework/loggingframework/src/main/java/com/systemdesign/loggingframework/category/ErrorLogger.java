package com.systemdesign.loggingframework.category;

import com.systemdesign.loggingframework.sink.LoggerSubject;

public class ErrorLogger extends AbstractLogger {
	
	protected ErrorLogger(int levels) {
		this.levels = levels; 
	}
	
	@Override
	protected void display(String message,LoggerSubject loggerSubject) {
		loggerSubject.notifyAllObservers(2, "ERROR "+ message); 
	}
}
