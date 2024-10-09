package com.systemdesign.loggingframework.sink;

public class FileLog implements LogObserver {
	@Override
	public void log(String message) {
		System.out.println("FILE MESSAGE: " + message);
	}
}
