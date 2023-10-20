package com.ty.ams.daoimp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ty.ams.dao.TimeSheetDao;
import com.ty.ams.entity.TimeSheet;

@Repository
public class TimeSheetDaoImp implements TimeSheetDao {

	@Override
	public TimeSheet saveTimeSheet(TimeSheet timeSheet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TimeSheet updateTimeSheet(TimeSheet timeSheet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<TimeSheet> findTimeSheetById(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void deleteTimeSheetById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TimeSheet> findAllTimeSheets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TimeSheet findTimeSheetByDate(LocalDate fromDate, LocalDate toDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TimeSheet> findAllTimeSheet(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TimeSheet> findAllTimeSheetOfAYear(int year) {
		// TODO Auto-generated method stub
		return null;
	}

}
