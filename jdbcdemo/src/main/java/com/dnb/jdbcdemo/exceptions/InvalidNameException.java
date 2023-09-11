package com.dnb.jdbcdemo.exceptions;

public class InvalidNameException extends Exception {

	public InvalidNameException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return super.toString() + super.getMessage();
	}
}
