package com.ty.ams.exceptionclasses.user;

public class NoBatchAssignedException extends RuntimeException {
	@Override
	public String getMessage() {
		return "There are no assigned batches for this user. They have not been assigned to any batches.";

	}
}
