package com.ty.ams.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.ty.ams.util.AttendenceStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int attendanceId;
	private LocalDate date;
	private LocalTime loginTime;
	private LocalTime logoutTime;
	private AttendenceStatus attendenceStatus;
	private double totalWorkingHours;
	@ManyToMany
	private List<Batch>  batchs;
	
}