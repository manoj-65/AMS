package com.ty.ams.serviceimp;

import java.time.LocalTime;
import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ams.daoimp.UserDaoImp;
import com.ty.ams.entity.User;
import com.ty.ams.exceptionclasses.user.DuplicateEmailException;
import com.ty.ams.exceptionclasses.user.DuplicatePhoneNumberException;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.service.UserService;
import com.ty.ams.util.UserRole;
import com.ty.ams.util.UserStatus;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDaoImp userDaoImp;

	@Override
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		if (user == null)
			throw new NullPointerException("User Object Is Null no data Found in Request Body...");
		if (userDaoImp.findUserByPhoneNumber(user.getPhone()).isEmpty())
			throw new DuplicatePhoneNumberException();
		if (userDaoImp.findUserByEmail(user.getEmail()).isEmpty())
			throw new DuplicateEmailException();
		user.setPassword(user.getPassword().substring(0, 4) + (user.getPhone() + "").substring(6, 10));
		user = userDaoImp.saveUser(user);
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User Saved Successfully...");
		structure.setBody(userDaoImp.saveUser(user));
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<Optional<User>>> findUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<Optional<User>>> findUserByEmpId(String empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> findUserByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> deleteUserByUserId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> findUserByPhoneNumber(long phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<User>>> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<User>>> findUserByRole(UserRole role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<User>>> findUserByCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<User>>> findUserByStatus(UserStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<LocalTime>>> findBatchTimingsOfUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> findUserByPhoneAndPassword(long phone, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> setUserStatusToInAcativeByUserId(UserStatus userStatus, int userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
