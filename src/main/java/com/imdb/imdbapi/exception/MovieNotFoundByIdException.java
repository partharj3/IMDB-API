package com.imdb.imdbapi.exception;

public class MovieNotFoundByIdException  extends RuntimeException {
	private String message;

	public MovieNotFoundByIdException(String message) {
		super(message);
		this.message = message;
	}

}
