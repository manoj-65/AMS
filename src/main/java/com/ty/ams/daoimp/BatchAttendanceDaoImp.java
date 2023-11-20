package com.ty.ams.daoimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.ams.dao.BatchAttendanceDao;
import com.ty.ams.entity.BatchAttendance;
import com.ty.ams.repository.BatchAttendanceRepository;
@Repository
public class BatchAttendanceDaoImp implements BatchAttendanceDao{
	@Autowired
	private BatchAttendanceRepository attendanceRepository;
	
	@Override
	public BatchAttendance saveBatchAttendance(BatchAttendance batchAttendance) {
		
		return attendanceRepository.save(batchAttendance);
	}

	@Override
	public BatchAttendance updateBatchAttendance(BatchAttendance batchAttendance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBatchAttendance(BatchAttendance batchAttendance) {
		// TODO Auto-generated method stub
		
	}
	public int findBatchAttendanceCountByBatchId(int batch_id) {
		return attendanceRepository.findBatchAttendanceCountByBatchId(batch_id);
	}
	

//	@Override
//	public Optional<BatchAttendance> findBatchAttendanceById(int id) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}


}
