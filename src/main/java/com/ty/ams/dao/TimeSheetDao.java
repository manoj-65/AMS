package com.ty.ams.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ty.ams.entity.TimeSheet;

public interface TimeSheetDao {
	TimeSheet saveTimeSheet(TimeSheet timeSheet);

	TimeSheet updateTimeSheet(TimeSheet timeSheet);

	Optional<TimeSheet> findTimeSheetById(int id);

	void deleteTimeSheetById(int id);

	List<TimeSheet> findAllTimeSheets();

	TimeSheet findTimeSheetByDate(LocalDate fromDate, LocalDate toDate);

	List<TimeSheet> findAllTimeSheet(int userId);

	List<TimeSheet> findAllTimeSheetOfAYear(int year);
}
