package com.ty.ams.serviceimp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ams.dao.BatchAttendanceDao;
import com.ty.ams.dao.BatchDao;
import com.ty.ams.entity.Batch;
import com.ty.ams.entity.BatchAttendance;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.service.BatchAttendanceService;
@Service
public class BatchAttendanceServiceImp implements BatchAttendanceService{
	@Autowired
	private BatchAttendanceDao batchAttendanceDao;
	
	@Autowired
	private BatchDao batchDao;
	
	
	public ResponseEntity<ResponseStructure<BatchAttendance>> saveBatchAttendance(BatchAttendance batchAttendance,int batch_id){
		     Optional<Batch> batch =batchDao.findBatchById(batch_id);
		
		ResponseStructure<BatchAttendance> responseStructure=null;
		if(batch.isPresent() && findBatchAttendanceCountByBatchId(batch_id)<5) {
			batchAttendance.setBatch(batch.get());
			batchAttendanceDao.saveBatchAttendance(batchAttendance);
			

		
		 responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Success");
		responseStructure.setBody(batchAttendance);
		}
		return new ResponseEntity<ResponseStructure<BatchAttendance>>(responseStructure,HttpStatus.OK);
	}
	
	
	@Override
	public ResponseEntity<ResponseStructure<BatchAttendance>> updateBatchAttendance(BatchAttendance batchAttendance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<BatchAttendance>> findBatchAttendanceById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<BatchAttendance>> deleteBatchAttendance(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<BatchAttendance>> findBatchAttendanceByBatchId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	int findBatchAttendanceCountByBatchId(int batch_id) {
		return batchAttendanceDao.findBatchAttendanceCountByBatchId(batch_id);
	}
}
