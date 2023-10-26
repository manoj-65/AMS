package com.ty.ams.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.ams.entity.Attendance;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.serviceimp.AttendanceServiceImp;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

	@Autowired
	AttendanceServiceImp service;

	@PostMapping
	public ResponseEntity<ResponseStructure<Attendance>> saveAttendance(@RequestBody Attendance attendance) {
		return service.saveAttendance(attendance);
	}

	@GetMapping("/{attendanceId}")
	public ResponseEntity<ResponseStructure<Attendance>> findAttendanceById(@PathVariable int attendanceId) {
		return service.findAttandanceById(attendanceId);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Attendance>> updateAttendance(@RequestBody Attendance attendance){
		return service.updateAttandance(attendance) ;
	}

	@DeleteMapping("/{attendanceId}")
	public ResponseEntity<ResponseStructure<Attendance>> deleteAttendance(@PathVariable int attendanceId) {
		return service.deleteAttandance(attendanceId);
	}
	
	@GetMapping("/status/{attendanceStatus}")
	public ResponseEntity<ResponseStructure<List<Attendance>>> findAllAttendanceByStatus(@PathVariable String attendanceStatus){
		return service.findAllAttendanceByAttendanceStatus(attendanceStatus) ;
	}
	
	@GetMapping("/date/{date}")
	public ResponseEntity<ResponseStructure<List<Attendance>>> findAllAttendanceByDate(@PathVariable LocalDate date){
		return service.findAllAttendenceByDate(date) ;
	}
	
	@GetMapping("/status/{attendanceStatus}/{date}")
	public ResponseEntity<ResponseStructure<List<Attendance>>> findAllAttendanceByStatusAndDate(@PathVariable String attendanceStatus, @PathVariable LocalDate date){
		return service.findAllAttendanceByAttendanceStatusAndDate(attendanceStatus, date) ;
	}
}
