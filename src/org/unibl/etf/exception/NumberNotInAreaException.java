package org.unibl.etf.exception;

public class NumberNotInAreaException extends Exception {

	public NumberNotInAreaException() {
		super("Number not in area!");
	}

	public NumberNotInAreaException(String message) {
		super(message);
	}
	
}
