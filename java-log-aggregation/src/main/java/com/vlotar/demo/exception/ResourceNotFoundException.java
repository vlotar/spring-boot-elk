package com.vlotar.demo.exception;

/**
 * This exception should be thrown in all cases when a resource cannot be found
 *
 * @author vlotar
 */
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * Instantiates a new {@link ResourceNotFoundException}.
	 *
	 * @param message the message
	 */
	public ResourceNotFoundException(final String message) {
		super(message);
	}
}
