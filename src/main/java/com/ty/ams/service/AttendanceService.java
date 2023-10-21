package com.ty.ams.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ty.ams.entity.Attendance;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.util.AttendanceStatus;

public interface AttendanceService {
	
	ResponseEntity<ResponseStructure<Attendance>> saveAttendance(Attendance attendance) ;
	
	ResponseEntity<ResponseStructure<Attendance>> findAttandanceById(Attendance attendance) ;
	
	ResponseEntity<ResponseStructure<Attendance>> updateAttandance(Attendance attendance) ;
	
	ResponseEntity<ResponseStructure<Attendance>> deleteAttandance(Attendance attendance) ;
	
	ResponseEntity<ResponseStructure<List<Attendance>>> findAllAttendanceByAttendanceStatus (AttendanceStatus status) ;
	
	ResponseEntity<ResponseStructure<List<Attendance>>> findAllAttendenceByDate (LocalDate date) ;
	
	ResponseEntity<ResponseStructure<List<Attendance>>> findAllAttendanceByAttendanceStatusAndDate(AttendanceStatus status, LocalDate date);

	
}
