package com.ty.ams.serviceimp;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ams.daoimp.BatchDaoImp;
import com.ty.ams.daoimp.UserDaoImp;
import com.ty.ams.dto.MailRequest;
import com.ty.ams.dto.UserDto;
import com.ty.ams.entity.Batch;
import com.ty.ams.entity.User;
import com.ty.ams.exceptionclasses.batch.BatchIdNotFoundException;
import com.ty.ams.exceptionclasses.user.DuplicateEmailException;
import com.ty.ams.exceptionclasses.user.DuplicatePhoneNumberException;
import com.ty.ams.exceptionclasses.user.EmployeeIDNotFoundException;
import com.ty.ams.exceptionclasses.user.IdNotFoundException;
import com.ty.ams.exceptionclasses.user.InvalidEmailOrPasswordException;
import com.ty.ams.exceptionclasses.user.InvalidPhoneNumberException;
import com.ty.ams.exceptionclasses.user.NoUserFoundException;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.service.UserService;
import com.ty.ams.util.UserCategory;
import com.ty.ams.util.UserRole;
import com.ty.ams.util.UserStatus;
import com.ty.ams.util.UserUtill;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDaoImp userDaoImp;
	@Autowired
	private BatchDaoImp batchDaoImp;
	@Autowired(required = true)
	private EmailSenderService senderService;
	@Autowired
	private UserUtill userUtill;

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

		// sending email

		Map<String, Object> user_map = new HashMap<>();
		user_map.put("userName", user.getName());
		user_map.put("userEmail", user.getEmail());
		user_map.put("userPassword", user.getPassword());
		MailRequest request = new MailRequest();
		request.setName(user.getName());
		request.setSubject("your account has been created successfully");
		request.setFrom("podichervupavansai@gmail.com");
		request.setTo(user.getEmail());
		senderService.sendEmail(request, user_map);

		// email-sent successfully

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
//		if (user.getUserStatus() == UserStatus.valueOf("ACTIVE"))
		user.setUserStatus(UserStatus.valueOf("IN_ACTIVE"));
