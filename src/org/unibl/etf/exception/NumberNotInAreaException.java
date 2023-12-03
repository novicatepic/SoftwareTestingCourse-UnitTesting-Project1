package org.unibl.etf.exception;

/**
 * Represents an exception class thrown when the number is not in the appropriate area, extends Exception class.
 * Contains one default constructor with no other methods.
 * 
 * @author Novica Tepic
 * @version 1.0
 * @since 2023-11-23
 */
public class NumberNotInAreaException extends Exception {

	/**
	 * Represents long value used for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Represents constructor which calls the parent constructor with a 'Number not in area!' message.
	 */
	public NumberNotInAreaException() {
		super("Number not in area!");
	}

}
