package com.ty.ams.exceptionclasses.user;

public class InvalidPhoneNumberOrPasswordException extends RuntimeException{
	@Override
	public String getMessage() {
		return "Invalid credentials. Please verify the phone number or password entered and try again.";
	}
}
