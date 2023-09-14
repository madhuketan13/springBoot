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
		SpringApplication.run(JdbcdemoApplication.class, args);
		
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
