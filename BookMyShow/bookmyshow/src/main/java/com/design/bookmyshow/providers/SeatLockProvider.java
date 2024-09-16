package com.design.bookmyshow.providers;

import java.util.List;

import com.design.bookmyshow.model.Seat;
import com.design.bookmyshow.model.Show;

public interface SeatLockProvider {
	void lockSeats(Show show,List<Seat> seats,Integer userId); 
	void unlockSeats(Show show,List<Seat> seats,Integer userId); 
	boolean validateLock(Show show,Seat seat,Integer userId); 
	
	List<Seat> getLockedSeats(Show show); 
}
