package com.ty.ams.exceptionclasses.user;

public class IdNotFoundException extends RuntimeException{
	@Override
	public String getMessage() {
		return "The ID was not found for the specified entity.";
	}
}
