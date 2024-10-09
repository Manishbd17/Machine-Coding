package com.systemdesign.loggingframework.category;

import com.systemdesign.loggingframework.sink.ConsoleLog;
import com.systemdesign.loggingframework.sink.FileLog;
import com.systemdesign.loggingframework.sink.LoggerSubject;

public class LogManager {
	
	public static AbstractLogger doChaining() {
		AbstractLogger infoLogger = new InfoLogger(1); 
		AbstractLogger errorLogger = new ErrorLogger(2); 
		infoLogger.setNextLevelLogger(errorLogger);
		AbstractLogger debugLogger = new DebugLogger(3); 
		errorLogger.setNextLevelLogger(debugLogger);
		return infoLogger;
	}
	
	public static LoggerSubject addObservers() {
		LoggerSubject loggerSubject = new LoggerSubject(); 
		ConsoleLog consoleLogger = new ConsoleLog(); 
		loggerSubject.addObserver(1,consoleLogger); 
		loggerSubject.addObserver(2,consoleLogger); 
		loggerSubject.addObserver(3,consoleLogger); 
		FileLog fileLogger = new FileLog(); 
		loggerSubject.addObserver(1,fileLogger);
		loggerSubject.addObserver(2,fileLogger);
		loggerSubject.addObserver(3,fileLogger);
		return loggerSubject; 
	}
}