//		else
//			user.setUserStatus(UserStatus.valueOf("ACTIVE"));
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub

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

	public ResponseEntity<ResponseStructure<List<UserDto>>> findAllTrainersToAssiginBatch() {
		List<User> users = userDaoImp.findUserByRole(UserRole.TRAINER);
		List<UserDto> dtos = userUtill.getUserDto(users);
		ResponseStructure<List<UserDto>> structure = new ResponseStructure<>();
		structure.setBody(dtos);
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Found");
		return new ResponseEntity<ResponseStructure<List<UserDto>>>(structure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> assignBatchToUser(int batchId, int userId) {
		Optional<User> user1 = userDaoImp.findUserById(userId);
		if(user1.isEmpty()) {
			throw new EmployeeIDNotFoundException();
		}
		Optional<Batch> batch1 = batchDaoImp.findBatchById(batchId);
		if(batch1.isEmpty()) {
			throw new BatchIdNotFoundException();
		}
		User user = user1.get();
		Batch batch = batch1.get();
		List<Batch> batchs = user.getBatchs();
		try {
			batchs.add(batch);
		}catch(Exception e) {
			batchs = new ArrayList<>();
			batchs.add(batch);
		}
		batch.setUser(user);
		batchDaoImp.updateBatch(batch);
		user.setBatchs(batchs);
		userDaoImp.updateUser(user);
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Batch Assigned To User Successfully...");
		structure.setBody(user);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

//	@Override
//	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
////		if (user == null)
////			throw new NullPointerException("User Object Is Null no data Found in Request Body...");
//		if (!Pattern.compile("[6-9]{1}[0-9]{9}").matcher("" + user.getPhone()).matches())
//			throw new InvalidPhoneNumberException();
//		if (userDaoImp.findUserByPhoneNumber(user.getPhone()).isPresent())
//			throw new DuplicatePhoneNumberException();
//		if (userDaoImp.findUserByEmail(user.getEmail()).isPresent())
//			throw new DuplicateEmailException();
//		
//		user.setPassword(user.getEmail().substring(0, 4) + (user.getPhone() + "").substring(6, 10));
//		user = userDaoImp.saveUser(user);
//		ResponseStructure<User> structure = new ResponseStructure<>();
//		structure.setStatusCode(HttpStatus.CREATED.value());
//		structure.setMessage("User Saved Successfully...");
//		structure.setBody(user);
//		return new ResponseEntity<>(structure, HttpStatus.CREATED);
//	}
//
//	@Override
//	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
//		if (user == null)
//			throw new NullPointerException("User Object Is Null no data Found in Request Body...");
//		user.setPassword(user.getPassword().substring(0, 4) + (user.getPhone() + "").substring(6, 10));
//		user = userDaoImp.updateUser(user);
//		ResponseStructure<User> structure = new ResponseStructure<>();
//		structure.setStatusCode(HttpStatus.OK.value());
//		structure.setMessage("User Updated Successfully...");
//		structure.setBody(user);
//		return new ResponseEntity<>(structure, HttpStatus.OK);
//	}
//
//	@Override
//	public ResponseEntity<ResponseStructure<User>> findUserById(int userId) {
//		Optional<User> optional = userDaoImp.findUserById(userId);
//		if (optional.isEmpty())
//			throw new IdNotFoundException();
//		ResponseStructure<User> structure = new ResponseStructure<>();
//		structure.setStatusCode(HttpStatus.OK.value());
//		structure.setMessage("User Found Successfully...");
//		structure.setBody(optional.get());
//		return new ResponseEntity<>(structure, HttpStatus.OK);
//	}
//
//	@Override
//	public ResponseEntity<ResponseStructure<User>> findUserByEmpId(String empId) {
//		Optional<User> optional = userDaoImp.findUserByEmpId(empId);
//		if (optional.isEmpty())
//			throw new EmployeeIDNotFoundException();
//		ResponseStructure<User> structure = new ResponseStructure<>();
//		structure.setStatusCode(HttpStatus.OK.value());
//		structure.setMessage("User Found Successfully...");
//		structure.setBody(optional.get());
//		return new ResponseEntity<>(structure, HttpStatus.OK);
//	}
//
//	public ResponseEntity<ResponseStructure<User>> verifyUserByCredentials(String username,String password){
//		if(Pattern.compile("[6-9]{1}[0-9]{9}").matcher(username).matches())
//			return findUserByPhoneAndPassword(Long.parseLong(username), password);
//		return findUserByEmailAndPassword(username, password);
//	}
//	
//	
//	
//	@Override
//	public ResponseEntity<ResponseStructure<User>> findUserByEmailAndPassword(String email, String password) {
//		Optional<User> optional = userDaoImp.findUserByEmailAndPassword(email, password);
//		if (optional.isEmpty())
//			throw new InvalidEmailOrPasswordException();
//		ResponseStructure<User> structure = new ResponseStructure<>();
//		structure.setStatusCode(HttpStatus.OK.value());
//		structure.setMessage("User Verified Successfully...");
//		structure.setBody(optional.get());
//		return new ResponseEntity<>(structure, HttpStatus.OK);
//	}
//
//	@Override
//	public ResponseEntity<ResponseStructure<String>> deleteUserByUserId(int id) {
//		Optional<User> optional = userDaoImp.findUserById(id);
//		if (optional.isEmpty())
//			throw new IdNotFoundException();
//		userDaoImp.deleteUserById(id);
//		ResponseStructure<String> structure = new ResponseStructure<>();
//		structure.setStatusCode(HttpStatus.OK.value());
//		structure.setMessage("User Deleted Successfully...");
//		structure.setBody("User Deleted Successfully...");
//		return new ResponseEntity<>(structure, HttpStatus.OK);
//	}
//
//	@Override
//	public ResponseEntity<ResponseStructure<User>> findUserByPhoneNumber(long phone) {
//		Optional<User> optional = userDaoImp.findUserByPhoneNumber(phone);
//		if (optional.isEmpty())
//			throw new InvalidPhoneNumberException();
//		ResponseStructure<User> structure = new ResponseStructure<>();
//		structure.setStatusCode(HttpStatus.OK.value());
//		structure.setMessage("User found Successfully...");
//		structure.setBody(optional.get());
//		return new ResponseEntity<>(structure, HttpStatus.OK);
//	}
//
//	@Override
//	public ResponseEntity<ResponseStructure<User>> findUserByEmail(String email) {
//		Optional<User> optional = userDaoImp.findUserByEmail(email);
//		if (optional.isEmpty())
//			throw new InvalidEmailException();
//		ResponseStructure<User> structure = new ResponseStructure<>();
//		structure.setStatusCode(HttpStatus.OK.value());
//		structure.setMessage("User found Successfully...");
//		structure.setBody(optional.get());
//		return new ResponseEntity<>(structure, HttpStatus.OK);
//	}
//
//	@Override
//	public ResponseEntity<ResponseStructure<List<User>>> findAllUsers() {
//		List<User> users = userDaoImp.findAllUsers();
//		if (users.isEmpty())
//			throw new NoUserFoundException();
//		ResponseStructure<List<User>> structure = new ResponseStructure<>();
//		structure.setStatusCode(HttpStatus.OK.value());
//		structure.setMessage("User found Successfully...");
//		structure.setBody(users);
//		return new ResponseEntity<>(structure, HttpStatus.OK);
//	}
//
//	@Override
//	public ResponseEntity<ResponseStructure<List<User>>> findUserByRole(UserRole role) {
//		List<User> users = userDaoImp.findUserByRole(role);
//		if (users.isEmpty())
//			throw new NoUserFoundException();
//		ResponseStructure<List<User>> structure = new ResponseStructure<>();
//		structure.setStatusCode(HttpStatus.OK.value());
//		structure.setMessage("User found Successfully...");
//		structure.setBody(users);
//		return new ResponseEntity<>(structure, HttpStatus.OK);
//	}
//
//	@Override
//	public ResponseEntity<ResponseStructure<List<User>>> findUserByCategory(Category category) {
//		List<User> users = userDaoImp.findUserByCategory(category);
//		if (users.isEmpty())
//			throw new NoUserFoundException();
//		ResponseStructure<List<User>> structure = new ResponseStructure<>();
//		structure.setStatusCode(HttpStatus.OK.value());
//		structure.setMessage("User found Successfully...");
//		structure.setBody(users);
//		return new ResponseEntity<>(structure, HttpStatus.OK);
//	}
//
//	@Override
//	public ResponseEntity<ResponseStructure<List<User>>> findUserByStatus(UserStatus status) {
//		List<User> users = userDaoImp.findUserByStatus(status);
//		if (users.isEmpty())
//			throw new NoUserFoundException();
//		ResponseStructure<List<User>> structure = new ResponseStructure<>();
//		structure.setStatusCode(HttpStatus.OK.value());
//		structure.setMessage("User found Successfully...");
//		structure.setBody(users);
//		return new ResponseEntity<>(structure, HttpStatus.OK);
//	}
//
//	@Override
//	public ResponseEntity<ResponseStructure<List<LocalTime>>> findBatchTimingsOfUser(int userId) {
//		Optional<User> optional = userDaoImp.findUserById(userId);
//		if (optional.isEmpty())
//			throw new IdNotFoundException();
//		User user = optional.get();
//		List<Batch> batchs = user.getBatchs();
//		if (batchs.isEmpty())
//			throw new NoBatchAssignedException();
//		List<LocalTime> batchTimings = new ArrayList<>();
//		System.out.println(batchs.stream().filter(batch -> (batch.getLoginTime()) != null)
//				.map(batch -> batchTimings.add(batch.getLoginTime())).collect(Collectors.toList()));
//		if (batchTimings.isEmpty())
//			throw new NoBatchAssignedException();
//		ResponseStructure<List<LocalTime>> structure = new ResponseStructure<>();
//		structure.setStatusCode(HttpStatus.OK.value());
//		structure.setMessage("Batch Timings of User Found Successfully...");
//		structure.setBody(batchTimings);
//		return new ResponseEntity<>(structure, HttpStatus.OK);
//	}
//
//	@Override
//	public ResponseEntity<ResponseStructure<User>> findUserByPhoneAndPassword(long phone, String password) {
//		Optional<User> optional = userDaoImp.findUserByPhoneAndPassword(phone, password);
//		if (optional.isEmpty())
//			throw new InvalidPhoneNumberOrPasswordException();
//		ResponseStructure<User> structure = new ResponseStructure<>();
//		structure.setStatusCode(HttpStatus.OK.value());
//		structure.setMessage("User Verified Successfully...");
//		structure.setBody(optional.get());
//		return new ResponseEntity<>(structure, HttpStatus.OK);
//	}
//
//	@Override
//	public ResponseEntity<ResponseStructure<User>> setUserStatusToInAcativeByUserId(int userId) {
//		Optional<User> optional = userDaoImp.findUserById(userId);
//		if (optional.isEmpty())
//			throw new IdNotFoundException();
//		User user = optional.get();
////		if (user.getUserStatus() == UserStatus.valueOf("ACTIVE"))
//		user.setUserStatus(UserStatus.valueOf("IN_ACTIVE"));
////		else
////			user.setUserStatus(UserStatus.valueOf("ACTIVE"));
//		user = userDaoImp.updateUser(user);
//		ResponseStructure<User> structure = new ResponseStructure<>();
//		structure.setStatusCode(HttpStatus.OK.value());
//		structure.setMessage("User Status Set to IN_ACTIVE Done  Successfully...");
//		structure.setBody(user);
//		return new ResponseEntity<>(structure, HttpStatus.OK);
//	}
}
