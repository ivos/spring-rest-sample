package it.customer.create;

import org.apache.http.HttpStatus;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import it.support.Support;

public class CreateCustomerIT {

	String URL = Support.urlBase() + "customers";

	@Test
	public void fn() {
		String request = Support.loadRequest(this, "fn");
		String response = Support.loadResponse(this, "fn");
		RestAssured.given().contentType(ContentType.JSON).body(request).when().post(URL).then()
				.statusCode(HttpStatus.SC_CREATED).contentType(ContentType.JSON).body(Support.equalTo(response));
	}

	@Test
	public void val_Empty() {
		String request = Support.loadRequest(this, "val_Empty");
		String response = Support.loadResponse(this, "val_Empty");
		RestAssured.given().contentType(ContentType.JSON).body(request).when().post(URL).then()
				.statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY).contentType(ContentType.JSON)
				.body(Support.equalTo(response));
	}

}
