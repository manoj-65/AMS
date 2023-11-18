package com.ty.ams.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BatchAttendance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private LocalDate localDate;
	private int numOfStudents;
//	@OneToMany
//	private List<Image> images;

}
