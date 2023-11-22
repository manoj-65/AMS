package com.ty.ams.exceptionclasses.batch;

public class BatchCodeNotFoundException extends RuntimeException{

	@Override
	public String getMessage() {
		return "The provided batch code is not valid. Please double-check and provide a valid batch code.";
	}
}
