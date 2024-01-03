package com.imdb.imdbapi.exception;

public class ReviewNotFoundByIdException  extends RuntimeException {
	private String message;

	public ReviewNotFoundByIdException(String message) {
		super(message);
		this.message = message;
	}

}
