package com.ty.ams.exceptionclasses.timesheet;

public class TimeSheetDoesNotExist extends RuntimeException {
	
	@Override
	public String getMessage() {
		return   "No time sheet found for the specified ID." ;
	}

}
