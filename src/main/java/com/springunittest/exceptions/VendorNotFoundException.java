package com.springunittest.exceptions;


public class VendorNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String msg;
	
	public VendorNotFoundException() {
		super();
	}
	
	public VendorNotFoundException(String msg) {
		this.msg = msg;
	}
	
}
