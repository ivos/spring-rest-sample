package com.github.ivos.springrest.exception;

import com.github.ivos.springrest.ValidationErrors;

public class ValidationException extends RuntimeException {

	private final ValidationErrors errors;

	public ValidationException(ValidationErrors errors) {
		super("Validation errors: " + errors);
		this.errors = errors;
	}

	public ValidationErrors getErrors() {
		return errors;
	}

	private static final long serialVersionUID = 1L;

}
