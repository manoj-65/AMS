package com.ty.ams.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.ty.ams.util.Attendence_status;

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
	private LocalTime login_time;
	private LocalTime logout_time;
	private Attendence_status attendence_e;
	private double totalWorkingHours;

	@ManyToMany
	private List<Batch>  batch;

}