package com.imdb.imdbapi.exception;

public class UserNotFoundByIdException  extends RuntimeException {
	private String message;

	public UserNotFoundByIdException(String message) {
		super(message);
		this.message = message;
	}

}
