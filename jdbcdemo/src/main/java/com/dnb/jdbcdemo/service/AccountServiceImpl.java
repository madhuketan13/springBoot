package com.dnb.jdbcdemo.service;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.dto.Customer;
import com.dnb.jdbcdemo.exceptions.IdNotFoundException;
import com.dnb.jdbcdemo.exceptions.InvalidDateException;
import com.dnb.jdbcdemo.repo.AccountRepository;
//import com.dnb.jdbcdemo.repo.AccountRepositoryImpl;
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
	private AccountRepository accountRepository;

	@Override
	public Account createAccount(Account account) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return accountRepository.save(account);
	}

	@Override
	public Optional<Account> getAccountById(String accountId) throws InvalidNameException, InvalidDateException {
		// TODO Auto-generated method stub
		return accountRepository.findById(accountId);
	}

	@Override
	public boolean deleteAccountById(String accountId) throws IdNotFoundException {
		
		if(accountRepository.existsById(accountId)) {
			accountRepository.deleteById(accountId);
			return true;
		}
		throw new IdNotFoundException("No accound found by the given id");
	}

	@Override
	public Iterable<Account> getAllAccounts() throws InvalidNameException, InvalidDateException {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

	@Override
	public boolean existsById(String accountId) {
		return accountRepository.existsById(accountId);
	}

	@Override
	public Optional<Account> findByContactNumber(String contactNumber) {
		// TODO Auto-generated method stub
		return accountRepository.findByContactNumber(contactNumber);
	}


   
}

