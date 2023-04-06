package com.axis.exception;

public class IdNotFoundException extends Exception {

	public IdNotFoundException(int id) {
		super("User Merchant id"+id+"not found") ;
	}
}
