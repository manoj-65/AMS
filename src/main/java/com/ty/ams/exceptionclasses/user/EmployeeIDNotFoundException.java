package com.ty.ams.exceptionclasses.user;

public class EmployeeIDNotFoundException extends RuntimeException{
	@Override
	public String getMessage() {
		return "The employee ID (EMPID) provided is not found in the database. Please check and ensure you have entered a valid EMPID.";
	}
}
