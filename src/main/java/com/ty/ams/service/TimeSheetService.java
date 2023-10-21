package com.ty.ams.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ty.ams.entity.TimeSheet;

public interface TimeSheetService {
	ResponseEntity<TimeSheet> saveTimeSheet(TimeSheet timeSheet, int userId);

	ResponseEntity<TimeSheet> updateTimeSheet(TimeSheet timeSheet);

	ResponseEntity<TimeSheet> findTimeSheetById(int id);

	ResponseEntity<String> deleteTimeSheetById(int id, int userId);

	ResponseEntity<List<TimeSheet>> findAllTimeSheetsOfAllUsers();

	ResponseEntity<List<TimeSheet>> findAllTimeSheetOfUser(int userId);

	ResponseEntity<List<TimeSheet>> findAllTimeSheetOfAYearOfUser(int year, int userId);

	ResponseEntity<TimeSheet> findTimeSheetByMonthNameOfUser(String month, int year, int userId);

	ResponseEntity<List<TimeSheet>> findAllTimeSheetBetweenYearsOfUser(int startYear, int endYear, int userId);

	ResponseEntity<List<TimeSheet>> findAllTimeSheetBetweenMonthsOfUser(String startMonth, String endMonth, int year,
			int userId);

	ResponseEntity<List<TimeSheet>> findTimeSheetByMonthNameOfAllEmployees(String month, int year);

	ResponseEntity<List<TimeSheet>> findTimeSheetOfUserOnCustomDates(String startMonth, int start_year, String endMonth,
			int end_year, int user_id);

	ResponseEntity<List<TimeSheet>> findTimeSheetOnCustomDates(String startMonth, int start_year, String endMonth,
			int end_year);
}
