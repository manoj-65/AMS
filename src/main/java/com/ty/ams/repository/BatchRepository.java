package com.ty.ams.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.ams.entity.Batch;
import com.ty.ams.util.BatchMode;
import com.ty.ams.util.BatchStatus;

public interface BatchRepository extends JpaRepository<Batch, Integer> {

	Batch findByBatchCode(String batchCode);

	List<Batch> findBySubjectName(String subjectName);

	List<Batch> findBySubjectNameAndBatchStatus(String subjectName, BatchStatus status);

	List<Batch> findByBatchStartDate(LocalDate StartDate);

	List<Batch> findByBatchMode(BatchMode mode);

	List<Batch> findByUserUserIdAndBatchStatus(int userId, BatchStatus batchStatus);

	List<Batch> findByBatchStartDateBetween(LocalDate startDate, LocalDate endDate);

}
