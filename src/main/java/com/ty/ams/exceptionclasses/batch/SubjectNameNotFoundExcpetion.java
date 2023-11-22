package com.ty.ams.exceptionclasses.batch;

public class SubjectNameNotFoundExcpetion extends RuntimeException{
 @Override
public String getMessage() {
	return  "The subject name is not present or does not exist."  ;
}
}
