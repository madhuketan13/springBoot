package com.dnb.jdbcdemo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.exceptions.ContactNumberNotExisting;
import com.dnb.jdbcdemo.exceptions.IdNotFoundException;
import com.dnb.jdbcdemo.exceptions.InvalidDateException;
import com.dnb.jdbcdemo.payload.request.AccountRequest;
import com.dnb.jdbcdemo.service.AccountService;
import com.dnb.jdbcdemo.utils.RequestToEntityMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/account")
public class AccountController {
	
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	RequestToEntityMapper mapper;

	@PostMapping("/create")
	public ResponseEntity<?> createAccount(@Valid @RequestBody AccountRequest account) {
		
		 
		try {
			Account accRequest = mapper.getAccountEntity(account);
			Account account1 = accountService.createAccount(accRequest);
			return new ResponseEntity(account1, HttpStatus.CREATED);
		} 
		catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/{accountId}")
	public ResponseEntity<?> getAccountById(@PathVariable("accountId") String accountId) throws IdNotFoundException {
		try {
			Optional<Account> optional = accountService.getAccountById(accountId);
			if(optional.isPresent()) {
				
				return ResponseEntity.ok(optional.get());
			}
			else {
				
				throw new IdNotFoundException("ID not found");
			}
		} catch (InvalidNameException | InvalidDateException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());
		}		
		
	}
	@GetMapping("/cn/{contactNumber: ^[0-9]{10}$}")
	public ResponseEntity<?> getAccountByContactNumber(@PathVariable("contactNumber") String contactNumber) throws ContactNumberNotExisting{
		Optional<Account> optional = accountService.findByContactNumber(contactNumber);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		else {
			throw new ContactNumberNotExisting("Contact number not found");
		}
	}
	
	@DeleteMapping("/{accountId}")
	public ResponseEntity<?> deleteAccountById(@PathVariable("accountId") String accountId) throws IdNotFoundException{
		if(accountService.existsById(accountId)) {
				try
				{
					accountService.deleteAccountById(accountId);
					return ResponseEntity.ok("acc deleted");
				} catch (IdNotFoundException e) {
					// TODO Auto-generated catch block
					throw new IdNotFoundException("Id not found");
				}
		}else {
			throw new IdNotFoundException("Id not found");
		}
		
	}
}
