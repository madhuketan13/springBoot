package com.dnb.jdbcdemo.service;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.dto.Customer;
import com.dnb.jdbcdemo.exceptions.IdNotFoundException;
import com.dnb.jdbcdemo.exceptions.InvalidDateException;
import com.dnb.jdbcdemo.repo.AccountRepository;
import com.dnb.jdbcdemo.repo.AccountRepositoryImpl;
import com.dnb.jdbcdemo.repo.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

@Service
public class AccountServiceImpl implements AccountService{


    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Account createAccount(Account account) throws IdNotFoundException {
    	
    	Optional<Customer> optional = customerRepository.getCustomerById(account.getCustomer().getCustomerId());
    	
    	if(optional.isPresent()) {
    		return accountRepository.createAccount(account);
    	}
		throw new IdNotFoundException("Customer id not found");
    }

    @Override
    public Optional<Account> getAccountById(String accountId) throws InvalidNameException, InvalidDateException {
        return accountRepository.getAccountById(accountId);
    }

    @Override
    public boolean deleteAccountById(String accountId) {
        return accountRepository.deleteAccountById(accountId);
    }

    public List<Account> getAllAccounts() throws InvalidNameException, InvalidDateException{
        return accountRepository.getAllAccounts();
    }
}
