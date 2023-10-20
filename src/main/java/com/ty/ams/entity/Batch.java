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
	private int batch_id;
	private String batch_code;
	private String subject_name;
	private BatchStatus batch_status;
	private LocalDate batch_started_date;
	private LocalDate batch_ended_date;
	private LocalTime login_time;
	private LocalTime logout_time;
	private int total_days;
	private BatchMode batchMode;
	private String instituteName;
	private String location;
	@ManyToOne
	private User user;

}
