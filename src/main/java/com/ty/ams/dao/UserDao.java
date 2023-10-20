package com.ty.ams.dao;

import com.ty.ams.entity.User;

public interface UserDao {

	User findUserById(long userId);

	User saveUser(User user);
	
	
	
	void deleteUser(int id);
}
