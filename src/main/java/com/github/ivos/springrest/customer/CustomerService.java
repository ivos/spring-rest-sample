package com.github.ivos.springrest.customer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private final List<Customer> customers;

	public CustomerService() {
		String data = "1 Jack Bauer,2 Chloe O'Brian,3 Kim Bauer,4 David Palmer,5 Michelle Dessler";
		customers = Arrays.asList(data.split(",")).stream().map(name -> {
			String[] parts = name.split(" ");
			return new Customer(Long.valueOf(parts[0]), parts[1], parts[2]);
		}).collect(Collectors.toList());
	}

	public List<Customer> listCustomers() {
		logger.debug("> Listing customers with size {}.", customers.size());
		return customers;
	}

	public Customer createCustomer(CustomerDtoCreate dto) {
		long maxId = customers.stream().map(mappedCustomer -> {
			return mappedCustomer.getId();
		}).max(Long::compare).get();
		Customer customer = new Customer();
		customer.setId(maxId + 1);
		customer.setFirstName(dto.firstName);
		customer.setLastName(dto.lastName);
		logger.debug("> Created customer {}.", customer);
		// disable to make simplistic tests repeatable
		// customers.add(customer);
		return customer;
	}

	public Customer getCustomer(Long id) {
		logger.debug("> Getting customer for id {}.", id);
		return customers.stream().filter(filteredCustomer -> {
			return filteredCustomer.getId().equals(id);
		}).findAny().get();
	}

}
