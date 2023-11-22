package com.ty.ams.exceptionclasses.user;

public class DuplicateEmailException extends RuntimeException{
	@Override
	public String getMessage() {
		return "The email address is already registered in the database";
	}
}