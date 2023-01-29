package com.resttemplate.demo.exception;

import java.util.Date;

public class ErrorDetails {

	private Date timestamp;
	private String message;
	private int statusCode;
	
	public ErrorDetails() {}
	
	public ErrorDetails(Date timestamp, String message, int statusCode) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.statusCode = statusCode;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
