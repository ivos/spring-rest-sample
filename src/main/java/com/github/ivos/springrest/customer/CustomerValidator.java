package com.github.ivos.springrest.customer;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.ivos.springrest.ValidationErrors;

@Component
public class CustomerValidator {

	@Autowired
	private Validator validator;

	@Autowired
	private ValidationErrors errors;

	public void validate(Customer customer) {
		errors.addConstraintViolations(validator.validate(customer));
	}

}
