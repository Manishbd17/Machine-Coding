package com.systemdesign.loggingframework.logger;

import java.io.Serializable;

import com.systemdesign.loggingframework.category.AbstractLogger;
import com.systemdesign.loggingframework.category.LogManager;
import com.systemdesign.loggingframework.sink.LoggerSubject;

public class Logger implements Cloneable, Serializable {
	
	private volatile static Logger logger; 
	private volatile static AbstractLogger chainOfLogger;
	private volatile static LoggerSubject loggerSubject; 
	private volatile static LogManager logManager; 
	private Logger() {
		if(logger!=null)  {
			throw new IllegalStateException("Object Already created"); 
		}
	}
	
	public static Logger getLogger() {
		if(logger == null) {
			synchronized(Logger.class) {
				if(logger == null) {
					logger = new Logger(); 
					chainOfLogger = logManager.doChaining(); 
					loggerSubject = logManager.addObservers(); 
				}
			}
		}
		return logger; 
	}

	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException(); 
	}
	
	protected Object readResolve() {
		return logger; 
	}
	
	public void info(String message) {
		createLog(1, message); 
	}
	
	public void error(String message) {
		createLog(2,message); 
	}
	
	public void debug(String message) {
		createLog(3,message); 
	}
	
	private void createLog(int level,String message) {
		chainOfLogger.logMessage(level,message,loggerSubject); 
	}
	
}
