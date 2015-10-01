package com.github.ivos.springrest;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Path.Node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.ivos.springrest.exception.ValidationException;

/**
 * Collect validation errors to report all of them together.
 */
public class ValidationErrors {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private Map<String, Set<String>> errors;

	public Map<String, Set<String>> getErrors() {
		return errors;
	}

	private String extractFieldName(ConstraintViolation<?> constraintViolation) {
		Iterator<Node> iterator = constraintViolation.getPropertyPath().iterator();
		Node node = null;
		while (iterator.hasNext()) {
			node = iterator.next();
		}
		return node.getName();
	}

	private void performAddError(String field, String message, Object... arguments) {
		logger.debug("Adding for field [{}] message [{}] with arguments {}.", field, message, arguments);
		if (null == errors) {
			errors = new TreeMap<String, Set<String>>();
		}
		Set<String> fieldErrors = errors.get(field);
		if (null == fieldErrors) {
			fieldErrors = new TreeSet<String>();
			errors.put(field, fieldErrors);
		}
		String formatted = MessageFormat.format(message, arguments);
		fieldErrors.add(formatted);
	}

	public <T> void addConstraintViolations(Set<ConstraintViolation<T>> constraintViolations) {
		for (ConstraintViolation<T> constraintViolation : constraintViolations) {
			String field = extractFieldName(constraintViolation);
			performAddError(field, constraintViolation.getMessage());
		}
	}

	public void verify() {
		if (null != errors) {
			logger.info("Validation errors: {}.", errors);
			throw new ValidationException(this);
		}
	}

	@Override
	public String toString() {
		return "ValidationErrors [errors=" + errors + "]";
	}

}
