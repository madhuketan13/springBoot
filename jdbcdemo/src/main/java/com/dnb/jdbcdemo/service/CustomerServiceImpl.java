package com.dnb.jdbcdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.jdbcdemo.dto.Customer;
import com.dnb.jdbcdemo.exceptions.IdNotFoundException;
import com.dnb.jdbcdemo.repo.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
//	@Override
//	public Customer createAccount(Customer customer) {
//		// TODO Auto-generated method stub
//		return customerRepository.createAccount(customer);
//	}
//
//	@Override
//	public Optional<Customer> getCustomerById(int customerId) {
//		// TODO Auto-generated method stub
//		return customerRepository.getCustomerById(customerId);
//	}
//
//	@Override
//	public boolean deleteCustomerById(int customerId) {
//		// TODO Auto-generated method stub
//		return customerRepository.deleteCustomerById(customerId);
//	}
//
//	@Override
//	public List<Customer> getAllCustomers() {
//		// TODO Auto-generated method stub
//		return customerRepository.getAllCustomers();
//	}

	@Override
	public Customer createAccount(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

	@Override
	public Optional<Customer> getCustomerById(int customerId) {
		// TODO Auto-generated method stub
		return customerRepository.findById(customerId);
	}

	@Override
	public boolean deleteCustomerById(int customerId) throws IdNotFoundException {
		if(customerRepository.existsById(customerId)) {
			customerRepository.deleteById(customerId);
			return true;
		}
		// TODO Auto-generated method stub
		throw new IdNotFoundException("ID not found in Table");
	}

	@Override
	public Iterable<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

}
