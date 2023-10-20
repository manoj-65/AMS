package com.ty.ams.dao;

import java.util.Optional;

import com.ty.ams.entity.Batch;

public interface BatchDao {

	Optional<Batch> findBatchById(int batchId);

	Batch saveBatch(Batch batch);

	Batch updateBatch(Batch batch);

	void deleteBatch(int batchId);
}
