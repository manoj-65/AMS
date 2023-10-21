package com.ty.ams.serviceimp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ams.dao.TimeSheetDao;
import com.ty.ams.dao.UserDao;
import com.ty.ams.entity.TimeSheet;
import com.ty.ams.entity.User;
import com.ty.ams.service.TimeSheetService;

@Service
public class TimeSheetServiceImp implements TimeSheetService {

	@Autowired
	TimeSheetDao timeSheetDao;

	@Autowired
	UserDao userDao;

	@Override
	public ResponseEntity<TimeSheet> saveTimeSheet(TimeSheet timeSheet, int userId) {
		Optional<User> user = userDao.findUserById(userId);
		if (user != null) {
			TimeSheet sheet = timeSheetDao.saveTimeSheet(timeSheet);
			return new ResponseEntity<>(sheet, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<TimeSheet> updateTimeSheet(TimeSheet timeSheet) {
		try {
			timeSheetDao.updateTimeSheet(timeSheet);
			return new ResponseEntity<TimeSheet>(timeSheet, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<TimeSheet>(timeSheet, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<TimeSheet> findTimeSheetById(int id) {
		Optional<TimeSheet> sheet = timeSheetDao.findTimeSheetById(id);
		if (sheet.isPresent()) {
			return new ResponseEntity<TimeSheet>(sheet.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<String> deleteTimeSheetById(int id) {
		try {
			timeSheetDao.deleteTimeSheetById(id);
			return new ResponseEntity<>("SUCCESS", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<List<TimeSheet>> findAllTimeSheets() {
		try {
			List<TimeSheet> timeSheets = timeSheetDao.findAllTimeSheets();
			return new ResponseEntity<List<TimeSheet>>(timeSheets, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<TimeSheet>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<List<TimeSheet>> findByDateBetween(LocalDate fromDate, LocalDate toDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<TimeSheet>> findAllTimeSheet(int userId) {
		try {
			List<TimeSheet> timeSheets = timeSheetDao.findAllTimeSheet(userId);
			return new ResponseEntity<List<TimeSheet>>(timeSheets, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<TimeSheet>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<List<TimeSheet>> findAllTimeSheetOfAYear(int year, int userId) {
		try {
			List<TimeSheet> listOfTimeSheets = timeSheetDao.findAllTimeSheet(userId);
			List<TimeSheet> timesheetOfYear = null;
			if (listOfTimeSheets != null) {
				timesheetOfYear = listOfTimeSheets.stream()
						.filter(timeSheet -> timeSheet.getStart_date().getYear() == year).collect(Collectors.toList());
			}
			return new ResponseEntity<List<TimeSheet>>(timesheetOfYear, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<TimeSheet>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<TimeSheet> findByMonthName(String month, int userId) {
		// TODO Auto-generated method stub
		return null;
	}
}