package com.ty.ams.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.ams.entity.Attendance;
import com.ty.ams.util.AttendenceStatus;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
	
	List<Attendance> findByAttendanceStatus(AttendenceStatus status) ;
	
	List<Attendance> findByDate(LocalDate date) ;
	
	List<Attendance> findByAttendanceStatusAndDate(AttendenceStatus status, LocalDate date) ;


}
