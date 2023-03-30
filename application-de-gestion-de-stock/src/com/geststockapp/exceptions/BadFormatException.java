package com.geststockapp.exceptions;

@SuppressWarnings("serial")
public class BadFormatException extends Exception {
	public BadFormatException() {
	}
	
	public BadFormatException(String message) {
		super(message);
	}
}
