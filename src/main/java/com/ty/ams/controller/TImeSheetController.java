package com.ty.ams.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ty.ams.entity.TimeSheet;
import com.ty.ams.service.TimeSheetService;

@RestController
@RequestMapping("timesheet")
public class TImeSheetController {

	@Autowired
	TimeSheetService timeSheetService;

	@PostMapping("/{userId}")
	public ResponseEntity<TimeSheet> saveTimeSheet(@RequestBody TimeSheet timeSheet, @PathVariable int userId) {
		return timeSheetService.saveTimeSheet(timeSheet, userId);
	}

	@PutMapping
	public ResponseEntity<TimeSheet> updateTimeSheet(@RequestBody TimeSheet timeSheet) {
		return timeSheetService.updateTimeSheet(timeSheet);
	}

	@DeleteMapping("/{id}/{userId}")
	public ResponseEntity<String> deleteTimeSheet(@PathVariable int id, @PathVariable int userId) {
		return timeSheetService.deleteTimeSheetById(id, userId);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TimeSheet> findTimeSheetById(@PathVariable int id) {
		return timeSheetService.findTimeSheetById(id);
	}

	@GetMapping
	public ResponseEntity<List<TimeSheet>> findAllTimeSheets() {
		return timeSheetService.findAllTimeSheetsOfAllUsers();
	}

	@GetMapping("byUserId/{userId}")
	public ResponseEntity<List<TimeSheet>> findAllTimeSheetOfUser(@PathVariable int userId) {
		return timeSheetService.findAllTimeSheetOfUser(userId);
	}

	@GetMapping("{year}/{userId}")
	public ResponseEntity<List<TimeSheet>> findAllTimeSheetOfAYearOfUser(@PathVariable int year,
			@PathVariable int userId) {
		return timeSheetService.findAllTimeSheetOfAYearOfUser(year, userId);
	}

	@GetMapping("{month}/{year}/{userId}")
	ResponseEntity<TimeSheet> findTimeSheetByMonthNameOfUser(@PathVariable String month, @PathVariable int year,
			@PathVariable int userId) {
		return timeSheetService.findTimeSheetByMonthNameOfUser(month, year, userId);
	}

	@GetMapping("byYear/{startYear}/{endYear}/{userId}")
	public ResponseEntity<List<TimeSheet>> findAllTimeSheetBetweenYearsOfUser(@PathVariable int startYear,
			@PathVariable int endYear, @PathVariable int userId) {
		return timeSheetService.findAllTimeSheetBetweenYearsOfUser(startYear, endYear, userId);
	}

	@GetMapping("byMonth/{startMonth}/{endMonth}/{year}/{userId}")
	public ResponseEntity<List<TimeSheet>> findAllTimeSheetBetweenMonthsOfUser(@PathVariable String startMonth,
			@PathVariable String endMonth, @PathVariable int year, @PathVariable int userId) {
		return timeSheetService.findAllTimeSheetBetweenMonthsOfUser(startMonth, endMonth, year, userId);
	}

	@GetMapping("{startMonth}/{start_year}/{endMonth}/{end_year}/{userId}")
	public ResponseEntity<List<TimeSheet>> findTimeSheetOfUserOnCustomDates(String startMonth, int start_year,
			String endMonth, int end_year, int user_id) {
		return timeSheetService.findTimeSheetOfUserOnCustomDates(startMonth, start_year, endMonth, end_year, user_id);
	}

	@GetMapping("{startMonth}/{start_year}/{endMonth}/{end_year}")
	public ResponseEntity<List<TimeSheet>> findTimeSheetOnCustomDates(String startMonth, int start_year,
			String endMonth, int end_year) {
		return timeSheetService.findTimeSheetOfUserOnCustomDates(startMonth, start_year, endMonth, end_year, end_year);
	}
}
