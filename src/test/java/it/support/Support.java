package it.support;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Assert;

public class Support {

	public static String urlBase() {
		return "http://localhost:8080/";
	}

	public static String load(Object testInstance, String fileName) {
		try {
			return IOUtils.toString(testInstance.getClass().getResourceAsStream(fileName), "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException("Cannot load file " + fileName, e);
		}
	}

	public static String loadRequest(Object testInstance, String testCase) {
		return load(testInstance, ((null == testCase) ? "" : testCase + "-") + "request.json");
	}

	public static String loadResponse(Object testInstance, String testCase) {
		return load(testInstance, ((null == testCase) ? "" : testCase + "-") + "response.json");
	}

	public static org.hamcrest.Matcher<Object> equalTo(Object operand) {
		return new EqualToMatcher(operand);
	}

	public static class EqualToMatcher extends BaseMatcher<Object> {
		private final Object expectedValue;

		public EqualToMatcher(Object expectedValue) {
			this.expectedValue = expectedValue;
		}

		@Override
		public void describeTo(Description description) {
			description.appendValue(expectedValue);
		}

		@Override
		public boolean matches(Object item) {
			Assert.assertEquals(expectedValue, item);
			return true;
		}
	}

}
