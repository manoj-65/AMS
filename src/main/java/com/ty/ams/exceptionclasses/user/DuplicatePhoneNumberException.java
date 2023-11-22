package com.ty.ams.exceptionclasses.user;

public class DuplicatePhoneNumberException extends RuntimeException{
	@Override
	public String getMessage() {
		return "The phone number is already registered in the database.";
	}
}