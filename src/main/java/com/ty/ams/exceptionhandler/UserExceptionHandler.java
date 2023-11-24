package com.ty.ams.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ty.ams.exceptionclasses.user.DuplicateEmailException;
import com.ty.ams.exceptionclasses.user.DuplicatePhoneNumberException;
import com.ty.ams.exceptionclasses.user.EmployeeIDNotFoundException;
import com.ty.ams.exceptionclasses.user.IdNotFoundException;
import com.ty.ams.exceptionclasses.user.InvalidEmailException;
import com.ty.ams.exceptionclasses.user.InvalidEmailOrPasswordException;
import com.ty.ams.exceptionclasses.user.InvalidPhoneNumberException;
import com.ty.ams.exceptionclasses.user.InvalidPhoneNumberOrPasswordException;
import com.ty.ams.exceptionclasses.user.NoBatchAssignedException;
import com.ty.ams.exceptionclasses.user.NoUserFoundException;
import com.ty.ams.responsestructure.ResponseStructure;

@RestControllerAdvice
public class UserExceptionHandler {

	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<ResponseStructure<String>> duplicateEmailExceptionHandler(
			DuplicateEmailException duplicateEmailException) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("The email address provided already exists in the database. Please use a unique email address.");
		structure.setBody(duplicateEmailException.getMessage());
		return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicatePhoneNumberException.class)
	public ResponseEntity<ResponseStructure<String>> duplicatePhoneNumberExceptionHandler(
			DuplicatePhoneNumberException duplicatePhoneNumberException) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("The phone number provided already exists in the database. Please use a unique phone number.");
		structure.setBody(duplicatePhoneNumberException.getMessage());
		return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmployeeIDNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> employeeIDNotFoundExceptionHandler(
			EmployeeIDNotFoundException employeeIDNotFoundException) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage( "No employee found for the provided EMPID. Please verify and enter a valid EMPID.");
		structure.setBody(employeeIDNotFoundException.getMessage());
		return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> idNotFoundExceptionHandler(
			IdNotFoundException idNotFoundException) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setMessage("No user found for the provided user ID. Please verify and enter a valid user ID.");
		structure.setBody(idNotFoundException.getMessage());
		return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidEmailOrPasswordException.class)
	public ResponseEntity<ResponseStructure<String>> invalidEmailOrPasswordExceptionHandler(
			InvalidEmailOrPasswordException invalidEmailOrPasswordException) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Invalid credentials. Please check the email or password and try again.");
		structure.setBody(invalidEmailOrPasswordException.getMessage());
		return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidEmailException.class)
	public ResponseEntity<ResponseStructure<String>> invalidEmailExceptionHandler(
			InvalidEmailException invalidEmailException) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("The email provided is not valid. Please enter a valid email address.");
		structure.setBody(invalidEmailException.getMessage());
		return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidPhoneNumberException.class)
	public ResponseEntity<ResponseStructure<String>> invalidPhoneNumberExceptionHandler(
			InvalidPhoneNumberException invalidPhoneNumberException) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("The phone number you provided is invalid. Please enter a valid phone number.");
		structure.setBody(invalidPhoneNumberException.getMessage());
		return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidPhoneNumberOrPasswordException.class)
	public ResponseEntity<ResponseStructure<String>> invalidPhoneNumberOrPasswordExceptionHandler(
			InvalidPhoneNumberOrPasswordException invalidPhoneNumberOrPasswordException) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Invalid credentials. Please check the email or password entered and try again.");
		structure.setBody(invalidPhoneNumberOrPasswordException.getMessage());
		return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoBatchAssignedException.class)
	public ResponseEntity<ResponseStructure<String>> noBatchAssignedExceptionHandler(
			NoBatchAssignedException noBatchAssignedException) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("No batches have been assigned for the particular user.");
		structure.setBody(noBatchAssignedException.getMessage());
		return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoUserFoundException.class)
	public ResponseEntity<ResponseStructure<String>> noUserFoundExceptionHandler(
			NoUserFoundException noUserFoundException) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("The database is currently empty, and no user records are present.");
		structure.setBody(noUserFoundException.getMessage());
		return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
	}
	
} 
