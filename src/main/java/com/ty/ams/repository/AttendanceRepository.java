package com.ty.ams.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.ams.entity.Attendance;
import com.ty.ams.util.AttendanceStatus;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
	
	List<Attendance> findByAttendanceStatus(AttendanceStatus status) ;
	
	List<Attendance> findByDate(LocalDate date) ;
	
	List<Attendance> findByAttendanceStatusAndDate(AttendanceStatus status, LocalDate date) ;


}
