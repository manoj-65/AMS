package com.ty.ams.daoimp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ty.ams.dao.UserDao;
import com.ty.ams.entity.User;
import com.ty.ams.repository.UserRepository;

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

}
