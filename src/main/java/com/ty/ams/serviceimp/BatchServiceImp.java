package com.ty.ams.serviceimp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.ams.dao.BatchDao;
import com.ty.ams.entity.Batch;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.service.BatchService;
import com.ty.ams.util.BatchMode;
import com.ty.ams.util.BatchStatus;

@Service
public class BatchServiceImp implements BatchService {

	@Autowired
	private BatchDao batchDao;

	@Override
	public ResponseEntity<ResponseStructure<Batch>> findBatchById(int batchId) {
		Optional<Batch> optional = batchDao.findBatchById(batchId);
		if (optional.get() != null) {
			ResponseStructure<Batch> responseStructure = new ResponseStructure<Batch>();
			responseStructure.setBody(optional.get());
			responseStructure.setMessage("Batch found Successfully");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.OK);
		} else {
			return null;
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Batch>> saveBatch(Batch batch) {
		ResponseStructure<Batch> responseStructure = new ResponseStructure<Batch>();
		responseStructure.setBody(batchDao.saveBatch(batch));
		responseStructure.setMessage("Batch created Successfully");
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<Batch>> updateBatch(Batch batch) {
		ResponseStructure<Batch> responseStructure = new ResponseStructure<Batch>();
		responseStructure.setBody(batch);
		responseStructure.setMessage("Batch Updated Successfully");
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<String>> deleteBatch(int batchId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Batch>>> findAllBatchs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<Batch>> findBatchByBatchCode(String batchCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Batch>>> findBatchBySubjectName(String subjectName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Batch>>> findBatchBySubjectNameAndBatchStatus(String subjectName,
			BatchStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Batch>>> findBatchByStartedDate(LocalDate startDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Batch>>> findBatchByBatchMode(BatchMode mode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Batch>>> findBatchByUserIdAndBatchStatus(int userId,
			BatchStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public ResponseEntity<ResponseStructure<List<Batch>>> findBatchBetweenDates(LocalDate fromDate, LocalDate toDate) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
