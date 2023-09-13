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
	
	private static CustomerService customerService = null;

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(JdbcdemoApplication.class, args);
		
		accountService = applicationContext.getBean(AccountService.class);
		customerService = applicationContext.getBean(CustomerService.class);
		
		
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
	                    System.out.println("Name:");
	                    String accountHolderName = sc.next();
	                    
	                    System.out.println("Account type:");
	                    String accountType = sc.next();
	                    
	                    System.out.println("Balance:");
	                    Float accountBalance = sc.nextFloat();
	                    
	                    System.out.println("contactNumber:");
	                    String contactNumber = sc.next();
	                    
	                    System.out.println("address:");
	                    String address = sc.next();
	                    
	                    System.out.println("date:");
	                    String dob = sc.next();
	                
	                    System.out.println("account status:");
	                    Boolean accountStatus = sc.nextBoolean();
	                    Account account;
						try {
							account = new Account(accountHolderName,accountType,accountBalance,
									contactNumber,address,LocalDate.now(),dob,accountStatus);
							
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
						try {
							accountService.getAllAccounts().forEach(account -> {
								System.out.println(account.getAccountId() + " " + account.getAccountHolderName()
								+ " " + account.getAccountType() + " " + account.getBalance() + " " + account.getContactNumber()
								+ " " + account.getAddress() + " " + account.getDob() + " " + account.getAccountCreatedDate()
								+ " " + account.isAccountStatus());
							});
						} catch (InvalidNameException | InvalidDateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                   
	                }
	            }

	        } while (choice != 5);
		
		
//		do {
//            System.out.println("1. Create an Customer");
//            System.out.println("2. Get Customer Details");
//            System.out.println("3. Delete Customer");
//            System.out.println("4. Get All Customers");
//            System.out.println("5. Exit");
//
//            choice = sc.nextInt();
//
//            switch (choice) {
//                case 1 -> {
//                    
//                    int customerId = sc.nextInt();
//                    String customerName = sc.next();
//                    String customerContactNumber = sc.next();
//                    String customerAddress = sc.next();
//                    String customerPAN = sc.next();
//                    String customerUUID = sc.next();
//                    
//                    Customer customer;
//					
//					customer = new Customer(customerId,customerName,customerContactNumber,customerAddress,
//							customerPAN,customerUUID);
//					
//					customerService.createAccount(customer);
//					
//                }
//                case 2 -> {
//                    int customerId = sc.nextInt();
//                    Optional<Customer> result;
//					
//					result = customerService.getCustomerById(customerId);
//					System.out.println(result);
//                    
//                }
//                case 3 -> deleteCustomer();
//                case 4 -> {
//					
//					customerService.getAllCustomers().forEach(customer -> {
//						System.out.println(customer.getCustomerId() + " " + customer.getCustomerName()
//						+ " " + customer.getCustomerContactNumber() + " " + customer.getCustomerAddress()+ " " + customer.getCustomerPAN()
//						+ " " + customer.getCustomerUUID());
//					});
//                }
//            }
//
//        } while (choice != 5);
	}
	
	public static void deleteAccount() {
        Scanner sc = new Scanner(System.in);
        String accountId = sc.next();
        Boolean result = null;
		try {
			result = accountService.deleteAccountById(accountId);
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (result) {
            System.out.println("Customer deleted Successfully");
        }
    }
	public static void deleteCustomer() {
        Scanner sc = new Scanner(System.in);
        int customerId = sc.nextInt();
        Boolean result = null;
		try {
			result = customerService.deleteCustomerById(customerId);
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (result) {
            System.out.println("Customer deleted Successfully");
        }
    }
}
