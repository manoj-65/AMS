package com.ty.ams.daoimp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.ams.dao.BatchDao;
import com.ty.ams.entity.Batch;
import com.ty.ams.repository.BatchRepository;

@Repository
public class BatchDaoImp implements BatchDao {

	@Autowired
	private BatchRepository batchRepository;

	@Override
	public Optional<Batch> findBatchById(int batchId) {
		return batchRepository.findById(batchId);
	}

	@Override
	public Batch saveBatch(Batch batch) {
		return batchRepository.save(batch);
	}

	@Override
	public Batch updateBatch(Batch batch) {
		return batchRepository.save(batch);
	}

	@Override
	public void deleteBatch(int batchId) {
		batchRepository.deleteById(batchId);
	}

}
