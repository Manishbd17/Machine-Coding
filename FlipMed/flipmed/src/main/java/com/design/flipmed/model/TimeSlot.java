package com.design.flipmed.model;

import java.util.Objects;

public class TimeSlot {
	String start;
	String end; 
	
	public TimeSlot (String start,String end) {
		this.start = start;
		this.end = end; 
	}
	
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	@Override
	public int hashCode() {
		return Objects.hash(start,end);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeSlot other = (TimeSlot) obj;
		return Objects.equals(start, other.start) && Objects.equals(end, other.end);
	}
	
	
	
}
