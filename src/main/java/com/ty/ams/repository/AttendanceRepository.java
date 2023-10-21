package com.ty.ams.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.ams.entity.Attendance;
import com.ty.ams.util.Attendence_status;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
	
	List<Attendance> findAllAttendanceByAttendanceStatus(Attendence_status status, String empId) ;
	
	List<Attendance> findByDate(LocalDate date) ;
	
	List<Attendance> findAllAttendanceByAttendanceStatusAndDate(Attendence_status status, LocalDate date) ;

}
