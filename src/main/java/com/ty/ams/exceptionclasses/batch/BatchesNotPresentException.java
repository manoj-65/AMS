package com.ty.ams.exceptionclasses.batch;

public class BatchesNotPresentException extends RuntimeException{

	@Override
	public String getMessage() {
		return  "No batches are currently available.";
	}
}
