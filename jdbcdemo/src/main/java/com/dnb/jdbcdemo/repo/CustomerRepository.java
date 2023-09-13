package com.dnb.jdbcdemo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.dto.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
	
//	public Customer createAccount(Customer customer);
//
//    public Optional<Customer> getCustomerById(int customerId);
//
//    public boolean deleteCustomerById(int customerId);
//
//    public List<Customer> getAllCustomers();

}
