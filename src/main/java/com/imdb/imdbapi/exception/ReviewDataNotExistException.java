package com.imdb.imdbapi.exception;

public class ReviewDataNotExistException extends RuntimeException {
	private String message;

	public ReviewDataNotExistException(String message) {
		super(message);
		this.message = message;
	}

}
