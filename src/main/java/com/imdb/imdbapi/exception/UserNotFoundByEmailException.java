package com.imdb.imdbapi.exception;

public class UserNotFoundByEmailException  extends RuntimeException {
	private String message;

	public UserNotFoundByEmailException(String message) {
		super(message);
		this.message = message;
	}

}
