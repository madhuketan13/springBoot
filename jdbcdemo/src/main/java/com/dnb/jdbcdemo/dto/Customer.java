package com.dnb.jdbcdemo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Customer {
	
	@Id
	@Column public int customerId;
	@Column private String customerName;
	@Column private String customerContactNumber;
	@Column private String customerAddress;
	@Column private String customerPAN;
	@Column private String customerUUID;
	
	public Customer(int customerId, String customerName, String customerContactNumber, String customerAddress,
			String customerPAN, String customerUUID) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerContactNumber = customerContactNumber;
		this.customerAddress = customerAddress;
		this.customerPAN = customerPAN;
		this.customerUUID = customerUUID;
	} 
  

}
