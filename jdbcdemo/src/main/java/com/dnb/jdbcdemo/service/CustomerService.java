package com.dnb.jdbcdemo.service;

import java.util.List;
import java.util.Optional;

import com.dnb.jdbcdemo.dto.Customer;
import com.dnb.jdbcdemo.exceptions.IdNotFoundException;

public interface CustomerService {

	public Customer createAccount(Customer customer);

    public Optional<Customer> getCustomerById(int customerId);

    public boolean deleteCustomerById(int customerId) throws IdNotFoundException;

    public Iterable<Customer> getAllCustomers();
}
