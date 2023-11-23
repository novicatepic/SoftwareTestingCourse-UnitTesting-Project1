package org.unibl.etf.exception;

/**
 * Represents an exception class thrown when dividing by zero, extends Exception class.
 * Contains one default constructor with no other methods.
 * 
 * @author Novica Tepic
 * @version 1.0
 * @since 2023-11-23
 */
public class DivisionByZeroException extends Exception {

	/**
	 * Represents long value used for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Represents constructor which calls parent constructor with a 'Division by zero!' message.
	 */
	public DivisionByZeroException() {
		super("Division by zero!");
	}
	
}
