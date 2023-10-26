package com.ty.ams.controller;

import java.time.LocalTime;
import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.ams.entity.User;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.serviceimp.UserServiceImp;
import com.ty.ams.util.UserRole;
import com.ty.ams.util.UserStatus;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserServiceImp userServiceImp;

	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return userServiceImp.saveUser(user);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		return userServiceImp.updateUser(user);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<ResponseStructure<User>> findUserById(int userId) {
		return userServiceImp.findUserById(userId);
	}

	@GetMapping("/empid/{empId}")
	public ResponseEntity<ResponseStructure<User>> findUserByEmpId(String empId) {
		return userServiceImp.findUserByEmpId(empId);
	}

	@PostMapping("/verify")
	public ResponseEntity<ResponseStructure<User>> findUserByEmailAndPassword(String username, String password) {
		return findUserByEmailAndPassword(username, password);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<String>> deleteUserByUserId(int userId) {
		return userServiceImp.deleteUserByUserId(userId);
	}

	@GetMapping("/phone/{phone}")
	public ResponseEntity<ResponseStructure<User>> findUserByPhoneNumber(long phone) {
		return userServiceImp.findUserByPhoneNumber(phone);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<ResponseStructure<User>> findUserByEmail(String email) {
		return userServiceImp.findUserByEmail(email);
	}

	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<User>>> findAllUsers() {
		return userServiceImp.findAllUsers();
	}

	@GetMapping("/role/{role}")
	public ResponseEntity<ResponseStructure<List<User>>> findUserByRole(UserRole role) {
		return userServiceImp.findUserByRole(role);
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<ResponseStructure<List<User>>> findUserByCategory(Category category) {
		return userServiceImp.findUserByCategory(category);
	}

	@GetMapping("/statsu/{status}")
	public ResponseEntity<ResponseStructure<List<User>>> findUserByStatus(UserStatus status) {
		return userServiceImp.findUserByStatus(status);
	}

	@GetMapping("/timings/{userId}")
	public ResponseEntity<ResponseStructure<List<LocalTime>>> findBatchTimingsOfUser(int userId) {
		return userServiceImp.findBatchTimingsOfUser(userId);
	}

	public ResponseEntity<ResponseStructure<User>> findUserByPhoneAndPassword(long phone, String password) {
		return userServiceImp.findUserByPhoneAndPassword(phone, password);
	}

	@PatchMapping("/{userId}")
	public ResponseEntity<ResponseStructure<User>> setUserStatusToInAcativeByUserId(int userId) {
		return userServiceImp.setUserStatusToInAcativeByUserId(userId);

	}

}
