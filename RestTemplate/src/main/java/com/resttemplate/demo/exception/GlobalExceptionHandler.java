package com.resttemplate.demo.exception;

import java.sql.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.resttemplate.demo.exception.APIException;
import com.resttemplate.demo.exception.ErrorDetails;
import com.resttemplate.demo.exception.ResourceNotFoundException;

public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class) // to handle specific exceptions and send custom response to the client
	public ResponseEntity<?> resourceNotFoundException
						(ResourceNotFoundException resourceNotFoundException){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(0), resourceNotFoundException.getMessage(), 400);
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(APIException.class)
	public ResponseEntity<?> handleAPIException
						(APIException exception){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(0), exception.getMessage(), 400);
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	
	// handle global exception
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException
						(Exception exception){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(0), exception.getMessage(), 400);
		
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
