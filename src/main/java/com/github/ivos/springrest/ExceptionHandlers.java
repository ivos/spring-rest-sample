package com.github.ivos.springrest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.ivos.springrest.exception.ValidationException;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionHandlers {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ResponseBody
	public Object handleValidationException(ValidationException e) {
		return e.getErrors();
	}

}
