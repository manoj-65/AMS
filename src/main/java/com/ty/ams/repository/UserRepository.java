package com.ty.ams.repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.ams.entity.User;
import com.ty.ams.util.UserRole;
import com.ty.ams.util.UserStatus;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmpId(String empId);
	
	List<User> findByRole(UserRole role);
	
	List<User> findByCategory(Category category);
	
	Optional<User> findByPhone(long phone);

	Optional<User> findByEmail(String email);
	
	List<User> findByStatus(UserStatus status);
	
	Optional<User> findByEmailAndPassword(String email, String password);
	
	Optional<User> findByPhoneAndPassword(long phone, String password);
	
//	We can't execute The Not Select Query in repository 
	
//	@Query("update User u set u.userStatus=:userStatus where u.userId=:userId")
//	User setUserStatusToInAcativeByUserId(UserStatus userStatus, int userId);

}
