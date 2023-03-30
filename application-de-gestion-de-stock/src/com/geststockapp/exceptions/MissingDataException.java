package com.geststockapp.exceptions;

public class MissingDataException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MissingDataException() {
	}
	
	public MissingDataException(String message) {
		super(message);
	}
}
