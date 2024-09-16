package com.design.bookmyshow.model;

import java.time.Instant;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SeatLock {
	private Seat seat; 
	private Show show; 
	private Integer timeOutInSeconds; 
	private Date lockTime;
	private Integer lockedBy; 
	
	public boolean isLockExpired() {
		final Instant lockInstant = lockTime.toInstant().plusSeconds(timeOutInSeconds); 
		final Instant currentInstant = new Date().toInstant(); 
		return lockInstant.isBefore(currentInstant); 
	}
}
