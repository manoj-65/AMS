package com.ty.ams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.ams.entity.User;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.serviceimp.UserServiceImp;
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

	@PatchMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestParam String userStatus,
			@RequestParam int userId) {
		return userServiceImp.setUserStatusToInAcativeByUserId(UserStatus.valueOf(userStatus), userId);
	}

}
