package com.ty.ams.serviceimp;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
import com.ty.ams.exceptionclasses.timesheet.TimeSheetAlreadyExists;
import com.ty.ams.service.TimeSheetService;

@Service
public class TimeSheetServiceImp implements TimeSheetService {

	@Autowired
	TimeSheetDao timeSheetDao;

	@Autowired
	UserDao userDao;

	@Override
	public ResponseEntity<TimeSheet> saveTimeSheet(TimeSheet timeSheet, int userId) {
		Optional<User> optionalUser = userDao.findUserById(userId);
		if (optionalUser.isPresent()) {
			LocalDate currentDate = LocalDate.now();
			List<TimeSheet> listOfTimeSheets = optionalUser.get().getTimeSheets();
			if (listOfTimeSheets != null) {
				Optional<TimeSheet> timesheet = listOfTimeSheets.stream()
						.filter(sheet -> sheet.getStart_date().getMonth().equals(currentDate.getMonth())
								&& timeSheet.getStart_date().getYear() == currentDate.getYear())
						.findAny();
				if (timesheet.isPresent()) {
					throw new TimeSheetAlreadyExists();
				} else {
					User user = optionalUser.get();
					int month = currentDate.getMonth().getValue();
					int year = currentDate.getYear();
					if (month == 12) {
						year += 1;
						timeSheet.setEnd_date(LocalDate.parse("'" + year + "'-'" + month + 1 + "'-26"));
					} else {
						timeSheet.setEnd_date(LocalDate.parse("'" + year + "'-'" + month + 1 + "'-25"));
					}
					timeSheetDao.saveTimeSheet(timeSheet);
					user.getTimeSheets().add(timeSheet);
					userDao.saveUser(user);
					return new ResponseEntity<>(timeSheet, HttpStatus.CREATED);
				}
			} else {
				User user = optionalUser.get();
				timeSheetDao.saveTimeSheet(timeSheet);
				user.getTimeSheets().add(timeSheet);
				userDao.saveUser(user);
				return new ResponseEntity<>(timeSheet, HttpStatus.CREATED);
			}
		} else {
//			return new UserNotFoundException();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

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
	public ResponseEntity<String> deleteTimeSheetById(int id, int userId) {
		try {
			User user = userDao.findUserById(userId).get();
			user.getTimeSheets().remove(timeSheetDao.findTimeSheetById(id).get());
			userDao.updateUser(user);
			timeSheetDao.deleteTimeSheetById(id);
			return new ResponseEntity<>("SUCCESS", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<List<TimeSheet>> findAllTimeSheetsOfAllUsers() {
		try {
			List<TimeSheet> timeSheets = timeSheetDao.findAllTimeSheets();
			return new ResponseEntity<List<TimeSheet>>(timeSheets, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<TimeSheet>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<List<TimeSheet>> findAllTimeSheetOfUser(int userId) {
		try {
			List<TimeSheet> timeSheets = timeSheetDao.findAllTimeSheet(userId);
			return new ResponseEntity<List<TimeSheet>>(timeSheets, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<TimeSheet>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<List<TimeSheet>> findAllTimeSheetOfAYearOfUser(int year, int userId) {
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
	public ResponseEntity<TimeSheet> findTimeSheetByMonthNameOfUser(String month, int year, int userId) {
		Optional<TimeSheet> optTimeSheet = null;
		try {
			Optional<User> optUser = userDao.findUserById(userId);
			if (optUser.isPresent()) {
				User user = optUser.get();
				List<TimeSheet> timeSheets = user.getTimeSheets();
				optTimeSheet = timeSheets.stream()
						.filter(timeSheet -> timeSheet.getStart_date().getMonth()
								.getDisplayName(TextStyle.FULL, Locale.getDefault()).equalsIgnoreCase(month)
								&& timeSheet.getStart_date().getYear() == year)
						.findAny();
			}
			return new ResponseEntity<TimeSheet>(optTimeSheet.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<TimeSheet>> findAllTimeSheetBetweenYearsOfUser(int startYear, int endYear, int userId) {
		try {
			User user = userDao.findUserById(userId).get();
			List<TimeSheet> timeSheets = user.getTimeSheets().stream()
					.filter(timeSheet -> timeSheet.getStart_date().getYear() >= startYear
							&& timeSheet.getStart_date().getYear() <= endYear)
					.collect(Collectors.toList());
			return new ResponseEntity<List<TimeSheet>>(timeSheets, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<TimeSheet>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseEntity<List<TimeSheet>> findAllTimeSheetBetweenMonthsOfUser(String startMonth, String endMonth,
			int year, int userId) {
		try {
			User user = userDao.findUserById(userId).get();
			List<TimeSheet> timeSheets = user.getTimeSheets().stream()
					.filter(timeSheet -> timeSheet.getStart_date().getYear() == year
							&& timeSheet.getStart_date().getMonth().getValue() >= Month
									.valueOf(startMonth.toUpperCase()).getValue()
							&& timeSheet.getStart_date().getMonth().getValue() <= Month.valueOf(endMonth.toUpperCase())
									.getValue())
					.collect(Collectors.toList());
			return new ResponseEntity<List<TimeSheet>>(timeSheets, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<TimeSheet>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<TimeSheet>> findTimeSheetByMonthNameOfAllEmployees(String month, int year) {
		try {
			List<TimeSheet> timeSheets = timeSheetDao.findAllTimeSheets().stream()
					.filter(timeSheet -> timeSheet.getStart_date().getMonth().getValue() == Month
							.valueOf(month.toUpperCase()).getValue() && timeSheet.getStart_date().getYear() == year)
					.collect(Collectors.toList());
			return new ResponseEntity<>(timeSheets, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<TimeSheet>> findTimeSheetOfUserOnCustomDates(String startMonth, int start_year,
			String endMonth, int end_year, int userId) {
		try {
			User user = userDao.findUserById(userId).get();
			List<TimeSheet> timeSheets = user.getTimeSheets().stream().filter(
					timesheet -> timesheet.getStart_date().getMonth().getValue() == Month.valueOf(startMonth).getValue()
							&& timesheet.getStart_date().getYear() == start_year
							&& timesheet.getEnd_date().getMonth().getValue() == Month.valueOf(endMonth).getValue()
							&& timesheet.getEnd_date().getYear() == end_year)
					.collect(Collectors.toList());

			return new ResponseEntity<>(timeSheets, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseEntity<List<TimeSheet>> findTimeSheetOnCustomDates(String startMonth, int start_year,
			String endMonth, int end_year) {
		try {
			List<TimeSheet> timeSheets = timeSheetDao.findAllTimeSheets().stream().filter(
					timesheet -> timesheet.getStart_date().getMonth().getValue() == Month.valueOf(startMonth).getValue()
							&& timesheet.getStart_date().getYear() == start_year
							&& timesheet.getEnd_date().getMonth().getValue() == Month.valueOf(endMonth).getValue()
							&& timesheet.getEnd_date().getYear() == end_year)
					.collect(Collectors.toList());

			return new ResponseEntity<>(timeSheets, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}

}