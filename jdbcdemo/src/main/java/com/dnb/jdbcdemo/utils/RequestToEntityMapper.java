package com.dnb.jdbcdemo.utils;

import org.springframework.stereotype.Component;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.payload.request.AccountRequest;

@Component
public class RequestToEntityMapper {
	
	public Account getAccountEntity(AccountRequest accountRequest) {
		
		Account account = new Account();
		account.setAccountHolderName(accountRequest.getAccountHolderName());
		account.setAccountType(accountRequest.getAccountType());
		account.setAddress(accountRequest.getAddress());
		account.setBalance(accountRequest.getBalance());
		account.setContactNumber(accountRequest.getContactNumber());
		account.setDob(accountRequest.getDob());
		account.setCustomerId(accountRequest.getCustomerId());
		
		return account;
	}

}
