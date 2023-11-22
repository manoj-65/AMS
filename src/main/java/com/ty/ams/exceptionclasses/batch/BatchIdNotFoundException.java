package com.ty.ams.exceptionclasses.batch;

public class BatchIdNotFoundException extends RuntimeException{

	@Override
	public String getMessage() {
		return "Batch ID not found. Please provide a valid Batch ID." ;
	}
}
