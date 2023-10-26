package com.ty.ams.exceptionclasses.user;

public class InvalidEmailAndPasswordException extends RuntimeException{
	@Override
	public String getMessage() {
		return "Inavalid Credentials, Invalid Email or Password";
	}
}
