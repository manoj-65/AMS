package com.ty.ams.daoimp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.ty.ams.dao.AttendanceDao;
import com.ty.ams.entity.Attendance;
import com.ty.ams.util.AttendenceStatus;

public class AttendanceDaoImp implements AttendanceDao {

	
	public Attendance saveAttendance(Attendance attendance) {
		
		return null;
	}

	
	public Optional<Attendance> findById(int id) {
		
		return Optional.empty();
	}

	
	public Optional<Attendance> updateAttendance(Attendance attendance) {
		
		return Optional.empty();
	}

	
	public boolean deleteAttendance(int id) {
		
		return false;
	}

	
	public List<Attendance> findAllAttendanceByAttendanceStatus(AttendenceStatus status, LocalDate date) {
		
		return null;
	}

	
	public List<Attendance> findByDate(LocalDate date) {
		
		return null;
	}

	
	public List<Attendance> findAllAttendanceByAttendanceStatusAndDate(AttendenceStatus status, LocalDate date) {
		
		return null;
	}


	@Override
	public List<Attendance> findAllAttendanceByAttendanceStatus(AttendenceStatus status, String empId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Attendance> findAllAttendanceByAttendanceStatusAndDate(AttendenceStatus status, String empId) {
		// TODO Auto-generated method stub
		return null;
	}

}
