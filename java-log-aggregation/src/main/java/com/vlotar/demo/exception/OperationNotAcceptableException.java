package com.vlotar.demo.exception;

/**
 * This exception should be thrown in all cases when particular action or operation cannot be executed
 *
 * @author vlotar
 */
public class OperationNotAcceptableException extends RuntimeException {

	/**
	 * Instantiates a new {@link OperationNotAcceptableException}.
	 *
	 * @param message the message
	 */
	public OperationNotAcceptableException(final String message) {
		super(message);
	}
}
