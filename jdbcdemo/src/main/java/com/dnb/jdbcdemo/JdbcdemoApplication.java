package com.dnb.jdbcdemo;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.naming.InvalidNameException;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.dto.Customer;
import com.dnb.jdbcdemo.exceptions.IdNotFoundException;
import com.dnb.jdbcdemo.exceptions.InvalidDateException;
import com.dnb.jdbcdemo.service.AccountService;
import com.dnb.jdbcdemo.service.CustomerService;

@SpringBootApplication
public class JdbcdemoApplication {
	
	private static AccountService accountService = null;

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(JdbcdemoApplication.class, args);
		
		accountService = applicationContext.getBean(AccountService.class);
		
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		
		 do {
	            System.out.println("1. Create an Account");
	            System.out.println("2. Get Account Details");
	            System.out.println("3. Delete Account");
	            System.out.println("4. Get All Accounts");
	            System.out.println("5. Exit");

	            choice = sc.nextInt();

	            switch (choice) {
	                case 1 -> {
	                    
	                    String accountId = sc.next();
	                    String accountHolderName = sc.next();
	                    String accountType = sc.next();
	                    Float accountBalance = sc.nextFloat();
	                    String contactNumber = sc.next();
	                    String address = sc.next();
	                    String date = sc.next();
	                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	                    LocalDate dob = LocalDate.parse(date, formatter);
	                
	                    Boolean accountStatus = sc.nextBoolean();
	                    Customer customer =	new Customer(2,"Madhu","9594939494","Hyderabad","GGPPP4909A","4646464646");
	                    Account account;
						try {
							account = new Account(accountId,accountHolderName,accountType,accountBalance,
									contactNumber,address,LocalDate.now(),dob,accountStatus,customer);
							
							accountService.createAccount(account);
						} 
						catch (IdNotFoundException |InvalidNameException | InvalidDateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                }
	                case 2 -> {
	                    String accountId = sc.next();
	                    Optional<Account> result;
						try {
							result = accountService.getAccountById(accountId);
							System.out.println(result);
						}
						catch (InvalidNameException | InvalidDateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                    
	                }
	                case 3 -> deleteAccount();
	                case 4 -> {
	                    List<Account> allAccounts;
						try {
							allAccounts = accountService.getAllAccounts();
							 System.out.println(allAccounts);
						} catch (InvalidNameException | InvalidDateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                   
	                }
	            }

	        } while (choice != 5);
	}
	public static void deleteAccount() {
        Scanner sc = new Scanner(System.in);
        String accountId = sc.next();
        Boolean result = accountService.deleteAccountById(accountId);

        if (result) {
            System.out.println("Customer deleted Successfully");
        }
    }
}
