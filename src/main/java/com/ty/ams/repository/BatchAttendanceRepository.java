package com.ty.ams.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.ams.entity.BatchAttendance;

public interface BatchAttendanceRepository extends JpaRepository<BatchAttendance, Integer>{
	
//	BatchAttendance saveBatchAttendance(BatchAttendance batchAttendance);
//	BatchAttendance updateBatchAttendance(BatchAttendance batchAttendance);
//	void deleteBatchAttendance(BatchAttendance batchAttendance);
	Optional<BatchAttendance> findBatchAttendanceById(int id);
	
	@Query("SELECT COUNT(b.id) FROM BatchAttendance b where b.batch.batchId=?1")
	int findBatchAttendanceCountByBatchId(int batch_id);
	
	
}
