package com.ty.ams.exceptionclasses.timesheet;

public class TimeSheetAlreadyExists extends RuntimeException {

	@Override
	public String getMessage() {
		return  "Time sheet has already been submitted for this month." ;
	}
}
