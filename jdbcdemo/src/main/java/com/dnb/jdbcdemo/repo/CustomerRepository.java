package com.dnb.jdbcdemo.repo;

import java.util.List;
import java.util.Optional;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.dto.Customer;

public interface CustomerRepository {
	
	public Customer createAccount(Customer customer);

    public Optional<Customer> getCustomerById(int customerId);

    public boolean deleteCustomerById(int customerId);

    public List<Customer> getAllCustomers();

}
