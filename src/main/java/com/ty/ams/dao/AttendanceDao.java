package com.ty.ams.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.ty.ams.entity.Attendance;
import com.ty.ams.util.AttendenceStatus;

public interface AttendanceDao {

	public Attendance saveAttendance(Attendance attendance);

	public Optional<Attendance> findById(int id);

	public Attendance updateAttendance(Attendance attendance);

	public void deleteAttendance(int id);

	public List<Attendance> findAllAttendanceByAttendanceStatusAndEmployeeID(AttendenceStatus status, String empId);

	public List<Attendance> findAllAttendenceByDate(LocalDate date);

	public List<Attendance> findAllAttendanceByAttendanceStatusAndDate(AttendenceStatus status, LocalDate date);

}
