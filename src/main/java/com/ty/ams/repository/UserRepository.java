package com.ty.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.ams.entity.User;
import com.ty.ams.util.UserStatus;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("update User u set u.userStatus=:userStatus where u.userId=:userId")
	User setUserStatusToInAcativeByUserId(UserStatus userStatus, int userId);

}
