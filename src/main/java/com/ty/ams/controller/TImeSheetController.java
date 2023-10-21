package com.ty.ams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	public ResponseEntity<TimeSheet> updateTimeSheet(@RequestBody TimeSheet timeSheet) {
		return timeSheetService.updateTimeSheet(timeSheet);
	}

}
