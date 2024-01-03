package com.imdb.imdbapi.exception;

public class MovieDataNotExistException extends RuntimeException {

	private String message;
	
	public MovieDataNotExistException(String message) {
		super(message);
		this.message=message;
	}
}
