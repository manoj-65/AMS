package com.ty.ams.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ams.daoimp.UserDaoImp;
import com.ty.ams.entity.User;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.service.UserService;
import com.ty.ams.util.UserStatus;
@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDaoImp userDaoImp;
	
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User Saved Successfully...");
		structure.setBody(userDaoImp.saveUser(user));
		return new ResponseEntity<>(structure,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<User>> setUserStatusToInAcativeByUserId(UserStatus userStatus, int userId) {
		
		
		
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User Status Updated Successfully...");
		return new ResponseEntity<>(structure,HttpStatus.OK);
	}

}
