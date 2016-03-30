package com.vlotar.demo.exception;

/**
 * @author vlotar
 */
public class NotFoundException extends RuntimeException {

	public NotFoundException(final String message) {
		super(message);
	}
}
