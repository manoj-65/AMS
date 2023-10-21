package com.ty.ams.daoimp;

import java.time.LocalTime;

import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.ams.dao.UserDao;
import com.ty.ams.entity.User;
import com.ty.ams.repository.UserRepository;
import com.ty.ams.util.UserRole;
import com.ty.ams.util.UserStatus;

@Repository
public class UserDaoImp implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> findUserById(int userId) {
		return userRepository.findById(userId);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<User> findUserByEmpId(String empId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<User> findUserByRole(UserRole role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUserByCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public User setUserStatusToInAcativeByUserId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByPhoneNumber(long phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUserByStatus(UserStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LocalTime> findBatchTimingsOfUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByPhoneAndPassword(long phone, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
