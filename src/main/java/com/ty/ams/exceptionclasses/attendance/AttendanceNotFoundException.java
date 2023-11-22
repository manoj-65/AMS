package com.ty.ams.exceptionclasses.attendance;

public class AttendanceNotFoundException extends RuntimeException{
	
	@Override
	public String getMessage() {
		return "The requested attendance record could not be found.";
	}

}
