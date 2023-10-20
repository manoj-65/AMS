package com.ty.ams.entity;

import java.util.List;

import org.hibernate.engine.jdbc.batch.spi.Batch;

import com.ty.ams.util.UserCategory;
import com.ty.ams.util.UserRole;
import com.ty.ams.util.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String empId;
	private String name;
	private String email;
	private String password;
	@Column(nullable = false, unique = true)
	private long phone;
	private UserRole userRole;
	private UserStatus userStatus;
	UserCategory userCategory;
	@OneToMany
	private List<TimeSheet> timeSheets;
	@OneToMany
	private List<Batch> batchs;

}
