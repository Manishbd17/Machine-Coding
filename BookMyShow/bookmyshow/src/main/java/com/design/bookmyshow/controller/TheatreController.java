package com.design.bookmyshow.controller;

import com.design.bookmyshow.model.Screen;
import com.design.bookmyshow.model.Theatre;
import com.design.bookmyshow.service.TheatreService;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class TheatreController {
	final private TheatreService theatreService;

    public Integer createTheatre(@NonNull final String theatreName) {
        return theatreService.createTheatre(theatreName).getTheatreId();
    }

    public Integer createScreenInTheatre(@NonNull final String screenName, @NonNull final Integer theatreId) {
        final Theatre theatre = theatreService.getTheatre(theatreId);
        return theatreService.createScreenInTheatre(screenName, theatre).getScreenId(); 
    }

    public Integer createSeatInScreen(@NonNull final Integer rowNo, @NonNull final Integer seatNo, @NonNull final Integer screenId) {
        final Screen screen = theatreService.getScreen(screenId);
        return theatreService.createSeatInScreen(rowNo, seatNo, screen).getSeatId(); 
    }
}
