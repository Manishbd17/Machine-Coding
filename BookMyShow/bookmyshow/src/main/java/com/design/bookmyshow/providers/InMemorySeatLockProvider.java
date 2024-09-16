package com.design.bookmyshow.providers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.design.bookmyshow.exception.SeatTemporarilyUnAvailableException;
import com.design.bookmyshow.model.Seat;
import com.design.bookmyshow.model.SeatLock;
import com.design.bookmyshow.model.Show;

import lombok.NonNull;

public class InMemorySeatLockProvider implements SeatLockProvider {
	
	private final Integer lockTimeout; 
	private final Map<Show,Map<Seat,SeatLock>> locks; 
	
	public InMemorySeatLockProvider(@NonNull final Integer lockTimeout) {
		this.lockTimeout = lockTimeout; 
		this.locks = new HashMap<>(); 
	}

	@Override
	synchronized public void lockSeats(@NonNull final Show show,@NonNull final List<Seat> seats,@NonNull final Integer userId) {
		for(Seat seat : seats) {
			if(isSeatLocked(show,seat)) {
				throw new SeatTemporarilyUnAvailableException();
			}
		}
		for(Seat seat: seats) {
			lockSeat(show,seat,userId,lockTimeout); 
		}
	}

	@Override
	public void unlockSeats(@NonNull final  Show show,@NonNull final List<Seat> seats,@NonNull final Integer userId) {
		for(Seat seat : seats) {
			if(validateLock(show,seat,userId)) {
				unlockSeat(show,seat); 
			}
		}
	}
	
	private void unlockSeat(@NonNull final Show show, @NonNull final Seat seat) {
		if(!locks.containsKey(show)) {
			return ; 
		}
		locks.get(show).remove(seat); 
	}

	@Override
	public boolean validateLock(@NonNull final Show show, @NonNull final Seat seat, @NonNull final Integer userId) {
		return isSeatLocked(show,seat) && locks.get(show).get(seat).getLockedBy().equals(userId); 
	}

	@Override
	public List<Seat> getLockedSeats(Show show) {
		final List<Seat> lockedSeats = new ArrayList<>(); 
		if(!locks.containsKey(show)) {
			return lockedSeats; 
		}
		for(Seat seat: locks.get(show).keySet()) {
			if(isSeatLocked(show,seat)) {
				lockedSeats.add(seat); 
			}
		}
		return lockedSeats;
	}
	
	private void lockSeat(final Show show,final Seat seat,final Integer userId,final Integer timeoutInSeconds) {
		if(!locks.containsKey(show)) {
			locks.put(show,new HashMap<>()); 
		}
		final SeatLock lock = new SeatLock(seat,show,timeoutInSeconds,new Date(),userId); 
		locks.get(show).put(seat, lock); 
	}
	
	
	private boolean isSeatLocked(final Show show,final Seat seat) {
		return locks.containsKey(show) && locks.get(show).containsKey(seat) && !locks.get(show).get(seat).isLockExpired(); 
	}

}
