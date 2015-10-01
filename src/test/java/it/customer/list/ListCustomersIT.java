package it.customer.list;

import org.apache.http.HttpStatus;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import it.support.Support;

public class ListCustomersIT {

	String URL = Support.urlBase() + "customers";

	@Test
	public void fn() {
		String response = Support.loadResponse(this, null);
		RestAssured.when().get(URL).then().statusCode(HttpStatus.SC_OK).contentType(ContentType.JSON)
				.body(Support.equalTo(response));

	}

}
