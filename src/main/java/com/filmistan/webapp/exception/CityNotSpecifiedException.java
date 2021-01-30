package com.filmistan.webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class CityNotSpecifiedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public CityNotSpecifiedException(String message) {
		super(message);
	}

}
