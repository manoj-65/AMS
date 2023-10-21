package com.ty.ams.service;

import org.springframework.http.ResponseEntity;

import com.ty.ams.entity.User;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.util.UserStatus;

public interface UserService {
	ResponseEntity<ResponseStructure<User>> setUserStatusToInAcativeByUserId(UserStatus userStatus, int userId);
}
