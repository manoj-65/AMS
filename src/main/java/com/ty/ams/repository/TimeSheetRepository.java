package com.ty.ams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ty.ams.entity.TimeSheet;

public interface TimeSheetRepository extends JpaRepository<TimeSheet, Integer> {

}
