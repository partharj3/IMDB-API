package com.imdb.imdbapi.exception;

public class RecordAlreadyExistException extends RuntimeException{

	private String message;
	
	public RecordAlreadyExistException(String message) {
		super(message);
		this.message=message;
	}
	
}
