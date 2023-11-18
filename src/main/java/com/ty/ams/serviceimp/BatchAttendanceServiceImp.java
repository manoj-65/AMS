package com.ty.ams.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ams.dao.BatchAttendanceDao;
import com.ty.ams.entity.BatchAttendance;
import com.ty.ams.responsestructure.ResponseStructure;
@Service
public class BatchAttendanceServiceImp {
	@Autowired
	private BatchAttendanceDao batchAttendanceDao;
	
	public ResponseEntity<ResponseStructure<BatchAttendance>> saveBatchAttendance(BatchAttendance batchAttendance){
		batchAttendanceDao.saveBatchAttendance(batchAttendance);
		ResponseStructure<BatchAttendance> responseStructure=new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Success");
		responseStructure.setBody(batchAttendance);
		return new ResponseEntity<ResponseStructure<BatchAttendance>>(responseStructure,HttpStatus.OK);
	}
	

}
