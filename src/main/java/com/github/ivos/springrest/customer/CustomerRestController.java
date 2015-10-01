package com.github.ivos.springrest.customer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CustomerService customerService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Customer> listCustomers() {
		logger.info("List customers.");
		return customerService.listCustomers();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Customer createCustomer(@RequestBody CustomerDtoCreate dto) {
		logger.info("Create customer {}.", dto);
		return customerService.createCustomer(dto);
	}

	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	public Customer getCustomer(@PathVariable Long customerId) {
		logger.info("Get customer {}.", customerId);
		return customerService.getCustomer(customerId);
	}

}
