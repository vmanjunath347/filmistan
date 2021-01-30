package com.filmistan.webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidInputExcerption extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	public InvalidInputExcerption(String message) {
		super(message);
	}
}
