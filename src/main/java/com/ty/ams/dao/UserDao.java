package com.ty.ams.dao;

import java.util.Optional;

import com.ty.ams.entity.User;

public interface UserDao {

	Optional<User> findUserById(int userId);

	User saveUser(User user);
	
	User updateUser(User user);
}
