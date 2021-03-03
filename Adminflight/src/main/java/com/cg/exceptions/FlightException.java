package com.cg.exceptions;

@SuppressWarnings("serial")
public class FlightException extends RuntimeException {
	public FlightException(String errorMessage) {
		super(errorMessage);
	}
}
