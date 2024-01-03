package com.imdb.imdbapi.exception;

public class MovieNotFoundByNameException  extends RuntimeException {
	private String message;

	public MovieNotFoundByNameException(String message) {
		super(message);
		this.message = message;
	}

}
