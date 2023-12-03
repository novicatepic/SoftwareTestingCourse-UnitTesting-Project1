package org.unibl.etf.exception;

/**
 * Represents an exception class thrown when an operation is not supported, extends Exception class.
 * Contains one default constructor with no other methods.
 * 
 * @author Novica Tepic
 * @version 1.0
 * @since 2023-11-23
 */
public class NotSupportedOperationException extends Exception {

	/**
	 * Represents long value used for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Represents constructor which calls parent the constructor with a 'Not supported operation!' message.
	 */
	public NotSupportedOperationException() {
		super("Not supported operation!");
	}
	
}
