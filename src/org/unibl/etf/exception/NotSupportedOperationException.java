package org.unibl.etf.exception;

public class NotSupportedOperationException extends Exception {

	public NotSupportedOperationException() {
		super("Not supported operation!");
	}

	public NotSupportedOperationException(String message) {
		super(message);
	}
	
}
