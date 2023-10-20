package com.ty.ams.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.ams.entity.TimeSheet;

public interface TimeSheetRepository extends JpaRepository<TimeSheet, Integer> {
	List<TimeSheet> findByDateBetween(LocalDate fromDate, LocalDate toDate);

	List<TimeSheet> findByUserId(int id);

	TimeSheet findByMonthName(String month, int userId);
}
