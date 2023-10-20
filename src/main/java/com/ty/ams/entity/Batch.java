package com.ty.ams.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ty.ams.util.BatchMode;
import com.ty.ams.util.BatchStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Batch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int batchId;
	private String batchCode;
	private String subjectName;
	private BatchStatus batchStatus;
	private LocalDate batchStartDate;
	private LocalDate batchEndDate;
	private LocalTime loginTime;
	private LocalTime logoutTime;
	private int totalDays;
	private BatchMode batchMode;
	private String instituteName;
	private String location;
	@ManyToOne
	private User user;

}
