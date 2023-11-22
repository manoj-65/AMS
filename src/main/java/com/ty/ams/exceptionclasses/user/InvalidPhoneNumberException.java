package com.ty.ams.exceptionclasses.user;

public class InvalidPhoneNumberException extends RuntimeException{
	@Override
	public String getMessage() {
		return "The provided phone number is invalid. Please ensure you use a valid phone number format.";
	}
}