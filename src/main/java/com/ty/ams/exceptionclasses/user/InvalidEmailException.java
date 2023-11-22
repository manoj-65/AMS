package com.ty.ams.exceptionclasses.user;

public class InvalidEmailException extends RuntimeException{
	@Override
	public String getMessage() {
		return "The email address provided is invalid. Please ensure it follows the correct email format.";
	}
}
