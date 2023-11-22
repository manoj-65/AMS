package com.ty.ams.exceptionclasses.user;

public class NoUserFoundException extends RuntimeException {
	@Override
	public String getMessage() {
		return "The database is currently empty, and there are no user records present.";
	}
}