package com.systemdesign.loggingframework.sink;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoggerSubject {
	Map<Integer,List<LogObserver>> logObservers = new HashMap<>(); 
	
	//addObserver,notifyAllObserver,removeObserver
	
	public void addObserver(int level,LogObserver logObserver) {
		List<LogObserver> currentLogger = logObservers.getOrDefault(level,new ArrayList<>());
		currentLogger.add(logObserver); 
		this.logObservers.put(level, currentLogger); 
	}
	
	public void removeObserver(LogObserver logObserver) {
		for(Map.Entry<Integer, List<LogObserver>> entry : logObservers.entrySet()) {
			entry.getValue().remove(logObserver); 
		}
	}
	
	public void notifyAllObservers(int level,String message) {
		for(Map.Entry<Integer, List<LogObserver>> entry : logObservers.entrySet()) {
			if(entry.getKey() == level) {
				entry.getValue().forEach(observer -> observer.log(message));
			}
		}
	}
	
}
