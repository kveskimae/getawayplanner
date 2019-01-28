package com.foo.exception;

public class ActivityException extends RuntimeException {

	public ActivityException(String message) {
		super(message);
	}

	public ActivityException(String message, Throwable cause) {
		super(message, cause);
	}

}
