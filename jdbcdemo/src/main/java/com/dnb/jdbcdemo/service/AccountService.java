package com.dnb.jdbcdemo.service;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.exceptions.IdNotFoundException;
import com.dnb.jdbcdemo.exceptions.InvalidDateException;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

public interface AccountService {

    public Account createAccount(Account account) throws IdNotFoundException;

    public Optional<Account> getAccountById(String accountId) throws InvalidNameException, InvalidDateException;

    public boolean deleteAccountById(String accountId) throws IdNotFoundException;

    public Iterable<Account> getAllAccounts() throws InvalidNameException, InvalidDateException;
}
