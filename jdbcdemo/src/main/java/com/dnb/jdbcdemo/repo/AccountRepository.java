package com.dnb.jdbcdemo.repo;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.exceptions.InvalidDateException;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;



public interface AccountRepository {
    public Account createAccount(Account account);

    public Optional<Account> getAccountById(String accountId) throws InvalidNameException, InvalidDateException;

    public boolean deleteAccountById(String accountId);

    public List<Account> getAllAccounts() throws InvalidNameException, InvalidDateException;
}
