package com.alchives.alchiveserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alchives.alchiveserver.entity.Alumni;

public interface AlumniRepo extends JpaRepository<Alumni, Integer> {
  List<Alumni> findByBatchYearAndProgramId(String batchYear, Integer programId);

  Optional<Alumni> findByStudentNumber(String studentNumber);
}
