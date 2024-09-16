package com.design.bookmyshow.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Show {
	private final Integer showId;
	private final Movie movie; 
	private final Screen screen; 
	private final Date startTime;
	private final Integer durationInSecs; 
}
