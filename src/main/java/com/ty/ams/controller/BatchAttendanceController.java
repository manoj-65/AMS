package com.ty.ams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.ams.entity.BatchAttendance;
import com.ty.ams.entity.User;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.service.BatchAttendanceService;
import com.ty.ams.serviceimp.BatchAttendanceServiceImp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class BatchAttendanceController {
	@Autowired
	private BatchAttendanceServiceImp batchAttendanceServiceImp;
	
	@Operation(description = "BatchAttendance Object Will be Saved...", summary = "To Save BatchAttendance Object to Database...")
	@ApiResponses(value = { @ApiResponse(description = "BatchAttendance Saved Successfully", responseCode = "201"),
			@ApiResponse(description = "Unable To Save BatchAttendance To Database", responseCode = "409") })
	@PostMapping("/savebatchattendance")
	public ResponseEntity<ResponseStructure<BatchAttendance>> saveBatchAttendance(@RequestBody BatchAttendance batchAttendance) {
		return batchAttendanceServiceImp.saveBatchAttendance(batchAttendance);
	}

}
