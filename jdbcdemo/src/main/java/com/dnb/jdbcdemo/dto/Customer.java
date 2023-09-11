package com.dnb.jdbcdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	

	public int customerId;
    private String customerName;
    private String customerContactNumber;
    private String customerAddress;
    private String customerPAN;
    private String customerUUID;
    
    
   

}
