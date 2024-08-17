package com.alchives.alchiveserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alchives.alchiveserver.entity.Alumni;

public interface AlumniRepo extends JpaRepository<Alumni, Integer> {
  List<Alumni> findByBatchYearAndProgramProgramId(String batchYear, Integer programId);
  
  Page<Alumni> findByBatchYearAndProgramName(String batchYear, String programName, Pageable pageable);
  Page<Alumni> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName, Pageable pageable);

  Optional<Alumni> findByStudentNumber(String studentNumber);
}
