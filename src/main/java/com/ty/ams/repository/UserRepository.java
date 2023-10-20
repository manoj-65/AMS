package com.ty.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.ams.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
