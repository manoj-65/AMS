package com.ty.ams.daoimp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.ams.dao.TimeSheetDao;
import com.ty.ams.entity.TimeSheet;
import com.ty.ams.repository.TimeSheetRepository;

@Repository
public class TimeSheetDaoImp implements TimeSheetDao {

	@Autowired
	TimeSheetRepository timeSheetRepository;

	@Override
	public TimeSheet saveTimeSheet(TimeSheet timeSheet) {
		return timeSheetRepository.save(timeSheet);
	}

	@Override
	public TimeSheet updateTimeSheet(TimeSheet timeSheet) {
		return timeSheetRepository.save(timeSheet);
	}

	@Override
	public Optional<TimeSheet> findTimeSheetById(int id) {
		return timeSheetRepository.findById(id);
	}

	@Override
	public void deleteTimeSheetById(int id) {
		timeSheetRepository.deleteById(id);
	}

	@Override
	public List<TimeSheet> findAllTimeSheets() {
		return timeSheetRepository.findAll();
	}

	@Override
	public List<TimeSheet> findByDateBetween(LocalDate fromDate, LocalDate toDate) {
		return timeSheetRepository.findByDateBetween(fromDate, toDate);
	}

	@Override
	public List<TimeSheet> findAllTimeSheet(int userId) {
		return timeSheetRepository.findByUserId(userId);
	}

	@Override
	public List<TimeSheet> findAllTimeSheetOfAYear(int year) {
		return null;
	}

	@Override
	public TimeSheet findByMonthName(String month, int userId) {
		return timeSheetRepository.findByMonthName(month, userId);
	}

}
