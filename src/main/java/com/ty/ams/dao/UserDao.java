package com.ty.ams.dao;

import java.time.LocalTime;
import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import com.ty.ams.entity.User;
import com.ty.ams.util.UserRole;
import com.ty.ams.util.UserStatus;

public interface UserDao {

	User saveUser(User user);

	User updateUser(User user);

	Optional<User> findUserById(int userId);

	void deleteUser(int id);

	Optional<User> findUserByEmpId(String empId);

	List<User> findUserByRole(UserRole role);

	List<User> findUserByCategory(Category category);

	User setUserStatusToInAcativeByUserId(UserStatus userStatus, int userId);

	List<User> findAllUsers();

	User findUserByPhoneNumber(long phone);

	User findUserByEmail(String email);

	List<User> findUserByStatus(UserStatus status);

	User findUserByEmailAndPassword(String email, String password);

	List<LocalTime> findBatchTimingsOfUser(int userId);

	User findUserByPhoneAndPassword(long phone, String password);

}
