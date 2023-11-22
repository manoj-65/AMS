package com.ty.ams.exceptionclasses.batch;

public class StartedDateInvalidException extends RuntimeException{

	@Override
	public String getMessage() {
		return  "The given date for the batch start date is not valid." ;
	}
	
}
