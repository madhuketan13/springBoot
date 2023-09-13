package com.dnb.jdbcdemo.dto;

import lombok.AllArgsConstructor;
import lombok.CustomLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.naming.InvalidNameException;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;

import com.dnb.jdbcdemo.exceptions.InvalidDateException;
import com.dnb.jdbcdemo.utils.CustomAccountIdGenerator;
import com.dnb.jdbcdemo.utils.DatePrefixedSequenceIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Data
@EqualsAndHashCode
@NoArgsConstructor
@ToString(exclude = "customer")
@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
	@GenericGenerator(name = "account_seq", strategy = "com.dnb.jdbcdemo.utils.CustomAccountIdGenerator",
	parameters = {@Parameter(name = CustomAccountIdGenerator.INCREMENT_PARAM, value = "50"),
	@Parameter(name = CustomAccountIdGenerator.VALUE_PREFIX_PARAMETER, value = "A_"),
	@Parameter(name = CustomAccountIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
	
    @Column String accountId;
	
	
	@Column(nullable = false)
	@NotBlank(message = "Account holder name should not be blank")
	private String accountHolderName;
	
	@Column private String accountType;
	
	@Min(value = 0, message = "Balance must be more than 0")
	@Column private float balance;
	
	@Column(nullable = false)
	//@Length(min = 10 , max = 10)
	@jakarta.validation.constraints.Pattern(regexp = "^[0-9]{10}$",message = "Wrong phoneNumber input")
	private String contactNumber;
	
	@NotBlank(message = "Address should not be blank")
	@Column private String address;
	
	@Column private LocalDate accountCreatedDate = LocalDate.now();
	
	@NotNull(message = "Date must be provided")
	
	@jakarta.validation.constraints.Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$",message = "Wrong phoneNumber input")
	@Column private String dob;
 
	@Column private boolean accountStatus;
    
    @Transient
    private Customer customer;
    
    
    public Account(String accountHolderName,String accountType,float balance,String contactNumber,String address,LocalDate accountCreatedDate, String dob, boolean accountStatus) throws InvalidNameException, InvalidDateException

	{

		super();

		this.setAccountHolderName(accountHolderName);
		this.setAccountCreatedDate(accountCreatedDate);
		this.setAccountStatus(accountStatus);
		this.setAccountType(accountType);
		this.setAddress(address);
		this.setBalance(balance);
		this.setContactNumber(contactNumber);
		//this.setCustomer(customer);
		this.setDob(dob);

	}
}
