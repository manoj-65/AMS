package com.ty.ams.exceptionclasses.user;

public class InvalidEmailOrPasswordException extends RuntimeException{
	@Override
	public String getMessage() {
		return "Invalid credentials. Please check the email or password entered and try again.";
	}
}
