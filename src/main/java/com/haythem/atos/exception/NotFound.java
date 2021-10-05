package com.haythem.atos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * 
 * @author HJEBEL
 *
 *         This class is for personal exception NOT FOUND
 *
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFound(String message) {
		super(message);
	}

	public NotFound(String message, Throwable cause) {
		super(message, cause);
	}
}
