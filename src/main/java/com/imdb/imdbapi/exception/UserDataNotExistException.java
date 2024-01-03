package com.imdb.imdbapi.exception;

public class UserDataNotExistException  extends RuntimeException {
	private String message;

	public UserDataNotExistException(String message) {
		super(message);
		this.message = message;
	}

}
