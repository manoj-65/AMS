package com.ty.ams.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ty.ams.entity.Attendance;
import com.ty.ams.util.AttendenceStatus;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
	
	List<Attendance> findAllAttendanceByAttendanceStatus(AttendenceStatus status, String empId) ;
	
	List<Attendance> findAllAttendenceByDate(LocalDate date) ;
	
	List<Attendance> findAllAttendanceByAttendanceStatusAndDate(AttendenceStatus status, LocalDate date) ;

}
