package com.haythem.atos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author HJEBEL
 * 
 * 
 *         This class is for personal exception Bad Request.
 *
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequest extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BadRequest(String message) {
		super(message);
	}

	public BadRequest(String message, Throwable cause) {
		super(message, cause);
	}
}
