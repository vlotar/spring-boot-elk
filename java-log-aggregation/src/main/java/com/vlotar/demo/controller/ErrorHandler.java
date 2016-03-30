package com.vlotar.demo.controller;

import com.vlotar.demo.exception.ResourceNotFoundException;
import com.vlotar.demo.exception.OperationNotAcceptableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Generic error handling mechanism.
 *
 * @author vlotar
 */
@ControllerAdvice
public class ErrorHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

	@ResponseStatus(HttpStatus.NOT_FOUND)  // 404
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseBody
	public String handleNotFound(ResourceNotFoundException ex) {
		LOGGER.warn("Entity was not found", ex);
		return "{\"errorCode\":\"E0001\"}";
	}

	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)  // 406
	@ExceptionHandler(OperationNotAcceptableException.class)
	@ResponseBody
	public String handleNotFound(OperationNotAcceptableException ex) {
		LOGGER.warn("Operation cannot be performed", ex);
		return "{\"errorCode\":\"E0002\"}";
	}
}
