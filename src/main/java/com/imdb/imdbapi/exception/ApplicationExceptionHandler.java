package com.imdb.imdbapi.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.imdb.imdbapi.util.ErrorStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<ObjectError> allErrors = ex.getAllErrors();
		
		Map<String, String> mapErrors = new HashMap<String, String>();
		for(ObjectError error:allErrors) {
			FieldError fieldError =  (FieldError)error;
			String message = fieldError.getDefaultMessage(); /** VALUE **/
			String field = fieldError.getField(); /** KEY **/
			
			mapErrors.put(field, message);
		}
		return new ResponseEntity<Object>(mapErrors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure> userNotFoundById(UserNotFoundByIdException exp) {
		ErrorStructure structure = new ErrorStructure();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(exp.getMessage());
		structure.setRootCause("User record not found for the requested ID !!");
		return new ResponseEntity<ErrorStructure>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> recordAlreadyFound(RecordAlreadyExistException exp) {
		ErrorStructure structure = new ErrorStructure();
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage(exp.getMessage());
		structure.setRootCause("Record Already Exist!!");
		return new ResponseEntity<ErrorStructure>(structure, HttpStatus.FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure> userNotFoundByEmail(UserNotFoundByEmailException exp) {
		ErrorStructure structure = new ErrorStructure();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(exp.getMessage());
		structure.setRootCause("User record not found for the requested Email !!");
		return new ResponseEntity<ErrorStructure>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> movieNotFoundById(MovieNotFoundByIdException exp) {
		ErrorStructure structure = new ErrorStructure();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(exp.getMessage());
		structure.setRootCause("Movie record not found for the requested ID !!");
		return new ResponseEntity<ErrorStructure>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure> MovieNotFoundByName(MovieNotFoundByNameException exp) {
		ErrorStructure structure = new ErrorStructure();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(exp.getMessage());
		structure.setRootCause("Movie record not found for the requested Name !!");
		return new ResponseEntity<ErrorStructure>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> movieDataNotFound(MovieDataNotExistException exp) {
		ErrorStructure structure = new ErrorStructure();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(exp.getMessage());
		structure.setRootCause("Movie record not found!!");
		return new ResponseEntity<ErrorStructure>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> userDataNotFound(UserDataNotExistException exp) {
		ErrorStructure structure = new ErrorStructure();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(exp.getMessage());
		structure.setRootCause("User record not found!!");
		return new ResponseEntity<ErrorStructure>(structure, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> reviewDataNotFound(ReviewDataNotExistException exp) {
		ErrorStructure structure = new ErrorStructure();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(exp.getMessage());
		structure.setRootCause("Review record not found!!");
		return new ResponseEntity<ErrorStructure>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure> reviewNotFoundbyId(ReviewNotFoundByIdException exp) {
		ErrorStructure structure = new ErrorStructure();
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(exp.getMessage());
		structure.setRootCause("Review record not found by ID!!");
		return new ResponseEntity<ErrorStructure>(structure, HttpStatus.NOT_FOUND);
	}
	
}
