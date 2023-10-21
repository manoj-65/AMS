package com.ty.ams.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.ty.ams.entity.TimeSheet;

public interface TimeSheetService {
	ResponseEntity<TimeSheet> saveTimeSheet(TimeSheet timeSheet, int userId);

	ResponseEntity<TimeSheet> updateTimeSheet(TimeSheet timeSheet);

	ResponseEntity<TimeSheet> findTimeSheetById(int id);

	ResponseEntity<String> deleteTimeSheetById(int id);

	ResponseEntity<List<TimeSheet>> findAllTimeSheets();

	ResponseEntity<List<TimeSheet>> findByDateBetween(LocalDate fromDate, LocalDate toDate);

	ResponseEntity<List<TimeSheet>> findAllTimeSheet(int userId);

	ResponseEntity<List<TimeSheet>> findAllTimeSheetOfAYear(int year, int userId);

	ResponseEntity<TimeSheet> findByMonthName(String month, int userId);

}
