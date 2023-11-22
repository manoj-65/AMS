package com.ty.ams.exceptionclasses.batch;

public class NoSuchBatchModeFoundException extends RuntimeException {
	@Override
	public String getMessage() {
		return  "The specified Batch Mode does not exist." ;
	}

}
