package com.dnb.jdbcdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.naming.InvalidNameException;

import com.dnb.jdbcdemo.exceptions.InvalidDateException;

@Getter
@EqualsAndHashCode
@ToString(exclude = "customer")
public class Account {
	
	
	
    public String accountId;
    private String accountHolderName;
    private String accountType;
    private float balance;
    private String contactNumber;
    private String address;
    private LocalDate accountCreatedDate = LocalDate.now();
    private LocalDate dob;
    private boolean accountStatus;
    private Customer customer;
    
    public Account(String accountId,String accountHolderName,String accountType,float balance,String contactNumber,String address,LocalDate accountCreatedDate, LocalDate dob, boolean accountStatus,Customer customer) throws InvalidNameException, InvalidDateException

	{

		super();

		this.setAccountId(accountId);
		this.setAccountHolderName(accountHolderName);
		this.setAccountCreatedDate(accountCreatedDate);
		this.setAccountStatus(accountStatus);
		this.setAccountType(accountType);
		this.setAddress(address);
		this.setBalance(balance);
		this.setContactNumber(contactNumber);
		this.setCustomer(customer);
		this.setDob(dob);

	}
    
    
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public void setAccountHolderName(String accountHolderName) throws InvalidNameException {
		
		String regEx = "^[a-zA-Z]{2,}$";
		
		if(Pattern.compile(regEx).matcher(accountHolderName).find()) {
			this.accountHolderName = accountHolderName;
		}
		else {
			throw new InvalidNameException("Name is invalid");
		}
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setAccountCreatedDate(LocalDate accountCreatedDate) {
		this.accountCreatedDate = accountCreatedDate;
	}
	
	public void setDob(LocalDate dob) throws InvalidDateException {
		
		String regEx = "^\\d{4}-\\d{2}-\\d{2}$";
		if(dob != null && dob.toString().matches(regEx)) {
			this.dob = dob;
		}
		else {
			throw new InvalidDateException("Invalid Date of birth format");
		}
		
	}
	
	public void setAccountStatus(boolean accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
