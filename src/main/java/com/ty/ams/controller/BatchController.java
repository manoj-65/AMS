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

import com.ty.ams.entity.Batch;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.service.BatchService;
import com.ty.ams.util.BatchMode;
import com.ty.ams.util.BatchStatus;

@RestController
@RequestMapping("/batch")
public class BatchController {
	@Autowired
	private BatchService batchService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Batch>> saveBatch(@RequestBody Batch batch) {
		return batchService.saveBatch(batch);
	}

	@GetMapping("/{batchId}")
	public ResponseEntity<ResponseStructure<Batch>> findBatchById(@PathVariable int batchId) {
		return batchService.findBatchById(batchId);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Batch>> updateBatch(@RequestBody Batch batch) {
		return batchService.updateBatch(batch);
	}

	@DeleteMapping("/{batchId}")
	public ResponseEntity<ResponseStructure<String>> deleteBatch(@PathVariable int batchId) {
		return batchService.deleteBatch(batchId);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Batch>>> findAllBatchs() {
		return batchService.findAllBatchs();
	}

	@GetMapping("/batchcode/{batchCode}")
	public ResponseEntity<ResponseStructure<Batch>> findBatchByBatchCode(@PathVariable String batchCode) {
		return batchService.findBatchByBatchCode(batchCode);
	}

	@GetMapping("/subjectname/{subjectName}")
	public ResponseEntity<ResponseStructure<List<Batch>>> findBatchBySubjectName(@PathVariable String subjectName) {
		return batchService.findBatchBySubjectName(subjectName);
	}

	@GetMapping("/subjectname-name/{subjectName}/{status}")
	public ResponseEntity<ResponseStructure<List<Batch>>> findBatchBySubjectNameAndBatchStatus(
			@PathVariable String subjectName, @PathVariable BatchStatus status) {
		return batchService.findBatchBySubjectNameAndBatchStatus(subjectName, status);
	}

	@GetMapping("/startdate/{startDate}")
	public ResponseEntity<ResponseStructure<List<Batch>>> findBatchByStartedDate(@PathVariable LocalDate startDate) {
		return batchService.findBatchByStartedDate(startDate);
	}

	@GetMapping("/mode/{mode}")
	public ResponseEntity<ResponseStructure<List<Batch>>> findBatchByBatchMode(@PathVariable BatchMode mode) {
		return batchService.findBatchByBatchMode(mode);
	}

	@GetMapping("/userid-status/{userId}/{status}")
	public ResponseEntity<ResponseStructure<List<Batch>>> findBatchByUserIdAndBatchStatus(@PathVariable int userId,
			@PathVariable BatchStatus status) {

		return batchService.findBatchByUserIdAndBatchStatus(userId, status);
	}

	@GetMapping("/fromdate-todate/{fromDate}/{toDate}")
	public ResponseEntity<ResponseStructure<List<Batch>>> findBatchBetweenDates(@PathVariable LocalDate fromDate,
			@PathVariable LocalDate toDate) {
		return batchService.findBatchBetweenDates(fromDate, toDate);
	}
}
