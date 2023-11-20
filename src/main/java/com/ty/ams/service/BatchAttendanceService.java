package com.ty.ams.service;

import org.springframework.http.ResponseEntity;

import com.ty.ams.entity.BatchAttendance;
import com.ty.ams.responsestructure.ResponseStructure;

public interface BatchAttendanceService {
	ResponseEntity<ResponseStructure<BatchAttendance>> saveBatchAttendance(BatchAttendance batchAttendance,int batch_id);
	ResponseEntity<ResponseStructure<BatchAttendance>> updateBatchAttendance(BatchAttendance batchAttendance);
	ResponseEntity<ResponseStructure<BatchAttendance>> findBatchAttendanceById(int id);
	ResponseEntity<ResponseStructure<BatchAttendance>> deleteBatchAttendance(int id);
	ResponseEntity<ResponseStructure<BatchAttendance>> findBatchAttendanceByBatchId(int id);

}
