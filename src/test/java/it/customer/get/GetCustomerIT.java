package it.customer.get;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Test;

public class GetCustomerIT {

	@Test
	public void fn() throws ClientProtocolException, IOException {
		HttpResponse response = Request.Get("http://localhost:8080/customers/1").execute().returnResponse();
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		assertEquals(ContentType.APPLICATION_JSON.toString(), response.getEntity().getContentType().getValue());
		String expected = IOUtils.toString(getClass().getResourceAsStream("response.json"), "UTF-8");
		String actual = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
		assertEquals(expected, actual);
	}

}
