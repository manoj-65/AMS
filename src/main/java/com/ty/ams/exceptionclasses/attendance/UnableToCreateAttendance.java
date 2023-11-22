package com.ty.ams.exceptionclasses.attendance;

public class UnableToCreateAttendance extends RuntimeException {
	
	@Override
	public String getMessage() {
		return  "Unable to create attendance. Please check the details and timesheet ID." ;
	}

}
