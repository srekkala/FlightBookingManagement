package com.cg.exceptions;

@SuppressWarnings("serial")
public class FlightNotFoundException extends RuntimeException  {
	
	public FlightNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
