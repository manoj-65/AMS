package com.ty.ams.dao;

import java.util.Optional;

import com.ty.ams.entity.BatchAttendance;

public interface BatchAttendanceDao {
	
	BatchAttendance saveBatchAttendance(BatchAttendance batchAttendance);
	BatchAttendance updateBatchAttendance(BatchAttendance batchAttendance);
	void deleteBatchAttendance(BatchAttendance batchAttendance);
//	Optional<BatchAttendance> findBatchAttendanceById(int id);
//	Optional<BatchAttendance> findBatchAttendanceByBatchId(int batch_id);
	public int findBatchAttendanceCountByBatchId(int batch_id);
//	
	
}
