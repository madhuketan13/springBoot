package com.dnb.jdbcdemo.exceptions;

public class InvalidDateException extends Exception {
	
	public InvalidDateException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return super.toString() + super.getMessage();
	}

}
