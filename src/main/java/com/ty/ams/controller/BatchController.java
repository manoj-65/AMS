package com.ty.ams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.ams.entity.Batch;
import com.ty.ams.responsestructure.ResponseStructure;
import com.ty.ams.service.BatchService;

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
}
