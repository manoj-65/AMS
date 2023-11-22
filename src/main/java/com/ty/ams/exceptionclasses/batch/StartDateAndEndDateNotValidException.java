package com.ty.ams.exceptionclasses.batch;

public class StartDateAndEndDateNotValidException extends RuntimeException{
@Override
public String getMessage() {
	return   "The provided Start Date and End Date are not valid. Please ensure that the dates are entered correctly." ;
}
}
