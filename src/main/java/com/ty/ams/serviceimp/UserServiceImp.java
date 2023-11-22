package com.ty.ams.serviceimp;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ty.ams.daoimp.UserDaoImp;
import com.ty.ams.entity.Batch;
import com.ty.ams.entity.User;
import com.ty.ams.exceptionclasses.user.DuplicateEmailException;
import com.ty.ams.exceptionclasses.user.DuplicatePhoneNumberException;
import com.ty.ams.exceptionclasses.user.EmployeeIDNotFoundException;
import com.ty.ams.exceptionclasses.user.IdNotFoundException;
import com.ty.ams.exceptionclasses.user.InvalidEmailOrPasswordException;
import com.ty.ams.exceptionclasses.user.InvalidEmailException;
import com.ty.ams.exceptionclasses.user.InvalidPhoneNumberException;
import com.ty.ams.exceptionclasses.user.InvalidPhoneNumberOrPasswordException;
import com.ty.ams.exceptionclasses.user.NoBatchAssignedException;
import com.ty.ams.exceptionclasses.user.NoUserFoundException;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.service.UserService;
import com.ty.ams.util.UserCategory;
import com.ty.ams.util.UserRole;
import com.ty.ams.util.UserStatus;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDaoImp userDaoImp;

	@Override
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		if (!Pattern.compile("[6-9]{1}[0-9]{9}").matcher("" + user.getPhone()).matches())
			throw new InvalidPhoneNumberException();
		if (userDaoImp.findUserByPhoneNumber(user.getPhone()).isPresent())
			throw new DuplicatePhoneNumberException();
		if (userDaoImp.findUserByEmail(user.getEmail()).isPresent())
			throw new DuplicateEmailException();
		user.setPassword(user.getEmail().substring(0, 4) + (user.getPhone() + "").substring(6, 10));
		user = userDaoImp.saveUser(user);
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("User Saved Successfully...");
		structure.setBody(user);
		return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		if (user == null)
			throw new NullPointerException("User Object Is Null no data Found in Request Body...");
		user.setPassword(user.getPassword().substring(0, 4) + (user.getPhone() + "").substring(6, 10));
		user = userDaoImp.updateUser(user);
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User Updated Successfully...");
		structure.setBody(user);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> findUserById(int id) {
		Optional<User> optional = userDaoImp.findUserById(id);
		if (optional.isEmpty())
			throw new IdNotFoundException();
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User Found Successfully...");
		structure.setBody(optional.get());
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> findUserByEmpId(String id) {
		Optional<User> optional = userDaoImp.findUserByEmpId(id);
		if (optional.isEmpty())
			throw new IdNotFoundException();
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User Found Successfully...");
		structure.setBody(optional.get());
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> deleteUserById(int id) {
		Optional<User> optional = userDaoImp.findUserById(id);
		if (optional.isEmpty())
			throw new IdNotFoundException();
		userDaoImp.deleteUserById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User Deleted Successfully...");
		structure.setBody("User Deleted Successfully...");
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> findUserByEmailAndPassword(String email, String password) {
		Optional<User> optional = userDaoImp.findUserByEmailAndPassword(email, password);
		if (optional.isEmpty())
			throw new InvalidEmailOrPasswordException();
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User Verified Successfully...");
		structure.setBody(optional.get());
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> setUserStatusToInActive(int id) {
		Optional<User> optional = userDaoImp.findUserById(id);
		if (optional.isEmpty())
			throw new IdNotFoundException();
		User user = optional.get();
		user.setUserStatus(UserStatus.valueOf("IN_ACTIVE"));
		user = userDaoImp.updateUser(user);
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User Status Set to IN_ACTIVE Done  Successfully...");
		structure.setBody(user);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<User>>> findAllUsers() {
		List<User> users = userDaoImp.findAllUsers();
		if (users.isEmpty())
			throw new NoUserFoundException();
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User found Successfully...");
		structure.setBody(users);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<User>>> findAllActiveUsers() {
		// TODO Auto-generated method stub
		List<User> users = userDaoImp.findAllActiveUsers();
		if (users.isEmpty())
			throw new NoUserFoundException();
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User found Successfully...");
		structure.setBody(users);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<User>>> findAllInActiveUsers() {

		List<User> users = userDaoImp.findAllInActiveUsers();
		if (users.isEmpty())
			throw new NoUserFoundException();
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User found Successfully...");
		structure.setBody(users);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<User>>> findUserByRole(UserRole role) {

		List<User> users = userDaoImp.findUserByRole(role);
		if (users.isEmpty())
			throw new NoUserFoundException();
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User found Successfully...");
		structure.setBody(users);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<User>>> findUserByCategory(UserCategory category) {
		List<User> users = userDaoImp.findUserByCategory(category);
		if (users.isEmpty())
			throw new NoUserFoundException();
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User found Successfully...");
		structure.setBody(users);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<User>>> findUserByStatus(UserStatus status) {
		List<User> users = userDaoImp.findUserByStatus(status);
		if (users.isEmpty())
			throw new NoUserFoundException();
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User found Successfully...");
		structure.setBody(users);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<LocalTime>>> findBatchTimingsOfUser(int userId) {
		Optional<User> optional = userDaoImp.findUserById(userId);
		if (optional.isEmpty())
			throw new IdNotFoundException();
		List<Batch> batchs = optional.get().getBatchs();
		List<LocalTime> localTimes = new ArrayList<>();
		for (Batch b : batchs) {
			localTimes.add(b.getLoginTime());
		}
		ResponseStructure<List<LocalTime>> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User found Successfully...");
		structure.setBody(localTimes);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> findUserByPhone(long phone) {
		Optional<User> optional = userDaoImp.findUserByPhoneNumber(phone);
		if (optional.isEmpty())
			throw new NoUserFoundException();
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User Found Successfully...");
		structure.setBody(optional.get());
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> findUserByEmail(String email) {
		Optional<User> optional = userDaoImp.findUserByEmail(email);
		if (optional.isEmpty())
			throw new NoUserFoundException();
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User Found Successfully...");
		structure.setBody(optional.get());
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> setUserStatusToInAcativeByUserId(int userId) {

		Optional<User> optional = userDaoImp.findUserById(userId);
		if (optional.isEmpty())
			throw new NoUserFoundException();
		User u = optional.get();
		u.setUserStatus(UserStatus.IN_ACTIVE);
		User user = userDaoImp.saveUser(u);
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("User Updated Successfully...");
		structure.setBody(user);
		return new ResponseEntity<>(structure, HttpStatus.OK);

	}
}
